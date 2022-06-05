package com.miage.gestion_etudiants.controllers;

import com.miage.gestion_etudiants.entities.Etudiant;
import com.miage.gestion_etudiants.repositories.EtudiantRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Controller
@RequestMapping("/Etudiant")
public class EtudiantController {
    @Autowired
    private EtudiantRepository etudiantRepository;

    @Value("${dir.images}") //Injecter une propriété
    private String imageDir;

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
    //MultipartFile (pour les fichiers
    public String SaveEtudiant(@Valid Etudiant etudiant, BindingResult bindingResult,@RequestParam(name = "picture")MultipartFile file) throws IOException {
        if (bindingResult.hasErrors()) {
            return "formEtudiant";
        }
        if (!(file.isEmpty())) {
            etudiant.setPhoto(file.getOriginalFilename());//Je veux stocker que le nom de la photo dans la base de données
        }
        etudiantRepository.save(etudiant);
        //S'il y a un fichier qui à été envoyé
        if (!(file.isEmpty())) {
            //file.transferTo(new File(""));
            etudiant.setPhoto(file.getOriginalFilename());//Je veux stocker que le nom de la photo dans la base de données
            file.transferTo(new File(imageDir+etudiant.getId())); //On le transfert quelque part dans le système de fichiers
        }
        return "redirect:index";
    }

    //Pour afficher la photo sur la vue
    @RequestMapping(value = "/getPhoto",produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] getPhoto(Long id) throws IOException {
        File file = new File(imageDir+id);
        return IOUtils.toByteArray(new FileInputStream(file));
    }

    @RequestMapping("/supprimer")
    public String supprimer(Long id) {
        etudiantRepository.deleteById(id);
        return "redirect:index";
    }

    @RequestMapping("/edit")
    public String edit(Model model,Long id){
        Etudiant etudiant = etudiantRepository.getOne(id);
        model.addAttribute("etudiant",etudiant);
        return "EditEtudiant";
    }

    @RequestMapping(value = "/UpdateEtudiant",method = RequestMethod.POST)
    //MultipartFile (pour les fichiers
    public String UpdateEtudiant(@Valid Etudiant etudiant, BindingResult bindingResult,@RequestParam(name = "picture")MultipartFile file) throws IOException {
        if (bindingResult.hasErrors()) {
            return "EditEtudiant";
        }
        if (!(file.isEmpty())) {
            etudiant.setPhoto(file.getOriginalFilename());//Je veux stocker que le nom de la photo dans la base de données
        }
        etudiantRepository.save(etudiant);
        //S'il y a un fichier qui à été envoyé
        if (!(file.isEmpty())) {
            //file.transferTo(new File(""));
            etudiant.setPhoto(file.getOriginalFilename());//Je veux stocker que le nom de la photo dans la base de données
            file.transferTo(new File(imageDir+etudiant.getId())); //On le transfert quelque part dans le système de fichiers
        }
        return "redirect:index";
    }
}
