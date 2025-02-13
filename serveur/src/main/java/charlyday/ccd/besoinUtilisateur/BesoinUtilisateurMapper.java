package charlyday.ccd.besoinUtilisateur;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BesoinUtilisateurMapper {
    BesoinUtilisateurMapper INSTANCE = Mappers.getMapper(BesoinUtilisateurMapper.class);
    @Mapping(target = "salarieId",source = "besoinUtilisateurKey.salarieId")
    @Mapping(target = "besoinId",source = "besoinUtilisateurKey.besoinId")
    BesoinUtilisateurDto mapToDto(BesoinUtilisateurEntity entity);
    @InheritInverseConfiguration
    BesoinUtilisateurEntity mapToEntity(BesoinUtilisateurDto dto);
    List<BesoinUtilisateurDto> mapToListDto(List<BesoinUtilisateurEntity> entities);
    List<BesoinUtilisateurEntity> mapToListEntity(List<BesoinUtilisateurDto> dtos);
}
