package charlyday.ccd.besoins;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;


@Repository
public interface BesoinsRepository extends ListCrudRepository<BesoinsEntity, UUID> {

	List<BesoinsEntity> findAll();

	Page<BesoinsEntity> findAll(Pageable pageable);


}