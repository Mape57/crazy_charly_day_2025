package charlyday.ccd.competences;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-13T18:12:29+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Homebrew)"
)
public class CompetenceMapperImpl implements CompetenceMapper {

    @Override
    public CompetenceDto mapToDTO(CompetenceEntity entity) {
        if ( entity == null ) {
            return null;
        }

        CompetenceDto competenceDto = new CompetenceDto();

        competenceDto.setId( entity.getId() );
        competenceDto.setLibelle( entity.getLibelle() );
        competenceDto.setValide( entity.isValide() );
        competenceDto.setCategorie( entity.getCategorie() );

        return competenceDto;
    }

    @Override
    public CompetenceEntity mapToEntity(CompetenceDto dto) {
        if ( dto == null ) {
            return null;
        }

        CompetenceEntity competenceEntity = new CompetenceEntity();

        competenceEntity.setId( dto.getId() );
        competenceEntity.setLibelle( dto.getLibelle() );
        competenceEntity.setValide( dto.isValide() );
        competenceEntity.setCategorie( dto.getCategorie() );

        return competenceEntity;
    }

    @Override
    public List<CompetenceDto> mapToListDTO(List<CompetenceEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<CompetenceDto> list = new ArrayList<CompetenceDto>( entities.size() );
        for ( CompetenceEntity competenceEntity : entities ) {
            list.add( mapToDTO( competenceEntity ) );
        }

        return list;
    }

    @Override
    public List<CompetenceEntity> mapToListEntity(List<CompetenceDto> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<CompetenceEntity> list = new ArrayList<CompetenceEntity>( dtos.size() );
        for ( CompetenceDto competenceDto : dtos ) {
            list.add( mapToEntity( competenceDto ) );
        }

        return list;
    }
}
