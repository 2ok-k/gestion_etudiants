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
import org.springframework.web.bind.annotation.RequestMethod;
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
                        @RequestParam(name="motCle",defaultValue = "") String mc){
        Page<Etudiant> etudiants = etudiantRepository.chercherEtudiants("%"+mc+"%",PageRequest.of(page,5));
        int pagesCount=etudiants.getTotalPages();
        int[] pages=new int[pagesCount];
        for (int i = 0; i < pagesCount; i++) {
            pages[i]=i;
        }
        model.addAttribute("pages",pages);
        model.addAttribute("PageEtudiants",etudiants);
        model.addAttribute("pageCourante",page);
        model.addAttribute("motCle",mc);
        return "etudiants";
    }

    @RequestMapping(value = "/form",method = RequestMethod.GET)
    public String formEtudiant(Model model){
        model.addAttribute("etudiant",new Etudiant());
        return "formEtudiant";
    }

    @RequestMapping(value = "/SaveEtudiant",method = RequestMethod.POST)
    public String SaveEtudiant(Model model,Etudiant etudiant){
        etudiantRepository.save(etudiant);
        return "formEtudiant";
    }
}
