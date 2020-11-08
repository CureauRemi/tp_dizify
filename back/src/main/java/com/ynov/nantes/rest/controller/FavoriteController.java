package com.ynov.nantes.rest.controller;


import com.ynov.nantes.rest.entity.Favorite;
import com.ynov.nantes.rest.entity.Song;
import com.ynov.nantes.rest.repository.FavoriteRepository;
import com.ynov.nantes.rest.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class FavoriteController {

    private FavoriteRepository favoriteRepository;

    @Autowired
    public FavoriteController(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    /*
    @ResponseBody
    @RequestMapping(value = "/song", method = RequestMethod.GET, params = "name")
    public List<Song> getAlbumsByTitle(@RequestParam(value = "name", defaultValue = "") String name) {
        List<Song> songs = favoriteRepository.findByTitle(name);
        return songs;
    }

     */
    @ResponseBody
    @GetMapping("/user/{id}/favorite")
    public Favorite getFavoriteByUserId(final @PathVariable("id") String favoriteId) {
        try {
            Optional<Favorite> favorite = favoriteRepository.findByUserId(Integer.valueOf(favoriteId));
            return favorite.get();
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/user/{id}/favorite")
    public Favorite addFavorite(@RequestBody Favorite favorite) {
        Favorite saved = favoriteRepository.save(favorite);
        return saved;
    }

    @PutMapping("/user/{id}/favorite")
    public Favorite updateAlbum(final @PathVariable("id") String albumId, @RequestBody Favorite album) {
        Favorite toUpdate = favoriteRepository.getOne(Integer.valueOf(albumId));
        if(toUpdate.getId() == Integer.valueOf(albumId)) {
            toUpdate = favoriteRepository.save(album);
        }
        return toUpdate;
    }

    @DeleteMapping("/user/{id}/favorite")
    public String deleteAlbum(@RequestBody Favorite favorite) {
        try{
            favoriteRepository.delete(favorite);
        } catch (Exception e) {
            return "error : " + e;
        }
        return "Favorite Deleted Successfully !";
    }
}
