package com.ynov.nantes.rest.entity;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Artist")
@Table(name = "artist")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String alias;
    
    private String image_artist;

    @OneToMany(mappedBy = "Artist")
    private Set<Album> albums;

    public Artist(String alias, String image_artist, Set<Album> albums) {
        this.alias = alias;
        this.image_artist = image_artist;
        this.albums = albums;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
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