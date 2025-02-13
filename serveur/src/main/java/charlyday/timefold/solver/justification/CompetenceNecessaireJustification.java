package charlyday.timefold.solver.justification;

import ai.timefold.solver.core.api.score.buildin.hardsoft.HardSoftScore;
import ai.timefold.solver.core.api.score.stream.ConstraintJustification;
import charlyday.timefold.domain.Besoin;

public record CompetenceNecessaireJustification(Besoin besoin, HardSoftScore score, String description) implements ConstraintJustification {
	public CompetenceNecessaireJustification(Besoin besoin, HardSoftScore score) {
		this(besoin, score, getDescription(besoin, score));
	}

	private static String getDescription(Besoin besoin, HardSoftScore score) {
		return String.format("Besoin %s nécessite une compétence que le salarié n'a pas : %s",
				besoin, score.toString());
	}

	@Override
	public String toString() {
		return description;
	}
}