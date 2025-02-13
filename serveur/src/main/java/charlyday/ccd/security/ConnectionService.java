package charlyday.ccd.security;

import charlyday.ccd.utilisateur.UtilisateurDto;
import charlyday.ccd.utilisateur.UtilisateurMapper;
import charlyday.ccd.utilisateur.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
public class ConnectionService implements UserDetailsService {
    private final UtilisateurService utilisateurService;
    @Autowired
    public ConnectionService(UtilisateurService utilisateurService){this.utilisateurService = utilisateurService;}

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UtilisateurDto utilisateurDto = UtilisateurMapper.INSTANCE.mapToDTO(utilisateurService.getByEmail(email));
        if (utilisateurDto == null){
            throw new UsernameNotFoundException("Error");
        }
        String role = "";
        if(utilisateurDto.getRole() == 0){
            role = "ADMIN";
        }else if(utilisateurDto.getRole() == 1){
            role = "SALARIE";
        }else if(utilisateurDto.getRole() == 2){
            role = "CLIENT";
        }else if(utilisateurDto.getRole() == 3){
            role = "FUTUR SALARIE";
        }
        return new User(utilisateurDto.getEmail(),utilisateurDto.getPassword(), Collections.singletonList(new SimpleGrantedAuthority(role)));
    }
}
