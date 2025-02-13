package charlyday.ccd.Besoins;


import charlyday.ccd.competences.CompetenceEntity;
import charlyday.ccd.utilisateur.UtilisateurEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "besoins")
public class BesoinsEntity implements Serializable {
	@Serial
	private static final long serialVersionUID = 2405172041950251807L;

	@Id
	@Column(name = "id")
	private UUID id;

	@Column(name = "client_id")
	private UUID clientId;

	@Column(name = "description")
	private String description;

	@Column(name = "competences_id")
	private UUID competencesId;

	@Column(name = "date_service")
	private Date dateService;

	@Column(name = "salarie_id")
	private String salarieId;

	@Column(name = "duree")
	private int duree;
}
