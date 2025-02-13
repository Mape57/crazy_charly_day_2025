package charlyday.ccd.utilisateur;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class CreerCompetence {
    private UUID id;
    private String libelle;
    private boolean valide;
    private String categorie;
    private int interet;
}
