package charlyday.ccd.besoinUtilisateur;

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
@RequestMapping("besoinUtilisateur")
@Transactional
public class BesoinUtilisateurController {
    private BesoinUtilisateurService service;
    @Autowired
    public BesoinUtilisateurController(BesoinUtilisateurService service){this.service = service;}

    @CrossOrigin
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "500", description = "Internal server error - BesoinSalarie was not found")
    })
    @GetMapping
    public List<BesoinUtilisateurDto> getAllBesoinSalarie(){
        List<BesoinUtilisateurDto> list = BesoinUtilisateurMapper.INSTANCE.mapToListDto(service.getAllBesoinUtilisateur());
        if(list == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "BesoinSalarie not found"
            );
        }else {
            return list;
        }
    }

    @CrossOrigin
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created"),
            @ApiResponse(responseCode = "500", description = "Internal server error - Besoin was not create")
    })
    @PostMapping
    public BesoinUtilisateurDto createBesoinSalarie(@RequestBody BesoinUtilisateurDto dto){
        return BesoinUtilisateurMapper.INSTANCE.mapToDto(service.createBesoinUtilisateur(BesoinUtilisateurMapper.INSTANCE.mapToEntity(dto)));
    }

    @CrossOrigin
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted"),
            @ApiResponse(responseCode = "500", description = "Internal server error - Competence was not delete")
    })
    @DeleteMapping("/{idSalarie}/{idBesoin}")
    public void deleteBesoinSalarie(@PathVariable UUID idSalarie, @PathVariable UUID idBesoin){
        BesoinUtilisateurEntity entity = new BesoinUtilisateurEntity();
        BesoinUtilisateurKey key = new BesoinUtilisateurKey();
        key.setSalarieId(idSalarie);
        key.setBesoinId(idBesoin);
        entity.setBesoinUtilisateurKey(key);
        service.deleteBesoinUtilisateur(entity);
    }
}
