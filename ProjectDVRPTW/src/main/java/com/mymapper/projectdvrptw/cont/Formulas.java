/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mymapper.projectdvrptw.cont;

import com.mymapper.projectdvrptw.defines.Definy;
import com.mymapper.projectdvrptw.entity.Pedido;
import com.mymapper.projectdvrptw.entity.Route;
import java.awt.Point;
import java.math.BigDecimal;
import java.math.MathContext;
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
    
    public static BigDecimal sqrtDistanceEuclidian(Point inicial, Point finish){
        return distanceEuclidiana2d(inicial, finish).sqrt(MathContext.DECIMAL32);
    }
    
    public static BigDecimal timeDistance(BigDecimal distancia){
        return distancia.divide(new BigDecimal(Definy.VELOCIDADE_CARROS));
    }
    
    public static BigDecimal timeOutRoute(Pedido atual, Pedido prox){
        BigDecimal timeTotal = BigDecimal.ZERO;
        timeTotal = atual.getTempoEntrega().add(prox.getTempoEntrega());
        timeTotal = timeTotal.add(timeDistance(sqrtDistanceEuclidian(atual.getCoord(), prox.getCoord())));
        return timeTotal;
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
