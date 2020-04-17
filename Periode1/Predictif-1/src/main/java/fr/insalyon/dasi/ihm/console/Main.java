package fr.insalyon.dasi.ihm.console;

import fr.insalyon.dasi.dao.JpaUtil;
import fr.insalyon.dasi.metier.modele.Astrologue;
import fr.insalyon.dasi.metier.modele.Cartomancien;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.modele.Spirite;
import fr.insalyon.dasi.metier.service.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author DASI Team
 */
public class Main {

    public static void main(String[] args) {

        // Contrôlez l'affichage du log de JpaUtil grâce à la méthode log de la classe JpaUtil
        JpaUtil.init();

        initialiserClients();            // Question 3
        initialiserEmployes();
        initialiserMediums();
        testerInscriptionClient();       // Question 4 & 5
        testerRechercheClient();         // Question 6
        testerListeClients();            // Question 7
        testerAuthentificationClient();  // Question 8
       // saisirInscriptionClient();       // Question 9
        saisirRechercheClient();
        testerProfilAstral();
        testerConsultation();
        JpaUtil.destroy();
    }

    public static void afficherClient(Client client) {
        System.out.println("-> " + client);
    }
    
    public static void afficherClientProfil(Client client) {
        System.out.println("-> " + client.getProfil().toString());
    }
    
    public static void afficherEmploye(Employe employe) {
        System.out.println("-> " + employe);
    }
    
    public static void afficherMedium(Medium medium) {
        System.out.println("-> " + medium.toString());
    }
    
    public static void initialiserClients() {
        
        System.out.println();
        System.out.println("**** initialiserClients() ****");
        System.out.println();
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU-TP");
        EntityManager em = emf.createEntityManager();
        
        Date date = new Date(1998, 3, 2);
        Client ada = new Client("Lovelace", "Ada", "ada.lovelace@insa-lyon.fr", "Ada1012", "123456", "male", date, "1");
        Client blaise = new Client("Pascal", "Blaise", "blaise.pascal@insa-lyon.fr", "Blaise1906", "987654", "female", date, "2");
        Client fred = new Client("Fotiadu", "Frédéric", "frederic.fotiadu@insa-lyon.fr", "INSA-Forever", "65738", "nb", date, "3");
        
        System.out.println();
        System.out.println("** Clients avant persistance: ");
        afficherClient(ada);
        afficherClient(blaise);
        afficherClient(fred);
        System.out.println();
        
        Consultation a = new Consultation(date, "1", "3", "hmmm");
        Service service = new Service();
        ada.getConsultations().add(a);
        a = new Consultation(date, "4", "5", "pas de problème");
        blaise.getConsultations().add(a);    
        a = new Consultation(date, "1", "1:30", "n/a");
        fred.getConsultations().add(a);
        
        try {
            em.getTransaction().begin();
            em.persist(ada);
            em.persist(blaise);
            em.persist(fred);
            em.getTransaction().commit();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service", ex);
            try {
                em.getTransaction().rollback();
            }
            catch (IllegalStateException ex2) {
                // Ignorer cette exception...
            }
        } finally {
            em.close();
        }

        System.out.println();
        System.out.println("** Clients après persistance: ");
        afficherClient(ada);
        afficherClient(blaise);
        afficherClient(fred);
        System.out.println();
    }
    
    public static void initialiserEmployes() {
        
        System.out.println();
        System.out.println("**** initialiserEmployes() ****");
        System.out.println();
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU-TP");
        EntityManager em = emf.createEntityManager();
        
        Date date = new Date(1998, 3, 2);
        Employe one = new Employe("A", "Zakaria", "azak@insa-lyon.fr", "111", "123456", "?", true, 0);
        Employe two = new Employe("B", "Zihao", "bzih@insa-lyon.fr", "222", "123457", "??", true, 0);
        Employe three = new Employe("C", "Sophie", "csop@insa-lyon.fr", "333", "123458", "???", true, 0);
        
        Consultation a = new Consultation(date, "1", "3", "great");
        Service service = new Service();
        one.getConsultations().add(a);
        one.setNbConsultations(one.getNbConsultations()+1);
        a = new Consultation(date, "4", "4:50", "ok");
        one.getConsultations().add(a);
        one.setNbConsultations(one.getNbConsultations()+1);
        
        a = new Consultation(date, "1", "1:30", "difficile");
        two.getConsultations().add(a);
        two.setNbConsultations(two.getNbConsultations()+1);
        
        System.out.println();
        System.out.println("** Employes avant persistance: ");
        afficherEmploye(one);
        afficherEmploye(two);
        afficherEmploye(three);
        System.out.println();

        try {
            em.getTransaction().begin();
            em.persist(one);
            em.persist(two);
            em.persist(three);
            em.getTransaction().commit();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service", ex);
            try {
                em.getTransaction().rollback();
            }
            catch (IllegalStateException ex2) {
                // Ignorer cette exception...
            }
        } finally {
            em.close();
        }

        System.out.println();
        System.out.println("** Employes après persistance: ");
        afficherEmploye(one);
        afficherEmploye(two);
        afficherEmploye(three);
        System.out.println();
    }
    
    public static void initialiserMediums() {
        
        System.out.println();
        System.out.println("**** initialiserMediums() ****");
        System.out.println();
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU-TP");
        EntityManager em = emf.createEntityManager();
        
        Medium one = new Spirite("Professeur Tran", "H", "Votre avenir est devant vous : regardons-le ensemble !", 
                "Marc de café, boule de cristal, oreilles de lapin");
        Medium two = new Astrologue("Serena", "F", 
                "Basée à Champigny-sur-Marne, Serena vous révèlera votre avenir pour éclairer votre passé.", 
                "École Normale Supérieure d’Astrologie (ENS-Astro)", 2006);
        Medium three = new Cartomancien("Mme Irna", "F", "Comprenez votre entourage grâce à mes cartes ! Résultats rapides.");
        
        Medium four = new Spirite("Gwenaëlle", "F", "Spécialiste des grandes conversations au-delà de TOUTES les frontières.", 
                "Boule de cristal");
        Medium five = new Astrologue("Mr M", "H", 
                "Avenir, avenir, que nous réserves-tu ? N'attendez plus, demandez à me consulter!", 
                " Institut des Nouveaux Savoirs Astrologiques", 2010);
        Medium six = new Cartomancien("Mme Elle", "F", "Résultats excellentes !");
      
        System.out.println();
        System.out.println("** Mediums avant persistance: ");
        afficherMedium(one);
        afficherMedium(two);
        afficherMedium(three);
        afficherMedium(four);
        afficherMedium(five);
        afficherMedium(six);
        System.out.println();
        
        Date date = new Date(2010, 4, 5);        
        Consultation a = new Consultation(date, "8", "9", "great-ish");
        Service service = new Service();
        one.getConsultations().add(a);
        one.setNbConsultations(one.getNbConsultations()+1);
        a = new Consultation(date, "4", "5", "ok-ish");
        one.getConsultations().add(a);
        one.setNbConsultations(one.getNbConsultations()+1);
        
        a = new Consultation(date, "1", "1:20", "difficile...");
        two.getConsultations().add(a);
        two.setNbConsultations(two.getNbConsultations()+1);

        try {
            em.getTransaction().begin();
            em.persist(one);
            em.persist(two);
            em.persist(three);
            em.persist(four);
            em.persist(five);
            em.persist(six);
            em.getTransaction().commit();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service", ex);
            try {
                em.getTransaction().rollback();
            }
            catch (IllegalStateException ex2) {
                // Ignorer cette exception...
            }
        } finally {
            em.close();
        }

        System.out.println();
        System.out.println("** Mediums après persistance: ");
        afficherMedium(one);
        afficherMedium(two);
        afficherMedium(three);
        afficherMedium(four);
        afficherMedium(five);
        afficherMedium(six);
        System.out.println();
    }

    public static void testerInscriptionClient() {
        
        System.out.println();
        System.out.println("**** testerInscriptionClient() ****");
        System.out.println();
        
        Service service = new Service();
        Date date = new Date(1998, 3, 2);
        Client claude = new Client("Chappe", "Claude", "claude.chappe@insa-lyon.fr", "HaCKeR", "123", "male", date, "Drance 3");
        Long idClaude = service.inscrireClient(claude);
        if (idClaude != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        afficherClient(claude);

         date = new Date(1998, 3, 2);
        Client hedy = new Client("Lamarr", "Hedy", "hlamarr@insa-lyon.fr", 
                "WiFi", "321", "female", date, "france 1");
        Long idHedy = service.inscrireClient(hedy);
        if (idHedy != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        afficherClient(hedy);

        date = new Date(1998, 3, 2);
        Client hedwig = new Client("Lamarr", "Hedwig Eva Maria", 
                "hlamarr@insa-lyon.fr", "WiFi", "333" ,"female",date , "france 2");
        Long idHedwig = service.inscrireClient(hedwig);
        if (idHedwig != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        afficherClient(hedwig);
    }

    public static void testerRechercheClient() {
        
        System.out.println();
        System.out.println("**** testerRechercheClient() ****");
        System.out.println();
        
        Service service = new Service();
        long id;
        Client client;

        id = 1;
        System.out.println("** Recherche du Client #" + id);
        client = service.rechercherClientParId(id);
        if (client != null) {
            afficherClient(client);
        } else {
            System.out.println("=> Client non-trouvé");
        }

        id = 3;
        System.out.println("** Recherche du Client #" + id);
        client = service.rechercherClientParId(id);
        if (client != null) {
            afficherClient(client);
        } else {
            System.out.println("=> Client non-trouvé");
        }

        id = 17;
        System.out.println("** Recherche du Client #" + id);
        client = service.rechercherClientParId(id);
        if (client != null) {
            afficherClient(client);
        } else {
            System.out.println("=> Client #" + id + " non-trouvé");
        }
    }
    
        public static void testerProfilAstral() {
        
        System.out.println();
        System.out.println("**** testerProfilAstral() ****");
        System.out.println();
        
        Service service = new Service();
        long id;
        Client client;

        id = 1;
        System.out.println("** Profil Astral du Client #" + id);
        client = service.rechercherClientParId(id);
        if (client != null && client.getProfil() != null) {
            afficherClient(client);
        } else {
            System.out.println("=> Profil Astral non-trouvé");
        }

        id = 3;
        System.out.println("** Profil Astral du Client #" + id);
        client = service.rechercherClientParId(id);
        if (client != null && client.getProfil() != null) {
            afficherClient(client);
        } else {
            System.out.println("=> Profil Astral non-trouvé");
        }

        id = 17;
        System.out.println("** Profil Astral du Client #" + id);
        client = service.rechercherClientParId(id);
        if (client != null && client.getProfil() != null) {
            afficherClient(client);
        } else {
            System.out.println("=> Profil Astral #" + id + " non-trouvé");
        }
    }

    public static void testerListeClients() {
        
        System.out.println();
        System.out.println("**** testerListeClients() ****");
        System.out.println();
        
        Service service = new Service();
        List<Client> listeClients = service.listerClients();
        System.out.println("*** Liste des Clients");
        if (listeClients != null) {
            for (Client client : listeClients) {
                afficherClient(client);
            }
        }
        else {
            System.out.println("=> ERREUR...");
        }
    }

    public static void testerAuthentificationClient() {
        
        System.out.println();
        System.out.println("**** testerAuthentificationClient() ****");
        System.out.println();
        
        Service service = new Service();
        Client client;
        String mail;
        String motDePasse;

        mail = "ada.lovelace@insa-lyon.fr";
        motDePasse = "Ada1012";
        client = service.authentifierClient(mail, motDePasse);
        if (client != null) {
            System.out.println("Authentification réussie avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
            afficherClient(client);
        } else {
            System.out.println("Authentification échouée avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
        }

        mail = "ada.lovelace@insa-lyon.fr";
        motDePasse = "Ada2020";
        client = service.authentifierClient(mail, motDePasse);
        if (client != null) {
            System.out.println("Authentification réussie avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
            afficherClient(client);
        } else {
            System.out.println("Authentification échouée avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
        }

        mail = "etudiant.fictif@insa-lyon.fr";
        motDePasse = "********";
        client = service.authentifierClient(mail, motDePasse);
        if (client != null) {
            System.out.println("Authentification réussie avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
            afficherClient(client);
        } else {
            System.out.println("Authentification échouée avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
        }
    }
    
        public static void testerConsultation() {
        System.out.println();
        System.out.println("**** testerConsultation() ****");
        System.out.println();
        
        Service service = new Service();
        long id = 1;
        Client client;
        client = service.rechercherClientParId(id);
        Employe employe;
        employe = service.rechercherEmployeParId(id);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date2=null;
        try {
            date2 = dateFormat.parse("01-10-2010");
        } catch (ParseException e) {
            
        }
        Consultation c = new Consultation(date2, "1", "3", "great");
        client.getConsultations().add(c);
        System.out.println(client.getConsultations().get(0).getCommentaire());
        c = new Consultation(date2, "3", "4:30", "not amazing");
        employe.getConsultations().add(c);
        System.out.println(employe.getConsultations().get(0).getCommentaire());
        int input = Saisie.lireInteger("[0 pour quitter] ");
        if(input!=0) {
            System.out.println("Quitting anyway sorry...");
        }
    }

    public static void saisirInscriptionClient() {
        Service service = new Service();

        System.out.println();
        System.out.println("Appuyer sur Entrée pour passer la pause...");
        Saisie.pause();

        System.out.println();
        System.out.println("**************************");
        System.out.println("** NOUVELLE INSCRIPTION **");
        System.out.println("**************************");
        System.out.println();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date2=null;
      
        String nom = Saisie.lireChaine("Nom ? ");
        String prenom = Saisie.lireChaine("Prénom ? ");
        String mail = Saisie.lireChaine("Mail ? ");
        String motDePasse = Saisie.lireChaine("Mot de passe ? ");
        String telephone = Saisie.lireChaine("Telephone ? ");
        String genre = Saisie.lireChaine("Genre ? ");
        String dateInput = Saisie.lireChaine("Date ? ");
        try {
            date2 = dateFormat.parse(dateInput);
        } catch (ParseException e) {
            
        }
        String adresse = Saisie.lireChaine("Adresse ? ");

        Client client = new Client(nom, prenom, mail, motDePasse, telephone, genre, date2, adresse);
        Long idClient = service.inscrireClient(client);

        if (idClient != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        afficherClient(client);

    }
    
    public static void saisirConnexionClient() {
        Service service = new Service();

        System.out.println();
        System.out.println("Appuyer sur Entrée pour passer la pause...");
        Saisie.pause();

        System.out.println();
        System.out.println("**************************");
        System.out.println("** NOUVELLE CONNEXION **");
        System.out.println("**************************");
        System.out.println();

        String mail = Saisie.lireChaine("Mail ? ");
        String motDePasse = Saisie.lireChaine("Mot de passe ? ");

        Client client = service.authentifierClient(mail, motDePasse);

        if (client != null) {
            System.out.println("> Succès connexion");
        } else {
            System.out.println("> Échec connexion");
        }
        afficherClient(client);

    }

    public static void saisirRechercheClient() {
        Service service = new Service();

        System.out.println();
        System.out.println("*********************");
        System.out.println("** MENU INTERACTIF **");
        System.out.println("*********************");
        System.out.println();

        Saisie.pause();

        System.out.println();
        System.out.println("**************************");
        System.out.println("** RECHERCHE de CLIENTS **");
        System.out.println("**************************");
        System.out.println();
        System.out.println();
        System.out.println("** Recherche par Identifiant:");
        System.out.println();

        Integer idClient = Saisie.lireInteger("Identifiant ? [0 pour quitter] ");
        while (idClient != 0) {
            Client client = service.rechercherClientParId(idClient.longValue());
            if (client != null) {
                afficherClient(client);
            } else {
                System.out.println("=> Client #" + idClient + " non-trouvé");
            }
            System.out.println();
            idClient = Saisie.lireInteger("Identifiant ? [0 pour quitter] ");
        }

        System.out.println();
        System.out.println("********************************");
        System.out.println("** AUTHENTIFICATION de CLIENT **");
        System.out.println("********************************");
        System.out.println();
        System.out.println();
        System.out.println("** Authentifier Client:");
        System.out.println();

        String clientMail = Saisie.lireChaine("Mail ? [0 pour quitter] ");

        while (!clientMail.equals("0")) {
            String clientMotDePasse = Saisie.lireChaine("Mot de passe ? ");
            Client client = service.authentifierClient(clientMail, clientMotDePasse);
            if (client != null) {
                afficherClient(client);
            } else {
                System.out.println("=> Client non-authentifié");
            }
            clientMail = Saisie.lireChaine("Mail ? [0 pour quitter] ");
        }

        System.out.println();
        System.out.println("*****************");
        System.out.println("** AU REVOIR ! **");
        System.out.println("*****************");
        System.out.println();

    }
}
