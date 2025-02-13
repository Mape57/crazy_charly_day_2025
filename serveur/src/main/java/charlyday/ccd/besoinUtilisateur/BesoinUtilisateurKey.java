package charlyday.ccd.besoinUtilisateur;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Embeddable
public class BesoinUtilisateurKey implements Serializable {
    @Serial
    private static final long serialVersionUID = 2405172041950251807L;

    @Column(name = "salarie_id")
    private UUID salarieId;
    @Column(name = "besoin_id")
    private UUID besoinId;
}
