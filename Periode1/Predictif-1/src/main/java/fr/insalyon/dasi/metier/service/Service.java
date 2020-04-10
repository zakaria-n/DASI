package fr.insalyon.dasi.metier.service;

import fr.insalyon.dasi.technique.service.Message;
import fr.insalyon.dasi.dao.ClientDao;
import fr.insalyon.dasi.dao.ConsultationDao;
import fr.insalyon.dasi.dao.EmployeDao;
import fr.insalyon.dasi.dao.JpaUtil;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Employe;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DASI Team
 */
public class Service {

    protected ClientDao clientDao = new ClientDao();
    protected EmployeDao employeDao = new EmployeDao();
    protected ConsultationDao consultationDao = new ConsultationDao();
    
    public Long inscrireClient(Client client) {
        Long resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            clientDao.creer(client);
            JpaUtil.validerTransaction();
            resultat = client.getId();
            Message.envoyerMail("Predictif", client.getMail(), "Inscription réussie", "yay");
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service inscrireClient(client)");
            JpaUtil.annulerTransaction();
            resultat = null;
            Message.envoyerMail("Predictif", client.getMail(), "Inscription refusée", "rip");
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    public Long creerConsultation(Consultation c) {
        Long resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            consultationDao.creer(c);
            JpaUtil.validerTransaction();
            resultat = c.getId();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service creerConsultation(c)");
            JpaUtil.annulerTransaction();
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }

    public Client rechercherClientParId(Long id) {
        Client resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = clientDao.chercherParId(id);
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service rechercherClientParId(id)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    public Employe rechercherEmployeParId(Long id) {
        Employe resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = employeDao.chercherParId(id);
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service rechercherEmployeParId(id)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }

    public Client authentifierClient(String mail, String motDePasse) {
        Client resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            // Recherche du client
            Client client = clientDao.chercherParMail(mail);
            if (client != null) {
                // Vérification du mot de passe
                if (client.getMotDePasse().equals(motDePasse)) {
                    resultat = client;
                }
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service authentifierClient(mail,motDePasse)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }

    public List<Client> listerClients() {
        List<Client> resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = clientDao.listerClients();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service listerClients()", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }

}
