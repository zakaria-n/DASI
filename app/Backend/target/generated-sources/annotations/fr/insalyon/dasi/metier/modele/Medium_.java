package fr.insalyon.dasi.metier.modele;

import fr.insalyon.dasi.metier.modele.Consultation;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-30T13:07:47")
@StaticMetamodel(Medium.class)
public abstract class Medium_ { 

    public static volatile SingularAttribute<Medium, String> presentation;
    public static volatile SingularAttribute<Medium, Integer> nbConsultations;
    public static volatile SingularAttribute<Medium, String> genre;
    public static volatile SingularAttribute<Medium, Long> id;
    public static volatile ListAttribute<Medium, Consultation> consultations;
    public static volatile SingularAttribute<Medium, String> denomination;

}