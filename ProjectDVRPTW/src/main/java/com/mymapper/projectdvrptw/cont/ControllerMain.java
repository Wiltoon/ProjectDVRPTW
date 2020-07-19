/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mymapper.projectdvrptw.cont;

import com.mymapper.projectdvrptw.entity.MapaPrincipal;
import com.mymapper.projectdvrptw.parser.DataReader;
import com.mymapper.projectdvrptw.view.StartMapper;
import java.io.File;

/**
 *
 * @author Wilton Costa
 */
public class ControllerMain extends Thread {

    public MapaPrincipal mapaPrincipal = new MapaPrincipal();
    public StartMapper start = new StartMapper();
    @Override
    public void run() {
        long startTime = 0;
        long endTime = 1 * 1000;
        while (startTime < endTime) {
            try {
                sleep(20);
                startTime += 20;
            } catch (InterruptedException erro) {}
            mapaPrincipal.tempoCurrenty = startTime;
            executar(mapaPrincipal,start);
        }
    }

    public static void executar(MapaPrincipal mapa, StartMapper start) {
        start.setMapa(mapa);
        start.paint(start.getGraphics());
    }

}
