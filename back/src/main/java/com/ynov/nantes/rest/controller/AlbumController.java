package com.ynov.nantes.rest.controller;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

import com.ynov.nantes.rest.entity.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.ynov.nantes.rest.entity.Album;
import com.ynov.nantes.rest.repository.AlbumRepository;

@RestController
@RequestMapping("album")
public class AlbumController {

    @Autowired
    private final AlbumRepository albumRepository;


    public AlbumController(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @ResponseBody
    @GetMapping("SearchByName")
    public List<Album> getAlbumsByTitle(@Param("name") String name) {
        List<Album> albums = albumRepository.findByName(name);
        return albums;
    }

    @ResponseBody
    @GetMapping
    public Page<Album> getAlbums(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        PageRequest paginationSize = PageRequest.of(page, limit);
        return albumRepository.findAll(paginationSize);
    }

    @ResponseBody
    @GetMapping("/artist/{id}")
    public List<Album> getAlbumByArtistId(final @PathVariable("id") Artist artist) {
        try {
            return albumRepository.findByArtist(artist);
        } catch (Exception e) {
            return null;
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("/{id}")
    public Album getAlbumById(final @PathVariable("id") Integer albumId) {
        try {
            Optional<Album> album = albumRepository.findById(albumId);
            return album.get();
        } catch (Exception e) {
            return null;
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @PostMapping
    public Album addAlbum(@RequestBody Album album) {
        Album saved = albumRepository.save(album);
        return saved;
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @PutMapping()
    public Album updateAlbum(@RequestBody Album album) {
            return albumRepository.save(album);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteAlbum(final @PathVariable("id") Integer albumId) {
        try{
            albumRepository.deleteById(albumId);
            return HttpStatus.OK;
        } catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }

    }
}
