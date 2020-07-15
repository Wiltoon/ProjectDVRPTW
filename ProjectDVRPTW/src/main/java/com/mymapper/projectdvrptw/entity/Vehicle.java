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
public class Vehicle {
    private int id;
    private int capacidade;
    private BigDecimal combustivel;
    private Point localAtual = new Point();
    private List<Route> rotasDoVeiculo = new ArrayList<>();
    private Route rotaSelecionada = new Route();

    //<editor-fold defaultstate="collapsed" desc="Constructor">
    public Vehicle(int id, int capacidade, BigDecimal combustivel) {
        this.id = id;
        this.capacidade = capacidade;
        this.combustivel = combustivel;
    }
    
    public Vehicle() {
        this.id = 0;
        this.capacidade = 0;
        this.combustivel = BigDecimal.ZERO;
        moveVehicle(new Point(0,0));
    }
    
    //</editor-fold>
    
    public void moveVehicle(Point destiny){
        this.localAtual = destiny;
    }

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public int getId() {    
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getCapacidade() {
        return capacidade;
    }
    
    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public Route getRotaSelecionada() {
        return rotaSelecionada;
    }

    public void setRotaSelecionada(Route rotaSelecionada) {
        this.rotaSelecionada = rotaSelecionada;
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
