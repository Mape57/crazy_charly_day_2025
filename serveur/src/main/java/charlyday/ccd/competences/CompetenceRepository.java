package charlyday.ccd.competences;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CompetenceRepository extends ListCrudRepository<CompetenceEntity, UUID> {
}