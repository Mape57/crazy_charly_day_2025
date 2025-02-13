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
	private String email;
	private String motDePasse;
	private int role;
	private List<SalarieCompetence> competences;

	public Utilisateur(UUID id, String nom, String email, String motDePasse, int role) {
		this.id = id;
		this.nom = nom;
		this.email = email;
		this.motDePasse = motDePasse;
		this.role = role;
		this.competences = null;
	}

	@Override
	public String toString() {
		return nom;
	}
}
