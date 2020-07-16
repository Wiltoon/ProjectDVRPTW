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
    private int id = 0;
    private int qtd = 0;
    private BigDecimal tempoEntrega = BigDecimal.ZERO;
    private BigDecimal tempoPedido = BigDecimal.ZERO;
    private Point coord = new Point();
    private boolean vizitado = false;

    public Pedido(Pedido copia) {
        this.id = copia.getId();
        this.qtd = copia.getQtd();
        this.coord = copia.getCoord();
        this.tempoEntrega = copia.getTempoEntrega();
        this.vizitado = copia.isVizitado();
    }
    public Pedido(RequestVRP request){
        this.id = request.getId();
        this.coord = new Point(request.getxCoord(),request.getyCoord());
        this.qtd = request.getDemand();
        this.tempoEntrega = new BigDecimal(request.getServiceTime());
        this.tempoPedido = new BigDecimal(request.getAvailableTime());
        this.vizitado = false;
    }
    
    public Pedido() {
    }
    public Pedido(Point coord) {
        this.coord = coord;
    }
    
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

    public BigDecimal getTempoPedido() {
        return tempoPedido;
    }

    public void setTempoPedido(BigDecimal tempoPedido) {
        this.tempoPedido = tempoPedido;
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
