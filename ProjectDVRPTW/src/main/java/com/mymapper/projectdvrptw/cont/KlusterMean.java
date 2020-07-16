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

    public static Map<Centroid, List<Pedido>> fit(int k, List<Pedido> pedidos, int maxInteration) {
        Map<Centroid, List<Pedido>> clusters = new HashMap<>();
        Map<Centroid, List<Pedido>> lastState = new HashMap<>();
        List<Centroid> rotas = gerarCentroids(pedidos, k);
        for (int i = 0; i < maxInteration; i++) {
            boolean lastIteration = i == maxInteration - 1;
            for (Pedido pedido : pedidos) {
                Centroid centroid = nearestCentroid(pedido, rotas);
                pedidoDoCentroid(clusters, centroid, pedido);
            }
            boolean terminate = lastIteration || clusters.equals(lastState);
            lastState = clusters;
            if (terminate) {
                break;
            }
            rotas = realocateCentroids(clusters);
            clusters = new HashMap<>();
        }
        return lastState;
    }

    public static Centroid nearestCentroid(Pedido pedido, List<Centroid> centroids) {
        double minDistance = Double.MAX_VALUE;
        Centroid nearest = null;
        for (Centroid centroid : centroids) {
            BigDecimal distancia = centroid.distanciaPedido(pedido.getCoord());
            if (distancia.doubleValue() < minDistance) {
                minDistance = distancia.doubleValue();
                nearest = centroid;
            }
        }
        return nearest;
    }

    public static void pedidoDoCentroid(Map<Centroid, List<Pedido>> clusters, Centroid centroid, Pedido pedido) {
        centroid.adicionarPedidoNaRota(pedido);
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

    public static List<Centroid> realocateCentroids(Map<Centroid,List<Pedido>> clusters) {
//        for (Centroid centroid : mapa.getZonas()) {
//            average(centroid,centroid.getPedidos());
//        }
        return clusters.entrySet().stream().map(e -> average(e.getKey(), e.getValue())).collect(toList());
    }

    public static List<Centroid> gerarCentroids(List<Pedido> pedidos, int k) {
        List<Centroid> centroids = new ArrayList<>();
        BigDecimal maxX = null;
        BigDecimal minX = null;
        BigDecimal maxY = null;
        BigDecimal minY = null;

        //Faixa de valores para os Centroids
        for (Pedido pedido : pedidos) {
            maxX = comparacaoNula(maxX, new BigDecimal(pedido.getCoord().x), (maxX.intValue() < pedido.getCoord().x));
            minX = comparacaoNula(minX, new BigDecimal(pedido.getCoord().x), (minX.intValue() > pedido.getCoord().x));
            maxY = comparacaoNula(maxY, new BigDecimal(pedido.getCoord().y), (maxY.intValue() < pedido.getCoord().y));
            minY = comparacaoNula(minY, new BigDecimal(pedido.getCoord().y), (minY.intValue() < pedido.getCoord().y));
        }
        BigDecimal angle = new BigDecimal(360 / k);
        BigDecimal centroX = (maxX.subtract(minX)).divide(new BigDecimal(2));
        BigDecimal centroY = (maxY.subtract(minY)).divide(new BigDecimal(2));
        BigDecimal Rmax = raioMaximo(maxX, maxY, centroX, centroY);
        for (int i = 0; i < k; k++) {
            BigDecimal tethaMIN = angle.multiply(new BigDecimal(i));
            Double raio = random.nextDouble() % Rmax.doubleValue();
            Double tetha = random.nextDouble() % angle.doubleValue() + tethaMIN.doubleValue();
            Double x = centroX.doubleValue() + raio * cosseno(tetha);
            Double y = centroY.doubleValue() + raio * cosseno(tetha);
            if (x >= maxX.doubleValue()) {
                x = x % maxX.doubleValue();
                y = x * seno(tetha);
            }
            if (y >= maxY.doubleValue()) {
                y = y % maxY.doubleValue();
                x = y * cosseno(tetha);
            }
            Centroid centro = new Centroid(new Point(x.intValue(), y.intValue()));
            centroids.add(centro);
        }
        return centroids;
    }

    public static Double cosseno(Double angulo) {
        return Math.cos(Math.toRadians(angulo));
    }

    public static Double seno(Double angulo) {
        return Math.sin(Math.toRadians(angulo));
    }

    public static BigDecimal raioMaximo(BigDecimal x, BigDecimal y, BigDecimal cx, BigDecimal cy) {
        BigDecimal dx = x.subtract(cx);
        BigDecimal dy = y.subtract(cy);
        BigDecimal mod = (dx.pow(2)).add(dy.pow(2));
        return mod.sqrt(MathContext.DECIMAL64);
    }

    public static BigDecimal comparacaoNula(BigDecimal value, BigDecimal expect, Boolean condicao) {
        return (value == null)
                ? expect : condicao
                        ? expect : value;
    }
}
