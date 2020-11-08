package com.ynov.nantes.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ynov.nantes.rest.entity.Album;
import com.ynov.nantes.rest.repository.AlbumRepository;

@RestController
public class AlbumController {

    private AlbumRepository albumRepository;

    @Autowired
    public AlbumController(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @ResponseBody
    @RequestMapping(value = "/album", method = RequestMethod.GET, params = "name")
    public List<Album> getAlbumsByTitle(@RequestParam(value = "name", defaultValue = "") String name) {
        List<Album> albums = albumRepository.findByName(name);
        return albums;
    }

    @ResponseBody
    @GetMapping("/album")
    public List<Album> getAlbums() {
        return albumRepository.findAll();
    }

    @ResponseBody
    @GetMapping("/album/{id}")
    public Album getAlbumById(final @PathVariable("id") String albumId) {
        try {
            Optional<Album> album = albumRepository.findById(Integer.valueOf(albumId));
            return album.get();
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/album")
    public Album addAlbum(@RequestBody Album album) {
        Album saved = albumRepository.save(album);
        return saved;
    }

    @PutMapping("/album/{id}")
    public Album updateAlbum(final @PathVariable("id") String albumId, @RequestBody Album album) {
        Album toUpdate = albumRepository.getOne(Integer.valueOf(albumId));
        if(toUpdate.getId() == Integer.valueOf(albumId)) {
            toUpdate = albumRepository.save(album);
        }
        return toUpdate;
    }

    @DeleteMapping("/album")
    public String deleteAlbum(@RequestBody Album album) {
        try{
            albumRepository.delete(album);
        } catch (Exception e) {
            return "error : " + e;
        }
        return "Album Deleted Successfully !";
    }
}
