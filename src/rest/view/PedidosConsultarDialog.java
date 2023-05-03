package rest.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import rest.control.Controller;
import rest.model.Cliente;
import rest.model.GestionObserver;
import rest.model.Pedido;
import rest.model.Plato;
import rest.model.Proveedor;

public class PedidosConsultarDialog extends JDialog implements GestionObserver {

	private Controller ctrl;
	
	List<Pedido> pedidosMesa = new ArrayList<Pedido>();
	List<Pedido> pedidosDelivery = new ArrayList<Pedido>();
	
	private DefaultComboBoxModel<String> optionsModel = new DefaultComboBoxModel<>();
	private DefaultComboBoxModel<String> pedidosModel;
	private JComboBox<String> comboBoxOptions;
	private JComboBox<String> comboBoxPedidos;
	
	private JButton consultarButton;
	
	private TableModel platos;
	private JTable platosTable;
	
	public PedidosConsultarDialog(Frame parent, Controller ctrl) {
		super(parent, true);
		this.ctrl = ctrl;
		ctrl.addObserver(this);
		setPreferredSize(new Dimension(450, 450));
		setResizable(false);
		setLocationRelativeTo(null);
		initGUI();
	}
	
	private void initGUI() {
		setTitle("Consultar pedido");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		JPanel boxesPanel = new JPanel();
		boxesPanel.setLayout(new BorderLayout());
		boxesPanel.setAlignmentX(CENTER_ALIGNMENT);
		
		JPanel typePanel = new JPanel();
		comboBoxOptions = new JComboBox<>(optionsModel);
		comboBoxOptions.setPreferredSize(new Dimension(350, 30));
		typePanel.add(new JLabel("Tipo: "));
		typePanel.add(comboBoxOptions);
		boxesPanel.add(typePanel, BorderLayout.NORTH);
		comboBoxOptions.addActionListener((e) -> {
	        String selectedOption = (String) comboBoxOptions.getSelectedItem();
	        consultarButton.setEnabled(true);
	        if (selectedOption.equals("Mesa")) {
	            updatePedidosModel(pedidosMesa);
	        } else {
	            updatePedidosModel(pedidosDelivery);
	        }
		});
		
		JPanel pedidoPanel = new JPanel();
		pedidosModel = new DefaultComboBoxModel<>();
		comboBoxPedidos = new JComboBox<>(pedidosModel);
		comboBoxPedidos.setPreferredSize(new Dimension(350, 30));
		pedidoPanel.add(new JLabel("Id: "));
		pedidoPanel.add(comboBoxPedidos);
		boxesPanel.add(pedidoPanel, BorderLayout.CENTER);
		comboBoxOptions.addActionListener((e) -> {
			
		});
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		buttonPanel.add(Box.createHorizontalGlue());
		
		consultarButton = new JButton();
		consultarButton.setEnabled(false);
		consultarButton.setMaximumSize(new Dimension(75, 75));
		consultarButton.setMinimumSize(new Dimension(75, 75));
		consultarButton.setPreferredSize(new Dimension(75, 75));
		consultarButton.setToolTipText("Consultar un pedido");
		consultarButton.setText("Consultar");
		consultarButton.addActionListener((e)->{
			List<Plato> platos = new ArrayList<Plato>();
			String selectedOption = (String) comboBoxOptions.getSelectedItem();
			 if (selectedOption.equals("Mesa")) {
				 int i = 0;
				 boolean found = false;
				 String mesa = comboBoxPedidos.getSelectedItem().toString();
				 while(i < pedidosMesa.size() && !found) {
					 if(pedidosMesa.get(i).getIdMesa().equals(mesa)) {
						 found = true;
						 platos = pedidosMesa.get(i).getPlatos();
					 }
					 i++;
				 }
		     } 
			 else {
				 int i = 0;
				 boolean found = false;
				 String id = comboBoxPedidos.getSelectedItem().toString();
				 while(i < pedidosDelivery.size() && !found) {
					 if(pedidosDelivery.get(i).getId().equals(id)) {
						 found = true;
						 platos = pedidosDelivery.get(i).getPlatos();
					 }
					 i++;
				 }
		     }
			
			 this.platos = new PlatosTableModel(platos);
			 platosTable.setModel(this.platos);
			 platosTable.repaint();
		});
		
		buttonPanel.add(consultarButton);
		
		buttonPanel.add(Box.createHorizontalGlue());
		
		boxesPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		mainPanel.add(boxesPanel, BorderLayout.NORTH);
		
		JPanel infoPanel = new JPanel();
		this.platos = new PlatosTableModel();
	
		platosTable = new JTable(this.platos);

		
		infoPanel.add(new JScrollPane(platosTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		
		mainPanel.add(infoPanel);
		
		/*
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setAlignmentX(CENTER_ALIGNMENT);
		// _toolaBar = new JToolBar();
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener((e) -> {
			// Si el usuario pulsa el bot�n Cancel, simplemente pon el _status a 0 y haz el di�logo invisible.
			setVisible(false);
		});
		buttonsPanel.add(cancelButton, JButton.CENTER_ALIGNMENT);
		
		// TODO crear los botones OK y Cancel y a�adirlos al pane
		okButton = new JButton("Ok");
		okButton.addActionListener((e) -> {
			setVisible(false);
		});
		buttonsPanel.add(okButton);
		
		mainPanel.add(buttonsPanel, BorderLayout.SOUTH);
		*/
		
		this.add(mainPanel);
		
		int selection = JOptionPane.showConfirmDialog(
	            null, mainPanel, "Consultar un pedido : "
	            , JOptionPane.OK_CANCEL_OPTION
	            , JOptionPane.PLAIN_MESSAGE);
		
	    if (selection == JOptionPane.OK_OPTION){
	        	
            JOptionPane.showMessageDialog(null
                    , "Consultado con exito"
                    , "Bienvenido "
                    , JOptionPane.PLAIN_MESSAGE);
	    }
	    else if (selection == JOptionPane.CANCEL_OPTION){
	    	// Do something here.
	    }
		
		pack();
	}

	@Override
	public void onRegister(List<Cliente> clientes, List<Pedido> pedidos, List<Proveedor> proveedores) {
		optionsModel.addElement("Mesa");
		optionsModel.addElement("Delivery");
		
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
		}
		else {
			this.pedidosDelivery.add(ped);
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
			if(i < pedidosMesa.size())
				pedidosMesa.remove(i);
		}
		else {
			i = this.find(pedidosDelivery, ped);
			if(i < pedidosDelivery.size())
				pedidosDelivery.remove(i);
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
		}
		else {
			i = this.find(pedidosDelivery, ped);
			if(i < pedidosDelivery.size()) {
				pedidosDelivery.remove(i);
			}
			pedidosDelivery.add(ped);
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
	
	private void updatePedidosModel(List<Pedido> pedidos) {
	    pedidosModel.removeAllElements();
	    for (Pedido p : pedidos) {
	    	if(p.getType().equals("Mesa")) {
	    		 pedidosModel.addElement(p.getIdMesa());
	    	}
	    	else {
	    		pedidosModel.addElement(p.getId());
	    	}
	    }
	       
	}
}
