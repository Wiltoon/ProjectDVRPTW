/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mymapper.projectdvrptw.defines;

import com.mymapper.projectdvrptw.entity.MapaPrincipal;
import java.awt.Point;
import java.math.BigDecimal;

/**
 *
 * @author Wilton Costa
 */
public class Definy {
    /**
     * Tempo máximo
     */
    public BigDecimal TEMPO_MAX = BigDecimal.valueOf(100);
    /**
     * Localização do Deposito
     */
    public Point DEPOSITO = new Point(0,0);
    /**
     * Capacidade total necessária para entregar todos os pedidos
     */
    public int CAPACIDADE_TOTAL_MAPA = 0;
    /**
     * Capacidade Maxima dos veiculos
     */
    public int CAPACIDADE_MAXIMA_DOS_VEICULOS = 20;
    /**
     * Quatidade total de veiculos no mapa
     */
    public int QTD_TOTAL_DE_VEICULOS = 0;
    /**
     * Quantidade de veiculos utilizados
     */
    public int QTD_DE_VEICULOS_USADOS = 0;
    /**
     * Quantidade maxima de interações para o K-Means
     */
    public int INTERACOES = 100;
    /**
     * Velocidade dos Veiculos, quantos pontos/segundos
     */
    public static int VELOCIDADE_CARROS = 3;
}
