package com.ynov.nantes.rest.controller;

import com.ynov.nantes.rest.entity.*;
import com.ynov.nantes.rest.service.FavoriteService;
import com.ynov.nantes.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
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
    @PostMapping("add/artist/{id}")
    public Favorite addFavoriteArtist(@PathVariable("id") Integer idArtist) {
        return favoriteService.addFavoriteArtistToUser(idArtist) ;
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("{id}")
    public HttpStatus deleteFavorite(@PathVariable("id") Integer id){
        return favoriteService.deleteFavorite(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("delete/song/{id}")
    public HttpStatus deleteSongFromFavorite(@PathVariable("id") Integer id){
        return favoriteService.deleteFavoriteSongToUser(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("delete/album/{id}")
    public HttpStatus deleteAlbumFromFavorite(@PathVariable("id") Integer id){
        return favoriteService.deleteFavoriteAlbumToUser(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("delete/artist/{id}")
    public HttpStatus deleteArtistFromFavorite(@PathVariable("id") Integer id){
        return favoriteService.deleteFavoriteArtistToUser(id);
    }

}
