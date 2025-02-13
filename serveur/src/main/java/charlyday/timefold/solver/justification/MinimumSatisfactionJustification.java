package charlyday.timefold.solver.justification;

import ai.timefold.solver.core.api.score.buildin.hardsoft.HardSoftScore;
import ai.timefold.solver.core.api.score.stream.ConstraintJustification;
import charlyday.timefold.domain.Besoin;
import charlyday.timefold.domain.Utilisateur;

import java.util.List;

public record MinimumSatisfactionJustification(Utilisateur client, List<Besoin> besoins, HardSoftScore score, String description) implements ConstraintJustification {
	public MinimumSatisfactionJustification(Utilisateur client, List<Besoin> besoins, HardSoftScore score) {
		this(client, besoins, score, getDescription(client, besoins, score));
	}

	private static String getDescription(Utilisateur client, List<Besoin> besoins, HardSoftScore score) {
		return String.format("Client %s n'a aucun besoin de satisfait %d : %s",
				client, besoins.size(), score.toString());
	}

	@Override
	public String toString() {
		return description;
	}
}
