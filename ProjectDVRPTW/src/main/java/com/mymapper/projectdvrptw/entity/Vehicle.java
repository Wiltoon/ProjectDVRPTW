/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mymapper.projectdvrptw.entity;

import com.mymapper.projectdvrptw.defines.Definy;
import java.awt.Point;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Wilton Costa
 */
public class Vehicle {
    //<editor-fold defaultstate="collapsed" desc="Variaveis do Vehicle">
    /**
     * Preenchido no MapaPrincipal o id do carro
     */
    private int id;
    /**
     * Capacidade do carro (MapaPrincipal)
     */
    private int capacidade;
    /**
     * Velocidade do carro (MapaPrincipal)
     */
    private int velocidade;
    /**
     * Capacidade maxima de combustivel (MapaPrincipal)
     */
    private BigDecimal combustivel;
    /**
     * Posição atual do Carro inicia no DEPOSITO
     * DEVE SER ATUALIZADA QUANDO CHEGAR NO PROXIMO DESTINO
     */
    private Point localAtual = new Point();
    /**
     * Vetor AUXILIAR PARA VERIFICAR AS MELHORES ROTAS APOS PEDIDO NOVO (ControllerDynamic)
     */
    private List<Route> rotasDoVeiculo = new ArrayList<>();
    /**
     * ROTA PROGRAMADA (ControllerEstatic)
     */
    private Route rotaSelecionada = new Route();
    /**
     * Se o carro não estiver no deposito
     */
    private Boolean usado = Boolean.FALSE;
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructor">
    public Vehicle(int id, int capacidade, int velocidade, BigDecimal combustivel, Point point) {
        this.id = id;
        this.capacidade = capacidade;
        this.velocidade = velocidade;
        this.combustivel = combustivel;
        this.localAtual = point;
        this.usado = Boolean.FALSE;
    }
    
    public Vehicle() {
        this.id = 0;
        this.capacidade = 0;
        this.combustivel = BigDecimal.ZERO;
        moveVehicle(new Point(0,0));
    }
    
    //</editor-fold>
    
    public void percorrerRota(){
        
    }
    
    public static Vehicle usarVehicle(int id, MapaPrincipal map){
        return map.getAllVehicles().get(id);
    }
    
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

    public Boolean getUsado() {
        return usado;
    }

    public void setUsado(Boolean usado) {
        this.usado = usado;
    }

    
    public int getCapacidade() {
        return capacidade;
    }
    
    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public int getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
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
