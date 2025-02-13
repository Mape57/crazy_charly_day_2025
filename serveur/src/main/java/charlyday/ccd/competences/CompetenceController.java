package charlyday.ccd.competences;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("competences")
public class CompetenceController {
    private final CompetenceService competenceService;
    @Autowired
    public CompetenceController(CompetenceService competenceService)
    {this.competenceService = competenceService;}

    @CrossOrigin
    @Operation(summary = "Get all utilisateurs",description = "Returns all utilisateurs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "500", description = "Internal server error - Utilisateurs were not found")
    })
    public List<CompetenceDto> getAllCompetences(){
        List<CompetenceDto> list = CompetenceMapper.INSTANCE.mapToListDTO(competenceService.getAllCompetences());
        if (list == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Competence not found"
            );
        }else {
            return list;
        }
    }

    @CrossOrigin
    @Operation(summary = "Create competence",description = "Create competence with name, description and level")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created"),
            @ApiResponse(responseCode = "404", description = "Internal server error - Competence was not create")
    })
    @PostMapping
    public CompetenceDto createCompetence(@RequestBody CompetenceDto competenceDto){
        CompetenceDto competence = competenceDto;
        competence.setId(UUID.randomUUID());
        //TODO VALIDATION COMPETENCE
        return CompetenceMapper.INSTANCE.mapToDTO(competenceService.createCompetence(CompetenceMapper.INSTANCE.mapToEntity(competence)));
    }

    @CrossOrigin
    @Operation(summary = "Update competence",description = "Update competence with id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated"),
            @ApiResponse(responseCode = "404", description = "Internal server error - Competence was not update")
    })
    @PatchMapping("/{id}")
    public CompetenceDto updateCompetence(@PathVariable UUID id, @RequestBody CompetenceDto competenceDto){
        CompetenceDto dto = CompetenceMapper.INSTANCE.mapToDTO(competenceService.getCompetenceById(id));
        if (dto == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Competence not found"
            );
        }else {
            CompetenceDto modif = competenceDto;
            modif.setId(id);
            return CompetenceMapper.INSTANCE.mapToDTO(competenceService.updateCompetence(CompetenceMapper.INSTANCE.mapToEntity(modif)));
        }
    }

    @CrossOrigin
    @Operation(summary = "Delete competence",description = "Delete competence with id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Internal server error - Competence was not delete")
    })

    @DeleteMapping("/{id}")
    public void deleteCompetence(@PathVariable UUID id){
        CompetenceEntity entity = competenceService.getCompetenceById(id);
        if (entity == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Competence not found"
            );
        }else {
            competenceService.deleteCompetence(entity);
        }
    }

    @CrossOrigin
    @Operation(summary = "Get one competence",description = "Return competence with id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Internal server error - Competence was not found")
    })
    @GetMapping("/{id}")
    public CompetenceDto getCompetence(@PathVariable UUID id){
        CompetenceDto competence = CompetenceMapper.INSTANCE.mapToDTO(competenceService.getCompetenceById(id));
        if (competence == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Competence not found"
            );
        }else {
            return competence;
        }
    }


}














