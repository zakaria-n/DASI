package fr.insalyon.dasi.metier.modele;

import fr.insalyon.dasi.metier.modele.Consultation;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-30T13:25:14")
@StaticMetamodel(Employe.class)
public class Employe_ { 

    public static volatile SingularAttribute<Employe, String> motDePasse;
    public static volatile SingularAttribute<Employe, Integer> nbConsultations;
    public static volatile SingularAttribute<Employe, String> mail;
    public static volatile SingularAttribute<Employe, String> genre;
    public static volatile SingularAttribute<Employe, String> telephone;
    public static volatile SingularAttribute<Employe, Long> id;
    public static volatile SingularAttribute<Employe, String> nom;
    public static volatile SingularAttribute<Employe, String> prenom;
    public static volatile ListAttribute<Employe, Consultation> consultations;
    public static volatile SingularAttribute<Employe, Boolean> disponible;

}