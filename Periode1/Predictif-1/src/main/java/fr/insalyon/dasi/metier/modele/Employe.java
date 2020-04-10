package fr.insalyon.dasi.metier.modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 *
 * @author DASI Team
 */
@Entity(name="Employe")
public class Employe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    @Column(unique = true)
    private String mail;
    private String motDePasse;
    private String telephone;
    private String genre;
    private boolean disponible;
    private String nbConsultations;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "employe_id")
    private List<Consultation> consultations; 
    
    protected Employe() {
    }

    public Employe(String nom, String prenom, String mail, String motDePasse, 
            String telephone, String genre, boolean disponible, String nbConsultations) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.motDePasse = motDePasse;
        this.telephone = telephone;
        this.genre = genre;
        this.disponible = disponible;
        this.nbConsultations = nbConsultations;
        this.consultations = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
    
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getNbConsultations() {
        return nbConsultations;
    }

    public void setNbConsultations(String nbConsultations) {
        this.nbConsultations = nbConsultations;
    }

    public List<Consultation> getConsultations() {
        return consultations;
    }

    public void setConsultations(List<Consultation> consultations) {
        this.consultations = consultations;
    }
    

    @Override
    public String toString() {
        return "Client : id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", "
                + "mail=" + mail + ", motDePasse=" + motDePasse + ", téléphone=" 
                + telephone + ", genre=" + genre + ", disponible=" + disponible + 
                ", nbConsultations=" + nbConsultations;
    }
    

}
