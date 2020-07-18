/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mymapper.projectdvrptw.entity;

import com.mymapper.projectdvrptw.cont.Formulas;
import java.awt.Point;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Wilton Costa
 */
public class State {

    private List<Pedido> pedidos = new ArrayList<>();
    private List<Pedido> pedidosVisitados = new ArrayList<>();
    private List<Pedido> pedidosNaoVisitados = new ArrayList<>();
    private Point pontoAtual = new Point();
    private BigDecimal score = BigDecimal.ZERO;

    public State(List<Pedido> pedidos, Point ponto, BigDecimal score) {
        this.pedidos = pedidos;
        this.pontoAtual = ponto;
        this.score = score;
        this.pedidosVisitados = new ArrayList<>();
        this.pedidosNaoVisitados = pedidos;
    }

    public State() {
    }

    public State(State copy) {
        this.pedidos = copy.pedidos;
        this.pedidosNaoVisitados = copy.pedidosNaoVisitados;
        this.pedidosVisitados = copy.pedidosVisitados;
        this.pontoAtual = copy.pontoAtual;
        this.score = copy.score;
    }

    public State proxState(State estadoAtual, List<State> allStatesNaoVisitados) {
        List<State> statesPossiveis = new ArrayList<>();
        visitaStateAtual(estadoAtual);
        statesPossiveis = estadoAtual.gerarStates(estadoAtual);
        allStatesNaoVisitados.remove(estadoAtual.excluiState(allStatesNaoVisitados));
        for (State st : statesPossiveis) {
            allStatesNaoVisitados.add(st);
        }
        return chooserBestState(allStatesNaoVisitados);
    }

    public State excluiState(List<State> lista) {
        for (State at : lista) {
            if (at.equals(this)) {
                return at;
            }
        }
        return null;
    }

    public void visitaStateAtual(State estadoAtual) {
        estadoAtual.getPedidosVisitados().add(new Pedido(estadoAtual.getPontoAtual()));
        estadoAtual.getPedidosNaoVisitados().remove(excluirPedido(pontoAtual));
        visitarPontoDoPedido(pontoAtual);
    }

    public List<State> gerarStates(State estadoAtual) {
        List<State> statesPossiveis = new ArrayList<>();
        for (Pedido p : estadoAtual.getPedidosNaoVisitados()) {
            State stateNovo = new State(pedidos, p.getCoord(), estadoAtual.score.add(Formulas.distanceEuclidiana2d(pontoAtual, p.getCoord())));
            listasAtualizadas(estadoAtual,stateNovo);
            statesPossiveis.add(stateNovo);
        }

        return statesPossiveis;
    }

    public void listasAtualizadas(State estado, State novo){
        novo.pedidosVisitados = estado.pedidosVisitados;
        novo.pedidosNaoVisitados = estado.pedidosNaoVisitados;
    } 
    
    public void visitarPontoDoPedido(Point ponto) {
        for (Pedido p : getPedidos()) {
            if (ponto.equals(p.getCoord())) {
                p.setVizitado(true);
            }
        }
    }

    public Pedido excluirPedido(Point pont) {
        if (!getPedidosNaoVisitados().isEmpty()) {
            for (Pedido p : getPedidosNaoVisitados()) {
                if (pont.equals(p.getCoord())) {
                    return p;
                }
            }
        }
        return null;
    }

    public State chooserBestState(List<State> states) {
        BigDecimal melhorScore = BigDecimal.valueOf(100 * 100 * 100 * 100);
        State choose = new State();
        for (State state : states) {
            if (state.getScore().compareTo(melhorScore) < 0) {
                choose = state;
            }
        }
//        if(choose.score > 0){
//            
//        }
        return choose;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public List<Pedido> getPedidosVisitados() {
        return pedidosVisitados;
    }

    public void setPedidosVisitados(List<Pedido> pedidosVisitados) {
        this.pedidosVisitados = pedidosVisitados;
    }

    public List<Pedido> getPedidosNaoVisitados() {
        return pedidosNaoVisitados;
    }

    public void setPedidosNaoVisitados(List<Pedido> pedidosNaoVisitados) {
        this.pedidosNaoVisitados = pedidosNaoVisitados;
    }

    public Point getPontoAtual() {
        return pontoAtual;
    }

    public void setPontoAtual(Point pontoAtual) {
        this.pontoAtual = pontoAtual;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

}
