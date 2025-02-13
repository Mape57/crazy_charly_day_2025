package charlyday.ccd.competences;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.type.descriptor.jdbc.VarcharJdbcType;

import java.util.UUID;

@Data
@NoArgsConstructor
public class CompetenceDto {
    @Schema(name = "id",example = "1")
    private UUID id;
    @Schema(name = "libelle",example = "BRICO")
    private String libelle;
    @Schema(name = "valide",example = "true")
    private boolean valide;
    @Schema(name = "categorie",example = "Bricolage")
    private String categorie;
}


