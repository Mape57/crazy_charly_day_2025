package charlyday.ccd.besoins;


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

	@Column(name = "competence_id")
	private UUID competenceId;

	@Column(name = "date_service")
	private Date dateService;

	@Column(name = "duree")
	private int duree;
}
