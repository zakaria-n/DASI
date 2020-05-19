package fr.insalyon.dasi.metier.service;

import fr.insalyon.dasi.dao.ClientDao;
import fr.insalyon.dasi.dao.ConsultationDao;
import fr.insalyon.dasi.dao.EmployeDao;
import fr.insalyon.dasi.dao.JpaUtil;
import fr.insalyon.dasi.dao.MediumDao;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.modele.ProfilAstral;
import fr.insalyon.dasi.techniques.service.AstroTest;
import fr.insalyon.dasi.techniques.service.Message;
import fr.insalyon.dasi.techniques.service.Statistics;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TimeZone;
import java.util.TreeMap;
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
    protected MediumDao mediumDao = new MediumDao();

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
    
    public Employe rechercherEmployeParMail(String mail) {
        Employe resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = employeDao.chercherParMail(mail);
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service rechercherEmployeParId(id)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    public long authentifierClient(String mail, String motDePasse) {
        long resultat = -1;
        JpaUtil.creerContextePersistance();
        try {
            // Recherche du client
            Client client = clientDao.chercherParMail(mail);
            if (client != null) {
                // Vérification du mot de passe
                if (client.getMotDePasse().equals(motDePasse)) {
                    resultat = client.getId();
                }
            }
        } catch (Exception ex) {
            System.out.println("in the catch");
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service authentifierClient(mail,motDePasse)", ex);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        System.out.println("result" + resultat);
        return resultat;
    }
    
    public long authentifierEmploye(String mail, String motDePasse) 
    {
        long resultat = -1;
        JpaUtil.creerContextePersistance();
        try {
            // Recherche du client
            Employe employe = employeDao.chercherParMail(mail);
            if (employe != null) {
                // Vérification du mot de passe
                if (employe.getMotDePasse().equals(motDePasse)) {
                    resultat = employe.getId();
                }
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service authentifierClient(mail,motDePasse)", ex);
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
    
    public List<Employe> listerEmployes() {
        List<Employe> resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = employeDao.listerEmployes();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service listerClients()", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    public List<Medium> filterMediums(String type) {
        JpaUtil.creerContextePersistance();
        List<Medium> resultat=null;
        try {
            switch(type){
                case "Cartomancien":
                    resultat  = mediumDao.listerCartomanciens();
                    break;
                case "Astrologue" :
                    resultat  = mediumDao.listerAstrologues();
                    break;
                case "Spirite" :
                    resultat  = mediumDao.listerSpirites();
                    break;
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service listerMediums()", ex);
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
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service listerClients()", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    public Medium chercherMedium(String denomination) {
        JpaUtil.creerContextePersistance();
        Medium resultat = null;
        try {
            // Recherche du médium
            Medium medium = mediumDao.chercherParDenomination(denomination);
            if (medium != null) {
                resultat = medium;
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service chercherMedium(String denomination)", ex);
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
    
    public Employe demanderConsultation(Medium choice, Client client) { //identifiant du medium choisi
        Employe result=null;
        try {
            result = choisirEmploye(choice.getGenre());
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service chercherParId()", ex);
            result = null;
        } finally {
        }
        if(result!=null)
        {
            result.setNbConsultations(result.getNbConsultations()+1);
            result.setDisponible(false);
            choice.setNbConsultations(choice.getNbConsultations()+1);
            Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
            Date date = calendar.getTime();
            Consultation consultation = new Consultation(date,null, null, null, client, choice, result);
            creerConsultation(consultation);
            choice.getConsultations().add(consultation);
            result.getConsultations().add(consultation);
            client.getConsultations().add(consultation);
            JpaUtil.creerContextePersistance();
            try {
                JpaUtil.ouvrirTransaction();
                mediumDao.update(choice);
                employeDao.update(result);
                clientDao.update(client);
                JpaUtil.validerTransaction();
            } catch (Exception ex) {
                Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service demanderConsultation(c)");
                JpaUtil.annulerTransaction();
            } finally {
                JpaUtil.fermerContextePersistance();
            }
            Message.envoyerMail("Predictif", result.getMail() , "Nouvelle consultation",
                   "Vous avez une nouvelle consultation où vous devez incarner:"
            + choice.getDenomination() + "\nVotre client est joignable au" +
                           client.getTel());
            
        }else
        {
            Message.envoyerMail("Predictif", client.getMail(), "Demande de consultation rejetée",
                    "Bonjour,"+"\n" + choice.getDenomination() + "n'est pas disponible"
                            + "en ce moment."+ "/n" +"Veuillez réessayer plus tard");
        }
        return result;
    }
    
    public List<String> generatePrediction(Client c, int amour, int sante, int travail) 
    {
        List<String> result = null;
        AstroTest astro = new AstroTest();
        try {
            result=astro.getPredictions(c.getProfil().getCouleurBonheur(),
                    c.getProfil().getAnimalTotem(),amour,sante,travail);
        } catch (IOException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public void ajouterCommentaire(Consultation c, String comment) 
    {
        c.setCommentaire(comment);
    }
    
    public void afficherCommentaire(Consultation c)
    {
        System.out.println(c.getCommentaire());
    }
    
    public void showConsultation(Consultation c) { //this might be front-end stuff? idk
        System.out.println(c.toString());
    }
    
    public void statistics(Statistics stats)
    {
        List<Medium> top5 = null;
        List<Medium> mediumList = null;
        List<Employe> employeList = null;
        JpaUtil.creerContextePersistance();
        try {
            top5 = mediumDao.listerTop5();
            mediumList = mediumDao.listerMediums();
            employeList = employeDao.listerEmployes();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service statistics()", ex);
            top5 = null;
            mediumList = null;
            employeList = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        
        stats.setTop5(top5);
        SortedMap<Integer, Employe> clientsParEmploye = new TreeMap<>();
        SortedMap<Integer, Medium> consultationsParMedium = new TreeMap<>();
        for (Employe e : employeList)
        {
            clientsParEmploye.put(e.getNbConsultations(), e);
        }
        stats.setClientsParEmploye(clientsParEmploye);
        for (Medium m : mediumList)
        {
            consultationsParMedium.put(m.getNbConsultations(), m);
        }
        stats.setConsultationsParMedium(consultationsParMedium);
    } 
    
    public void confirmConsultation(Consultation c) {
        // send text to client, need client and medium for this
        Client client = c.getClient();
        Medium medium = c.getMedium();
        Message.envoyerMail("Predictif", client.getMail(), "Consultation confirmée",
                "Votre consultation est confirmée. Vous allez bientôt  rceevoir un appel"
                        + "de la part de" + medium.getDenomination());
        Calendar cal = Calendar.getInstance();
        Date date=cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String formattedDate=dateFormat.format(date);
        c.setHeureDebut(formattedDate);
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            consultationDao.update(c);
            JpaUtil.validerTransaction();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service confrimConsultation(c)");
            JpaUtil.annulerTransaction();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
    }
   
    public void terminerConsultation(Consultation c) {
        // send text to client, need client and medium for this
        Client client = c.getClient();
        Employe employe = c.getEmploye();
        Message.envoyerMail("Predictif", client.getMail(), "Consultation terminée",
                "Votre consultation est terminée. Merci de votre confiance.");
        Message.envoyerMail("Predictif", employe.getMail(), "Consultation terminée",
                "Vous venez de terminer votre consultation. Veuillez laisser un commentaire"
                        + "pour assister vos collègues dans le futur.");
        Calendar cal = Calendar.getInstance();
        Date date=cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String formattedDate=dateFormat.format(date);
        c.setHeureFin(formattedDate);
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            consultationDao.update(c);
            JpaUtil.validerTransaction();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service confrimConsultation(c)");
            JpaUtil.annulerTransaction();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
    }
    
    public List<Consultation> showClientConsultations(Client c) { 
        List<Consultation> consultations = c.getConsultations();
        return consultations;
    }
    
    public long getCurrentConsultationClient(Employe e) { 
        long clientId = -1;
        List<Consultation> consultations = e.getConsultations();
        for(int i=0; i < consultations.size(); i++) {
            if(consultations.get(i).getHeureDebut()==null) {
                clientId = consultations.get(i).getClient().getId();
                break;
            }
        }
        return clientId;
    }
    
    public void showMediums(List<Medium> mediums) {
        for(int i=0; i < mediums.size(); i++) {
            System.out.println(mediums.get(i).toString());
        }
    }
  
    public ProfilAstral showProfilAstral(Client c) {
        ProfilAstral profile = c.getProfil();
        return profile;
    }

}