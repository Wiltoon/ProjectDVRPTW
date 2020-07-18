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
import com.mymapper.projectdvrptw.entity.Vehicle;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Wilton Costa
 */
public class ControllerEstatic {
    /**
     * Cria centroides no mapa e cria as rotas para os veículos correspondentes
     * @param mapa 
     */
    public static void criarCentroidesRotas(MapaPrincipal mapa){
        //Por K-Means
        //Qtd de carros necessarios
        mapa.capacityTotal();
        // K minimo me informa a quantidade minima de carros que são necessários
        int Kminimo = mapa.def.CAPACIDADE_TOTAL_MAPA/mapa.def.CAPACIDADE_MAXIMA_DOS_VEICULOS;
        int Kreg = 5*Kminimo/4;
//        int Kreg = 10;
        if(mapa.def.QTD_TOTAL_DE_VEICULOS < Kreg){
            Kreg = mapa.def.QTD_TOTAL_DE_VEICULOS;
        }
        Map<Centroid,List<Pedido>> mapper = new HashMap<Centroid,List<Pedido>>();
        do{
            mapper = KlusterMean.fit(Kreg, mapa.getAllPedidos(), mapa);
        }while(Kminimo+1 > mapper.size());
        for(Centroid key : mapper.keySet()){
            key.setPedidos(mapper.get(key));
            mapa.adicionaZona(key);            
        }
        criarRotas(mapa);
    }
    public static void criarRotas(MapaPrincipal mapa){
        int id = 0;
        for(Centroid cent : mapa.getZonas()){
            cent.constroiRota();
            mapa.def.QTD_DE_VEICULOS_USADOS++;
            id = mapa.def.QTD_DE_VEICULOS_USADOS;
            Vehicle car = Vehicle.usarVehicle(id, mapa);
            car.setUsado(Boolean.TRUE);
            car.setRotaSelecionada(cent.getRotas().get(0));
            car.getRotaSelecionada().heuristicaAestrela(mapa.def, cent.getPedidos());
            car.getRotaSelecionada().distanceAndTimeForRoute(mapa.def);
            cent.setVeiculoSelecionado(car);
        }
        //Criar rotas com heuristica A*
        //Verificar se tem tempo suficiente
        //Verificar melhores distancias de cada centroide
        //Verificar quantos veiculos serao necessarios
    }
    
}
