package com.miage.gestion_etudiants;

import com.miage.gestion_etudiants.entities.Etudiant;
import com.miage.gestion_etudiants.repositories.EtudiantRepository;
import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class GestionEtudiantsApplication {

    @SneakyThrows
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(GestionEtudiantsApplication.class, args);
        EtudiantRepository etudiantRepository = ctx.getBean(EtudiantRepository.class);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        etudiantRepository.save(
                new Etudiant(null,"Mohammed",df.parse("1998-03-07"),"mohamed@gmail.com","mohamed.jpg")
        );
        etudiantRepository.save(
                new Etudiant(null,"Ibrahim",df.parse("1997-05-05"),"ibrahim@gmail.com","ibrahim.jpg")
        );
        etudiantRepository.save(
                new Etudiant(null,"Moussa",df.parse("1999-05-10"),"moussa@gmail.com","moussa.jpg")
        );

        Page<Etudiant> etudiants = etudiantRepository.chercherEtudiants("%i%",PageRequest.of(0,5));
        etudiants.forEach(etudiant -> System.out.println(etudiant.getNom()));
    }

}
