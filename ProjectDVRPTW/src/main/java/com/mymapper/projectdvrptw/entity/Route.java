/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mymapper.projectdvrptw.entity;

import java.awt.Point;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Wilton Costa
 */
public class Route {
    private Vehicle respForRoute = new Vehicle();
    private List<Pedido> pedidosDaRota = new ArrayList<>();
    private List<Point> pontos = new ArrayList<>();
    private BigDecimal tempoGasto = BigDecimal.ZERO;
    private BigDecimal distancia = BigDecimal.ZERO;

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public List<Pedido> getPedidosDaRota() {
        return pedidosDaRota;
    }
    
    public void setPedidosDaRota(List<Pedido> pedidosDaRota) {
        this.pedidosDaRota = pedidosDaRota;
    }
    
    public List<Point> getPontos() {
        return pontos;
    }
    
    public void setPontos(List<Point> pontos) {
        this.pontos = pontos;
    }
    
    public BigDecimal getTempoGasto() {
        return tempoGasto;
    }
    
    public void setTempoGasto(BigDecimal tempoGasto) {
        this.tempoGasto = tempoGasto;
    }
    
    public BigDecimal getDistancia() {
        return distancia;
    }
    
    public void setDistancia(BigDecimal distancia) {
        this.distancia = distancia;
    }
    //</editor-fold>
    
}
