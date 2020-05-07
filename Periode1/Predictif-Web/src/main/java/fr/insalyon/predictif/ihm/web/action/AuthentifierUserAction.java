package fr.insalyon.predictif.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.service.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author zakaria
 */
public class AuthentifierUserAction extends Action {
    
    @Override
    public void executer(HttpServletRequest request) {
        
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        Service service = new Service();
        
        Client client = service.authentifierClient(login, password);
        Employe employe = service.authentifierEmploye(login, password);
        if(client!=null) {
            request.setAttribute("client", client); 
            request.setAttribute("user", "client"); 
        }
        else {
            if(employe!=null) {
            request.setAttribute("employe", employe); 
            request.setAttribute("user", "employe"); 
        }
        }
        
        // Gestion de la Session: ici, enregistrer l'ID du Client authentifié
        HttpSession session = request.getSession();
        if (client != null) {
            session.setAttribute("idClient", client.getId());
        }
        else {
            session.removeAttribute("idClient");
            if (employe != null) {
            session.setAttribute("idEmploye", employe.getId());
            }
            else {
                session.removeAttribute("idEmploye");
            }
        }
    }
}
