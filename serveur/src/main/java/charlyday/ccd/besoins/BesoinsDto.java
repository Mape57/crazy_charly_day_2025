package charlyday.ccd.besoins;

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

	@Schema(name = "clientId", example = "2")
	private UUID clientId;

	@Schema(name = "description", example = "3")
	private String description;

	@Schema(name = "competenceId", example = "3")
	private UUID competenceId;

	@Schema(name = "dateService", example = "2025-01-01")
	private Date dateService;

	@Schema(name = "salarieId", example = "4")
	private UUID salarieId;

	@Schema(name = "duree", example = "5")
	private int duree;
}
