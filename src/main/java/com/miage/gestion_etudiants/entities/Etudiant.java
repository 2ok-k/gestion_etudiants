package com.miage.gestion_etudiants.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Etudiant implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String nom;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateNaissance;
    @NotEmpty
    @Email(message = "Il doit etre de type example@gmail.com")
    private String email;
    private String photo;
    //private byte[] photo;
}
