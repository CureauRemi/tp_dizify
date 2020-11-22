package com.ynov.nantes.rest.controller;

import com.ynov.nantes.rest.entity.Artist;
import com.ynov.nantes.rest.entity.Playlist;
import com.ynov.nantes.rest.entity.Dto.playlist.GetPlaylistDto;
import com.ynov.nantes.rest.entity.dto.playlist.PlaylistDto;
import com.ynov.nantes.rest.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Page<GetPlaylistDto> getPlaylists(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        return playlistService.getAllPlaylist(page, limit);
    }

    @ResponseBody
    @GetMapping("/{id}")
    public PlaylistDto getPlaylistDetail(@PathVariable("id") Integer id) {
        return playlistService.getPlaylist(id);
    }

    //@ResponseBody
    //@PostMapping
    //public Playlist addPlaylist(@RequestBody AddPlaylistDto playlist) {
    //    return playlistService.addAPlaylist(playlist);
    //}

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
