package charlyday.ccd.security;

import charlyday.ccd.utilisateur.UtilisateurDto;
import charlyday.ccd.utilisateur.UtilisateurEntity;
import charlyday.ccd.utilisateur.UtilisateurMapper;
import charlyday.ccd.utilisateur.UtilisateurService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AuthController {
    private final ConnectionService connectionService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final UtilisateurService utilisateurService;

    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserConnection userConnection){
        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userConnection.getEmail(),userConnection.getPassword()));
            if(authentication.isAuthenticated()){
                Map<String,Object> authData = new HashMap<>();
                authData.put("token",jwtUtils.generateToken(userConnection.getEmail()));
                authData.put("type","Bearer");
                return ResponseEntity.ok(authData);
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error");
        }catch (AuthenticationException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error");
        }
    }

    @CrossOrigin
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UtilisateurDto utilisateurDto){
        try{
            Map<String,Object> authData = new HashMap<>();
            UtilisateurDto newDto = utilisateurDto;
            newDto.setId(UUID.randomUUID());
            UtilisateurEntity entity = utilisateurService.createUtilisateur(UtilisateurMapper.INSTANCE.mapToEntity(newDto));
            authData.put("token",jwtUtils.generateToken(entity.getEmail()));
            authData.put("type","Bearer");
            return ResponseEntity.ok(authData);
        }catch (AuthenticationException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error");
        }
    }
}
