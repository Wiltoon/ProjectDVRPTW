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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Wilton Costa
 */
public class Route {

    private List<Pedido> pedidosDaRota = new ArrayList<>();
    private List<Pedido> pedidosOrdenados = new ArrayList<>();
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

    public List<Pedido> getPedidosOrdenados() {
        return pedidosOrdenados;
    }

    public void setPedidosOrdenados(List<Pedido> pedidosOrdenados) {
        this.pedidosOrdenados = pedidosOrdenados;
    }

    public BigDecimal getDistancia() {
        return distancia;
    }

    public void setDistancia(BigDecimal distancia) {
        this.distancia = distancia;
    }

    //</editor-fold>
    public void sortPedidos(Definy definicoes, List<Pedido> pedidos) {
        List<Pedido> pedidosL = new ArrayList<>();
        NeederPedido aux = buscaPedidoProximo(definicoes.DEPOSITO, pedidosDaRota);
        pedidosL.add(aux.getPedidoAuxiliar());
        getPedidosDaRota().get(aux.getIndice()).setVizitado(true);
        for (int idx = 0; idx < getPedidosDaRota().size() - 1; idx++) {
            NeederPedido ux = buscaPedidoProximo(pedidosL.get(idx).getCoord(), pedidosDaRota);
            pedidosL.add(ux.getPedidoAuxiliar());
            getPedidosDaRota().get(ux.getIndice()).setVizitado(true);
        }
        setPedidosOrdenados(pedidosL);
    }

    public void heuristicaAestrela(Definy definicoes, List<Pedido> pedidos) {
        //State inicial = DEPOSITO
        //DEPOSITO -> 1 ; DEPOSITO -> 2 ; DEPOSITO -> 3 
        List<Pedido> pedidoComDeposito = new ArrayList<>();
        List<State> stateVisitados = new ArrayList<>();
        List<State> stateNaoVisitados = new ArrayList<>();
        pedidoComDeposito = pedidos;
        pedidoComDeposito.add(new Pedido(definicoes.DEPOSITO));
        pedidoComDeposito.add(0, new Pedido(definicoes.DEPOSITO));
        BigDecimal score = BigDecimal.ZERO;
        State estadoAtual = new State(pedidoComDeposito, definicoes.DEPOSITO, score);
        stateVisitados.add(estadoAtual);
        long node = 0;
        while (!estadoFinal(estadoAtual)) {
            State proximoState = estadoAtual.proxState(estadoAtual, stateNaoVisitados);
            estadoAtual = new State(proximoState);
            node++;
            System.out.println("Nodes = "+node);
        }
        System.out.println("Score = "+estadoAtual.getScore());
        setPedidosOrdenados(estadoAtual.getPedidosVisitados());
    }

    public boolean estadoFinal(State estadoAtual) {
        return estadoAtual.getPedidosNaoVisitados().isEmpty();
    }

    public State chooserBestState(List<State> states) {
        BigDecimal melhorScore = BigDecimal.valueOf(100 * 100 * 100 * 100);
        State choose = new State();
        for (State state : states) {
            if (state.getScore().compareTo(melhorScore) < 0) {
                choose = state;
            }
        }
        return choose;
    }

    /**
     * Depois que tiver o pedidosOrdenados
     *
     * @return
     */
    public void distanceAndTimeForRoute(Definy def) {
        int idx = 0;
        setDistancia(getDistancia().add(Formulas.sqrtDistanceEuclidian(def.DEPOSITO, getPedidosOrdenados().get(0).getCoord())));
        setTempoGasto(getTempoGasto().add(Formulas.timeOutRoute(new Pedido(def.DEPOSITO), getPedidosOrdenados().get(0))));
        idx++;
        for (Pedido p : getPedidosOrdenados()) {
            if (idx < getPedidosOrdenados().size()) {
                setDistancia(getDistancia().add(Formulas.sqrtDistanceEuclidian(p.getCoord(), getPedidosOrdenados().get(idx).getCoord())));
                setTempoGasto(getTempoGasto().add(Formulas.timeOutRoute(p, getPedidosOrdenados().get(idx))));
                idx++;
            }
        }
        setDistancia(getDistancia().add(Formulas.sqrtDistanceEuclidian(getPedidosOrdenados().get(idx - 1).getCoord(), def.DEPOSITO)));
        setTempoGasto(getTempoGasto().add(Formulas.timeOutRoute(getPedidosOrdenados().get(idx - 1), new Pedido(def.DEPOSITO))));
    }

    public NeederPedido buscaPedidoProximo(Point actual, List<Pedido> pedidos) {
        NeederPedido np = new NeederPedido();
        Integer indice = 0;
        BigDecimal menorDistance = null;
        Pedido nearPedido = new Pedido();
        for (Pedido pedido : pedidos) {
            if (!pedido.isVizitado()) {
                if (menorDistance == null) {
                    menorDistance = Formulas.distanceEuclidiana2d(actual, pedido.getCoord());
                    nearPedido = new Pedido(pedido);
                    np = new NeederPedido(indice, nearPedido);
                }
                if (menorDistance.compareTo(Formulas.distanceEuclidiana2d(actual, pedido.getCoord())) > 0) {
                    menorDistance = Formulas.distanceEuclidiana2d(actual, pedido.getCoord());
                    nearPedido = new Pedido(pedido);
                    np = new NeederPedido(indice, nearPedido);
                }
            }
            indice++;
        }
        return np;
    }
}
