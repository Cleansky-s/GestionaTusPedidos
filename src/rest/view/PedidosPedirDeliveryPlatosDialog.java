package rest.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableModel;

import rest.DAO.PlatoDAOImpl;
import rest.control.Controller;
import rest.factories.Builder;
import rest.factories.BuilderBasedFactory;
import rest.factories.PlatoBuilder;
import rest.model.Cliente;
import rest.model.GestionObserver;
import rest.model.Pedido;
import rest.model.PedidoDomicilio;
import rest.model.PedidoLocal;
import rest.model.Plato;
import rest.model.Proveedor;

public class PedidosPedirDeliveryPlatosDialog extends JDialog implements GestionObserver {
	
	private Controller ctrl;
	
	private JButton eliminarLineaButton;
	private JButton anyadirButton;
	
	private Integer selectedRow;
	
	private TableModel platos;
	private JTable platosTable;
	
	private List<Plato> platosList = new ArrayList<Plato>();
	
	private JDialog anyadirDialog;
	
	private Pedido pedido;

	public PedidosPedirDeliveryPlatosDialog(Frame parent, Controller ctrl, PedidoDomicilio pedido) {
		super(parent, true);
		this.ctrl = ctrl;
		ctrl.addObserver(this);
		this.pedido = pedido;
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
                null, mainPanel, "Añadir plato : "
                , JOptionPane.OK_CANCEL_OPTION
                , JOptionPane.PLAIN_MESSAGE);
    	
    	    if (selection == JOptionPane.OK_OPTION){
    	    	
    	    	List<Builder<Plato>> b2 = new Vector<>();
	            b2.add(new PlatoBuilder());
	    		PlatoDAOImpl daoPlato = new PlatoDAOImpl(new BuilderBasedFactory<Plato>(b2));
    	        
	    		try {
					daoPlato.read();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    	    	
    	    	Pedido ped = pedido;
    	        
    	        for(Plato p: platosList) {
    	        	ped.addPlato(p);
    	        	daoPlato.create(p);
    	        }
    	        	
    	    	ctrl.updatePedido(ped);
    	    	
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

	}

	@Override
	public void onCliAdded(Cliente cli) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPedAdded(Pedido ped) {

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

	}

	@Override
	public void onProvUpdated(Proveedor prov) {
		// TODO Auto-generated method stub
		
	}
	
}
