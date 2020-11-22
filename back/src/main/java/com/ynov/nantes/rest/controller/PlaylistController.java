package com.ynov.nantes.rest.controller;

import com.ynov.nantes.rest.entity.Favorite;
import com.ynov.nantes.rest.entity.Playlist;
import com.ynov.nantes.rest.entity.dto.artist.getArtistDto;
import com.ynov.nantes.rest.entity.dto.playlist.PlaylistDto;
import com.ynov.nantes.rest.entity.dto.playlist.getPlaylistDto;
import com.ynov.nantes.rest.exception.playlist.PlaylistNotFoundException;
import com.ynov.nantes.rest.repository.FavoriteRepository;
import com.ynov.nantes.rest.repository.PlaylistRepository;
import com.ynov.nantes.rest.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/playlist")
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    @ResponseBody
    @GetMapping("/search/{name}")
    public List<Playlist> getPlaylistByName(final @PathVariable("name") String playlistName) {
        try {
            return playlistService.findByName(playlistName);
        } catch (Exception e) {
            return null;
        }
    }

    @ResponseBody
    @GetMapping
    public Page<getPlaylistDto> getPlaylists(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        return playlistService.getAllPlaylist(page, limit);
    }

    @ResponseBody
    @GetMapping("/{id}")
    public PlaylistDto getPlaylistDetail(@PathVariable("id") Integer id) {
        return playlistService.getPlaylist(id);
    }

    @ResponseBody
    @PostMapping
    public Playlist addPlaylist(@RequestBody Playlist playlist) {
        return playlistService.addAPlaylist(playlist);
    }

    @ResponseBody
    @PutMapping
    public Playlist updatePlaylist(@RequestBody Playlist playlist) {
        return playlistService.updatePlaylist(playlist);
    }

    @ResponseStatus
    @DeleteMapping("/{id}")
    public HttpStatus deletePlaylist(final @PathVariable("id") Integer playlistId) {
        return playlistService.deletePlaylist(playlistId);
    }
}
