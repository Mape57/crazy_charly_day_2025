package charlyday.ccd.competences;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CompetenceService {
    private final CompetenceRepository competenceRepository;
    @Autowired
    public CompetenceService(CompetenceRepository competenceRepository){
        this.competenceRepository = competenceRepository;
    }

    public List<CompetenceEntity> getAllCompetences()
    {return competenceRepository.findAll();}

    public CompetenceEntity createCompetence(CompetenceEntity entity)
    {return competenceRepository.save(entity);}

    public CompetenceEntity updateCompetence(CompetenceEntity entity)
    {return competenceRepository.save(entity);}

    public void deleteCompetence(CompetenceEntity entity)
    {competenceRepository.delete(entity);}

    public CompetenceEntity getCompetenceById(UUID id)
    {return competenceRepository.findById(id).orElse(null);}

}