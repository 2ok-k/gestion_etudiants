package com.miage.gestion_etudiants.controllers;

import com.miage.gestion_etudiants.repositories.EtudiantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class EtudiantController {
    private EtudiantRepository etudiantRepository;

    @GetMapping("/index")
    public String index(){
        return "etudiants";
    }
}
