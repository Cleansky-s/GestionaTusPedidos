package rest.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import rest.DAO.PedidoDAOImpl;
import rest.DAO.PlatoDAOImpl;
import rest.control.Controller;
import rest.factories.Builder;
import rest.factories.BuilderBasedFactory;
import rest.factories.PedidoDomicilioBuilder;
import rest.factories.PedidoLocalBuilder;
import rest.factories.PlatoBuilder;
import rest.model.Cliente;
import rest.model.GestionObserver;
import rest.model.Pedido;
import rest.model.PedidoLocal;
import rest.model.Plato;
import rest.model.Proveedor;

public class PedidosPedirMesaDialog extends JDialog implements GestionObserver {

	private final Integer NUM_MESAS = 9;
	
	private Controller ctrl;
	
	private JButton eliminarLineaButton;
	private JButton anyadirButton;
	
	private DefaultComboBoxModel<String> mesasModel = new DefaultComboBoxModel<>();
	private JComboBox<String> comboBoxMesas;
	
	private TableModel platos;
	private JTable platosTable;
	private List<Pedido> pedidosMesa = new ArrayList<Pedido>();
	
	private Integer selectedRow;
	private Pedido selectedPedido;
	private String idPlato;
	
	private JDialog anyadirDialog;
	
	private List<Plato> platosList = new ArrayList<Plato>();

	public PedidosPedirMesaDialog(Frame parent, Controller ctrl) {
		super(parent, true);
		this.ctrl = ctrl;
		ctrl.addObserver(this);
		setPreferredSize(new Dimension(450, 450));
		setResizable(false);
		setLocationRelativeTo(null);
		initGUI();
	}

	private void initGUI() {
		setTitle("Pedir platos");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new BorderLayout());
		
		//Boton eliminar
		JPanel eliminarPanel = new JPanel(new FlowLayout());
		eliminarLineaButton = new JButton("(-)");
		eliminarLineaButton.addActionListener((e) -> {
			platosList.remove((int) selectedRow);
			this.platos = new PlatosTableModel(platosList);
			platosTable.setModel(this.platos);
			platosTable.repaint();
		});
        
        JLabel eliminarLabel = new JLabel(" eliminar plato de la linea seleccionada");
        
        eliminarPanel.add(eliminarLineaButton);
        eliminarPanel.add(eliminarLabel);
        
        northPanel.add(eliminarPanel, BorderLayout.NORTH);
        
        //Boton anyadir
        JPanel anyadirPanel = new JPanel(new FlowLayout());
        anyadirButton = new JButton("(+)");
        anyadirButton.addActionListener((e) -> {
			anyadirDialog = new PedidosPedirMesaAnyadirDialog(Utils.getWindow(this), this.ctrl);
			platosList.add(((PedidosPedirMesaAnyadirDialog) anyadirDialog).getPlato());
			this.platos = new PlatosTableModel(platosList);
			platosTable.setModel(this.platos);
			platosTable.repaint();
			anyadirDialog = null;
		});
        
        JLabel anyadirLabel = new JLabel(" añadir nuevo plato");
        
        anyadirPanel.add(anyadirButton);
        anyadirPanel.add(anyadirLabel);
        
        northPanel.add(anyadirPanel, BorderLayout.SOUTH);
        
        mainPanel.add(northPanel, BorderLayout.NORTH);
        
        //Dropdown
        JPanel mesaPanel = new JPanel();
		comboBoxMesas = new JComboBox<>(mesasModel);
		comboBoxMesas.setPreferredSize(new Dimension(350, 30));
		mesaPanel.add(new JLabel("Mesa: "));
		mesaPanel.add(comboBoxMesas);
		northPanel.add(mesaPanel, BorderLayout.CENTER);
		comboBoxMesas.addActionListener((e) -> {
			comboBoxMesas.setEnabled(false);
			platosList.clear();
			this.platos = new PlatosTableModel(platosList);
			platosTable.setModel(this.platos);
			platosTable.repaint();
		});
		
		//Tabla de platos
		JPanel infoPanel = new JPanel();
		this.platos = new PlatosTableModel();
	
		platosTable = new JTable(this.platos);

		ListSelectionModel selectionModel = platosTable.getSelectionModel();
		selectionModel.addListSelectionListener((e) -> {
			this.selectedRow = platosTable.getSelectedRow();
		});
		
		infoPanel.add(new JScrollPane(platosTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		
        mainPanel.add(infoPanel);
        
        this.add(mainPanel);
       
        int selection = JOptionPane.showConfirmDialog(
                null, mainPanel, "Modificar Cliente : "
                , JOptionPane.OK_CANCEL_OPTION
                , JOptionPane.PLAIN_MESSAGE);
    	
    	    if (selection == JOptionPane.OK_OPTION){
    	    	
    	    	Pedido ped = new PedidoLocal();
    	    	boolean found = false;
    	    	
    	        for(Pedido p: pedidosMesa) {
    	        	if(p.getIdMesa().equals(comboBoxMesas.getSelectedItem().toString())) {
    	        		ped = p;
    	        		found = true;
    	        		break;
    	        	}
    	        }
    	        
    	        if(!found) {
    	        	ped = new PedidoLocal(ctrl.generateIdPedido() , null, platosList, 0.0, comboBoxMesas.getSelectedItem().toString());
    	        	ctrl.createPedido(ped);
    	        }
    	        
    	        else {
    	        	for(Plato p: platosList)
        	        	ped.addPlato(p);
    	        	ctrl.updatePedido(ped);
    	        }	
    	    	
                JOptionPane.showMessageDialog(null
                        , "Creado con exito"
                        , "Bienvenido "
                        , JOptionPane.PLAIN_MESSAGE);
    	    }
    	    else if (selection == JOptionPane.CANCEL_OPTION){
    	    	// Do something here.
    	    }
		
	}

	@Override
	public void onRegister(List<Cliente> clientes, List<Pedido> pedidos, List<Proveedor> proveedores) {
		for(Pedido p: pedidos) {
			if(p.getType().equals("Mesa")) {
				this.pedidosMesa.add(p);
				//mesasModel.addElement(p.getIdMesa());
			}
		}

		for(Integer i = 1; i <= NUM_MESAS; i++) {
			mesasModel.addElement(i.toString());
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
