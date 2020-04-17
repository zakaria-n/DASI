package fr.insalyon.dasi.ihm.console;

import fr.insalyon.dasi.dao.JpaUtil;
import fr.insalyon.dasi.metier.modele.*;
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

        // TODO : Pensez à créer une unité de persistance "DASI-PU" et à vérifier son nom dans la classe JpaUtil
        // Contrôlez l'affichage du log de JpaUtil grâce à la méthode log de la classe JpaUtil
        JpaUtil.init();

        /*initialiserClients();            // Question 3
        testerInscriptionClient();       // Question 4 & 5
        testerRechercheClient();         // Question 6
        testerListeClients();            // Question 7
        testerAuthentificationClient();  // Question 8
        saisirInscriptionClient();       // Question 9
        saisirRechercheClient();*/
        
        testerEmployeServices();
        testerMediumServices();
        JpaUtil.destroy();
    }

    public static void testerEmployeServices(){
        Service service = new Service();
        System.out.println();
        System.out.println("*************");
        System.out.println("** employe **");
        System.out.println("*************");
        System.out.println();
        Employe a = new Employe("Chappe", "Claude", "claude.chappe@insa-lyon.fr",
                "123456","1234567890","H",true,0);
        Employe b = new Employe("Tadokoro", "Koji", "tdkrkj@inm.jp",
                "114514","1919810","F",true,1919);
        Employe c = new Employe("Le Stylo", "Marie", "mls@mls.fr",
                "114514","1919810","F",true,2);
        Employe d = new Employe("Bernard", "Claude", "claude.bernard@insa-lyon.fr",
                "123456","1234567890","H",false,0);
        service.inscrireEmploye(a);
        service.inscrireEmploye(b);
        service.inscrireEmploye(c);
        service.inscrireEmploye(d);
        System.out.println(service.choisirEmploye("H"));// devrait afficher chappe
        System.out.println(service.choisirEmploye("F"));// devrait afficher le stylo
    }
    
    public static void testerMediumServices(){
        Service service = new Service();
        System.out.println();
        System.out.println("************");
        System.out.println("** medium **");
        System.out.println("************");
        System.out.println();
       
        System.out.println();
        System.out.println("**Lister TOUT**");
        System.out.println();
        List<Medium> ls1 = service.listerMediums();
        for(Medium a : ls1)
        System.out.println(a);
        
        System.out.println();
        System.out.println("**Filtrer les cartos**");
        System.out.println();
        List<Medium> ls2 = service.filterMediums("Cartomancien");
        for(Medium a : ls2)
        System.out.println(a);
        
        System.out.println();
        System.out.println("**Chercher par nom**");
        System.out.println();
        String nom = Saisie.lireChaine("son nom?");
        Medium ls3 = service.chercherMedium(nom);
        System.out.println(ls3);
    }
    
    public static void afficherClient(Client client) {
        System.out.println("-> " + client);
    }

    public static void initialiserClients() {
        
        System.out.println();
        System.out.println("**** initialiserClients() ****");
        System.out.println();
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU-TP");
        EntityManager em = emf.createEntityManager();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date=null;
        try {
            date = dateFormat.parse("1996-10-10");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Client ada = new Client("Lovelace", "Ada", "ada.lovelace@insa-lyon.fr", 
                "Ada1012","0612345678","F",date,"20 Av GB");
        Client blaise = new Client("Pascal", "Blaise", "blaise.pascal@insa-lyon.fr"
                , "Blaise1906","0765123478","H",date,"20 Av AE");
        Client fred = new Client("Fotiadu", "Frédéric", "frederic.fotiadu@insa-lyon.fr", 
                "INSA-Forever","0522367898","H",date,"21 Av AB");
        
        System.out.println();
        System.out.println("** Clients avant persistance: ");
        afficherClient(ada);
        afficherClient(blaise);
        afficherClient(fred);
        System.out.println();

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

    public static void testerInscriptionClient() {
        
        System.out.println();
        System.out.println("**** testerInscriptionClient() ****");
        System.out.println();
        
        Service service = new Service();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date=null;
        try {
            date = dateFormat.parse("1996-10-10");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Client claude = new Client("Chappe", "Claude", "claude.chappe@insa-lyon.fr",
                "HaCKeR","0678981234","H",date,"23 Av HB");
        Long idClaude = service.inscrireClient(claude);
        if (idClaude != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        afficherClient(claude);

        Client hedy = new Client("Lamarr", "Hedy", "hlamarr@insa-lyon.fr", "WiFi",
                "078912833","H",date, "24 Av Irene");
        Long idHedy = service.inscrireClient(hedy);
        if (idHedy != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        afficherClient(hedy);

        Client hedwig = new Client("Lamarr", "Hedwig Eva Maria", "hlamarr@insa-lyon.fr",
                "WiFi","0987123456","F",date,"5 Av Yeri");
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

        String nom = Saisie.lireChaine("Nom ? ");
        String prenom = Saisie.lireChaine("Prénom ? ");
        String mail = Saisie.lireChaine("Mail ? ");
        String motDePasse = Saisie.lireChaine("Mot de passe ? ");
        String tel=Saisie.lireChaine("Téléphone ? ");
        String genre=Saisie.lireChaine("Genre ? ");
        String date=Saisie.lireChaine("Date de naissance ? ");
        String adresse=Saisie.lireChaine("Adresse ? ");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date2=null;
        try {
            date2 = dateFormat.parse(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Client client = new Client(nom, prenom, mail, motDePasse,tel,genre,date2,adresse);
        Long idClient = service.inscrireClient(client);

        if (idClient != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        afficherClient(client);

    }
    
    public static void saisirAuthentificationClient() {
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
        
        Client client = service.authentifierClient(mail,motDePasse);
        Long idClient = client.getId();

        if (idClient != null) {
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
