package rest.view;

import javax.swing.table.AbstractTableModel;

import rest.model.Plato;

import java.util.ArrayList;
import java.util.List;

public class CuentaTableModel extends AbstractTableModel {

    private List<Plato> platos;
    private String[] columnNames = {"Nombre", "Cantidad", "Precio", "Subtotal"};

    public CuentaTableModel(List<Plato> platos) {
        this.platos = new ArrayList<Plato>(platos);
    }

    @Override
    public int getRowCount() {
        return platos.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Plato plato = platos.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return platos.get(rowIndex).getNombre();
            case 1:
                return platos.get(rowIndex).getCant();
            case 2:
                return platos.get(rowIndex).getPrice();
            case 3:
                return platos.get(rowIndex).getPrice() * platos.get(rowIndex).getCant();
            default:
                throw new IndexOutOfBoundsException("Column index out of bounds");
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}
