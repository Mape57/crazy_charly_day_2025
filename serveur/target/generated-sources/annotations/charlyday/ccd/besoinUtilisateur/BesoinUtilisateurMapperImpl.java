package charlyday.ccd.besoinUtilisateur;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-13T19:33:20+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Amazon.com Inc.)"
)
public class BesoinUtilisateurMapperImpl implements BesoinUtilisateurMapper {

    @Override
    public BesoinUtilisateurDto mapToDto(BesoinUtilisateurEntity entity) {
        if ( entity == null ) {
            return null;
        }

        BesoinUtilisateurDto besoinUtilisateurDto = new BesoinUtilisateurDto();

        besoinUtilisateurDto.setSalarieId( entityBesoinUtilisateurKeySalarieId( entity ) );
        besoinUtilisateurDto.setBesoinId( entityBesoinUtilisateurKeyBesoinId( entity ) );

        return besoinUtilisateurDto;
    }

    @Override
    public BesoinUtilisateurEntity mapToEntity(BesoinUtilisateurDto dto) {
        if ( dto == null ) {
            return null;
        }

        BesoinUtilisateurEntity besoinUtilisateurEntity = new BesoinUtilisateurEntity();

        besoinUtilisateurEntity.setBesoinUtilisateurKey( besoinUtilisateurDtoToBesoinUtilisateurKey( dto ) );

        return besoinUtilisateurEntity;
    }

    @Override
    public List<BesoinUtilisateurDto> mapToListDto(List<BesoinUtilisateurEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<BesoinUtilisateurDto> list = new ArrayList<BesoinUtilisateurDto>( entities.size() );
        for ( BesoinUtilisateurEntity besoinUtilisateurEntity : entities ) {
            list.add( mapToDto( besoinUtilisateurEntity ) );
        }

        return list;
    }

    @Override
    public List<BesoinUtilisateurEntity> mapToListEntity(List<BesoinUtilisateurDto> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<BesoinUtilisateurEntity> list = new ArrayList<BesoinUtilisateurEntity>( dtos.size() );
        for ( BesoinUtilisateurDto besoinUtilisateurDto : dtos ) {
            list.add( mapToEntity( besoinUtilisateurDto ) );
        }

        return list;
    }

    private UUID entityBesoinUtilisateurKeySalarieId(BesoinUtilisateurEntity besoinUtilisateurEntity) {
        if ( besoinUtilisateurEntity == null ) {
            return null;
        }
        BesoinUtilisateurKey besoinUtilisateurKey = besoinUtilisateurEntity.getBesoinUtilisateurKey();
        if ( besoinUtilisateurKey == null ) {
            return null;
        }
        UUID salarieId = besoinUtilisateurKey.getSalarieId();
        if ( salarieId == null ) {
            return null;
        }
        return salarieId;
    }

    private UUID entityBesoinUtilisateurKeyBesoinId(BesoinUtilisateurEntity besoinUtilisateurEntity) {
        if ( besoinUtilisateurEntity == null ) {
            return null;
        }
        BesoinUtilisateurKey besoinUtilisateurKey = besoinUtilisateurEntity.getBesoinUtilisateurKey();
        if ( besoinUtilisateurKey == null ) {
            return null;
        }
        UUID besoinId = besoinUtilisateurKey.getBesoinId();
        if ( besoinId == null ) {
            return null;
        }
        return besoinId;
    }

    protected BesoinUtilisateurKey besoinUtilisateurDtoToBesoinUtilisateurKey(BesoinUtilisateurDto besoinUtilisateurDto) {
        if ( besoinUtilisateurDto == null ) {
            return null;
        }

        BesoinUtilisateurKey besoinUtilisateurKey = new BesoinUtilisateurKey();

        besoinUtilisateurKey.setSalarieId( besoinUtilisateurDto.getSalarieId() );
        besoinUtilisateurKey.setBesoinId( besoinUtilisateurDto.getBesoinId() );

        return besoinUtilisateurKey;
    }
}
