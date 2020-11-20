package com.ynov.nantes.rest.controller;

import com.ynov.nantes.rest.entity.*;
import com.ynov.nantes.rest.repository.AlbumRepository;
import com.ynov.nantes.rest.repository.FavoriteRepository;
import com.ynov.nantes.rest.repository.PlaylistRepository;
import com.ynov.nantes.rest.repository.SongRepository;
import com.ynov.nantes.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("favorite")
public class FavoriteController {

    @Autowired
    private final FavoriteRepository favoriteRepository;
    @Autowired
    private final SongRepository songRepository;
    @Autowired
    private final AlbumRepository albumRepository;
    @Autowired
    private final PlaylistRepository playlistRepository;
    @Autowired
    private final UserService userService;


    public FavoriteController(FavoriteRepository favoriteRepository, SongRepository songRepository, AlbumRepository albumRepository, PlaylistRepository playlistRepository, UserService userService) {
        this.favoriteRepository = favoriteRepository;
        this.songRepository = songRepository;
        this.albumRepository = albumRepository;
        this.playlistRepository = playlistRepository;
        this.userService = userService;
    }


    @ResponseBody
    @GetMapping("{id}")
    public Favorite getFavoriteByUserId(final @PathVariable("id") Utilisateur user) {
        try {
            return favoriteRepository.findByUserId(user.getId());
        } catch (Exception e) {
            return null;
        }
    }

    @ResponseBody
    @PostMapping("add/song")
    public Favorite addFavoriteSong(@RequestParam("id") Song song) {
        Utilisateur user =  userService.getCurrentUser();
        Favorite favToUpdate = favoriteRepository.findByUserId(user.getId());
        Set<Song> songs  = favToUpdate.getFavoriteSongs();
        Boolean isAlreadyInTheList = false;
        for(Song s : songs) {
            if(s.getId()  == song.getId() && !isAlreadyInTheList) {
                isAlreadyInTheList = true;
            }
        }
        if(!isAlreadyInTheList) {
            Optional<Song> songFound = songRepository.findById(song.getId());
            Song songToAdd = songFound.get();
            songs.add(songToAdd);
            favToUpdate.setFavoriteSongs(songs);
        }

        Favorite saved = favoriteRepository.save(favToUpdate);
        return saved;
    }

    @ResponseBody
    @PostMapping("add/album")
    public Favorite addFavoriteAlbum(@RequestParam("id") Album album) {
        Utilisateur user =  userService.getCurrentUser();
        Favorite favToUpdate = favoriteRepository.findByUserId(user.getId());
        Set<Album> albums  = favToUpdate.getFavoriteAlbums();
        Boolean isAlreadyInTheList = false;
        for(Album a : albums) {
            if(a.getId()  == album.getId() && !isAlreadyInTheList) {
                isAlreadyInTheList = true;
            }
        }
        if(!isAlreadyInTheList) {
            Optional<Album> albumFound = albumRepository.findById(album.getId());
            Album albumToAdd = albumFound.get();
            albums.add(albumToAdd);
            favToUpdate.setFavoriteAlbums(albums);
        }
        return favoriteRepository.save(favToUpdate);
    }


    @ResponseBody
    @PostMapping("add/playlist")
    public Favorite addFavoriteArtist(@RequestBody Playlist playlist) {
        Utilisateur user =  userService.getCurrentUser();
        Favorite favToUpdate = favoriteRepository.findByUserId(user.getId());
        Set<Playlist> playlists  = favToUpdate.getFavoritePlaylists();
        Boolean isAlreadyInTheList = false;
        for(Playlist p : playlists) {
            if(p.getId()  == playlist.getId() && !isAlreadyInTheList) {
                isAlreadyInTheList = true;
            }
        }
        if(!isAlreadyInTheList) {
            Optional<Playlist> playlistFound = playlistRepository.findById(playlist.getId());
            Playlist playlistToAdd = playlistFound.get();
            playlists.add(playlistToAdd);
            favToUpdate.setFavoritePlaylists(playlists);
        }

        Favorite saved = favoriteRepository.save(favToUpdate);
        return saved;
    }


    @ResponseBody
    @PutMapping("{id}")
    public Favorite updateAlbum(@RequestBody Favorite favoriteToUpdate) {
        Favorite updated = favoriteRepository.save(favoriteToUpdate);
        return updated;
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("{id}")
    public HttpStatus deleteAlbum(@PathVariable("id") Favorite favorite) {
        try{
            favoriteRepository.deleteById(favorite.getId());
            return HttpStatus.OK;
        } catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
    }
}
