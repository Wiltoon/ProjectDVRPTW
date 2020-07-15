/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mymapper.projectdvrptw.entity;

import java.awt.Point;
import java.math.BigDecimal;

/**
 *
 * @author Wilton Costa
 */
public class Pedido {
    private int id;
    private int qtd = 0;
    private BigDecimal tempoEntrega = BigDecimal.ZERO;
    private Point coord = new Point();
    private boolean vizitado = false;

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public int getQtd() {
        return qtd;
    }
    
    public void setQtd(int qtd) {
        this.qtd = qtd;
    }
    public void setQtd(BigDecimal qtd) {
        this.qtd = qtd.intValue();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isVizitado() {
        return vizitado;
    }

    public void setVizitado(boolean vizitado) {
        this.vizitado = vizitado;
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
