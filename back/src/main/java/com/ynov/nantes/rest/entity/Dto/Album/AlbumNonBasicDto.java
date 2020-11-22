package com.ynov.nantes.rest.entity.dto.album;

import com.ynov.nantes.rest.entity.Album;
import com.ynov.nantes.rest.entity.Song;
import com.ynov.nantes.rest.entity.dto.song.SongBasicDto;

import java.util.ArrayList;
import java.util.List;

public class AlbumNonBasicDto {

    private Integer id;

    private String name;

    private Integer release_year;

    private String image_album;

    private List<SongBasicDto> songs;

    public AlbumNonBasicDto(Album album) {
        this.id = album.getId();
        this.name = album.getName();
        this.release_year = album.getRelease_year();
        this.image_album = album.getImage_album();
        if(album.getSongs().size() > 0 && album.getSongs() != null) {
            List<SongBasicDto> stock = new ArrayList<>();
            for(Song s: album.getSongs()) {
                stock.add(new SongBasicDto(s));
            }
            this.songs = stock;
        }
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

    public List<SongBasicDto> getSongs() {
        return songs;
    }

    public void setSongs(List<SongBasicDto> songs) {
        this.songs = songs;
    }
}
