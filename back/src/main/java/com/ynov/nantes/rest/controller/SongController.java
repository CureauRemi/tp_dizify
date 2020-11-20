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
@RequestMapping("/song")
public class SongController {

    @Autowired
    private final SongRepository songRepository;


    public SongController(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @ResponseBody
    @RequestMapping("getByName")
    public List<Song> getSongByTitle(@Param("name") String name) {
        return songRepository.findByTitle(name);
    }

    @ResponseBody
    @GetMapping
    public Page<Song> getSongs(@RequestParam("page") Integer page, @RequestParam("limit") Integer nb) {
        PageRequest paginationSize = PageRequest.of(page, nb);
        return songRepository.findAll(paginationSize);
    }

    @ResponseBody
    @GetMapping("/{id}")
    public Song getSongById(final @PathVariable("id") String songId) {
        try {
            Optional<Song> song = songRepository.findById(Integer.valueOf(songId));
            return song.get();
        } catch (Exception e) {
            return null;
        }
    }

    @ResponseBody
    @PostMapping
    public Song addSong(@RequestBody Song song) {
        return songRepository.save(song);
    }

    @ResponseBody
    @PutMapping
    public Song updateSong(@RequestBody Song song) {
        return songRepository.save(song);
    }



    @DeleteMapping("/{id}")
    public HttpStatus deleteSong(final @PathVariable("id") Integer songId) {
        try {
            songRepository.deleteById(songId);
            return HttpStatus.OK;
        }catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
    }
}
