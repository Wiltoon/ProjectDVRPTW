/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mymapper.projectdvrptw.entity;

import com.mymapper.projectdvrptw.cont.ControllerEstatic;
import com.mymapper.projectdvrptw.defines.Definy;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Wilton Costa
 */
public class MapaPrincipal {
    /**
     * Centroids do K-Means zonas para os vehicles
     */
    private List<Centroid> zonas = new ArrayList<>();
    /**
     * Todos os veículos que a companhia possuí
     */
    private List<Vehicle> allVehicles = new ArrayList<>();
    /**
     * Tempo LIMITE para entrega de todos os pedidos
     */
    private BigDecimal tempoMaximo = BigDecimal.ZERO;
    /**
     * Tempo decorrido desde o seu inicio!
     */
    private BigDecimal tempoDecorrido = BigDecimal.ZERO;
    /**
     * Capacidade maxima do mapa
     */
    private int capacityMax = 0;
    /**
     * Lista de todos os pedidos
     */
    private List<Pedido> allPedidos = new ArrayList<>();
    /**
     * Primeiro a ser definido no init
     */
    private VRPTW problem = new VRPTW();
    /**
     * Definido no init
     */
    public Definy def = new Definy();
    
    public void initMapa(VRPTW vrptw){
        setProblem(vrptw);
        def.DEPOSITO = RequestVRP.requestPoint(vrptw.getRequests().get(0));
        def.CAPACIDADE_MAXIMA_DOS_VEICULOS = vrptw.getCapacity();
        def.QTD_TOTAL_DE_VEICULOS = vrptw.getNrVehicles();
        for(int id = 1; id <= def.QTD_TOTAL_DE_VEICULOS;id++){
            Vehicle vehicle = new Vehicle(
                    id, 
                    def.CAPACIDADE_MAXIMA_DOS_VEICULOS, 
                    Definy.VELOCIDADE_CARROS, 
                    new BigDecimal(50), 
                    def.DEPOSITO);
            getAllVehicles().add(vehicle);
        }
        for(RequestVRP req : vrptw.getRequests()){
            Pedido pedido = new Pedido(req);
            getAllPedidos().add(pedido);
        }
        ControllerEstatic.criarCentroidesRotas(this);
        
    }
    
    public void capacityTotal(){
        this.def.CAPACIDADE_TOTAL_MAPA = 0;
        this.capacityMax = 0;
        if(!getAllPedidos().isEmpty()){
            for(Pedido pedido : getAllPedidos()){
                this.def.CAPACIDADE_TOTAL_MAPA += pedido.getQtd();
                this.capacityMax += pedido.getQtd();
            }
        }
    }
    public void adicionaZona(Centroid centroid){
        this.getZonas().add(centroid);
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

    public int getCapacityMax() {
        return capacityMax;
    }

    public void setCapacityMax(int capacityMax) {
        this.capacityMax = capacityMax;
    }

    public VRPTW getProblem() {
        return problem;
    }

    public void setProblem(VRPTW problem) {
        this.problem = problem;
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
