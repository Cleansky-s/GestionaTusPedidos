package rest.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import rest.control.Controller;
import rest.model.Cliente;
import rest.model.GestionObserver;
import rest.model.Pedido;
import rest.model.PedidoDomicilio;
import rest.model.PedidoLocal;
import rest.model.Plato;
import rest.model.Proveedor;

public class EntregaWindow extends JPanel implements GestionObserver {

private Controller ctrl;
	
	List<Pedido> pedidosMesa = new ArrayList<>();
	List<Pedido> pedidosDelivery = new ArrayList<>();
	
	JButton listoButton;
	
	private TableModel mesa;
	private JTable mesaTable;
	
	private TableModel delivery;
	private JTable deliveryTable;

	private int selectedRow;
	private String selectedTable;
 	
	public EntregaWindow(Controller ctrl) {
		this.ctrl = ctrl;
		ctrl.addObserver(this);
		initGUI();
	}

	private void initGUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		//Boton listo
		JPanel buttonPanel = new JPanel(new FlowLayout());
		
		JLabel listoText = new JLabel(" marcar linea seleccionada como recogida");
		listoButton = new JButton();
		listoButton.setBackground(Color.green);
		listoButton.setMaximumSize(new Dimension(60, 60));
		listoButton.setMinimumSize(new Dimension(60, 60));
		listoButton.setPreferredSize(new Dimension(60, 60));
		listoButton.setText("(X)");
		listoButton.addActionListener((e)->{
			if(selectedTable.equals("Mesa")) {
				Pedido ped = new PedidoLocal();
				Object mesaId = mesa.getValueAt(selectedRow, 0);
				Object platoId = mesa.getValueAt(selectedRow, 2);
				for(Pedido p: pedidosMesa) {
					if(p.getIdMesa().equals(mesaId)) {
						ped = p;
						for(Plato plat: p.getPlatos()) {
							if(platoId.equals(plat.getId())) {
								ped.removePlato(plat);
								plat.setStateCamarero(true);
								ped.addPlato(plat);
								break;
							}
						}
					}
				}
				ctrl.updatePedido(ped);
			}
			else {
				Pedido ped = new PedidoDomicilio();
				Object Id = delivery.getValueAt(selectedRow, 0);
				Object platoId = delivery.getValueAt(selectedRow, 2);
				for(Pedido p: pedidosDelivery) {
					if(p.getId().equals(Id)) {
						ped = p;
						for(Plato plat: p.getPlatos()) {
							if(platoId.equals(plat.getId())) {
								ped.removePlato(plat);
								plat.setStateCamarero(true);
								ped.addPlato(plat);
								break;
							}
						}
					}
				}
				ctrl.updatePedido(ped);
			}
			
			
		});
		buttonPanel.add(listoButton);
		buttonPanel.add(listoText);
		
		mainPanel.add(buttonPanel, BorderLayout.NORTH);
		
		//Tabla mesa
		JPanel infoPanel1 = new JPanel();
		this.mesa = new RecogidasInfoTableModel(this.pedidosMesa);
	
		
		
		mesaTable = new JTable(this.mesa);
		
		ListSelectionModel selectionModel1 = mesaTable.getSelectionModel();
		selectionModel1.addListSelectionListener((e) -> {
			this.selectedRow = mesaTable.getSelectedRow();
			this.selectedTable = "Mesa";
		});
		
		//SET SIZE
		
		//TableColumnModel columnModel = mesaTable.getColumnModel();
		//TableColumn secondColumn = columnModel.getColumn(1);
		//secondColumn.setPreferredWidth(150);
		
		infoPanel1.add(new JScrollPane(mesaTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		
        mainPanel.add(infoPanel1, BorderLayout.CENTER);
		
		//Tabla delivery
		
        JPanel infoPanel2 = new JPanel();
		this.delivery = new RecogidasInfoTableModel(this.pedidosDelivery);
		
		deliveryTable = new JTable(this.delivery);
		
		ListSelectionModel selectionModel2 = deliveryTable.getSelectionModel();
		selectionModel2.addListSelectionListener((e) -> {
			this.selectedRow = deliveryTable.getSelectedRow();
			this.selectedTable = "Delivery";
		});
		
		infoPanel2.add(new JScrollPane(deliveryTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		
        mainPanel.add(infoPanel2, BorderLayout.SOUTH);
        
		this.add(mainPanel);
	}

	@Override
	public void onRegister(List<Cliente> clientes, List<Pedido> pedidos, List<Proveedor> proveedores) {
		for(Pedido p: pedidos) {
			if(p.getType().equals("Mesa")) {
				this.pedidosMesa.add(p);
			}
			else {
				this.pedidosDelivery.add(p);
			}
		}
	}

	@Override
	public void onCliAdded(Cliente cli) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPedAdded(Pedido ped) {
		if(ped.getType().equals("Mesa")) {
			this.pedidosMesa.add(ped);
			this.mesa = new RecogidasInfoTableModel(pedidosMesa);
			mesaTable.setModel(this.mesa);
			mesaTable.repaint();
		}
		else {
			this.pedidosDelivery.add(ped);
			this.delivery = new RecogidasInfoTableModel(pedidosDelivery);
			deliveryTable.setModel(this.delivery);
			deliveryTable.repaint();
		}

	}

	@Override
	public void onProvAdded(Proveedor prov) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCliDeleted(Cliente cli) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPedDeleted(Pedido ped) {
		int i;
		if(ped.getType().equals("Mesa")) {
			i = this.find(pedidosMesa, ped);
			if(i < pedidosMesa.size()) {
				pedidosMesa.remove(i);
				this.mesa = new RecogidasInfoTableModel(pedidosMesa);
				mesaTable.setModel(this.mesa);
				mesaTable.repaint();
			}
				
		}
		else {
			i = this.find(pedidosDelivery, ped);
			if(i < pedidosDelivery.size()) {
				pedidosDelivery.remove(i);
				this.delivery = new RecogidasInfoTableModel(pedidosDelivery);
				deliveryTable.setModel(this.delivery);
				deliveryTable.repaint();
			}
				
		}
		
	}

	@Override
	public void onProvDeleted(Proveedor prov) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCliUpdated(Cliente cli) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPedUpdated(Pedido ped) {
		int i;
		if(ped.getType().equals("Mesa")) {
			i = this.find(pedidosMesa, ped);
			if(i < pedidosMesa.size()) {
				pedidosMesa.remove(i);
			}
			pedidosMesa.add(ped);
			this.mesa = new RecogidasInfoTableModel(pedidosMesa);
			mesaTable.setModel(this.mesa);
			mesaTable.repaint();
		}
		else {
			i = this.find(pedidosDelivery, ped);
			if(i < pedidosDelivery.size()) {
				pedidosDelivery.remove(i);
			}
			pedidosDelivery.add(ped);
			this.delivery = new RecogidasInfoTableModel(pedidosDelivery);
			deliveryTable.setModel(this.delivery);
			deliveryTable.repaint();
		}
		
	}

	@Override
	public void onProvUpdated(Proveedor prov) {
		// TODO Auto-generated method stub
		
	}
	
	private int find(List<Pedido> list, Pedido ped) {
		int i = 0;
		boolean found = false;
		while(i < list.size() && !found) {
			if(list.get(i).getId().equals(ped.getId())) {
				found = true;
				i--;
			}
			i++;
		}
		return i;
	}
	
}
