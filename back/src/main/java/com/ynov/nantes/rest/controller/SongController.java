package com.ynov.nantes.rest.controller;

import com.ynov.nantes.rest.entity.Song;
import com.ynov.nantes.rest.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    @RequestMapping(value = "/song", method = RequestMethod.GET, params = "name")
    public List<Song> getSongByTitle(@RequestParam(value = "name", defaultValue = "") String name) {
        List<Song> songs = songRepository.findByTitle(name);
        return songs;
    }

    @ResponseBody
    @GetMapping("/song")
    public List<Song> getSongs() {
        return songRepository.findAll();
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
        Song saved = songRepository.save(song);
        return saved;
    }

    @PutMapping("/song/{id}")
    public Song updateSong(final @PathVariable("id") String songId, @RequestBody Song song) {
        Song toUpdate = songRepository.getOne(Integer.valueOf(songId));
        if(toUpdate.getId() == Integer.valueOf(songId)) {
            toUpdate = songRepository.save(song);
        }
        return toUpdate;
    }

    @DeleteMapping("/song")
    public String deleteSong(@RequestBody Song song) {
        try{
            songRepository.delete(song);
        } catch (Exception e) {
            return "error : " + e;
        }
        return "Song Deleted Successfully !";
    }
}
