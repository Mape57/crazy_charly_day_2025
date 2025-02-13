package charlyday.ccd.SalarieCompetence;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class SalarieCompetenceDto {

    UUID salarieId;

    UUID competenceId;

    int interet;
}
