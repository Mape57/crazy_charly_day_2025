package charlyday.ccd.Besoins;

import charlyday.ccd.Besoins.BesoinsEntity;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BesoinsRepository extends ListCrudRepository<BesoinsEntity, UUID> {

}