/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mymapper.projectdvrptw.entity;

/**
 *
 * @author Wilton Costa
 */
public class NeederPedido {
    /**
     * Indice do pedido no vetor
     */
    private Integer indice;
    /**
     * Pedido auxiliar criado
     */
    private Pedido pedidoAuxiliar;

    public NeederPedido(){
        
    }
    
    public NeederPedido(Integer indice, Pedido pedidoAuxiliar) {
        this.indice = indice;
        this.pedidoAuxiliar = pedidoAuxiliar;
    }

    public Integer getIndice() {
        return indice;
    }

    public void setIndice(Integer indice) {
        this.indice = indice;
    }

    public Pedido getPedidoAuxiliar() {
        return pedidoAuxiliar;
    }

    public void setPedidoAuxiliar(Pedido pedidoAuxiliar) {
        this.pedidoAuxiliar = pedidoAuxiliar;
    }
    
}
