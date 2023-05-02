package rest.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

import rest.control.Controller;

public class MainWindow extends JFrame {
	private Controller ctrl;
	private Integer access;
	
	public MainWindow(Controller ctrl, Integer access) { 
		super("Gestiona Tus Pedidos"); 
		this.ctrl = ctrl; 
		this.access = access;
		initGUI();
		
		this.setSize(new Dimension(600, 600));
		this.setLocationRelativeTo(null);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
	
	private void initGUI() { 
		JPanel mainPanel = new JPanel(new BorderLayout()); 
		setContentPane(mainPanel);
	
		JTabbedPane tabbedPane = new JTabbedPane();
		
		/*
		 * admin = 0
		 * cocina = 1
		 * camarero = 2
		 */
		
		//COCINA
		if(access == 0 || access == 1) {
			JPanel panelCocina = new CocinaWindow(ctrl);
			tabbedPane.addTab("Cocina", panelCocina);
			
		}
		
		//ENTREGA
		if(access == 0 || access == 2) {
			JPanel panelEntrega = new EntregaWindow(ctrl);
			tabbedPane.addTab("Entrega", panelEntrega);
		}
		
		
		//CLIENTES
		if(access == 0 || access == 2) {
			JPanel panelClientes = new ClientesWindow(ctrl);
			tabbedPane.addTab("Clientes", panelClientes);
		}
		
		//PROVEEDORES
		if(access == 0) {
			JPanel panelProveedores = new ProveedoresWindow(ctrl);
			tabbedPane.addTab("Proveedores", panelProveedores);
		}
		
		//PEDIDOS
		if(access == 0 || access == 2) {
			JPanel panelPedidos = new PedidosWindow(ctrl);
			tabbedPane.addTab("Pedidos", panelPedidos);
		}
	
		mainPanel.add(tabbedPane);
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
}