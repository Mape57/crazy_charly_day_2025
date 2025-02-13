package charlyday.ccd.utilisateur;

import charlyday.ccd.besoinUtilisateur.BesoinUtilisateurEntity;
import charlyday.ccd.besoinUtilisateur.BesoinUtilisateurService;
import charlyday.ccd.besoins.BesoinsDto;
import charlyday.ccd.besoins.BesoinsEntity;
import charlyday.ccd.besoins.BesoinsMapper;
import charlyday.ccd.besoins.BesoinsService;
import charlyday.ccd.competences.*;
import charlyday.ccd.salarieCompetence.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("salaries")
public class SalarieController {
    private final UtilisateurService utilisateurService;
    private final CompetenceService competenceService;
    private final SalarieCompetenceService salarieCompetenceService;
    private final BesoinUtilisateurService besoinUtilisateurService;
    private final BesoinsService besoinsService;
    @Autowired
    public SalarieController(UtilisateurService utilisateurService,CompetenceService competenceService,SalarieCompetenceService salarieCompetenceService,BesoinUtilisateurService besoinUtilisateurService,BesoinsService besoinsService){this.utilisateurService = utilisateurService;this.competenceService = competenceService;this.salarieCompetenceService = salarieCompetenceService;this.besoinUtilisateurService = besoinUtilisateurService;this.besoinsService = besoinsService;}

    @CrossOrigin
    @Operation(summary = "Get all utilisateurs",description = "Returns all utilisateurs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "500", description = "Internal server error - Utilisateurs were not found")
    })
    @GetMapping
    public List<UtilisateurDto> getAllSalaries(){
        List<UtilisateurDto> list = UtilisateurMapper.INSTANCE.mapToListDTO(utilisateurService.getAllByRole(1));
        if (list == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Utilisateurs not found"
            );
        }else {
            return list;
        }
    }

    @CrossOrigin
    @Operation(summary = "Create utilisateur",description = "Create utilisateur with day, open and close")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created"),
            @ApiResponse(responseCode = "404", description = "Internal server error - Utilisateur was not create")
    })
    @PostMapping
    public UtilisateurDto createSalaries(@RequestBody UtilisateurDto utilisateurDto){
        UtilisateurDto utilisateur = utilisateurDto;
        utilisateur.setId(UUID.randomUUID());
        utilisateur.setRole(1);
        return UtilisateurMapper.INSTANCE.mapToDTO(utilisateurService.createUtilisateur(UtilisateurMapper.INSTANCE.mapToEntity(utilisateur)));
    }

    @CrossOrigin
    @Operation(summary = "Update utilisateur",description = "Update utilisateur with id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated"),
            @ApiResponse(responseCode = "404", description = "Internal server error - Utilisateur was not update")
    })
    @PatchMapping("/{id}")
    public UtilisateurDto updateSalaries(@PathVariable UUID id, @RequestBody UtilisateurDto utilisateurDto){
        UtilisateurDto dto = UtilisateurMapper.INSTANCE.mapToDTO(utilisateurService.getUtilisateurById(id));
        if (dto == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Disponibility not found"
            );
        }else if (dto.getRole() != 1) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Utilisateur is not salarie"
            );
        }else {
            UtilisateurDto modif = utilisateurDto;
            modif.setId(id);
            return UtilisateurMapper.INSTANCE.mapToDTO(utilisateurService.updateUtilisateur(UtilisateurMapper.INSTANCE.
                    mapToEntity(modif)));
        }
    }

    @CrossOrigin
    @Operation(summary = "Delete utilisateur",description = "Delete utilisateur with id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted"),
            @ApiResponse(responseCode = "500", description = "Internal server error - Utilisateur was not delete")
    })
    @DeleteMapping("/{id}")
    public void deleteSalaries(@PathVariable UUID id){
        UtilisateurEntity entity = utilisateurService.getUtilisateurById(id);
        if (entity == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Disponibility not found"
            );
        }else if(entity.getRole() != 1){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Utilisateur is not salarie"
            );
        }
        utilisateurService.deleteUtilisateur(entity);
    }

    @CrossOrigin
    @Operation(summary = "Get one utilisateur",description = "Return utilisateur with id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "500", description = "Internal server error - Utilisateur was not found")
    })
    @GetMapping("/{id}")
    public UtilisateurDto getSalaries(@PathVariable UUID id){
        UtilisateurDto modif = UtilisateurMapper.INSTANCE.mapToDTO(utilisateurService.getUtilisateurById(id));
        if (modif == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Utilisateur not found"
            );
        }else if(modif.getRole() != 1){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Utilisateur is not salarie"
            );
        }else{
            return modif;
        }
    }

    @CrossOrigin
    @Operation(summary = "Get competence for utilisateur",description = "Return competence with id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "500", description = "Internal server error - Utilisateur was not found")
    })
    @GetMapping("/{id}/competences")
    public List<CompetenceInteret> getCompetenceForSalarie(@PathVariable UUID id){
        UtilisateurEntity entity = utilisateurService.getUtilisateurById(id);
        List<CompetenceDto> list = CompetenceMapper.INSTANCE.mapToListDTO(utilisateurService.getUtilisateurById(id).getCompetences());
        if (list == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Competences not found"
            );
        }else if(entity.getRole() != 1) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Utilisateur is not salarie"
            );
        }else if(list.size() == 0){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No one competences for this salarie"
            );
        }else{
            List<CompetenceInteret> result = new ArrayList<>();
            for (CompetenceDto dto : list){
                CompetenceInteret interet = new CompetenceInteret();
                SalarieCompetenceEntity salarieCompetenceEntity = salarieCompetenceService.getCompetenceSalarie(id,dto.getId());
                interet.setInteret(salarieCompetenceEntity.getInteret());
                interet.setId(dto.getId());
                interet.setCategorie(dto.getCategorie());
                interet.setLibelle(dto.getLibelle());
                interet.setValide(dto.isValide());
                result.add(interet);
            }
            return result;
        }
    }

    @CrossOrigin
    @Operation(summary = "Create competence for salarie",description = "Create competence for salarie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created"),
            @ApiResponse(responseCode = "404", description = "Internal server error - Competence was not create")
    })
    @PostMapping("{id}/competences")
    public CompetenceDto createCompetenceForSalarie(@PathVariable UUID id, @RequestBody CreerCompetence creerCompetence){
        UtilisateurDto utilisateurDto = UtilisateurMapper.INSTANCE.mapToDTO(utilisateurService.getUtilisateurById(id));
        if (utilisateurDto == null ){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Utilisateur not found"
            );
        }else if(utilisateurDto.getRole() != 1){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Utilisateur is not salarie"
            );
        }

        CompetenceDto dto = new CompetenceDto();
        dto.setCategorie(creerCompetence.getCategorie());
        dto.setLibelle(creerCompetence.getLibelle());
        dto.setValide(creerCompetence.isValide());
        if (creerCompetence.getId() == null){
            dto.setId(UUID.randomUUID());
            dto = CompetenceMapper.INSTANCE.mapToDTO(competenceService.createCompetence(CompetenceMapper.INSTANCE.mapToEntity(dto)));
        }else{
            dto.setId(creerCompetence.getId());
        }

        SalarieCompetenceEntity salarieCompetenceEntity = new SalarieCompetenceEntity();
        salarieCompetenceEntity.setInteret(creerCompetence.getInteret());
        SalarieCompetenceKey salarieCompetenceKey = new SalarieCompetenceKey();
        salarieCompetenceKey.setSalarieId(id);
        salarieCompetenceKey.setCompetenceId(dto.getId());
        salarieCompetenceEntity.setSalarieCompetenceKey(salarieCompetenceKey);
        SalarieCompetenceEntity entity = salarieCompetenceService.createSalarieCompetence(salarieCompetenceEntity);

        return dto;
    }

    @CrossOrigin
    @Operation(summary = "Delete competence for salarie",description = "Delete competence for salarie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted"),
            @ApiResponse(responseCode = "500", description = "Internal server error - Competence was not delete")
    })
    @DeleteMapping("/{idSalarie}/competence/{idCompetence}")
    public void deleteSalaries(@PathVariable UUID idSalarie,@PathVariable UUID idCompetence){
        UtilisateurEntity entity = utilisateurService.getUtilisateurById(idSalarie);
        if (entity == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Salarie not found"
            );
        }else if(entity.getRole() != 1){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Utilisateur is not salarie"
            );
        }

        CompetenceEntity competence = competenceService.getCompetenceById(idCompetence);
        if (competence == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Competence not found"
            );
        }

        SalarieCompetenceEntity salarieCompetenceEntity = new SalarieCompetenceEntity();
        SalarieCompetenceKey salarieCompetenceKey = new SalarieCompetenceKey();
        salarieCompetenceKey.setCompetenceId(competence.getId());
        salarieCompetenceKey.setSalarieId(entity.getId());
        salarieCompetenceEntity.setSalarieCompetenceKey(salarieCompetenceKey);
        salarieCompetenceService.deleteSalarieCompetence(salarieCompetenceEntity);
    }

    @CrossOrigin
    @Operation(summary = "Update competence for salarie",description = "Update competence for salarie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated"),
            @ApiResponse(responseCode = "404", description = "Internal server error - Competence was not update")
    })
    @PatchMapping("/{idSalarie}/competence/{idCompetence}")
    public SalarieCompetenceEntity updateCompetenceForSalaries(@PathVariable UUID idSalarie, @PathVariable UUID idCompetence,@RequestBody CreerInteret creerInteret){
        List<SalarieCompetenceDto> dtos = SalarieCompetenceMapper.INSTANCE.maptToListDTO(salarieCompetenceService.getCompetenceForSalarie(idSalarie));
        SalarieCompetenceDto dto = null;

        if (dtos == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Competence not found"
            );
        }

        for (SalarieCompetenceDto salarieCompetenceDto : dtos){
            if (salarieCompetenceDto.getCompetenceId() == idCompetence)
                dto = salarieCompetenceDto;
        }
        if (dto == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Disponibility not found"
            );
        }

        UtilisateurEntity entity = utilisateurService.getUtilisateurById(idSalarie);

        if (entity.getRole() != 1) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Utilisateur is not salarie"
            );
        }

        SalarieCompetenceEntity salarieCompetenceEntity = new SalarieCompetenceEntity();
        SalarieCompetenceKey salarieCompetenceKey = new SalarieCompetenceKey();
        salarieCompetenceKey.setSalarieId(idSalarie);
        salarieCompetenceKey.setCompetenceId(idCompetence);
        salarieCompetenceEntity.setInteret(creerInteret.getInteret());

        return salarieCompetenceService.updateSalarieCompetence(salarieCompetenceEntity);
    }

    @CrossOrigin
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated"),
            @ApiResponse(responseCode = "404", description = "Internal server error - Besoins was not update")
    })
    @GetMapping("/{id}/besoins")
    public List<BesoinsDto> getBesoinsForSalarie(@PathVariable UUID id){
        UtilisateurEntity entity = utilisateurService.getUtilisateurById(id);
        if (entity == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Utilisateur not found"
            );
        }else if(entity.getRole() != 1){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Utilisateur is not salarie"
            );
        }

        List<BesoinUtilisateurEntity> besoinUtilisateurEntities = besoinUtilisateurService.getBesoinForSalarie(id);
        List<BesoinsEntity> besoinsEntities = new ArrayList<>();
        for (BesoinUtilisateurEntity entities : besoinUtilisateurEntities){
            BesoinsEntity besoins = besoinsService.getBesoinsById(entities.getBesoinUtilisateurKey().getBesoinId());
            besoinsEntities.add(besoins);
        }

        return BesoinsMapper.INSTANCE.mapToListDTO(besoinsEntities);
    }
}