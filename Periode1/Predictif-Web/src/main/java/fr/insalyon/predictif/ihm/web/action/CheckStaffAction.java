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
public class CheckStaffAction extends Action {
    @Override
    public void executer(HttpServletRequest request) {
        Service service = new Service();
        HttpSession session = request.getSession(); 
        Long id = (Long) session.getAttribute("idEmploye");
        if(id==null) {
            request.setAttribute("notLoggedIn", true);
        } else {
            Employe e = service.rechercherEmployeParId(id); 
            Consultation c = service.getCurrentConsultation(e);
            if(c!=null){
                request.setAttribute("inConsultation", true);
            }
        }
    }
}
