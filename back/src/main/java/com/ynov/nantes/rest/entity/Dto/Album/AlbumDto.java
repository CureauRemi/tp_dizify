package com.ynov.nantes.rest.entity.dto.album;

import com.ynov.nantes.rest.entity.Album;

public class AlbumDto {

    private Integer id;

    private String name;

    private Integer release_year;

    private String image_album;

    public AlbumDto(Album album) {
        this.id = album.getId();
        this.name = album.getName();
        this.release_year = album.getRelease_year();
        this.image_album = album.getImage_album();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRelease_year() {
        return release_year;
    }

    public void setRelease_year(Integer release_year) {
        this.release_year = release_year;
    }

    public String getImage_album() {
        return image_album;
    }

    public void setImage_album(String image_album) {
        this.image_album = image_album;
    }
}
