package fr.insalyon.predictif.ihm.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author zakaria
 */
public class Deconnexion extends Action {
    
    @Override
    public void executer(HttpServletRequest request) {
        
        // Gestion de la Session: ici, enregistrer l'ID du Client authentifié
        HttpSession session = request.getSession();
        session.invalidate();
    }
}
