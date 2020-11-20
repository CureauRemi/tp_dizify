package com.ynov.nantes.rest.controller;

import com.ynov.nantes.rest.entity.Favorite;
import com.ynov.nantes.rest.entity.Playlist;
import com.ynov.nantes.rest.repository.FavoriteRepository;
import com.ynov.nantes.rest.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/playlist")
public class PlaylistController {

    @Autowired
    private final PlaylistRepository playlistRepository;


    public PlaylistController(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    @ResponseBody
    @GetMapping("/{name}")
    public Playlist getPlaylistByName(final @PathVariable("name") String playlistName) {
        try {
            Optional<Playlist> playlist = playlistRepository.findByName(playlistName);
            return playlist.get();
        } catch (Exception e) {
            return null;
        }
    }

    @ResponseBody
    @PostMapping
    public Playlist addFavorite(@RequestBody Playlist playlist) {
        return playlistRepository.save(playlist);
    }


    @ResponseBody
    @PutMapping
    public Playlist updateAlbum(@RequestBody Playlist playlist) {
        return playlistRepository.save(playlist);
    }

    @ResponseStatus
    @DeleteMapping("/{id}")
    public HttpStatus deletePlaylist(final @PathVariable("id") Integer playlistId) {
        try {
            playlistRepository.deleteById(playlistId);
            return HttpStatus.OK;
        }catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
    }
}
