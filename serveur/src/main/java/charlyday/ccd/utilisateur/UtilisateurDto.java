package charlyday.ccd.utilisateur;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class UtilisateurDto {
    @Schema(name = "id",example = "1")
    private UUID id;
    @Schema(name = "nom",example = "Pierre")
    private String nom;
    @Schema(name = "email",example = "pierre@mail.com")
    private String email;
    @Schema(name = "password",example = "password")
    private String password;
    @Schema(name = "role",example = "2")
    private int role;
}
