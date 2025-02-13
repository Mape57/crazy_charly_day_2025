package charlyday.ccd.utilisateur;

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
@RequestMapping("admins")
public class AdminController {
    private final UtilisateurService utilisateurService;
    @Autowired
    public AdminController(UtilisateurService utilisateurService){this.utilisateurService = utilisateurService;}

    @CrossOrigin
    @Operation(summary = "Get all utilisateurs",description = "Returns all utilisateurs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "500", description = "Internal server error - Utilisateurs were not found")
    })
    @GetMapping
    public List<UtilisateurDto> getAllAdmin(){
        List<UtilisateurDto> list = UtilisateurMapper.INSTANCE.mapToListDTO(utilisateurService.getAllByRole(0));
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
    public UtilisateurDto createAdmin(@RequestBody UtilisateurDto utilisateurDto){
        UtilisateurDto utilisateur = utilisateurDto;
        utilisateur.setId(UUID.randomUUID());
        utilisateur.setRole(0);
        return UtilisateurMapper.INSTANCE.mapToDTO(utilisateurService.createUtilisateur(UtilisateurMapper.INSTANCE.mapToEntity(utilisateur)));
    }

    @CrossOrigin
    @Operation(summary = "Update utilisateur",description = "Update utilisateur with id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated"),
            @ApiResponse(responseCode = "404", description = "Internal server error - Utilisateur was not update")
    })
    @PatchMapping("/{id}")
    public UtilisateurDto updateAdmin(@PathVariable UUID id, @RequestBody UtilisateurDto utilisateurDto){
        UtilisateurDto dto = UtilisateurMapper.INSTANCE.mapToDTO(utilisateurService.getUtilisateurById(id));
        if (dto == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Disponibility not found"
            );
        }else if (dto.getRole() != 0) {
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
    public void deleteAdmin(@PathVariable UUID id){
        UtilisateurEntity entity = utilisateurService.getUtilisateurById(id);
        if (entity == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Disponibility not found"
            );
        }else if(entity.getRole() != 2){
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
    public UtilisateurDto getAdmin(@PathVariable UUID id){
        UtilisateurDto modif = UtilisateurMapper.INSTANCE.mapToDTO(utilisateurService.getUtilisateurById(id));
        if (modif == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Utilisateur not found"
            );
        }else if(modif.getRole() != 0){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Utilisateur is not salarie"
            );
        }else{
            return modif;
        }
    }
}