package charlyday.ccd.salarieCompetence;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class SalarieCompetenceDto {

    private UUID salarieId;

    private UUID competenceId;

    private int interet;
}
