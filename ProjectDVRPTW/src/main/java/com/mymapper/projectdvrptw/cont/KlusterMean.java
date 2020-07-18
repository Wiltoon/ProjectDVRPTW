/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mymapper.projectdvrptw.cont;

import com.mymapper.projectdvrptw.defines.Definy;
import com.mymapper.projectdvrptw.entity.Centroid;
import com.mymapper.projectdvrptw.entity.MapaPrincipal;
import com.mymapper.projectdvrptw.entity.Pedido;
import java.awt.Point;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import static java.util.stream.Collectors.toList;

/**
 *
 * @author Wilton Costa
 */
public class KlusterMean {

    private static final Random random = new Random();
    private static final Random random2 = new Random();

    public static Map<Centroid, List<Pedido>> fit(int k, List<Pedido> pedidos, MapaPrincipal mapa) {
        Map<Centroid, List<Pedido>> clusters = new HashMap<>();
        Map<Centroid, List<Pedido>> lastState = new HashMap<>();
        List<Centroid> rotas = gerarCentroids(pedidos, k, mapa);
        for (int i = 0; i < mapa.def.INTERACOES; i++) {
            boolean lastIteration = (i == mapa.def.INTERACOES - 1);
            for (Pedido pedido : pedidos) {
                Centroid centroid = nearestCentroid(pedido, rotas, mapa.def);
                if(centroid != null){
                    pedidoDoCentroid(clusters, centroid, pedido);
                }
            }
            boolean terminate = lastIteration || clusters.equals(lastState);
            lastState = clusters;
            if (terminate) {
                break;
            }
            rotas = realocateCentroids(clusters,mapa);
            clusters = new HashMap<>();
        }
        return lastState;
    }

    public static Centroid nearestCentroid(Pedido pedido, List<Centroid> centroids, Definy ck) {
        double minDistance = Double.MAX_VALUE;
        Centroid nearest = null;
        for (Centroid centroid : centroids) {
            BigDecimal distancia = centroid.distanciaPedido(pedido.getCoord());
            if (distancia.doubleValue() < minDistance && 
                    centroid.calculaCKatual() <= ck.CAPACIDADE_MAXIMA_DOS_VEICULOS) {
                minDistance = distancia.doubleValue();
                nearest = centroid;
            }
        }
        return nearest;
    }

    public static void pedidoDoCentroid(Map<Centroid, List<Pedido>> clusters, Centroid centroid, Pedido pedido) {
//        clusters.put(centroid, value);
//        centroid.adicionarPedidoNaRota(pedido);
        clusters.compute(centroid, (key, list) -> {
            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(pedido);
            return list;
        });
    }

    public static Centroid average(Centroid centroid, List<Pedido> pedidos) {
        if (pedidos == null || pedidos.isEmpty()) {
            return centroid;
        }
        int xMean = 0;
        int yMean = 0;
        for (Pedido pedido : pedidos) {
            xMean += pedido.getCoord().x;
            yMean += pedido.getCoord().y;
        }
        xMean = xMean / pedidos.size();
        yMean = yMean / pedidos.size();
        Point medio = new Point(xMean, yMean);
        centroid.setCentro(medio);
        return centroid;
    }

    public static List<Centroid> realocateCentroids(Map<Centroid, List<Pedido>> clusters, MapaPrincipal mapa) {
        for (Centroid centroid : mapa.getZonas()) {
            average(centroid,centroid.getPedidos());
        }
        return clusters.entrySet().stream().map(e -> average(e.getKey(), e.getValue())).collect(toList());
    }

    public static List<Centroid> gerarCentroids(List<Pedido> pedidos, int k, MapaPrincipal mapa) {
        List<Centroid> centroids = new ArrayList<>();
        BigDecimal maxX = null;
        BigDecimal minX = null;
        BigDecimal maxY = null;
        BigDecimal minY = null;

        //Faixa de valores para os Centroids
        for (Pedido pedido : pedidos) {
            maxX = comparacaoNula(maxX, new BigDecimal(pedido.getCoord().x),"Menor");
            minX = comparacaoNula(minX, new BigDecimal(pedido.getCoord().x),"Maior");
            maxY = comparacaoNula(maxY, new BigDecimal(pedido.getCoord().y),"Menor");
            minY = comparacaoNula(minY, new BigDecimal(pedido.getCoord().y),"Maior");
        }
        BigDecimal angle = new BigDecimal(360 / k);
        BigDecimal centroX = (maxX.subtract(minX)).divide(new BigDecimal(2));
        BigDecimal centroY = (maxY.subtract(minY)).divide(new BigDecimal(2));
        BigDecimal Rmax = raioMaximo(maxX, maxY, centroX, centroY);
        for (int i = 0; i < k; i++) {
            BigDecimal tethaMIN = angle.multiply(new BigDecimal(i)).setScale(2, RoundingMode.HALF_UP);
            Integer raio = random.nextInt()%Rmax.intValue();
            Integer tetha = random2.nextInt()%angle.intValue() + tethaMIN.intValue();
            BigDecimal x = new BigDecimal(centroX.doubleValue() + raio * cosseno(tetha)).setScale(1, RoundingMode.HALF_UP);
            BigDecimal y = new BigDecimal(centroY.doubleValue() + raio * cosseno(tetha)).setScale(1, RoundingMode.HALF_UP);
            if (x.compareTo(maxX) >= 0) {
                x = x.remainder(maxX);
                y = x.multiply(new BigDecimal(seno(tetha)).setScale(2, RoundingMode.HALF_UP));
            }
            if (y.compareTo(maxY) <= 0) {
                y = y.remainder(maxY);
                x = y.multiply(new BigDecimal(cosseno(tetha)).setScale(2, RoundingMode.HALF_UP));
            }
            Centroid centro = new Centroid(new Point(x.intValue(), y.intValue()),mapa.def);
            centroids.add(centro);
        }
        return centroids;
    }

    public static Double cosseno(Integer angulo) {
        return Math.cos(Math.toRadians(angulo));
    }

    public static Double seno(Integer angulo) {
        return Math.sin(Math.toRadians(angulo));
    }

    public static BigDecimal raioMaximo(BigDecimal x, BigDecimal y, BigDecimal cx, BigDecimal cy) {
        BigDecimal dx = x.subtract(cx);
        BigDecimal dy = y.subtract(cy);
        BigDecimal mod = (dx.pow(2)).add(dy.pow(2));
        return mod.sqrt(MathContext.DECIMAL64);
    }

    public static BigDecimal comparacaoNula(BigDecimal value, BigDecimal expect, String cond) {
        boolean condicao = false;
        if (value != null) {
            switch (cond) {
                case "Maior":
                    condicao = (value.compareTo(expect) > 0);
                    break;
                case "Menor":
                    condicao = (value.compareTo(expect) < 0);
                    break;
                default:
                    condicao = false;
                    break;
            }
        }
        return (value == null)
                ? expect : condicao
                        ? expect : value;
    }
}
