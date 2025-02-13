package charlyday.ccd.besoinUtilisateur;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class BesoinUtilisateurDto {
    private UUID salarieId;
    private UUID besoinId;
}
