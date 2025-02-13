package charlyday.ccd.utilisateur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UtilisateurService {
    private final UtilisateurRepository utilisateurRepository;
    @Autowired
    public UtilisateurService(UtilisateurRepository utilisateurRepository){
        this.utilisateurRepository = utilisateurRepository;
    }

    public List<UtilisateurEntity> getAllUtilisateurs(){return utilisateurRepository.findAll();}
    public UtilisateurEntity createUtilisateur(UtilisateurEntity entity){return utilisateurRepository.save(entity);}
    public UtilisateurEntity updateUtilisateur(UtilisateurEntity entity){return utilisateurRepository.save(entity);}
    public void deleteUtilisateur(UtilisateurEntity entity){utilisateurRepository.delete(entity);}
    public UtilisateurEntity getUtilisateurById(UUID id){return utilisateurRepository.findById(id).orElse(null);}
    public List<UtilisateurEntity> getAllByRole(int role){return utilisateurRepository.findByRole(role);}
    public UtilisateurEntity getByEmail(String email){return utilisateurRepository.findByEmail(email);}
}
