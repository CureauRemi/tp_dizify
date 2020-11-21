package com.ynov.nantes.rest.controller;

import java.util.List;
import com.ynov.nantes.rest.entity.Artist;
import com.ynov.nantes.rest.entity.dto.album.AlbumDto;
import com.ynov.nantes.rest.entity.dto.album.getAlbumDto;
import com.ynov.nantes.rest.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.ynov.nantes.rest.entity.Album;

@RestController
@RequestMapping("album")
public class AlbumController {


    @Autowired
    private AlbumService albumService;

    @ResponseBody
    @GetMapping("SearchByName")
    public List<Album> getAlbumsByTitle(@Param("name") String name) {
       return albumService.getAlbumsByTitle(name);
    }

    @ResponseBody
    @GetMapping
    public Page<getAlbumDto> getAlbums(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        return albumService.getAllAlbums(page, limit);
    }

    @ResponseBody
    @GetMapping("/artist/{id}")
    public List<Album> getAlbumByArtistId(final @PathVariable("id") Artist artist) {
        return albumService.getAlbumByArtist(artist);
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("/{id}")
    public Album getAlbumById(final @PathVariable("id") Integer albumId) {
        return albumService.getById(albumId);
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @PostMapping
    public Album addAlbum(@RequestBody Album album) {
        return albumService.addAlbum(album);
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @PutMapping
    public Album updateAlbum(@RequestBody Album album) {
            return albumService.updateAlbum(album);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteAlbum(final @PathVariable("id") Integer id) {
        return albumService.deleteAlbum(id);
    }

}