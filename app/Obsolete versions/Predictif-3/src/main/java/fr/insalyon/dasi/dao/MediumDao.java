/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dao;

import fr.insalyon.dasi.metier.modele.*;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author zakaria
 */
public class MediumDao {
    
    public void creer(Medium medium) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(medium);
    }
    
    public Medium chercherParId(Long mediumId) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.find(Medium.class, mediumId); // renvoie null si l'identifiant n'existe pas
    }
    
    public Medium chercherParDenomination(String denomination) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Medium> query = em.createQuery("SELECT c FROM Medium c WHERE c.mail = :mail", Medium.class);
        query.setParameter("denomination", denomination); // correspond au paramètre ":mail" dans la requête
        List<Medium> mediums = query.getResultList();
        Medium result = null;
        if (!mediums.isEmpty()) {
            result = mediums.get(0); // premier de la liste
        }
        return result;
    }
    
    public List<Medium> chercherParNom(String nom) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Medium> query = em.createQuery("SELECT c FROM Medium c WHERE c.nom = :nom", Medium.class);
        query.setParameter("nom", nom); // correspond au paramètre ":mail" dans la requête
        List<Medium> mediums = query.getResultList();
        
        return mediums;
    }
    
    
    public List<Medium> listerAstrologues() {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Medium> query = em.createQuery("SELECT c FROM medium c ORDER BY c.nom ASC, c.prenom ASC", Medium.class);
        List<Medium> mediums = query.getResultList();
        List<Medium> astros = null;
        for(int i=0; i < mediums.size(); i++) {
            if(mediums.get(i).getClass() == Astrologue.class) {
                astros.add(mediums.get(i));
            }
        }
        return astros;
    }

    public List<Medium> listerCartomanciens() {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Medium> query = em.createQuery("SELECT c FROM medium c ORDER BY c.nom ASC, c.prenom ASC", Medium.class);
        List<Medium> mediums = query.getResultList();
        List<Medium> cartos = null;
        for(int i=0; i < mediums.size(); i++) {
            if(mediums.get(i).getClass() == Cartomancien.class) {
                cartos.add(mediums.get(i));
            }
        }
        return cartos;
    }

    public List<Medium> listerSpirites() {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Medium> query = em.createQuery("SELECT c FROM medium c ORDER BY c.nom ASC, c.prenom ASC", Medium.class);
        List<Medium> mediums = query.getResultList();
        List<Medium> spirites = null;
        for(int i=0; i < mediums.size(); i++) {
            if(mediums.get(i).getClass() == Spirite.class) {
                spirites.add(mediums.get(i));
            }
        }
        return spirites;
    }    
    
    public List<Medium> listerMediums() {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Medium> query = em.createQuery("SELECT c FROM medium c ORDER BY c.nom ASC, c.prenom ASC", Medium.class);
        return query.getResultList();
    }
    
    // modifier / supprimer  ... 
}