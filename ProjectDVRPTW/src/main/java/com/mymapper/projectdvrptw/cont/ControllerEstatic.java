/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mymapper.projectdvrptw.cont;

import com.mymapper.projectdvrptw.defines.Definy;
import com.mymapper.projectdvrptw.entity.MapaPrincipal;

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
        int Kminimo = def.CAPACIDADE_TOTAL_MAPA/def.CAPACIDADE_MAXIMA_DOS_VEICULOS;
    }
    public void criarRotas(MapaPrincipal mapa){
        //Criar rotas com heuristica A*
        //Verificar se tem tempo suficiente
        //Verificar melhores distancias de cada centroide
        //Verificar quantos veiculos serao necessarios
    }
}
