package charlyday.ccd.salarieCompetence;

import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.UUID;

public interface SalarieCompetenceRepository extends ListCrudRepository<SalarieCompetenceEntity, UUID> {
    List<SalarieCompetenceEntity> findBySalarieCompetenceKeySalarieId(UUID idSalarie);
    SalarieCompetenceEntity findBySalarieCompetenceKeySalarieIdAndSalarieCompetenceKeyCompetenceId(UUID salarieId, UUID competenceId);
}
