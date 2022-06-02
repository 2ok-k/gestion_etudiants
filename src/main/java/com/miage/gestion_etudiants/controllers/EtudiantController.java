package com.miage.gestion_etudiants.controllers;

import com.miage.gestion_etudiants.entities.Etudiant;
import com.miage.gestion_etudiants.repositories.EtudiantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/Etudiant")
public class EtudiantController {
    private EtudiantRepository etudiantRepository;

    @RequestMapping("/index")
    public String index(Model model){
        List<Etudiant> etudiants = etudiantRepository.findAll();
        model.addAttribute("etudiants",etudiants);
        return "etudiants";
    }
}
