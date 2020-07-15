/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projectdvrptw.entity;

import java.awt.Point;
import java.math.BigDecimal;

/**
 *
 * @author Wilton Costa
 */
public class Pedido {
    private BigDecimal qtd = BigDecimal.ZERO;
    private BigDecimal tempoEntrega = BigDecimal.ZERO;
    private Point coord = new Point();

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public BigDecimal getQtd() {
        return qtd;
    }
    
    public void setQtd(BigDecimal qtd) {
        this.qtd = qtd;
    }
    
    public BigDecimal getTempoEntrega() {
        return tempoEntrega;
    }
    
    public void setTempoEntrega(BigDecimal tempoEntrega) {
        this.tempoEntrega = tempoEntrega;
    }
    
    public Point getCoord() {
        return coord;
    }
    
    public void setCoord(Point coord) {
        this.coord = coord;
    }
    //</editor-fold>
    
}
