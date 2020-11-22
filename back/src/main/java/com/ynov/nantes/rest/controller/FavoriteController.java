package com.ynov.nantes.rest.controller;

import com.ynov.nantes.rest.entity.*;
import com.ynov.nantes.rest.service.FavoriteService;
import com.ynov.nantes.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@RestController
@RequestMapping("favorite")
public class FavoriteController {

    @Autowired
    FavoriteService favoriteService;
    @Autowired
    UserService userService;



    @ResponseBody
    @GetMapping
    public Favorite getFavoriteByUserId() {
        return favoriteService.getAllFavoriteByUser();
    }

    @ResponseBody
    @PostMapping("add/song/{id}")
    public Favorite addFavoriteSong(@PathVariable("id") Integer idSong) {
        return favoriteService.addFavoriteSongToUser(idSong);
    }

    @ResponseBody
    @PostMapping("add/album/{id}")
    public Favorite addFavoriteAlbum(@PathVariable("id") Integer albumId) {
        return favoriteService.addFavoriteAlbumToUser(albumId);
    }


    @ResponseBody
    @PostMapping("add/playlist/{id}")
    public Favorite addFavoriteArtist(@PathVariable("id") Playlist playlist) {
        return null;
    }

}
