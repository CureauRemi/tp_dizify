package com.ynov.nantes.rest.entity.dto.song;

import com.ynov.nantes.rest.entity.Song;
import com.ynov.nantes.rest.entity.dto.album.AlbumDto;
import com.ynov.nantes.rest.entity.dto.artist.ArtistBasicDto;


public class SongDto {

    private Integer id;

    private String title;

    private String duration;

    private String image_song;

    private AlbumDto album;

    private ArtistBasicDto artist;

    public SongDto(Song song) {
        this.id = song.getId();
        this.title = song.getTitle();
        this.duration = song.getDuration();
        this.image_song = song.getImage_song();
        if(song.getArtist() != null) {
            this.artist = new ArtistBasicDto(song.getArtist());
        } else {
            this.artist = null;
        }
        if(song.getAlbum() != null) {
            this.album = new AlbumDto(song.getAlbum());
        } else {
            this.album = null;
        }

    }

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

    public AlbumDto getAlbum() {
        return album;
    }

    public void setAlbum(AlbumDto album) {
        this.album = album;
    }

    public ArtistBasicDto getArtist() {
        return artist;
    }

    public void setArtist(ArtistBasicDto artist) {
        this.artist = artist;
    }
}
