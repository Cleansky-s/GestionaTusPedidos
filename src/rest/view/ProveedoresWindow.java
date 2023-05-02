package rest.view;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import rest.control.Controller;
import rest.model.Cliente;
import rest.model.GestionObserver;
import rest.model.Pedido;
import rest.model.Proveedor;

public class ProveedoresWindow extends JPanel implements GestionObserver{
    private Controller ctrl;

    private JButton AltaButton;
    private ProveedorAltaDialog altaDialog;
    private JButton BajaButton;
    private ProveedorBajaDialog bajaDialog;
    private JButton ModificarButton;
    private ProveedorModificarDiaglog modificarDialog;
    private JButton ConsultaButton;
    private ProveedorConsultaDialog consultaDialog;
    private JButton DeudaButton;
    private ProveedorDeudaDialog deudaDialog;
    public ProveedoresWindow(Controller ctrl) {
        this.ctrl = ctrl;
        //ctrl.addObserver(this);
        initGUI();
    }

    private void initGUI() {
        AltaButton = createButton("ALTA", "Crear un proveedor", 120, Color.BLUE);
        AltaButton.addActionListener((e)->{
            altaDialog = new ProveedorAltaDialog(Utils.getWindow(this), ctrl);
        });
        add(AltaButton);
        BajaButton = createButton("BAJA", "Eliminar un proveedor", 120, Color.RED);
        BajaButton.addActionListener((e)->{
            bajaDialog = new ProveedorBajaDialog(Utils.getWindow(this), ctrl);
        });
        add(BajaButton);
        ModificarButton = createButton("MODIFICAR", "Modificar un proveedor", 120, Color.GREEN);
        ModificarButton.addActionListener((e)->{
            modificarDialog = new ProveedorModificarDiaglog(Utils.getWindow(this), ctrl);
        });
        add(ModificarButton);
        ConsultaButton = createButton("CONSULTA", "Consulta un proveedor", 120, Color.LIGHT_GRAY);
        ConsultaButton.addActionListener((e)->{
            consultaDialog = new ProveedorConsultaDialog(Utils.getWindow(this), ctrl);
        });
        add(ConsultaButton);
        DeudaButton = createButton("DEUDA", "Cambiar deuda de un proveedor", 120, Color.MAGENTA);
        DeudaButton.addActionListener((e)->{
        	deudaDialog = new ProveedorDeudaDialog(Utils.getWindow(this), ctrl);
        });
        add(DeudaButton);

    }
    
    private JButton createButton(String text, String toolTip, int square, Color color) {
    	JButton TempButton = new JButton();
    	TempButton.setBackground(color);
    	TempButton.setMaximumSize(new Dimension(square, square));
    	TempButton.setMinimumSize(new Dimension(square, square));
    	TempButton.setPreferredSize(new Dimension(square, square));
    	TempButton.setToolTipText(toolTip);
    	TempButton.setText(text);
        return TempButton;
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
