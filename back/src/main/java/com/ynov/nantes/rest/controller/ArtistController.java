package com.ynov.nantes.rest.controller;

import java.util.List;
import java.util.Optional;

import com.ynov.nantes.rest.entity.Album;
import com.ynov.nantes.rest.entity.dto.artist.ArtistDto;
import com.ynov.nantes.rest.entity.dto.artist.getArtistDto;
import com.ynov.nantes.rest.repository.AlbumRepository;
import com.ynov.nantes.rest.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.ynov.nantes.rest.entity.Artist;
import com.ynov.nantes.rest.repository.ArtistRepository;

@RestController
@RequestMapping("/artist")
public class ArtistController {

    @Autowired
    ArtistService artistService;


    @ResponseBody
    @PostMapping
    public Artist addArtist(@RequestBody ArtistDto artist) {
        return artistService.addArtist(artist);
    }


    @ResponseBody
    @GetMapping("SearchByName")
    public List<Artist> getArtistsByTitle(@Param("name") String name) {
        return artistService.getArtistsByTitle(name);
    }

    @ResponseBody
    @GetMapping("/{id}")
    public ArtistDto getArtistById(final @PathVariable("id") Integer artistId) {
       return artistService.getById(artistId);
    }

    @ResponseBody
    @GetMapping
    public Page<getArtistDto> getArtists(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
       return  artistService.getAllArtists(page, limit);
    }

    @ResponseBody
    @PutMapping
    public Artist editArtist(@RequestBody Artist artist) {
        return artistService.updateArtist(artist);
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @DeleteMapping("/{id}")
    public HttpStatus deleteArtist(final @PathVariable("id") Integer artistId) {
       return artistService.deleteArtist(artistId);

    }



}
