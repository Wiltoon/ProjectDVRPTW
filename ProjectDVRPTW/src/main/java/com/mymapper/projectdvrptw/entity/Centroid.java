/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mymapper.projectdvrptw.entity;

import com.mymapper.projectdvrptw.cont.Formulas;
import com.mymapper.projectdvrptw.defines.Definy;
import java.awt.Point;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Wilton Costa
 */
public class Centroid {
    /**
     * id do vehicle responsavel
     */
    private int id;
    private Point centro;
    private int capacityKluster = 0;
    private int capacityMinima = 0;
    private List<Pedido> pedidos = new ArrayList<>();
    private List<Route> rotas = new ArrayList<>();
    private Vehicle veiculoSelecionado = new Vehicle();

    public Centroid(Point centro, Definy def) {
        this.centro = centro;
        this.capacityKluster = def.CAPACIDADE_MAXIMA_DOS_VEICULOS;
        this.capacityMinima = def.CAPACIDADE_MAXIMA_DOS_VEICULOS/2;
    }
    
    public Centroid(){
        
    }
    
    public BigDecimal distanciaPedido(Point pontoPedido){
        return Formulas.distanceEuclidiana2d(centro, pontoPedido);
    }
    
    public void adicionarPedidoNaRota(Pedido pedido){
        pedidos.add(pedido);
    }
    
    public void constroiRota(){
        Route rota = new Route();
        rota.setPedidosDaRota(pedidos);
        getRotas().add(rota);
    }
    
    public void selecionarVehicleResponsavel(Vehicle veiculo){
        setId(veiculo.getId());
        setVeiculoSelecionado(veiculo);
        
    } 
    /**
     * Aqui deve listar todas as rotas possíveis pro centroide
     * e ordenar as 3 melhores rotas em primeiro
     * DJKSTRA / KRUSKAL / PRIM
     */
    public void buscarPossiveisRotas(List<Pedido> pedidosDaRota){
        
    }
    
    public Route melhorRota(){
        if(!getRotas().isEmpty()){
            return getRotas().get(0);
        } else{
            return null;
        }
    }
    
    public int calculaCKatual(){
        int capacidade = 0;
        for(Pedido ped : pedidos){
            capacidade += ped.getQtd();
        }
        return capacidade;
    }
    
    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">

    public int getCapacityKluster() {
        return capacityKluster;
    }

    public void setCapacityKluster(int capacityKluster) {
        this.capacityKluster = capacityKluster;
    }

    public int getCapacityMinima() {
        return capacityMinima;
    }

    public void setCapacityMinima(int capacityMinima) {
        this.capacityMinima = capacityMinima;
    }
    
    
    public List<Pedido> getPedidos() {
        return pedidos;
    }
    
    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Point getCentro() {
        return centro;
    }

    public void setCentro(Point centro) {
        this.centro = centro;
    }
    
    
    public List<Route> getRotas() {
        return rotas;
    }
    
    public void setRotas(List<Route> rotas) {
        this.rotas = rotas;
    }
    
    public Vehicle getVeiculoSelecionado() {
        return veiculoSelecionado;
    }
    
    public void setVeiculoSelecionado(Vehicle veiculoSelecionado) {
        this.veiculoSelecionado = veiculoSelecionado;
    }
    
//</editor-fold>
}
