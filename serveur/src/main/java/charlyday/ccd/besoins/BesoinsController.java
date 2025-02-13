package charlyday.ccd.besoins;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/besoins")
public class BesoinsController {

	private final BesoinsService besoinsService;

	@Autowired
	public BesoinsController(BesoinsService besoinsService) {
		this.besoinsService = besoinsService;
	}

	/*
	@CrossOrigin
	@Operation(summary = "Récupérer tous les besoins", description = "Retourne la liste complète des besoins")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Liste récupérée avec succès"),
			@ApiResponse(responseCode = "500", description = "Erreur serveur")
	})
	@GetMapping
	public List<BesoinsDto> getAllBesoins() {
		List<BesoinsDto> besoins = BesoinsMapper.INSTANCE.mapToListDTO(besoinsService.getAllBesoins());
		if (besoins == null) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "Aucun besoin trouvé"
			);
		}
		return besoins;
	}*/

	/*
	@CrossOrigin
	@Operation(summary = "Récupérer les besoins avec pagination", description = "Retourne une liste paginée des besoins sans métadonnées")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Liste paginée récupérée avec succès"),
			@ApiResponse(responseCode = "500", description = "Erreur serveur")
	})
	@GetMapping("/get/besoins")
	public List<BesoinsDto> getBesoinsPaginated(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int limit) {
		Pageable pageable = PageRequest.of(page, limit);
		return besoinsService.getAllBesoinsPaginated(pageable).map(BesoinsMapper.INSTANCE::mapToDTO).getContent();
	}
*/
	@CrossOrigin
	@Operation(summary = "Récupérer les besoins avec ou sans pagination",
			description = "Retourne tous les besoins si aucun paramètre n'est fourni, sinon retourne une liste paginée")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Liste récupérée avec succès"),
			@ApiResponse(responseCode = "500", description = "Erreur serveur")
	})
	@GetMapping
	public List<BesoinsDto> getBesoins(
			@RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer limit) {

		if (page == null || limit == null) {
			return BesoinsMapper.INSTANCE.mapToListDTO(besoinsService.getAllBesoins());
		}

		Pageable pageable = PageRequest.of(page - 1, limit);
		return besoinsService.getAllBesoinsPaginated(pageable).map(BesoinsMapper.INSTANCE::mapToDTO).getContent();
	}


	@CrossOrigin
	@Operation(summary = "Créer un besoin", description = "Crée un nouveau besoin avec les informations fournies")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Besoins créé avec succès"),
			@ApiResponse(responseCode = "500", description = "Erreur serveur")
	})
	@PostMapping
	public BesoinsDto createBesoins(@RequestBody BesoinsDto besoinsDto) {
		besoinsDto.setId(UUID.randomUUID());
		return BesoinsMapper.INSTANCE.mapToDTO(besoinsService.createBesoins(BesoinsMapper.INSTANCE.mapToEntity(besoinsDto)));
	}

	@CrossOrigin
	@Operation(summary = "Mettre à jour un besoin", description = "Met à jour un besoin avec un ID donné")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Besoins mis à jour avec succès"),
			@ApiResponse(responseCode = "404", description = "Besoins non trouvé")
	})
	@PatchMapping("/{id}")
	public BesoinsDto updateBesoins(@PathVariable UUID id, @RequestBody BesoinsDto besoinsDto) {
		BesoinsDto existingBesoins = BesoinsMapper.INSTANCE.mapToDTO(besoinsService.getBesoinsById(id));
		if (existingBesoins == null) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "Besoins non trouvé"
			);
		}

		BesoinsDto modif = besoinsDto;
		modif.setId(id);
		return BesoinsMapper.INSTANCE.mapToDTO(
				besoinsService.updateBesoins(BesoinsMapper.INSTANCE.mapToEntity(modif))
		);
	}

	@CrossOrigin
	@Operation(summary = "Supprimer un besoin", description = "Supprime un besoin avec un ID donné")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Besoins supprimé avec succès"),
			@ApiResponse(responseCode = "500", description = "Erreur serveur")
	})
	@DeleteMapping("/{id}")
	public void deleteBesoins(@PathVariable UUID id) {
		BesoinsEntity entity = besoinsService.getBesoinsById(id);
		if (entity == null) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "Besoins non trouvé"
			);
		}
		besoinsService.deleteBesoins(entity);
	}

	@CrossOrigin
	@Operation(summary = "Récupérer un besoin spécifique", description = "Retourne un besoin en fonction de son ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Besoins récupéré avec succès"),
			@ApiResponse(responseCode = "404", description = "Besoins non trouvé")
	})
	@GetMapping("/{id}")
	public BesoinsDto getBesoinsById(@PathVariable UUID id) {
		BesoinsDto besoins = BesoinsMapper.INSTANCE.mapToDTO(besoinsService.getBesoinsById(id));
		if (besoins == null) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "Besoins non trouvé"
			);
		}
		return besoins;
	}
}
