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
public class Route {

    private Vehicle respForRoute = new Vehicle();
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

    public Vehicle getRespForRoute() {
        return respForRoute;
    }

    public void setRespForRoute(Vehicle respForRoute) {
        this.respForRoute = respForRoute;
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
    public void sortPedidos(Definy definicoes) {
        int idx = 0;
        getPedidosOrdenados().add(buscaPedidoProximo(definicoes.DEPOSITO));
        for (Pedido pedido : getPedidosDaRota()) {
            if (!pedido.isVizitado()) {
                getPedidosOrdenados().add(buscaPedidoProximo(getPedidosOrdenados().get(idx).getCoord()));
                idx = idx + 1;
            }
        }

    }

    /**
     * Depois que tiver o pedidosOrdenados
     *
     * @return
     */
    public void distanceAndTimeForRoute(Definy def) {
        int idx = 0;
        setDistancia(getDistancia().add(Formulas.sqrtDistanceEuclidian(def.DEPOSITO, getPedidosOrdenados().get(0).getCoord())));
        setTempoGasto(getTempoGasto().add(Formulas.timeOutRoute(new Pedido(), getPedidosOrdenados().get(0))));
        idx++;
        for (Pedido p : getPedidosOrdenados()) {
            setDistancia(getDistancia().add(Formulas.sqrtDistanceEuclidian(getPedidosOrdenados().get(idx - 1).getCoord(), getPedidosOrdenados().get(idx).getCoord())));
            setTempoGasto(getTempoGasto().add(Formulas.timeOutRoute(getPedidosOrdenados().get(idx - 1), getPedidosOrdenados().get(idx))));
            idx++;
        }
        setDistancia(getDistancia().add(Formulas.sqrtDistanceEuclidian(getPedidosOrdenados().get(0).getCoord(),def.DEPOSITO)));
        setTempoGasto(getTempoGasto().add(Formulas.timeOutRoute(getPedidosOrdenados().get(idx), new Pedido())));
    }

    public Pedido buscaPedidoProximo(Point actual) {
        BigDecimal menorDistance = null;
        Pedido nearPedido = new Pedido();
        for (Pedido pedido : getPedidosDaRota()) {
            if (menorDistance == null) {
                menorDistance = Formulas.distanceEuclidiana2d(actual, pedido.getCoord());
            }
            if (menorDistance.compareTo(Formulas.distanceEuclidiana2d(actual, pedido.getCoord())) > 0) {
                menorDistance = Formulas.distanceEuclidiana2d(actual, pedido.getCoord());
                nearPedido = new Pedido(pedido);
                pedido.setVizitado(true);
            }
        }
        return nearPedido;
    }
}
