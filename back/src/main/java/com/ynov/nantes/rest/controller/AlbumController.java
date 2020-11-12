package com.ynov.nantes.rest.controller;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
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
    public Page<Album> getAlbums(@Param("page") Integer page, @Param("nb") Integer nb) {
        PageRequest paginationSize = PageRequest.of(page, nb);
        return albumRepository.findAll(paginationSize);
    }

    @ResponseBody
    @GetMapping("/album/{id}")
    public Album getAlbumById(final @PathVariable("id") Integer albumId) {
        try {
            Optional<Album> album = albumRepository.findById(albumId);
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

    @PutMapping("/album")
    public Album updateAlbum(@RequestBody Album album) {
            return albumRepository.save(album);
    }

    @DeleteMapping("/album/{id}")
    public HttpStatus deleteAlbum(final @PathVariable("id") Integer albumId) {
        albumRepository.deleteById(albumId);
        return HttpStatus.OK;
    }
}
