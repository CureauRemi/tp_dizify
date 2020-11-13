package com.ynov.nantes.rest.entity;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "artist")
public class Artist {

    // -------------------------------------------------
    //                  FIELDS
    // -------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String alias;
    
    private String image_artist;

    @OneToMany(targetEntity = Album.class, cascade = CascadeType.REMOVE)
    private Set<Album> albums;

    // -------------------------------------------------
    //                  GETTERS AND SETTERS
    // -------------------------------------------------

    public Integer getId() {
        return id;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
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