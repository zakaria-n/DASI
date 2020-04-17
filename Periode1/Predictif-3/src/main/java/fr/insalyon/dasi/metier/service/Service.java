package fr.insalyon.dasi.metier.service;

import fr.insalyon.dasi.dao.*;
import fr.insalyon.dasi.metier.modele.*;
import fr.insalyon.dasi.techniques.service.Message;
import java.time.ZoneId;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DASI Team
 */
public class Service {

    protected ClientDao clientDao = new ClientDao();
    protected MediumDao mediumDao = new MediumDao();
    protected EmployeDao employeDao = new EmployeDao();
    
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
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service inscrireClient(client)", ex);
            JpaUtil.annulerTransaction();
            resultat = null;
            Message.envoyerMail("Predictif", client.getMail(), "Inscription refusée", "rip");
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    public Long inscrireEmploye(Employe employe) {
        Long resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            employeDao.creer(employe);
            JpaUtil.validerTransaction();
            resultat = employe.getId();
            //Message.envoyerMail("Predictif", client.getMail(), "Inscription réussie", "yay");
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service inscrireClient(client)", ex);
            JpaUtil.annulerTransaction();
            resultat = null;
            //Message.envoyerMail("Predictif", client.getMail(), "Inscription refusée", "rip");
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
    
    public List<Medium> listerMediums() {
        List<Medium> resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = mediumDao.listerMediums();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service listerMediums()", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    public List<Medium> filterMediums(String type) {
        List<Medium> resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            switch(type){
                case "Cartomancien":
                    resultat = mediumDao.listerCartomanciens();
                    break;
                case "Astrologue" :
                    resultat = mediumDao.listerAstrologues();
                    break;
                case "Spirite" :
                    resultat = mediumDao.listerSpirites();
                    break;
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service FilterMediums()", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    public List<Medium> chercherMediums(String nom) { //nom de medium
        List<Medium> resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = mediumDao.chercherParNom(nom);
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service ChercherMediums()", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    public Employe choisirEmploye(String genre){
        Employe resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = employeDao.chercherParGenre(genre);
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service choisirEmploye()", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    /*public Employe demanderConsultation(Long mediumId, Long clientId) { //identifiant du medium choisi
        Medium choice = null;
        Employe result=null;
        Client client=clientDao.chercherParId(clientId);
        JpaUtil.creerContextePersistance();
        try {
            choice = mediumDao.chercherParId(mediumId);
            result = choisirEmploye(choice.getGenre());
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service chercherParId()", ex);
            result = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        if(result!=null)
        {
            result.setNbConsultations(result.getNbConsultations()+1);
            result.setDisponible(false);
            choice.setNbConsultations(choice.getNbConsultations()+1);
            Date debut = new Date();
            debut.toInstant().atZone(ZoneId.systemDefault()).getHour();
            Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
            Date date = calendar.getTime();
            Consultation consultation = new Consultation(date,debut.toString(), null, null );
            choice.addConsultations(consultation);
            result.addConsultations(consultation);
            Message.envoyerMail("Predictif", result.getMail() , "Nouvelle consultation",
                   "Vous avez une nouvelle consultation où vous devez incarner:"
            + choice.getDenomination());
            
        }else
        {
            Message.envoyerMail("Predictif", client.getMail(), "Demande de consultation rejetée",
                    "Bonjour,"+"/n" + choice.getDenomination() + "n'est pas disponible"
                            + "en ce moment."+ "/n" +"Veuillez réessayer plus tard");
        }
        return result;
    }*/
    
    //public void confirmerConsultation()
}