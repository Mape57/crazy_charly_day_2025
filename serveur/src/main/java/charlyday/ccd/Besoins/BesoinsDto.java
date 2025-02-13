package charlyday.ccd.Besoins;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
public class BesoinsDto {
	@Schema(name = "id", example = "1")
	private UUID id;

	@Schema(name = "client_id", example = "2")
	private UUID clientId;

	@Schema(name = "description", example = "Description du besoin")
	private String description;

	@Schema(name = "competences_id", example = "3")
	private UUID competencesId;

	@Schema(name = "date_service", example = "2025-01-01")
	private Date dateService;

	@Schema(name = "salarie_id", example = "4")
	private String salarieId;

	@Schema(name = "duree", example = "5")
	private int duree;
}
