/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mymapper.projectdvrptw.view;

import com.mymapper.projectdvrptw.entity.Pedido;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Wilton Costa
 */
public class PedidoTableModel extends AbstractTableModel{
    private List<Pedido> dataModel = new ArrayList<>();
    private String[] titleTable = {"Customer","xCoord","yCoord","QtdItem","ServiceTime","TempoChegada"};

    public List<Pedido> getDataModel() {
        return dataModel;
    }

    public void setDataModel(List<Pedido> dataModel) {
        this.dataModel = dataModel;
    }

    @Override
    public int getRowCount() {
        return dataModel.size();
    }

    @Override
    public int getColumnCount() {
        return titleTable.length;
    }

    @Override
    public Object getValueAt(int row, int column) {
        switch(column){
            case 0:
                return dataModel.get(row).getId();
            case 1:
                return dataModel.get(row).getCoord().x;
            case 2:
                return dataModel.get(row).getCoord().y;
            case 3:
                return dataModel.get(row).getQtd();
            case 4:
                return dataModel.get(row).getTempoEntrega();
            case 5:
                return dataModel.get(row).getTempoPedido();
            default:
                return null;
        }
    }
    @Override
    public String getColumnName(int column){
        return titleTable[column];
    }
    
}
