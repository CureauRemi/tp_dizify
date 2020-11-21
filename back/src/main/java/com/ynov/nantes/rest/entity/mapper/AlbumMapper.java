package com.ynov.nantes.rest.entity.mapper;

import com.ynov.nantes.rest.entity.Album;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
public class AlbumMapper extends ConfigurableMapper {

    protected void configure(MapperFactory factory) {
        factory.classMap(Album.class, Album.class)
                .field("id", "id")
                .field("name", "name")
                .field("release_year", "release_year")
                .field("image_album", "image_album")
                .field("songs", "songs")
                .field("artist", "artist")
                .byDefault()
                .register();
    }
}
