/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mymapper.projectdvrptw.cont;

import com.mymapper.projectdvrptw.entity.Route;
import java.awt.Point;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Wilton Costa
 */
public class Formulas {
    
    /**
     * nao retorna a raiz quadrada pois a comparação para ver quem é maior não altera
     * @param inicial ponto inicial
     * @param finish ponto final
     * @return dx^2+dy^2
     */
    public static BigDecimal distanceEuclidiana2d(Point inicial, Point finish){
        int dX = finish.x - inicial.x;
        int dY = finish.y - inicial.y;
        return new BigDecimal(dX*dX+dY*dY);
    }
    
    /**
     * Retorna uma Rota 
     * @param pontos
     * @return 
     */
    public Route dijkstra(List<Point> pontos){
        return new Route();
    }
}
