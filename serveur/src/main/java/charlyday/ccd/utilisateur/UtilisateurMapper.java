package charlyday.ccd.utilisateur;

import charlyday.ccd.competences.CompetenceMapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = CompetenceMapper.class)
public interface UtilisateurMapper {
    UtilisateurMapper INSTANCE = Mappers.getMapper(UtilisateurMapper.class);

    @Mapping(target = "competences", source = "competences")
    UtilisateurDto mapToDTO(UtilisateurEntity entity);
    @InheritInverseConfiguration
    UtilisateurEntity mapToEntity(UtilisateurDto dto);
    List<UtilisateurDto> mapToListDTO(List<UtilisateurEntity> entities);
    List<UtilisateurEntity> mapToListEntity(List<UtilisateurDto> dtos);
}
