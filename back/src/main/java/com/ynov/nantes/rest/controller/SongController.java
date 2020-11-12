package com.ynov.nantes.rest.controller;

import com.ynov.nantes.rest.entity.Song;
import com.ynov.nantes.rest.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class SongController {

    private SongRepository songRepository;

    @Autowired
    public SongController(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @ResponseBody
    @RequestMapping("/song")
    public List<Song> getSongByTitle(@Param("name") String name) {
        return songRepository.findByTitle(name);
    }

    @ResponseBody
    @GetMapping("/songs")
    public Page<Song> getSongs(@Param("page") Integer page, @Param("nb") Integer nb) {
        PageRequest paginationSize = PageRequest.of(page, nb);
        return songRepository.findAll(paginationSize);
    }

    @ResponseBody
    @GetMapping("/song/{id}")
    public Song getSongById(final @PathVariable("id") String songId) {
        try {
            Optional<Song> song = songRepository.findById(Integer.valueOf(songId));
            return song.get();
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/song")
    public Song addSong(@RequestBody Song song) {
        return songRepository.save(song);
    }

    @PutMapping("/song")
    public Song updateSong(@RequestBody Song song) {
        return songRepository.save(song);
    }

    @DeleteMapping("/song/{id}")
    public HttpStatus deleteSong(final @PathVariable("id") Integer songId) {
        try {
            songRepository.deleteById(songId);
            return HttpStatus.OK;
        }catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
    }
}
