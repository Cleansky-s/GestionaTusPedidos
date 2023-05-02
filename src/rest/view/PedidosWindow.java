package rest.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import rest.control.Controller;
import rest.model.Cliente;
import rest.model.GestionObserver;
import rest.model.Pedido;
import rest.model.Proveedor;

public class PedidosWindow extends JPanel implements GestionObserver {

	private Controller ctrl;
	
	private PedidosCerrarDialog cerrarDialog;
	private JButton cerrarButton;
	
	private PedidosConsultarDialog consultarDialog;
	private JButton consultarButton;
	
	private PedidosModificarDialog modificarDialog;
	private JButton modificarButton;
	
	private PedidosPedirDialog pedirDialog;
	private JButton pedirButton;
	
	public PedidosWindow(Controller ctrl) {
		this.ctrl = ctrl;
		//ctrl.addObserver(this);
		initGUI();
	}
	
	private void initGUI() {
		setLayout(new FlowLayout(FlowLayout.LEFT, 25, 25));
		
		cerrarButton = new JButton();
		cerrarButton.setBackground(Color.red);
		cerrarButton.setMaximumSize(new Dimension(120, 120));
		cerrarButton.setMinimumSize(new Dimension(120, 120));
		cerrarButton.setPreferredSize(new Dimension(120, 120));
		cerrarButton.setToolTipText("Cerrar una cuenta");
		cerrarButton.setText("CERRAR");
		cerrarButton.addActionListener((e)->{
			cerrarDialog = new PedidosCerrarDialog(Utils.getWindow(this), ctrl);
			cerrarDialog = null;
		});
		add(cerrarButton);
		
		consultarButton = new JButton();
		consultarButton.setBackground(Color.blue);
		consultarButton.setMaximumSize(new Dimension(120, 120));
		consultarButton.setMinimumSize(new Dimension(120, 120));
		consultarButton.setPreferredSize(new Dimension(120, 120));
		consultarButton.setToolTipText("Consultar pedidos");
		consultarButton.setText("CONSULTAR");
		consultarButton.addActionListener((e)->{
			consultarDialog = new PedidosConsultarDialog(Utils.getWindow(this), ctrl);
			consultarDialog = null;
		});
		add(consultarButton);
		
		modificarButton = new JButton();
		modificarButton.setBackground(Color.cyan);
		modificarButton.setMaximumSize(new Dimension(120, 120));
		modificarButton.setMinimumSize(new Dimension(120, 120));
		modificarButton.setPreferredSize(new Dimension(120, 120));
		modificarButton.setToolTipText("Modificar pedidos");
		modificarButton.setText("MODIFICAR");
		modificarButton.addActionListener((e)->{
			modificarDialog = new PedidosModificarDialog(Utils.getWindow(this), ctrl);
			modificarDialog = null;
		});
		add(modificarButton);
		
		pedirButton = new JButton();
		pedirButton.setBackground(Color.green);
		pedirButton.setMaximumSize(new Dimension(120, 120));
		pedirButton.setMinimumSize(new Dimension(120, 120));
		pedirButton.setPreferredSize(new Dimension(120, 120));
		pedirButton.setToolTipText("Tramitar una orden");
		pedirButton.setText("PEDIR");
		pedirButton.addActionListener((e)->{
			pedirDialog = new PedidosPedirDialog(Utils.getWindow(this), ctrl);
			pedirDialog = null;
		});
		add(pedirButton);
		
		
		
		
		
		setVisible(true);
	}

	@Override
	public void onRegister(List<Cliente> clientes, List<Pedido> pedidos, List<Proveedor> proveedores) {
		// TODO Auto-generated method stub
		
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
