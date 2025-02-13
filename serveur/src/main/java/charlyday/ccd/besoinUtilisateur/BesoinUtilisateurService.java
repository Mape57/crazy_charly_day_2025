package charlyday.ccd.besoinUtilisateur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BesoinUtilisateurService {
    private BesoinUtilisateurRepository besoinUtilisateurRepository;
    @Autowired
    public BesoinUtilisateurService(BesoinUtilisateurRepository besoinUtilisateurRepository){this.besoinUtilisateurRepository = besoinUtilisateurRepository;}

    public List<BesoinUtilisateurEntity> getAllBesoinUtilisateur(){return besoinUtilisateurRepository.findAll();}
    public BesoinUtilisateurEntity createBesoinUtilisateur(BesoinUtilisateurEntity besoinUtilisateurEntity){return besoinUtilisateurRepository.save(besoinUtilisateurEntity);}
    public BesoinUtilisateurEntity updateBesoinUtilisateur(BesoinUtilisateurEntity besoinUtilisateurEntity){return besoinUtilisateurRepository.save(besoinUtilisateurEntity);}
    public void deleteBesoinUtilisateur(BesoinUtilisateurEntity besoinUtilisateurEntity){besoinUtilisateurRepository.delete(besoinUtilisateurEntity);}
    public List<BesoinUtilisateurEntity> getBesoinForSalarie(UUID id){return besoinUtilisateurRepository.findByBesoinUtilisateurKeySalarieId(id);}
    public List<BesoinUtilisateurEntity> getBesoinFotBesoin(UUID id){return besoinUtilisateurRepository.findByBesoinUtilisateurKeyBesoinId(id);}
}
