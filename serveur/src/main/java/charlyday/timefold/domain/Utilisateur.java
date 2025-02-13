package charlyday.timefold.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Utilisateur {
	private UUID id;
	private String nom;
	private List<SalarieCompetence> competences;

	public Utilisateur(UUID id, String nom) {
		this.id = id;
		this.nom = nom;
		this.competences = null;
	}

	@Override
	public String toString() {
		return this.nom;
	}
}
