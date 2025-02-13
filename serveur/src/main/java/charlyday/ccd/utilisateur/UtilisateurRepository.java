package charlyday.ccd.utilisateur;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UtilisateurRepository extends ListCrudRepository<UtilisateurEntity, UUID> {
    List<UtilisateurEntity> findByRole(int role);
}
