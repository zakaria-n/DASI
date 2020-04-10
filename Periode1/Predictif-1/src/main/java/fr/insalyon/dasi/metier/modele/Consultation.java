/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.metier.modele;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author sophiecrowley
 */
@Entity(name="Consultation")
public class Consultation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    
    @Temporal(TemporalType.DATE)
    private Date date;
    private String heureDebut;
    private String heureFin;
    private String commentaire;

    protected Consultation() {
    }

    public Consultation(Date date, String heureDebut, String heureFin, String commentaire) {
        this.date = date;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.commentaire = commentaire;
    }

    public Long getId() {
        return id;
    }
   
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(String heureDebut) {
        this.heureDebut = heureDebut;
    }

    public String getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(String heureFin) {
        this.heureFin = heureFin;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    @Override
    public String toString() {
        return "Consultation{" + "id=" + id + ", date=" + date + ", heureDebut=" + heureDebut + ", heureFin=" + heureFin + ", commentaire=" + commentaire + '}';
    }
    
    
}