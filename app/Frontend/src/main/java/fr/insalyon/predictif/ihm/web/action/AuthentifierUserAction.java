package fr.insalyon.predictif.ihm.web.action;

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
        
        long clientId = service.authentifierClient(login, password);
        long employeId = service.authentifierEmploye(login, password);
        if(clientId!=-1) {
            request.setAttribute("client", clientId); 
            request.setAttribute("user", "client"); 
        }
        else if(employeId!=-1) {
            request.setAttribute("employe", employeId); 
            request.setAttribute("user", "employe"); 
        }
        
        // Gestion de la Session: ici, enregistrer l'ID du Client authentifié
        HttpSession session = request.getSession();
        if (clientId != -1) {
            session.setAttribute("idClient", clientId);
        }
        else if(employeId != -1) {
            session.setAttribute("idEmploye", employeId);
        }
    }
}
