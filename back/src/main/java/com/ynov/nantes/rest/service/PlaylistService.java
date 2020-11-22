package com.ynov.nantes.rest.service;

import com.ynov.nantes.rest.entity.Playlist;
import com.ynov.nantes.rest.entity.Utilisateur;
import com.ynov.nantes.rest.entity.Dto.playlist.GetPlaylistDto;
import com.ynov.nantes.rest.entity.dto.playlist.PlaylistDto;
import com.ynov.nantes.rest.exception.playlist.PlaylistErrorException;
import com.ynov.nantes.rest.exception.playlist.PlaylistNotFoundException;
import com.ynov.nantes.rest.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistService {

    @Autowired
    PlaylistRepository playlistRepository;
    @Autowired
    UserService userService;


    public Page<GetPlaylistDto> getAllPlaylist(Integer page, Integer limit) {
        try {
            PageRequest pageRequest = PageRequest.of(page, limit);
            return playlistRepository.findAll(pageRequest).map(GetPlaylistDto::new);
        } catch (Exception e) {
            throw new PlaylistErrorException(e.getMessage());
        }
    }

    public PlaylistDto getPlaylist(Integer id) {
        try {
            return new PlaylistDto(playlistRepository.getById(id));
        } catch (Exception e) {
            throw new PlaylistErrorException(e.getMessage());
        }
    }

    public List<Playlist> findByName(String name) {
        try {
            return playlistRepository.findByName(name);
        } catch (Exception e) {
            throw new PlaylistNotFoundException();
        }
    }


   // public Playlist addAPlaylist(AddPlaylistDto playlist) {
   //     try{
   //         Utilisateur currentUser = userService.getCurrentUser();
   //         Playlist newPlaylist = new Playlist();
   //         newPlaylist.setUser(currentUser);
   //         newPlaylist.setName(playlist.getName());
   //         return playlistRepository.save(newPlaylist);
   //     } catch (Exception e) {
   //         throw new PlaylistErrorException(e.getMessage() + " - cause : " + e.getCause());
   //     }
    // }

    public Playlist updatePlaylist(Playlist playlist) {
        try{
            return playlistRepository.save(playlist);
        } catch (Exception e) {
            throw new PlaylistErrorException(e.getMessage() + " - cause : " + e.getCause());
        }
    }

    public HttpStatus deletePlaylist(Integer playlistId) {
        try{
            playlistRepository.deleteById(playlistId);
            return HttpStatus.OK;
        } catch (Exception e) {
            throw new PlaylistErrorException(e.getMessage() + " - cause : " + e.getCause());
        }
    }


}
