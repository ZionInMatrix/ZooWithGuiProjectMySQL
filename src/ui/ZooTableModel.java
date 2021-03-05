package ui;

import core.Osetrovatele;

import javax.swing.table.AbstractTableModel;
import java.util.List;

class ZooTableModel extends AbstractTableModel {

    private static final int JMENO_COL = 0;
    private static final int NAROZEN_COL = 1;

    private String[] columnNames = {"Jmeno", "Narozen"};
    private List<Osetrovatele> osetrovateles;

    public ZooTableModel(List<Osetrovatele> theOsetrovateles) {
        osetrovateles = theOsetrovateles;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return osetrovateles.size();
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {

        Osetrovatele tempOsetrovatele = osetrovateles.get(row);

        switch (col) {
            case JMENO_COL:
                return tempOsetrovatele.getJmeno();
            case NAROZEN_COL:
                return tempOsetrovatele.getNarozen();
            default:
                return tempOsetrovatele.getJmeno();
        }
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
}
