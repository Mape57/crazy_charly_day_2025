package charlyday.timefold.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SalarieCompetence {
	private UUID id;
	private Utilisateur salarie;
	private Competence competence;
	private int interet;

	public SalarieCompetence(UUID id, Competence competence, int interet) {
		this.id = id;
		this.salarie = null;
		this.competence = competence;
		this.interet = interet;
	}

	@Override
	public String toString() {
		return "Competence salarie : " + competence + " pour le salarie " + (salarie == null ? "null" : salarie.getNom());
	}
}
