/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dao;

import fr.insalyon.dasi.metier.modele.Astrologue;
import fr.insalyon.dasi.metier.modele.Cartomancien;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.modele.Spirite;
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
    
    public void update(Medium medium)
    {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.merge(medium);
    }
    
    public void supprimer(Medium medium) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.remove(medium);
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
    
    public List<Medium> listerMediums() {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Medium> query = em.createQuery("SELECT m FROM Medium m", Medium.class);
        return query.getResultList();
    }
    
    public List<Medium> listerAstrologues() {
        MediumDao dao=new MediumDao();
        List<Medium> mediums = dao.listerMediums();
        List<Medium> astros = null;
        for(int i=0; i < mediums.size(); i++) {
            if(mediums.get(i) instanceof Astrologue) {
                astros.add((Astrologue) mediums.get(i));
            }
        }
        return astros;
    }

    public List<Medium> listerCartomanciens() {
        MediumDao dao=new MediumDao();
        List<Medium> mediums = dao.listerMediums();
        List<Medium> cartos = null;
        for(int i=0; i < mediums.size(); i++) {
            if(mediums.get(i) instanceof Cartomancien) {
                cartos.add((Cartomancien) mediums.get(i));
            }
        }
        return cartos;
    }

    public List<Medium> listerSpirites() {
        MediumDao dao=new MediumDao();
        List<Medium> mediums = dao.listerMediums();
        List<Medium> spirites = null;
        for(int i=0; i < mediums.size(); i++) {
            if(mediums.get(i) instanceof Spirite) {
                spirites.add((Spirite) mediums.get(i));
            }
        }
        return spirites;
    }
    
    public List<Medium> listerTop5() {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Medium> query = em.createQuery("SELECT m FROM medium m ORDER BY nbConsultations DESC"
                + "FETCH FIRST 5 ROWS ONLY", Medium.class);
        return query.getResultList();              
    }
    
    // modifier / supprimer  ... 
}