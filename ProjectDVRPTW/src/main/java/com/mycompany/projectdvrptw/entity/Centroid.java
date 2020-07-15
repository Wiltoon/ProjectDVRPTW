/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projectdvrptw.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Wilton Costa
 */
public class Centroid {
    private List<Pedido> pedidos = new ArrayList<Pedido>();
    private List<Route> rotas = new ArrayList<Route>();
    private List<Vehicle> veiculosDoCentroide = new ArrayList<Vehicle>();

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public List<Pedido> getPedidos() {
        return pedidos;
    }
    
    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
    
    public List<Route> getRotas() {
        return rotas;
    }
    
    public void setRotas(List<Route> rotas) {
        this.rotas = rotas;
    }
    
    public List<Vehicle> getVeiculosDoCentroide() {
        return veiculosDoCentroide;
    }
    
    public void setVeiculosDoCentroide(List<Vehicle> veiculosDoCentroide) {
        this.veiculosDoCentroide = veiculosDoCentroide;
    }
    
//</editor-fold>
}
