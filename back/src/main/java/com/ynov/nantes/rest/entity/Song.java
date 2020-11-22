package com.ynov.nantes.rest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;


@Entity
@Table(name = "song")
public class Song {

    // -------------------------------------------------
    //                  FIELDS
    // -------------------------------------------------

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String title;

    private String duration;

    private String image_song;

    @JsonIgnore
    @OneToOne(targetEntity = Album.class, fetch = FetchType.EAGER)
    private Album album;

    @JsonIgnore
    @OneToOne(targetEntity = Artist.class, fetch = FetchType.EAGER)
    private Artist artist;

    // -------------------------------------------------
    //                  GETTERS AND SETTERS
    // -------------------------------------------------

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

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
