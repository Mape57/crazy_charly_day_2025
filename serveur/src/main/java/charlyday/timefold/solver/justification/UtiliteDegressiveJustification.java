package charlyday.timefold.solver.justification;

import ai.timefold.solver.core.api.score.buildin.hardsoft.HardSoftScore;
import ai.timefold.solver.core.api.score.stream.ConstraintJustification;
import charlyday.timefold.domain.Besoin;
import charlyday.timefold.domain.Utilisateur;

import java.util.List;

public record UtiliteDegressiveJustification(Utilisateur salarie, List<Besoin> besoins, Integer scoreClient, HardSoftScore score, String description) implements ConstraintJustification {
	public UtiliteDegressiveJustification(Utilisateur salarie, List<Besoin> besoins, Integer scoreClient, HardSoftScore score) {
		this(salarie, besoins, scoreClient, score, getDescription(salarie, besoins, scoreClient, score));
	}

	private static String getDescription(Utilisateur client, List<Besoin> besoins, Integer scoreClient, HardSoftScore score) {
		return String.format("Client %s a %d besoins avec un score de %d et un score total de %s",
				client, besoins.size(), scoreClient, score.toString());
	}

	@Override
	public String toString() {
		return description;
	}
}