package com.ynov.nantes.rest.entity.dto.song;


import com.ynov.nantes.rest.entity.Song;

public class SongBasicDto {

    private Integer id;

    private String title;

    private String duration;

    private String image_song;

    public SongBasicDto(Song song) {
        this.id = song.getId();
        this.title = song.getTitle();
        this.duration = song.getDuration();
        this.image_song = song.getImage_song();
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
}
