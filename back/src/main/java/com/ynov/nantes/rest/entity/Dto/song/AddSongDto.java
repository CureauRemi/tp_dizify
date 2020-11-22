package com.ynov.nantes.rest.entity.dto.song;

import com.ynov.nantes.rest.entity.dto.album.AlbumDto;
import com.ynov.nantes.rest.entity.dto.artist.ArtistBasicDto;

public class AddSongDto {

    private Integer id;

    private String title;

    private String duration;

    private String image_song;

    private String album_name;

    private String artist_name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getImage_song() {
        return image_song;
    }

    public void setImage_song(String image_song) {
        this.image_song = image_song;
    }

    public String getAlbum_name() {
        return album_name;
    }

    public void setAlbum_name(String album_name) {
        this.album_name = album_name;
    }

    public String getArtist_name() {
        return artist_name;
    }

    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }
}
