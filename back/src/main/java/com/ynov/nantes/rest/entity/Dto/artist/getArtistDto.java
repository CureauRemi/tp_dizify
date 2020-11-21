package com.ynov.nantes.rest.entity.dto.artist;

import com.ynov.nantes.rest.entity.Album;
import com.ynov.nantes.rest.entity.Artist;
import com.ynov.nantes.rest.entity.dto.album.AlbumDto;

import java.util.List;

public class getArtistDto {

    private Integer id;

    private String alias;

    private String image_artist;

    public getArtistDto(Artist artist) {
        this.id = artist.getId();
        this.alias = artist.getAlias();
        this.image_artist = artist.getImage_artist();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getImage_artist() {
        return image_artist;
    }

    public void setImage_artist(String image_artist) {
        this.image_artist = image_artist;
    }
}
