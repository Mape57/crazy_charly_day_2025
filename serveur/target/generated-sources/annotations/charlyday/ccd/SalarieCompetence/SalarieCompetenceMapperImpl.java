package charlyday.ccd.SalarieCompetence;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-13T18:12:29+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Homebrew)"
)
public class SalarieCompetenceMapperImpl implements SalarieCompetenceMapper {

    @Override
    public SalarieCompetenceDto mapToDto(SalarieCompetenceEntity entity) {
        if ( entity == null ) {
            return null;
        }

        SalarieCompetenceDto salarieCompetenceDto = new SalarieCompetenceDto();

        salarieCompetenceDto.setSalarieId( entitySalarieCompetenceKeySalarieId( entity ) );
        salarieCompetenceDto.setCompetenceId( entitySalarieCompetenceKeyCompetenceId( entity ) );
        salarieCompetenceDto.setInteret( entity.getInteret() );

        return salarieCompetenceDto;
    }

    @Override
    public SalarieCompetenceEntity mapToEntity(SalarieCompetenceDto dto) {
        if ( dto == null ) {
            return null;
        }

        SalarieCompetenceEntity salarieCompetenceEntity = new SalarieCompetenceEntity();

        salarieCompetenceEntity.setSalarieCompetenceKey( salarieCompetenceDtoToSalarieCompetenceKey( dto ) );
        salarieCompetenceEntity.setInteret( dto.getInteret() );

        return salarieCompetenceEntity;
    }

    @Override
    public List<SalarieCompetenceDto> maptToListDTO(List<SalarieCompetenceEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<SalarieCompetenceDto> list = new ArrayList<SalarieCompetenceDto>( entities.size() );
        for ( SalarieCompetenceEntity salarieCompetenceEntity : entities ) {
            list.add( mapToDto( salarieCompetenceEntity ) );
        }

        return list;
    }

    @Override
    public List<SalarieCompetenceEntity> mapToListEntity(List<SalarieCompetenceDto> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<SalarieCompetenceEntity> list = new ArrayList<SalarieCompetenceEntity>( dtos.size() );
        for ( SalarieCompetenceDto salarieCompetenceDto : dtos ) {
            list.add( mapToEntity( salarieCompetenceDto ) );
        }

        return list;
    }

    private UUID entitySalarieCompetenceKeySalarieId(SalarieCompetenceEntity salarieCompetenceEntity) {
        if ( salarieCompetenceEntity == null ) {
            return null;
        }
        SalarieCompetenceKey salarieCompetenceKey = salarieCompetenceEntity.getSalarieCompetenceKey();
        if ( salarieCompetenceKey == null ) {
            return null;
        }
        UUID salarieId = salarieCompetenceKey.getSalarieId();
        if ( salarieId == null ) {
            return null;
        }
        return salarieId;
    }

    private UUID entitySalarieCompetenceKeyCompetenceId(SalarieCompetenceEntity salarieCompetenceEntity) {
        if ( salarieCompetenceEntity == null ) {
            return null;
        }
        SalarieCompetenceKey salarieCompetenceKey = salarieCompetenceEntity.getSalarieCompetenceKey();
        if ( salarieCompetenceKey == null ) {
            return null;
        }
        UUID competenceId = salarieCompetenceKey.getCompetenceId();
        if ( competenceId == null ) {
            return null;
        }
        return competenceId;
    }

    protected SalarieCompetenceKey salarieCompetenceDtoToSalarieCompetenceKey(SalarieCompetenceDto salarieCompetenceDto) {
        if ( salarieCompetenceDto == null ) {
            return null;
        }

        SalarieCompetenceKey salarieCompetenceKey = new SalarieCompetenceKey();

        salarieCompetenceKey.setSalarieId( salarieCompetenceDto.getSalarieId() );
        salarieCompetenceKey.setCompetenceId( salarieCompetenceDto.getCompetenceId() );

        return salarieCompetenceKey;
    }
}
