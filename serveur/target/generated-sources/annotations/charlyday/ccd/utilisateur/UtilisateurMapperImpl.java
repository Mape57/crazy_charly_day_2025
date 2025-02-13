package charlyday.ccd.utilisateur;

import charlyday.ccd.competences.CompetenceMapper;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-13T19:33:20+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Amazon.com Inc.)"
)
public class UtilisateurMapperImpl implements UtilisateurMapper {

    private final CompetenceMapper competenceMapper = CompetenceMapper.INSTANCE;

    @Override
    public UtilisateurDto mapToDTO(UtilisateurEntity entity) {
        if ( entity == null ) {
            return null;
        }

        UtilisateurDto utilisateurDto = new UtilisateurDto();

        utilisateurDto.setCompetences( competenceMapper.mapToListDTO( entity.getCompetences() ) );
        utilisateurDto.setId( entity.getId() );
        utilisateurDto.setNom( entity.getNom() );
        utilisateurDto.setEmail( entity.getEmail() );
        utilisateurDto.setPassword( entity.getPassword() );
        utilisateurDto.setRole( entity.getRole() );

        return utilisateurDto;
    }

    @Override
    public UtilisateurEntity mapToEntity(UtilisateurDto dto) {
        if ( dto == null ) {
            return null;
        }

        UtilisateurEntity utilisateurEntity = new UtilisateurEntity();

        utilisateurEntity.setCompetences( competenceMapper.mapToListEntity( dto.getCompetences() ) );
        utilisateurEntity.setId( dto.getId() );
        utilisateurEntity.setNom( dto.getNom() );
        utilisateurEntity.setEmail( dto.getEmail() );
        utilisateurEntity.setPassword( dto.getPassword() );
        utilisateurEntity.setRole( dto.getRole() );

        return utilisateurEntity;
    }

    @Override
    public List<UtilisateurDto> mapToListDTO(List<UtilisateurEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<UtilisateurDto> list = new ArrayList<UtilisateurDto>( entities.size() );
        for ( UtilisateurEntity utilisateurEntity : entities ) {
            list.add( mapToDTO( utilisateurEntity ) );
        }

        return list;
    }

    @Override
    public List<UtilisateurEntity> mapToListEntity(List<UtilisateurDto> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<UtilisateurEntity> list = new ArrayList<UtilisateurEntity>( dtos.size() );
        for ( UtilisateurDto utilisateurDto : dtos ) {
            list.add( mapToEntity( utilisateurDto ) );
        }

        return list;
    }
}
