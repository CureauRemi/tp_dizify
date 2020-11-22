package com.ynov.nantes.rest.entity.dto.playlist;

import com.ynov.nantes.rest.entity.Album;
import com.ynov.nantes.rest.entity.Playlist;
import com.ynov.nantes.rest.entity.Song;
import com.ynov.nantes.rest.entity.dto.album.AlbumDto;
import com.ynov.nantes.rest.entity.dto.song.SongDto;
import com.ynov.nantes.rest.entity.dto.utilisateur.UtilisateurDto;

import java.util.ArrayList;
import java.util.List;

public class PlaylistDto {

    private Integer id;

    private String name;

    private List<SongDto> songs;

    private UtilisateurDto user;

    public PlaylistDto(Playlist playlist) {
        this.id = playlist.getId();
        this.name = playlist.getName();
        if(playlist.getSongs() != null && playlist.getSongs().size() > 0) {
            List<SongDto> stock = new ArrayList<>();
            for(Song s: playlist.getSongs()){
                stock.add(new SongDto(s));
            }
            this.songs = stock;
        } else {
            this.songs = null;
        }
        if(playlist.getUser() != null) {
            this.user = new UtilisateurDto(playlist.getUser());
        } else {
            this.user = null;
        }
    }

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

    public List<SongDto> getSongs() {
        return songs;
    }

    public void setSongs(List<SongDto> songs) {
        this.songs = songs;
    }

    public UtilisateurDto getUser() {
        return user;
    }

    public void setUser(UtilisateurDto user) {
        this.user = user;
    }
}
