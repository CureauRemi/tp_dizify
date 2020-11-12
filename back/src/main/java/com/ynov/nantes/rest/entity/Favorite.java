package com.ynov.nantes.rest.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "favorite")
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(targetEntity = Song.class)
    private Set<Song> favoriteSongs;

    @OneToMany(targetEntity = Album.class)
    private Set<Song> albumSongs;

    @OneToMany(targetEntity = Playlist.class)
    private Set<Song> playlistSongs;

    @OneToOne(targetEntity = User.class)
    private User userId;



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

    public Set<Song> getAlbumSongs() {
        return albumSongs;
    }

    public void setAlbumSongs(Set<Song> albumSongs) {
        this.albumSongs = albumSongs;
    }

    public Set<Song> getPlaylistSongs() {
        return playlistSongs;
    }

    public void setPlaylistSongs(Set<Song> playlistSongs) {
        this.playlistSongs = playlistSongs;
    }

    public User getUser() {
        return userId;
    }

    public void setUser(User userId) {
        this.userId = userId;
    }
}
