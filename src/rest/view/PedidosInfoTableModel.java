package rest.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import rest.model.Pedido;
import rest.model.Plato;

public class PedidosInfoTableModel extends AbstractTableModel {
	
	String[] header = { "Id", "Nombre", "IdPlato", "Cantidad", "Ingredientes" };
	List<Pedido> pedidos;
	List<Plato> platosList = new ArrayList<>();
	Map<Plato, String> platos = new HashMap<>();
	
	public PedidosInfoTableModel() {
		this.pedidos = new ArrayList<>();
	}
	
	public PedidosInfoTableModel(List<Pedido> pedidos) {
		this.pedidos = pedidos;
		for(Pedido p: pedidos) {
			for(Plato plat: p.getPlatos()) {
				if(!plat.getStateCocina()) {
					platosList.add(plat);
					if(p.getType().equals("Mesa"))
						platos.put(plat, p.getIdMesa());
					else
						platos.put(plat, p.getId());
				}
			}
		}
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
			return platos.get(platosList.get(rowIndex));
		case 1:
			return platosList.get(rowIndex).getNombre();
		case 2:
			return platosList.get(rowIndex).getId();
		case 3:
			return platosList.get(rowIndex).getCant();
		case 4:
			return platosList.get(rowIndex).getIngredientes();
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
