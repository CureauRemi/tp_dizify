package com.ynov.nantes.rest.entity;

import org.hibernate.engine.spi.CascadeStyle;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "favorite")
public class Favorite {

    // -------------------------------------------------
    //                  FIELDS
    // -------------------------------------------------

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToMany(targetEntity = Song.class, fetch = FetchType.EAGER,  cascade = CascadeType.REMOVE)
    private Set<Song> favoriteSongs;

    @OneToMany(targetEntity = Album.class, fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<Album> favoriteAlbums;

    @OneToMany(targetEntity = Artist.class, fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<Artist> favoriteArtist;

    @ManyToOne(targetEntity = Utilisateur.class)
    private Utilisateur user;

    // -------------------------------------------------
    //                  GETTERS AND SETTERS
    // -------------------------------------------------

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Song> getFavoriteSongs() {
        return favoriteSongs;
    }

    public void setFavoriteSongs(Set<Song> favoriteSongs) {
        this.favoriteSongs = favoriteSongs;
    }

    public Set<Album> getFavoriteAlbums() { return favoriteAlbums; }

    public void setFavoriteAlbums(Set<Album> favoriteAlbums) {
        this.favoriteAlbums = favoriteAlbums;
    }

    public Set<Artist> getFavoriteArtists() {
        return favoriteArtist;
    }

    public void setFavoriteArtists(Set<Artist> favoriteArtist) {
        this.favoriteArtist = favoriteArtist;
    }

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }
}
