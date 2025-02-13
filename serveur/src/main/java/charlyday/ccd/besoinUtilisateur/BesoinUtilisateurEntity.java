package charlyday.ccd.besoinUtilisateur;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "besoins_utilisateur")
public class BesoinUtilisateurEntity {
    @EmbeddedId
    private BesoinUtilisateurKey besoinUtilisateurKey;
}
