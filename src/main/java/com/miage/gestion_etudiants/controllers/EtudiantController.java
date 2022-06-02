package com.miage.gestion_etudiants.controllers;

import com.miage.gestion_etudiants.entities.Etudiant;
import com.miage.gestion_etudiants.repositories.EtudiantRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/Etudiant")
public class EtudiantController {
    private EtudiantRepository etudiantRepository;

    @RequestMapping("/index")
    public String index(Model model,@RequestParam(name="page",defaultValue = "0") int page,
                        @RequestParam(name="page",defaultValue = "") String mc){
        Page<Etudiant> etudiants = etudiantRepository.findAll(PageRequest.of(page,5));
        int pagesCount=etudiants.getTotalPages();
        int[] pages=new int[pagesCount];
        for (int i = 0; i < pagesCount; i++) {
            pages[i]=i;
        }
        model.addAttribute("pages",pages);
        model.addAttribute("PageEtudiants",etudiants);
        model.addAttribute("pageCourante",page);
        return "etudiants";
    }
}
