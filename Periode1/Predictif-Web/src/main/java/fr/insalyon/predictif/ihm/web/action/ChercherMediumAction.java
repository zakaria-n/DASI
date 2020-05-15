/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.predictif.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.service.Service;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;


/**
 *
 * @author Zihao
 */
public class ChercherMediumAction extends Action {
    
    @Override
    
    public void executer(HttpServletRequest request) {
        Medium medium;
        List<Medium> mediums = new ArrayList<Medium>();
        Service service = new Service();
        String mediumName = request.getParameter("name");
        medium = service.chercherMedium(mediumName);
        mediums.add(medium);
        request.setAttribute("mediums", mediums);
        
        
    }
    
}
