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
import java.util.List;
import java.util.Map;

/**
 *
 * @author Wilton Costa
 */
public class ControllerEstatic {
    public void criarCentroides(MapaPrincipal mapa){
        //Por K-Means
        //Qtd de carros necessarios
        Definy def = new Definy();
        mapa.capacityTotal(def);
        // K minimo me informa a quantidade minima de carros que são necessários
        int Kminimo = def.CAPACIDADE_TOTAL_MAPA/def.CAPACIDADE_MAXIMA_DOS_VEICULOS;
        int Kreg = 5*Kminimo/4;
        if(def.QTD_TOTAL_DE_VEICULOS < Kreg){
            Kreg = def.QTD_TOTAL_DE_VEICULOS;
        }
        Map<Centroid,List<Pedido>> mapper = KlusterMean.fit(Kreg, mapa.getAllPedidos(), def.INTERACOES);
        for(Centroid key : mapper.keySet()){
            key.setPedidos(mapper.get(key));
            mapa.adicionaZona(key);            
        }
        criarRotas(mapa);
    }
    public void criarRotas(MapaPrincipal mapa){
        
        //Criar rotas com heuristica A*
        //Verificar se tem tempo suficiente
        //Verificar melhores distancias de cada centroide
        //Verificar quantos veiculos serao necessarios
    }
}
