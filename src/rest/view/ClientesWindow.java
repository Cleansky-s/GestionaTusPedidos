package rest.view;

import rest.control.Controller;
import rest.model.Cliente;
import rest.model.GestionObserver;
import rest.model.Pedido;
import rest.model.Proveedor;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ClientesWindow extends JPanel implements GestionObserver {
    private Controller ctrl;

    private JButton AltaButton;
    private ClienteAltaDialog altaDialog;
    private JButton BajaButton;
    private ClienteBajaDialog bajaDialog;
    private JButton ModificarButton;
    private ClienteModificarDiaglog modificarDialog;
    private JButton ConsultaButton;
    private ClienteConsultaDialog consultaDialog;
    public ClientesWindow(Controller ctrl) {
        this.ctrl = ctrl;
        //ctrl.addObserver(this);
        initGUI();
    }

    private void initGUI() {
        AltaButton = createButton("ALTA", "Crear un cliente", 120, Color.BLUE);
        AltaButton.addActionListener((e)->{
            altaDialog = new ClienteAltaDialog(Utils.getWindow(this), ctrl);
        });
        add(AltaButton);
        BajaButton = createButton("BAJA", "Eliminar un cliente", 120, Color.RED);
        BajaButton.addActionListener((e)->{
            bajaDialog = new ClienteBajaDialog(Utils.getWindow(this), ctrl);
        });
        add(BajaButton);
        ModificarButton = createButton("MODIFICAR", "Modificar un cliente", 120, Color.GREEN);
        ModificarButton.addActionListener((e)->{
            modificarDialog = new ClienteModificarDiaglog(Utils.getWindow(this), ctrl);
        });
        add(ModificarButton);
        ConsultaButton = createButton("CONSULTA", "Consultar un cliente", 120, Color.LIGHT_GRAY);
        ConsultaButton.addActionListener((e)->{
            consultaDialog = new ClienteConsultaDialog(Utils.getWindow(this), ctrl);
        });
        add(ConsultaButton);

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

    }

    @Override
    public void onCliAdded(Cliente cli) {

    }

    @Override
    public void onPedAdded(Pedido ped) {

    }

    @Override
    public void onProvAdded(Proveedor prov) {

    }

    @Override
    public void onCliDeleted(Cliente cli) {

    }

    @Override
    public void onPedDeleted(Pedido ped) {

    }

    @Override
    public void onProvDeleted(Proveedor prov) {

    }

    @Override
    public void onCliUpdated(Cliente cli) {

    }

    @Override
    public void onPedUpdated(Pedido ped) {

    }

    @Override
    public void onProvUpdated(Proveedor prov) {

    }
}
