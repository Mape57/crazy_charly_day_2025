package charlyday.timefold.solver;

import ai.timefold.solver.core.api.score.buildin.hardsoft.HardSoftScore;
import ai.timefold.solver.core.api.score.stream.*;
import charlyday.timefold.domain.Besoin;
import charlyday.timefold.solver.justification.*;
import org.jspecify.annotations.NonNull;

import java.util.Objects;

public class CrazyConstraintProvider implements ConstraintProvider {
	@Override
	public Constraint[] defineConstraints(@NonNull ConstraintFactory constraintFactory) {
		return new Constraint[]{
				tacheSimultanement(constraintFactory),
				competenceNecessaire(constraintFactory),
				utiliteDegressives(constraintFactory),
				minimumSatisfaction(constraintFactory)
		};
	}

	Constraint tacheSimultanement(ConstraintFactory constraintFactory) {
		return constraintFactory
				.forEach(Besoin.class)
				.join(Besoin.class, Joiners.equal(Besoin::getSalarie))
				.filter((besoinA, besoinB) -> !Objects.equals(besoinA, besoinB))
				.filter(Besoin::overlapTime)
				.penalize(HardSoftScore.ONE_HARD)
				.justifyWith(TacheSimultanementJustification::new)
				.asConstraint("Salarié peut avoir une seule tâche par jour");
	}

	Constraint competenceNecessaire(ConstraintFactory constraintFactory) {
		return constraintFactory
				.forEach(Besoin.class)
				.filter(besoin -> !besoin.estSalarieCompetent())
				.penalize(HardSoftScore.ONE_HARD)
				.justifyWith(CompetenceNecessaireJustification::new)
				.asConstraint("Besoin nécessite une compétence");
	}

	Constraint utiliteDegressives(ConstraintFactory constraintFactory) {
		return constraintFactory
				.forEach(Besoin.class)
				.groupBy(Besoin::getClient, ConstraintCollectors.toList())
				.expand((client, besoins) -> {
					int score = 0;
					for (int i = 0; i < besoins.size(); i++) {
						score += Math.max(1, besoins.get(i).interetSalarie() - i);
					}
					return score;
				})
				.reward(HardSoftScore.ONE_SOFT, (client, besoins, score) -> score)
				.justifyWith(UtiliteDegressiveJustification::new)
				.asConstraint("Utilité dégressive");
	}

	Constraint minimumSatisfaction(ConstraintFactory constraintFactory) {
		return constraintFactory
				.forEachIncludingUnassigned(Besoin.class)
				.groupBy(Besoin::getClient, ConstraintCollectors.toList())
				.filter((client, besoins) -> {
					boolean insatisfait = true;
					for (Besoin besoin : besoins) {
						if (besoin.getSalarie() != null) {
							insatisfait = false;
							break;
						}
					}
					return insatisfait;
				})
				.penalize(HardSoftScore.ONE_SOFT, (client, besoins) -> 10)
				.justifyWith(MinimumSatisfactionJustification::new)
				.asConstraint("Minimum satisfaction");
	}
}
