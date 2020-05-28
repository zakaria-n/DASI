/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.predictif.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.service.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author zakaria
 */
public class AddCommentAction extends Action {
    @Override
    public void executer(HttpServletRequest request) {
        String comment = request.getParameter("comment");
        HttpSession session = request.getSession();
        boolean success = false;
        Service service = new Service();
        Long id = (Long) session.getAttribute("idEmploye");
        System.out.println("Employe "+ id);
        if(id!=null) {
            Employe e = service.rechercherEmployeParId(id);
            Consultation consul = e.getConsultations().get(0);
            if (consul != null && consul.getCommentaire()!=null)
            {
                success = service.ajouterCommentaire(consul, comment);
            }
        }else{
            request.setAttribute("notLoggedIn", true);
        }
        request.setAttribute("success", success); 
    }
}
