package charlyday.ccd.SalarieCompetence;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "salarie_competences")
public class SalarieCompetenceEntity {
    @EmbeddedId
    private SalarieCompetenceKey salarieCompetenceKey;

    @Column(name = "interet")
    private int interet;
}
