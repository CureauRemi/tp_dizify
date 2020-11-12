package com.ynov.nantes.rest.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "song")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String title;

    private String duration;

    private String image_song;

    @ManyToOne
    private Artist artist;



    @ManyToMany @JoinColumn( name = "albumId")
    private Set<Album> albums = new HashSet<>();

    public Song() { }

    public Song(String title, String duration, String image_song, Artist artist) {
        this.title = title;
        this.duration = duration;
        this.image_song = image_song;
        this.artist = artist;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
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
