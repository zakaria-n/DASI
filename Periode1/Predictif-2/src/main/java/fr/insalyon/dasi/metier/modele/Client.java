package fr.insalyon.dasi.metier.modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author DASI Team
 */
@Entity
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    @Column(unique = true)
    private String mail;
    private String motDePasse;
    private String tel;
    private String genre;
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    private String adresse;
    @Embedded
    private ProfilAstral profil;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "employe_id")
    private List<Consultation> consultations;

    protected Client() {
    }

    public Client(String nom, String prenom, String mail, String motDePasse, 
            String tel, String genre, Date dateNaissance, String adresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.motDePasse = motDePasse;
        this.tel=tel;
        this.genre=genre;
        this.dateNaissance=dateNaissance;
        this.adresse=adresse;
        profil=new ProfilAstral(nom,dateNaissance);
        this.consultations=new ArrayList<>();
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

    public String getTel() {
        return tel;
    }

    public String getGenre() {
        return genre;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public ProfilAstral getProfil() {
        return profil;
    }

    public List<Consultation> getConsultations() {
        return consultations;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setProfil(ProfilAstral profil) {
        this.profil = profil;
    }

    public void setConsultations(List<Consultation> consultations) {
        this.consultations = consultations;
    }
    
    
    @Override
    public String toString() {
        return "Client : id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", "
                + "mail=" + mail + ", motDePasse=" + motDePasse + ", téléphone=" 
                + tel + ", genre=" + genre + ", date de naissance=" + dateNaissance 
                + ", adresse="+ adresse;
    }
    

}
