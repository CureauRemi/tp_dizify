package com.ynov.nantes.rest.entity.mapper;

import com.ynov.nantes.rest.entity.dto.utilisateur.UtilisateurOut;
import com.ynov.nantes.rest.entity.Utilisateur;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
public class UtilisateurMapper extends ConfigurableMapper {

    protected void configure(MapperFactory factory) {
        factory.classMap(Utilisateur.class, UtilisateurOut.class)
                .field("id", "id")
                .field("email", "email")
                .field("pseudo", "pseudo")
                .byDefault()
                .register();
    }
}

