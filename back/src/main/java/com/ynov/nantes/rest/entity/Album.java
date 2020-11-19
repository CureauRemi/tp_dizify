package com.ynov.nantes.rest.entity;


import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.aspectj.lang.annotation.Before;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "album")
public class Album {

    // -------------------------------------------------
    //                  FIELDS
    // -------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private Integer release_year;

    private String image_album;

    @OneToMany(cascade = CascadeType.REMOVE)
    private Set<Song> songs;
    
    @ManyToOne(targetEntity = Artist.class)
    private Artist artist;

    // -------------------------------------------------
    //                  GETTERS AND SETTERS
    // -------------------------------------------------

    public Set<Song> getSongs() { return songs; }

    public void setSongs(Set<Song> songs) { this.songs = songs; }

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

    public Artist getArtist() { return artist; }

    public void setArtist(Artist artist) { this.artist = artist; }
}