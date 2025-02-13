package charlyday.ccd.besoins;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BesoinsMapper {
	BesoinsMapper INSTANCE = Mappers.getMapper(BesoinsMapper.class);

	BesoinsDto mapToDTO(BesoinsEntity entity);

	@InheritInverseConfiguration
	BesoinsEntity mapToEntity(BesoinsDto dto);

	List<BesoinsDto> mapToListDTO(List<BesoinsEntity> entities);

	List<BesoinsEntity> mapToListEntity(List<BesoinsDto> dtos);
}