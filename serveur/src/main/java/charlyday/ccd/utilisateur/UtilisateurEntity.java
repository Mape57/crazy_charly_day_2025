package charlyday.ccd.utilisateur;

import charlyday.ccd.competences.CompetenceEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "utilisateurs")
public class UtilisateurEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 2405172041950251807L;

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private int role;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinTable(
            name = "salarie_competences",
            joinColumns = @JoinColumn(name = "salarie_id",  referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "competence_id", referencedColumnName = "id")
    )
    private List<CompetenceEntity> competences = new ArrayList<>();
}
