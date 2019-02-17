package model;

import entidade.DataExecucao;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import util.Utils;


public class DataExecucaoTableModel extends AbstractTableModel {

    private List<DataExecucao> dados = new ArrayList<>();
    private String[] colunas = {"Cód", "Data de diretório", "Situação PAJ"};

    @Override
    public String getColumnName(int colunm) {
        return colunas[colunm];
    }

    @Override
    public int getRowCount() {
        return dados.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch (coluna) {

            case 0:
                   return dados.get(linha).getId();

            case 1:
        {
            try {
                return Utils.formataDataDMY(Utils.formataDateSQL(dados.get(linha).getDataExec().getTime()));
            } catch (Exception ex) {
                Logger.getLogger(DataExecucaoTableModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

            case 2:
                return dados.get(linha).getRealizada();

           

        }
        return null;
    }

    public String[] getColunas() {
        return colunas;
    }

    public void addRow(DataExecucao t) {

        dados.add(t);
        fireTableDataChanged();

    }

    public void removeRow(int linha) {
        dados.remove(linha);
        fireTableRowsDeleted(linha, linha);

    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {

        return true;

    }

    @Override
    public void setValueAt(Object valor, int linha, int coluna) {

        switch (coluna) {

            case 1:
                dados.get(linha).setRealizada((String) valor);
                break;
                

                
                

        }

        fireTableRowsUpdated(linha, linha);

    }

}
