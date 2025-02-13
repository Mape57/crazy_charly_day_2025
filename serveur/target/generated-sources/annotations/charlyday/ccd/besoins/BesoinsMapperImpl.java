package charlyday.ccd.besoins;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-13T18:12:29+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Homebrew)"
)
public class BesoinsMapperImpl implements BesoinsMapper {

    @Override
    public BesoinsDto mapToDTO(BesoinsEntity entity) {
        if ( entity == null ) {
            return null;
        }

        BesoinsDto besoinsDto = new BesoinsDto();

        besoinsDto.setId( entity.getId() );
        besoinsDto.setClientId( entity.getClientId() );
        besoinsDto.setDescription( entity.getDescription() );
        besoinsDto.setCompetenceId( entity.getCompetenceId() );
        besoinsDto.setDateService( entity.getDateService() );
        besoinsDto.setSalarieId( entity.getSalarieId() );
        besoinsDto.setDuree( entity.getDuree() );

        return besoinsDto;
    }

    @Override
    public BesoinsEntity mapToEntity(BesoinsDto dto) {
        if ( dto == null ) {
            return null;
        }

        BesoinsEntity besoinsEntity = new BesoinsEntity();

        besoinsEntity.setId( dto.getId() );
        besoinsEntity.setClientId( dto.getClientId() );
        besoinsEntity.setDescription( dto.getDescription() );
        besoinsEntity.setCompetenceId( dto.getCompetenceId() );
        besoinsEntity.setDateService( dto.getDateService() );
        besoinsEntity.setSalarieId( dto.getSalarieId() );
        besoinsEntity.setDuree( dto.getDuree() );

        return besoinsEntity;
    }

    @Override
    public List<BesoinsDto> mapToListDTO(List<BesoinsEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<BesoinsDto> list = new ArrayList<BesoinsDto>( entities.size() );
        for ( BesoinsEntity besoinsEntity : entities ) {
            list.add( mapToDTO( besoinsEntity ) );
        }

        return list;
    }

    @Override
    public List<BesoinsEntity> mapToListEntity(List<BesoinsDto> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<BesoinsEntity> list = new ArrayList<BesoinsEntity>( dtos.size() );
        for ( BesoinsDto besoinsDto : dtos ) {
            list.add( mapToEntity( besoinsDto ) );
        }

        return list;
    }
}
