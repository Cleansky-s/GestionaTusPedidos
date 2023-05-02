package rest.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.json.JSONArray;
import org.json.JSONException;

import rest.DAO.MenuDAOImpl;
import rest.DAO.PlatoDAOImpl;
import rest.control.Controller;
import rest.factories.Builder;
import rest.factories.BuilderBasedFactory;
import rest.factories.PlatoBuilder;
import rest.factories.PlatoMenuBuilder;
import rest.model.Cliente;
import rest.model.GestionObserver;
import rest.model.Pedido;
import rest.model.Plato;
import rest.model.PlatoMenu;
import rest.model.Proveedor;
import rest.view.Utils;

public class PedidosPedirMesaAnyadirDialog extends JDialog implements GestionObserver {

	private Controller ctrl;
	
	private DefaultComboBoxModel<String> platosModel = new DefaultComboBoxModel<>();
	private JComboBox<String> comboBoxPlatos;
	
	private JTextField cantField;
	private JLabel cantLabel;
	
	private DefaultTableModel dataTableModel;
	private JTable dataTable;
	
	private List<PlatoMenu> menu;
	
	private Plato newPlato;
	
	private String cantStr;
	
	public PedidosPedirMesaAnyadirDialog(Frame parent, Controller ctrl) {
		super(parent, true);
		this.ctrl = ctrl;
		ctrl.addObserver(this);
		this.cantStr = "1";
		setPreferredSize(new Dimension(450, 450));
		setResizable(false);
		setLocationRelativeTo(null);
		initGUI();
	}

	private void initGUI() {
		setTitle("Añadir platos");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new BorderLayout());
		
		//Dropdown
		JPanel platoPanel = new JPanel();
		comboBoxPlatos = new JComboBox<>(platosModel);
		comboBoxPlatos.setPreferredSize(new Dimension(350, 30));
		platoPanel.add(new JLabel("Plato: "));
		platoPanel.add(comboBoxPlatos);
		northPanel.add(platoPanel, BorderLayout.NORTH);
		comboBoxPlatos.addActionListener((e) -> {
			dataTableModel.setRowCount(0);
			Map<String, Double> ingredientes = new HashMap<>();
	        String selectedOption = (String) comboBoxPlatos.getSelectedItem();
	        int i = 0;
			boolean found = false;
			while(i < menu.size() && !found) {
				if(menu.get(i).getNombre().equals(selectedOption)) {
				    found = true;
				    ingredientes = menu.get(i).getIngredientes();
			    }
				i++;
			}
			 
			i = 0;
			
			for(String key: ingredientes.keySet()) {
				ingredientes.get(key);
				dataTableModel.insertRow(i, new String[] {key , ingredientes.get(key).toString()});
				i++;
			}
		});
		
		//Select cant
		JPanel cantPanel = new JPanel(new FlowLayout());
		cantField = new JTextField("1");
		cantLabel = new JLabel("Cantidad: ");
		cantField.setMaximumSize(new Dimension(80, 40));
		cantField.setMinimumSize(new Dimension(80, 40));
		cantField.setPreferredSize(new Dimension(80, 40));
		cantField.addActionListener((e) -> {
			this.cantStr = cantField.getText();
		});
		cantPanel.add(cantLabel);
		cantPanel.add(cantField);
		
		northPanel.add(cantPanel, BorderLayout.SOUTH);
		
		mainPanel.add(northPanel, BorderLayout.NORTH);
		
		//Table ingredientes
		
		JPanel infoPanel = new JPanel();
		
		dataTableModel = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return column == 1;
			}
		};
		
		String[] header = new String[2];
		header[0] = "Ingrediente";
		header[1] = "Cantidad";
		
		dataTableModel.setColumnIdentifiers(header);
		
		dataTable = new JTable(dataTableModel);
		
		infoPanel.add(new JScrollPane(dataTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		
		mainPanel.add(infoPanel);
		
		this.add(mainPanel);
		
		int selection = JOptionPane.showConfirmDialog(
	            null, mainPanel, "Modificar Cliente : "
	            , JOptionPane.OK_CANCEL_OPTION
	            , JOptionPane.PLAIN_MESSAGE);
		
	    if (selection == JOptionPane.OK_OPTION){
	    	
	    	//Ingredientes
	    	Map<String, Double> ingredientes = new HashMap<>();
	    	for(int i = 0; i < dataTableModel.getRowCount(); i++) {
	            //HAY DATOS
	            if(!(dataTableModel.getValueAt(i, 0).toString().equals(""))) {
	            	Double cant = 0.0;
	            	cant = Double.parseDouble(dataTableModel.getValueAt(i, 1).toString());
	            	ingredientes.put(dataTableModel.getValueAt(i, 0).toString(), cant);
	            }
	        }
	    	//Nombre
	    	String nombre = (String) comboBoxPlatos.getSelectedItem();
	    	
	    	//ID
	    	List<Builder<Plato>> b2 = new Vector<>();
            b2.add(new PlatoBuilder());
    		
    		PlatoDAOImpl daoPlato = new PlatoDAOImpl(new BuilderBasedFactory<Plato>(b2));
    		
    		String id = daoPlato.GenerateNewId();
    		
    		//Price
    		Double price = 0.0;
    		for(PlatoMenu p: menu) {
    			if(p.getNombre().equals(nombre)) {
    				price = p.getPrice();
    			}
    		}
    		
    		//Cant
    		Integer cant = Integer.parseInt(cantStr);
    		
    		newPlato = new Plato(id, nombre, ingredientes, price, cant, false, false);
	    
            JOptionPane.showMessageDialog(null
                    , "Creado con exito"
                    , "Bienvenido "
                    , JOptionPane.PLAIN_MESSAGE);
	    }
	    else if (selection == JOptionPane.CANCEL_OPTION){
	    	// Do something here.
	    }
		
	}

	public Plato getPlato() {
		return newPlato;
	}
	
	
	@Override
	public void onRegister(List<Cliente> clientes, List<Pedido> pedidos, List<Proveedor> proveedores) {
		List<Builder<PlatoMenu>> b2 = new Vector<>();
        b2.add(new PlatoMenuBuilder());
		
		MenuDAOImpl daoMenu = new MenuDAOImpl(new BuilderBasedFactory<PlatoMenu>(b2));
		
		try {
			daoMenu.read();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.menu = daoMenu.listAll();
		
		for(PlatoMenu p: menu)
			this.platosModel.addElement(p.getNombre());

		
	}

	@Override
	public void onCliAdded(Cliente cli) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPedAdded(Pedido ped) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProvUpdated(Proveedor prov) {
		// TODO Auto-generated method stub
		
	}
}
