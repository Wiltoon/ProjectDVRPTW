/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mymapper.projectdvrptw.view;

import com.mymapper.projectdvrptw.entity.Centroid;
import com.mymapper.projectdvrptw.entity.MapaPrincipal;
import com.mymapper.projectdvrptw.entity.Pedido;
import com.mymapper.projectdvrptw.entity.Vehicle;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Wilton Costa
 */
public class StartMapper extends javax.swing.JFrame implements Runnable {

    int _WIDTH = 5;
    int _HEIGHT = 5;
    private MapaPrincipal mapa;
    private int Xmin = 2000;
    private int Xmax = 0;
    private int Ymin = 2000;
    private int Ymax = 0;
    private Graphics graf;

    /**
     * Creates new form SelectorInput
     */
    public StartMapper() {
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Mapa do VRP");
        setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public int getWIDTH() {
        return _WIDTH;
    }

    public void setWIDTH(int _WIDTH) {
        this._WIDTH = _WIDTH;
    }

    public int getHEIGHT() {
        return _HEIGHT;
    }

    public void setHEIGHT(int _HEIGHT) {
        this._HEIGHT = _HEIGHT;
    }

    public MapaPrincipal getMapa() {
        return mapa;
    }

    public void setMapa(MapaPrincipal mapa) {
        this.mapa = mapa;
    }

    public int getXmin() {
        return Xmin;
    }

    public void setXmin(int Xmin) {
        this.Xmin = Xmin;
    }

    public int getXmax() {
        return Xmax;
    }

    public void setXmax(int Xmax) {
        this.Xmax = Xmax;
    }

    public int getYmin() {
        return Ymin;
    }

    public void setYmin(int Ymin) {
        this.Ymin = Ymin;
    }

    public int getYmax() {
        return Ymax;
    }

    public void setYmax(int Yman) {
        this.Ymax = Yman;
    }

    public Graphics getGraf() {
        return graf;
    }

    public void setGraf(Graphics graf) {
        this.graf = graf;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Funções necessárias para o DESENHO">
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.clearRect(0, 0, 50000, 50000);
        normalize(mapa.getAllPedidos());
        HashMap<Integer, Pedido> map = drawPoints(g);
        Map<Vehicle, Integer> mapVeiculos = new HashMap<Vehicle, Integer>();
        mapVeiculos = mapa.attPositionVehicles();
        drawCentroids(g, mapa);
        drawRoutes(mapVeiculos, g, mapa);
    }

    public HashMap<Integer, Pedido> drawPoints(Graphics g) {
        HashMap<Integer, Pedido> map = new HashMap<Integer, Pedido>();
        for (Pedido request : getMapa().getAllPedidos()) {
            map.put(request.getId(), request);
            if (request.getQtd() == 0) {
                depositPoint(g, request.getCoord().x, request.getCoord().y);
            } else {
                demandPoint(g, request.getCoord().x, request.getCoord().y);
            }
        }
        return map;
    }

    public void drawCentroids(Graphics g, MapaPrincipal mapa) {
        for (Centroid c : mapa.getZonas()) {
            centroidPoint(g, c.getCentro().x, c.getCentro().y);
        }
    }

    public void drawRoutes(Map<Vehicle, Integer> map, Graphics g, MapaPrincipal mapaMain) {
        Color colors[] = {Color.RED,
            Color.GREEN,
            Color.black,
            Color.PINK,
            Color.DARK_GRAY,
            Color.BLUE,
            Color.MAGENTA,
            Color.ORANGE,
            Color.CYAN,
            Color.YELLOW};
        int i = 0;
        for (Vehicle v : map.keySet()) {
            g.setColor(colors[i % 10]);
            i++;
            if (map.get(v) <= mapaMain.tempoCurrenty) {
                for (int posi = 0; posi < v.getRotaSelecionada().getPedidosOrdenados().size() - 1; posi++) {
//                    if (posi == 0) {
//                        if (p.isVizitado()) {
//                            drawLinePoints(g, mapaMain.def.DEPOSITO, p.getCoord());
//                        }
//                    }
//                    else 
                    if (v.getRotaSelecionada().getPedidosOrdenados().get(posi).getCoord().equals(mapaMain.def.DEPOSITO)) {
                        drawLinePoints(g, mapaMain.def.DEPOSITO, v.getRotaSelecionada().getPedidosOrdenados().get(posi).getCoord());
                        continue;
                    } else {
                        if (posi == 0) {
                            if (v.getRotaSelecionada().getPedidosOrdenados().get(posi).isVizitado()) {
                                drawLinePoints(g, mapaMain.def.DEPOSITO, v.getRotaSelecionada().getPedidosOrdenados().get(posi).getCoord());
                                continue;
                            }
                        }
                    }
                    if (v.getRotaSelecionada().getPedidosOrdenados().get(posi).isVizitado()) {
                        drawLinePoints(g,
                                v.getRotaSelecionada().getPedidosOrdenados().get(posi).getCoord(),
                                v.getRotaSelecionada().getPedidosOrdenados().get(posi + 1).getCoord());
                    }
                }
                int posi = v.getRotaSelecionada().getPedidosOrdenados().size() - 1;
                if (v.getRotaSelecionada().getPedidosOrdenados().get(posi).isVizitado()) {
                    drawLinePoints(g, v.getRotaSelecionada().getPedidosOrdenados().get(posi).getCoord(), mapaMain.def.DEPOSITO);
                }

            }
        }
        g.setColor(Color.WHITE);
    }

    public void drawLinePoints(Graphics g, Point inicial, Point finish) {
        g.drawLine(coordNorm(inicial.x, 'x'), coordNorm(inicial.y, 'y'),
                coordNorm(finish.x, 'x'), coordNorm(finish.y, 'y'));
    }

    public void depositPoint(Graphics g, int x, int y) {
        g.setColor(Color.black);
        g.fillRect(coordNorm(x, 'x') - (_WIDTH / 2), coordNorm(y, 'y') - (_HEIGHT / 2), _WIDTH, _HEIGHT);
        g.setColor(Color.WHITE);
    }

    public void centroidPoint(Graphics g, int x, int y) {
        g.setColor(Color.RED);
        g.fillRect(coordNorm(x, 'x') - (_WIDTH / 2), coordNorm(y, 'y') - (_HEIGHT / 2), _WIDTH, _HEIGHT);
        g.setColor(Color.WHITE);
    }

    public void demandPoint(Graphics g, int x, int y) {
        g.setColor(Color.blue);
        g.fillOval(coordNorm(x, 'x') - (_WIDTH / 2), coordNorm(y, 'y') - (_HEIGHT / 2), _WIDTH, _HEIGHT);
        g.setColor(Color.WHITE);
    }

    public int coordNorm(int coord, char ord) {
        switch (ord) {
            case 'x':
                return (1160 * (coord - getXmin())) / ((getXmax() - getXmin())) + 50;
            case 'y':
                return (660 * (coord - getYmin())) / ((getYmax() - getYmin())) + 50;
            default:
                return coord;
        }
    }

    public void normalize(List<Pedido> coordenadas) {
        for (Pedido coord : coordenadas) {
            if (getXmax() < coord.getCoord().x) {
                setXmax(coord.getCoord().x);
            }
            if (getXmin() > coord.getCoord().x) {
                setXmin(coord.getCoord().x);
            }
            if (getYmax() < coord.getCoord().y) {
                setYmax(coord.getCoord().y);
            }
            if (getYmin() > coord.getCoord().y) {
                setYmin(coord.getCoord().y);
            }
        }
    }

    //</editor-fold>
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StartMapper.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StartMapper.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StartMapper.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StartMapper.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StartMapper().setVisible(true);
            }
        });
    }

    @Override
    public void run() {
        paint(this.getGraphics());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
