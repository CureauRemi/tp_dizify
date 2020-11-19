package com.ynov.nantes.rest.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    @OneToOne(targetEntity = Album.class)
    private Album album;

    @OneToOne(targetEntity = Artist.class)
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

}
