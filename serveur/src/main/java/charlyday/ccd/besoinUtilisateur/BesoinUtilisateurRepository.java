package charlyday.ccd.besoinUtilisateur;

import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.UUID;

public interface BesoinUtilisateurRepository extends ListCrudRepository<BesoinUtilisateurEntity, UUID> {
    List<BesoinUtilisateurEntity> findByBesoinUtilisateurKeySalarieId(UUID idSalarie);
    List<BesoinUtilisateurEntity> findByBesoinUtilisateurKeyBesoinId(UUID idBesoin);
}
