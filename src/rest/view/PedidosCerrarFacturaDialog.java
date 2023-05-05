package rest.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import rest.control.Controller;
import rest.model.Pedido;

public class PedidosCerrarFacturaDialog extends JDialog {

	private Pedido ped;
	
	public PedidosCerrarFacturaDialog(Frame parent, Pedido ped) {
		super(parent, true);
		this.ped = ped;
		setPreferredSize(new Dimension(450, 450));
		setResizable(false);
		setLocationRelativeTo(null);
		initGUI();
	}

	private void initGUI() {
		JPanel cuentaPanel = new JPanel(new BorderLayout());
    	
    	JPanel totalPanel = new JPanel();
    	JLabel totalLabel = new JLabel("Total: " + ped.getCuenta().toString());
    	totalPanel.add(totalLabel);
    	cuentaPanel.add(totalPanel, BorderLayout.SOUTH);
    	
    	JTable table = new JTable(new CuentaTableModel(ped.getPlatos()));
    	JScrollPane scrollPane = new JScrollPane(table);
    	cuentaPanel.add(scrollPane, BorderLayout.CENTER);
    	
    	this.add(cuentaPanel);
    	
		int selection = JOptionPane.showConfirmDialog(
	            null, cuentaPanel, "Procesar factura : "
	            , JOptionPane.OK_CANCEL_OPTION
	            , JOptionPane.PLAIN_MESSAGE);
		
		    if (selection == JOptionPane.OK_OPTION){


		    }
		    
		    else if (selection == JOptionPane.CANCEL_OPTION){
		    	// Do something here.
		    }
		
	}
}
