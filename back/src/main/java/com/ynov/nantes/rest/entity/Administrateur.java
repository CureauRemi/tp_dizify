package com.ynov.nantes.rest.entity;

import javax.persistence.*;

@Entity
@Table(name = "admin")
public class Administrateur {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne(targetEntity = Utilisateur.class)
    @JoinColumn(name = "user_id")
    private Utilisateur user;



}
