package charlyday.ccd.besoins;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class BesoinsService {

	private final BesoinsRepository besoinsRepository;

	@Autowired
	public BesoinsService(BesoinsRepository besoinsRepository){
		this.besoinsRepository = besoinsRepository;
	}

	public List<BesoinsEntity> getAllBesoins() {
		return besoinsRepository.findAll();
	}

	public Page<BesoinsEntity> getAllBesoinsPaginated(Pageable pageable) {
		return besoinsRepository.findAll(pageable);
	}

	public BesoinsEntity createBesoins(BesoinsEntity entity){return besoinsRepository.save(entity);}

	public BesoinsEntity updateBesoins(BesoinsEntity entity){return besoinsRepository.save(entity);}

	public void deleteBesoins(BesoinsEntity entity){besoinsRepository.delete(entity);}

	public BesoinsEntity getBesoinsById(UUID id) {
		return besoinsRepository.findById(id).orElse(null);
	}


}