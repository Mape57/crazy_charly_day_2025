package charlyday.timefold.solver.justification;

import ai.timefold.solver.core.api.score.buildin.hardsoft.HardSoftScore;
import ai.timefold.solver.core.api.score.stream.ConstraintJustification;
import charlyday.timefold.domain.Besoin;

public record TacheSimultanementJustification(Besoin besoinA, Besoin besoinB, HardSoftScore score,
											  String description) implements ConstraintJustification {
	public TacheSimultanementJustification(Besoin besoinA, Besoin besoinB, HardSoftScore score) {
		this(besoinA, besoinB, score, getDescription(besoinA, besoinB, score));
	}

	private static String getDescription(Besoin besoinA, Besoin besoinB, HardSoftScore score) {
		return String.format("Salarié peut avoir une seule tâche par jour : %s et %s %s",
				besoinA, besoinB, score.toString());
	}

	@Override
	public String toString() {
		return description;
	}
}
