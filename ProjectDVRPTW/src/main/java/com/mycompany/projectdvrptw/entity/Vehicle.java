/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projectdvrptw.entity;

import java.awt.Point;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Wilton Costa
 */
public class Vehicle {
    private int capacidade;
    private BigDecimal combustivel;
    private Point localAtual = new Point();
    private List<Route> rotasDoVeiculo = new ArrayList<>();

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public int getCapacidade() {
        return capacidade;
    }
    
    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }
    
    public BigDecimal getCombustivel() {
        return combustivel;
    }
    
    public void setCombustivel(BigDecimal combustivel) {
        this.combustivel = combustivel;
    }
    
    public Point getLocalAtual() {
        return localAtual;
    }
    
    public void setLocalAtual(Point localAtual) {
        this.localAtual = localAtual;
    }
    
    public List<Route> getRotasDoVeiculo() {
        return rotasDoVeiculo;
    }
    
    public void setRotasDoVeiculo(List<Route> rotasDoVeiculo) {
        this.rotasDoVeiculo = rotasDoVeiculo;
    }
    //</editor-fold>
    
}
