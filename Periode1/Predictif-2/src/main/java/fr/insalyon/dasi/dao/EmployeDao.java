package fr.insalyon.dasi.dao;

import fr.insalyon.dasi.metier.modele.Employe;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author DASI Team
 */
public class EmployeDao {
    
    public void creer(Employe employe) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(employe);
    }
    
    public void update(Employe employe)
    {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.merge(employe);
    }
    
    public Employe chercherParId(Long employeId) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.find(Employe.class, employeId); // renvoie null si l'identifiant n'existe pas
    }
    
    public Employe chercherParMail(String employeMail) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Employe> query = em.createQuery("SELECT e FROM Employe e WHERE e.mail = :mail", Employe.class);
        query.setParameter("mail", employeMail); // correspond au paramètre ":mail" dans la requête
        List<Employe> Employes = query.getResultList();
        Employe result = null;
        if (!Employes.isEmpty()) {
            result = Employes.get(0); // premier de la liste
        }
        return result;
    }
    
    public List<Employe> listerEmployes() {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Employe> query = em.createQuery("SELECT e FROM Employe e ORDER BY e.nom ASC, e.prenom ASC", Employe.class);
        return query.getResultList();
    }
    
    public Employe chercherParGenre(String genre) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Employe> query = em.createQuery("SELECT e FROM Employe e WHERE e.genre = :genre AND e.disponible=true ORDER BY e.nbConsultations DESC", Employe.class);
        query.setParameter("genre", genre); // correspond au paramètre ":genre" dans la requête
        List<Employe> Employes = query.getResultList();
        Employe result = null;
        if (!Employes.isEmpty()) {
            result = Employes.get(0); // premier de la liste
        }
        return result;
    }
    
}
