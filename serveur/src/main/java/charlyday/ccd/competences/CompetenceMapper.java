package charlyday.ccd.competences;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.factory.Mappers;

import java.util.List;

public interface CompetenceMapper {
    CompetenceMapper INSTANCE = Mappers.getMapper(CompetenceMapper.class);

    CompetenceDto mapToDTO(CompetenceEntity entity);
    @InheritInverseConfiguration
    CompetenceEntity mapToEntity(CompetenceDto dto);
    List<CompetenceDto> mapToListDTO(List<CompetenceEntity> entities);
    List<CompetenceEntity> mapToListEntity(List<CompetenceDto> dtos);
}
