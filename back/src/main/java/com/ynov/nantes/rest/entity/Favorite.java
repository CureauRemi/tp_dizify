package com.ynov.nantes.rest.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "favorite")
public class Favorite {

    // -------------------------------------------------
    //                  FIELDS
    // -------------------------------------------------

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(targetEntity = Song.class, fetch = FetchType.EAGER)
    private Set<Song> favoriteSongs;

    @OneToMany(targetEntity = Album.class, fetch = FetchType.EAGER)
    private Set<Album> favoriteAlbums;

    @OneToMany(targetEntity = Playlist.class, fetch = FetchType.EAGER)
    private Set<Playlist> favoritePlaylist;

    @ManyToOne(targetEntity = Utilisateur.class)
    private Utilisateur userId;

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

    public Set<Playlist> getFavoritePlaylists() {
        return favoritePlaylist;
    }

    public void setFavoritePlaylists(Set<Playlist> favoritePlaylist) {
        this.favoritePlaylist = favoritePlaylist;
    }

    public Utilisateur getUser() {
        return userId;
    }

    public void setUser(Utilisateur userId) {
        this.userId = userId;
    }
}
