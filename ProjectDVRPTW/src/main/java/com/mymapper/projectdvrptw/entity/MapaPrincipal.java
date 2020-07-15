/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mymapper.projectdvrptw.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Wilton Costa
 */
public class MapaPrincipal {
    private List<Centroid> zonas = new ArrayList<>();
    private List<Vehicle> allVehicles = new ArrayList<>();
    private BigDecimal tempoMaximo = BigDecimal.ZERO;
    private BigDecimal tempoDecorrido = BigDecimal.ZERO;
    private int capacityMax = 0;
    private List<Pedido> allPedidos = new ArrayList<>();
    private VRPTW problem = new VRPTW();
    
    public void capacityTotal(){
        this.capacityMax = 0;
        if(!getAllPedidos().isEmpty()){
            for(Pedido pedido : getAllPedidos()){
                this.capacityMax += pedido.getQtd();
            }
        }
    }
    
    public void createVehiclesInMap(){
        
    }
    
    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public List<Centroid> getZonas() {
        return zonas;
    }
    
    public void setZonas(List<Centroid> zonas) {
        this.zonas = zonas;
    }
    
    public List<Vehicle> getAllVehicles() {
        return allVehicles;
    }
    
    public void setAllVehicles(List<Vehicle> allVehicles) {
        this.allVehicles = allVehicles;
    }
    
    public BigDecimal getTempoMaximo() {
        return tempoMaximo;
    }
    
    public void setTempoMaximo(BigDecimal tempoMaximo) {
        this.tempoMaximo = tempoMaximo;
    }
    
    public BigDecimal getTempoDecorrido() {
        return tempoDecorrido;
    }
    
    public void setTempoDecorrido(BigDecimal tempoDecorrido) {
        this.tempoDecorrido = tempoDecorrido;
    }
    
    public List<Pedido> getAllPedidos() {
        return allPedidos;
    }
    
    public void setAllPedidos(List<Pedido> allPedidos) {
        this.allPedidos = allPedidos;
    }
    
//</editor-fold>
}
