/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.predictif.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.service.Service;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author zakaria
 */
public class AfficherMediumsAction extends Action {
    
     @Override
    public void executer(HttpServletRequest request) {
        List<Medium> mediums;
        Service service = new Service();
        String mediumType = request.getParameter("type");
        if(mediumType.equals("All")) {
            mediums = service.listerMediums();
        }
        else {
            mediums = service.filterMediums(mediumType);
        }

        request.setAttribute("mediums", mediums);
        
        
    }
}
