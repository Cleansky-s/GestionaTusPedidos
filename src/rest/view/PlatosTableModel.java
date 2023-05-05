package rest.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import rest.model.Plato;

public class PlatosTableModel extends AbstractTableModel {

	String[] header = { "Nombre", "Cantidad", "Estado" };
	List<Plato> platos;
	
	public PlatosTableModel() {
		this.platos = new ArrayList<>();
	}
	
	public PlatosTableModel(List<Plato> platos) {
		this.platos = platos;
	}
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return  platos.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return header.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex) {
		case 0:
			return platos.get(rowIndex).getNombre();
		case 1:
			return platos.get(rowIndex).getCant();
		case 2:
			if(!platos.get(rowIndex).getStateCocina() && !platos.get(rowIndex).getStateCamarero())
				return "En cocina";
			else if(platos.get(rowIndex).getStateCocina() && !platos.get(rowIndex).getStateCamarero())
				return "Listo";
			else if(platos.get(rowIndex).getStateCocina() && platos.get(rowIndex).getStateCamarero())
				return "Recogido";
		default:
			return null;
		}
	}
	
	public String getColumnName(int columnIndex) {
	    return header[columnIndex];
	}
	
	public String[] getHeader() {
		return header;
	}

}
