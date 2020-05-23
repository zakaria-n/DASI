/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.predictif.ihm.web.action;

import fr.insalyon.dasi.metier.service.Service;
import fr.insalyon.dasi.techniques.service.Statistics;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author zakaria
 */
public class DisplayStatsAction extends Action {
    
    @Override
    public void executer(HttpServletRequest request) {
        
        Statistics stats = new Statistics();
        Service service = new Service();
        service.statistics(stats);
        
        request.setAttribute("stats",stats);
    }
    
}
