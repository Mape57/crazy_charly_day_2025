package charlyday.ccd.salarieCompetence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SalarieCompetenceService {
    private final SalarieCompetenceRepository salarieCompetenceRepository;
    @Autowired
    public SalarieCompetenceService(SalarieCompetenceRepository salarieCompetenceRepository){this.salarieCompetenceRepository = salarieCompetenceRepository;}

    public List<SalarieCompetenceEntity> getAllSalarieCompetence(){return salarieCompetenceRepository.findAll();}
    public SalarieCompetenceEntity createSalarieCompetence(SalarieCompetenceEntity salarieCompetenceEntity){return salarieCompetenceRepository.save(salarieCompetenceEntity);}
    public SalarieCompetenceEntity updateSalarieCompetence(SalarieCompetenceEntity salarieCompetenceEntity){return salarieCompetenceRepository.save(salarieCompetenceEntity);}
    public void deleteSalarieCompetence(SalarieCompetenceEntity salarieCompetenceEntity){salarieCompetenceRepository.delete(salarieCompetenceEntity);}
    public List<SalarieCompetenceEntity> getCompetenceForSalarie(UUID id){return salarieCompetenceRepository.findBySalarieCompetenceKeySalarieId(id);}
    public SalarieCompetenceEntity getCompetenceSalarie(UUID idSalarie,UUID idCompetence){return salarieCompetenceRepository.findBySalarieCompetenceKeySalarieIdAndSalarieCompetenceKeyCompetenceId(idSalarie, idCompetence);}
}
