package com.ynov.nantes.rest.controller;

import com.ynov.nantes.rest.entity.Favorite;
import com.ynov.nantes.rest.entity.Playlist;
import com.ynov.nantes.rest.repository.FavoriteRepository;
import com.ynov.nantes.rest.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class PlaylistController {

    private PlaylistRepository playlistRepository;

    @Autowired
    public PlaylistController(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    @ResponseBody
    @GetMapping("/playlist")
    public Playlist getFavoriteByUserId(final @PathVariable("name") String playlistName) {
        try {
            Optional<Playlist> playlist = playlistRepository.findByName(playlistName);
            return playlist.get();
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/playlist")
    public Playlist addFavorite(@RequestBody Playlist playlist) {
        Playlist saved = playlistRepository.save(playlist);
        return saved;
    }

    @PutMapping("/playlist/{id}")
    public Playlist updateAlbum(final @PathVariable("id") String playlistId, @RequestBody Playlist playlist) {
        Playlist toUpdate = playlistRepository.getOne(Integer.valueOf(playlistId));
        if(toUpdate.getId() == Integer.valueOf(playlistId)) {
            toUpdate = playlistRepository.save(playlist);
        }
        return toUpdate;
    }

    @DeleteMapping("/playlist/{id}")
    public String deleteAlbum(@RequestBody Playlist playlist) {
        try{
            playlistRepository.delete(playlist);
        } catch (Exception e) {
            return "error : " + e;
        }
        return "Playlist Deleted Successfully !";
    }
}
