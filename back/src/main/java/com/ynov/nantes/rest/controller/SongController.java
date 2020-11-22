package com.ynov.nantes.rest.controller;

import com.ynov.nantes.rest.entity.Song;
import com.ynov.nantes.rest.entity.dto.song.AddSongDto;
import com.ynov.nantes.rest.entity.dto.song.SongDto;
import com.ynov.nantes.rest.repository.SongRepository;
import com.ynov.nantes.rest.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/song")
public class SongController {

    @Autowired
    private SongService songService;



    @ResponseBody
    @RequestMapping("getByName")
    public List<SongDto> getSongByTitle(@Param("name") String name) {
        return songService.getSongByTitle(name);
    }

    @ResponseBody
    @GetMapping
    public Page<SongDto> getSongs(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
       return songService.getAllSongs(page, limit);
    }

    @ResponseBody
    @GetMapping("/{id}")
    public SongDto getSongById(final @PathVariable("id") Integer id) {
        return songService.getSongById(id);
    }

    @ResponseBody
    @PostMapping
    public Song addSong(@RequestBody AddSongDto song) {
        return songService.addSong(song);
    }

    @ResponseBody
    @PutMapping
    public Song updateSong(@RequestBody Song song) {
        return songService.updateSong(song);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteSong(final @PathVariable("id") Integer songId) {
        return songService.deleteSong(songId);
    }
}
