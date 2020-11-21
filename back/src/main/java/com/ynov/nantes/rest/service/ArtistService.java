package com.ynov.nantes.rest.service;

import com.ynov.nantes.rest.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtistService {

    @Autowired
    ArtistRepository artistRepository;



}
