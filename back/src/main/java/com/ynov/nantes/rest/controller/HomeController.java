package com.ynov.nantes.rest.controller;


import com.ynov.nantes.rest.entity.Album;
import com.ynov.nantes.rest.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @RestController
// @RequestMapping("home")
public class HomeController {

//    @Autowired
//    private final AlbumRepository albumRepository;
//
//
//    public HomeController(AlbumRepository albumRepository) {
//        this.albumRepository = albumRepository;
//    }
//    @ResponseBody
//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping
//    public List<Album> getBestAlbums() {
//        List<Album> bestAlbums = albumRepository.findBestAlbums();
//        if(bestAlbums.size() != 0){
//            return bestAlbums;
//        } else {
//            return null;
//        }
//    }


}
