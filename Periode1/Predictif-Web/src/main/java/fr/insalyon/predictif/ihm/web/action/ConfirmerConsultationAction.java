/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.predictif.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.service.Service;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author zakaria
 */
public class ConfirmerConsultationAction extends Action {
    @Override
    public void executer(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Service service = new Service();
        Long id = (Long) session.getAttribute("idEmploye");
        boolean success = false;
        if(id!=null) {
            Employe e = service.rechercherEmployeParId(id);
            if(e!=null) {
                List<Consultation> cons = e.getConsultations();
                if(cons.size()>0) {
                Consultation c = e.getConsultations().get(0);
                if(c!=null) {
                    success = service.confirmConsultation(c); 
                }                       
                }             
            }
        }
        else {
            request.setAttribute("notLoggedIn", true);
        }
        request.setAttribute("success", success); 
    }
}
