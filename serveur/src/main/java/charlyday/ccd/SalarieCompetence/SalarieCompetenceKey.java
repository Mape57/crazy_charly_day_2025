package charlyday.ccd.SalarieCompetence;

import charlyday.ccd.competences.CompetenceEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Setter
@Getter
@Embeddable
public class SalarieCompetenceKey implements Serializable {
    @Serial
    private static final long serialVersionUID = 2405172041950251807L;

    @Column(name = "salarie_id")
    private UUID salarieId;

    @Column(name = "competence_id")
    private UUID competenceId;
}
