package com.ynov.nantes.rest.entity.dto.playlist;

import com.ynov.nantes.rest.controller.SongController;
import com.ynov.nantes.rest.entity.Playlist;
import com.ynov.nantes.rest.entity.Song;
import com.ynov.nantes.rest.entity.dto.song.SongBasicDto;
import com.ynov.nantes.rest.entity.dto.song.SongDto;
import com.ynov.nantes.rest.entity.dto.utilisateur.UtilisateurDto;

import java.util.ArrayList;
import java.util.List;

public class GetPlaylistDto {

    private Integer id;

    private String name;

    private List<SongBasicDto> songs;

    private UtilisateurDto user;

    public GetPlaylistDto(Playlist playlist) {
        this.id = playlist.getId();
        this.name = playlist.getName();
        if(playlist.getUser() != null) {
            this.user = new UtilisateurDto(playlist.getUser());
        } else {
            this.user = null;
        }
        if(playlist.getSongs().size() > 0 && playlist.getSongs() != null) {
            List<SongBasicDto> stock = new ArrayList<>();
            for(Song s: playlist.getSongs()) {
                stock.add(new SongBasicDto(s));
            }
            this.songs = stock;
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


    public UtilisateurDto getUser() {
        return user;
    }

    public void setUser(UtilisateurDto user) {
        this.user = user;
    }
}
