package charlyday.ccd.utilisateur;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UtilisateurMapper {
    UtilisateurMapper INSTANCE = Mappers.getMapper(UtilisateurMapper.class);

    UtilisateurDto mapToDTO(UtilisateurEntity entity);
    @InheritInverseConfiguration
    UtilisateurEntity mapToEntity(UtilisateurDto dto);
    List<UtilisateurDto> mapToListDTO(List<UtilisateurEntity> entities);
    List<UtilisateurEntity> mapToListEntity(List<UtilisateurDto> dtos);
}
