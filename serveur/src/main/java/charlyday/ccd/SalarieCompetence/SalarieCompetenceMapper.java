package charlyday.ccd.SalarieCompetence;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SalarieCompetenceMapper {

    SalarieCompetenceMapper INSTANCE = Mappers.getMapper(SalarieCompetenceMapper.class);
    @Mapping(target = "salarieId", source = "salarieCompetenceKey.salarieId")
    @Mapping(target = "competenceId",source = "salarieCompetenceKey.competenceId")
    SalarieCompetenceDto mapToDto(SalarieCompetenceEntity entity);
    @InheritInverseConfiguration
    SalarieCompetenceEntity mapToEntity(SalarieCompetenceDto dto);
    List<SalarieCompetenceDto> maptToListDTO(List<SalarieCompetenceEntity> entities);
    List<SalarieCompetenceEntity> mapToListEntity(List<SalarieCompetenceDto> dtos);
}
