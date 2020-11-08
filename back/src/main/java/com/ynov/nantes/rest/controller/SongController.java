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
    public List<Song> getAlbumsByTitle(@RequestParam(value = "name", defaultValue = "") String name) {
        List<Song> songs = songRepository.findByTitle(name);
        return songs;
    }

    @ResponseBody
    @GetMapping("/song")
    public List<Song> getAlbums() {
        return songRepository.findAll();
    }

    @ResponseBody
    @GetMapping("/song/{id}")
    public Song getAlbumById(final @PathVariable("id") String albumId) {
        try {
            Optional<Song> album = songRepository.findById(Integer.valueOf(albumId));
            return album.get();
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/song")
    public Song addAlbum(@RequestBody Song album) {
        Song saved = songRepository.save(album);
        return saved;
    }

    @PutMapping("/song/{id}")
    public Song updateAlbum(final @PathVariable("id") String albumId, @RequestBody Song album) {
        Song toUpdate = songRepository.getOne(Integer.valueOf(albumId));
        if(toUpdate.getId() == Integer.valueOf(albumId)) {
            toUpdate = songRepository.save(album);
        }
        return toUpdate;
    }

    @DeleteMapping("/song")
    public String deleteAlbum(@RequestBody Song album) {
        try{
            songRepository.delete(album);
        } catch (Exception e) {
            return "error : " + e;
        }
        return "Album Deleted Successfully !";
    }
}
