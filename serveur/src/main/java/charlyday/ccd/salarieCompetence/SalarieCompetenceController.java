package charlyday.ccd.salarieCompetence;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("salarieCompetence")
@Transactional
public class SalarieCompetenceController {
    private final SalarieCompetenceService service;
    @Autowired
    public SalarieCompetenceController(SalarieCompetenceService service){this.service = service;}

    @CrossOrigin
    @Operation(summary = "Get all competence salarie",description = "Returns all competence for all salarie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "500", description = "Internal server error - Competence were not found")
    })
    @GetMapping
    public List<SalarieCompetenceDto> getAllSalarieCompetence(){
        List<SalarieCompetenceDto> list = SalarieCompetenceMapper.INSTANCE.maptToListDTO(service.getAllSalarieCompetence());
        if(list == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Competence not found"
            );
        }else {
            return list;
        }
    }

    @CrossOrigin
    @Operation(summary = "Add competence for salarie", description = "Add cometence for salarie with their id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created"),
            @ApiResponse(responseCode = "500", description = "Internal server error - Competence was not create")
    })
    @PostMapping
    public SalarieCompetenceDto createSalarieCompetence(@RequestBody SalarieCompetenceDto dto){
        return SalarieCompetenceMapper.INSTANCE.mapToDto(service.createSalarieCompetence(SalarieCompetenceMapper.INSTANCE.mapToEntity(dto)));
    }

    @CrossOrigin
    @Operation(summary = "Delete competence for salarie",description = "Delete competence for salarie with their id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted"),
            @ApiResponse(responseCode = "500", description = "Internal server error - Competence was not delete")
    })
    @DeleteMapping("/{idSalarie}/{idCompetence}")
    public void deleteSalarieCompetence(@PathVariable UUID idSalarie, @PathVariable UUID idCompetence){
        SalarieCompetenceEntity entity = new SalarieCompetenceEntity();
        SalarieCompetenceKey key = new SalarieCompetenceKey();
        key.setSalarieId(idSalarie);
        key.setCompetenceId(idCompetence);
        entity.setSalarieCompetenceKey(key);
        service.deleteSalarieCompetence(entity);
    }

    @CrossOrigin
    @Operation(summary = "Get all competence for salarie",description = "Return all competence for salarie with his id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "500", description = "Internal server error - Competence were not found")
    })
    @GetMapping("/{id}")
    public List<SalarieCompetenceDto> getSalarieCompetence(@PathVariable UUID id){
        List<SalarieCompetenceDto> dtos = SalarieCompetenceMapper.INSTANCE.maptToListDTO(service.getCompetenceForSalarie(id));
        if (dtos == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Competence not found"
            );
        }else {
            return dtos;
        }
    }
}
