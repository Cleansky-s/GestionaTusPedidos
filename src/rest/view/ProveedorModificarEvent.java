package rest.view;

import rest.control.Controller;
import rest.model.Cliente;
import rest.model.GestionObserver;
import rest.model.Pedido;
import rest.model.Proveedor;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ProveedorModificarEvent extends JDialog implements GestionObserver {
    private Controller ctrl;
    private final static int dataSize = 6;
    private JTextField[] dataField = new JTextField[dataSize];
    private String datas[] = new String[dataSize];
    private Proveedor p;
    List<Proveedor> proveedorList = new ArrayList<Proveedor>();

    public ProveedorModificarEvent(Frame parent, Controller ctrl, String s){
        super(parent, true);
        this.ctrl = ctrl;
        ctrl.addObserver(this);
        this.p = ctrl.buscarProveedor(s);
        this.initiate();
        initGUI();
    }

    private void initiate(){
        dataField[0] = new JTextField(p.getNombre());
        dataField[1] = new JTextField(p.getdDireccion());
        dataField[2] = new JTextField(p.getTelefono());
        dataField[3] = new JTextField(p.getEmail());
        dataField[4] = new JTextField(p.getWeb());
        // dataField[5] = new JTextField(Double.toString(p.getDeuda()));
        dataField[5] = new JTextField(p.getId());
        dataField[0].setPreferredSize(new Dimension(50, 30));
        dataField[1].setPreferredSize(new Dimension(150, 30));
        dataField[2].setPreferredSize(new Dimension(50, 30));
        dataField[3].setPreferredSize(new Dimension(50, 30));
        dataField[4].setPreferredSize(new Dimension(50, 30));
        dataField[5].setPreferredSize(new Dimension(50, 30));
        
    }

    private void initGUI() {
        this.initiate();
        double pDouble = 0.0;
        setTitle("Modificar Proveedor");
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        JPanel boxesPanel = new JPanel();
        boxesPanel.setAlignmentX(CENTER_ALIGNMENT);
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(2, 2, 5, 5));
        centerPanel.setBorder(
                BorderFactory.createEmptyBorder(5, 5, 5, 5));
        centerPanel.setOpaque(true);
        centerPanel.setBackground(Color.WHITE);

        JLabel nombre = new JLabel("Introduce nombre : ");
        JLabel direccion = new JLabel("Introduce direccion : ");
        JLabel telefono = new JLabel("Introduce telefono : ");
        JLabel email = new JLabel("Introduce email : ");
        JLabel web = new JLabel("Introduce pagina web : ");
        JLabel id = new JLabel("Introduce id : ");
        centerPanel.add(nombre);
        centerPanel.add(dataField[0]);
        centerPanel.add(direccion);
        centerPanel.add(dataField[1]);
        centerPanel.add(telefono);
        centerPanel.add(dataField[2]);
        centerPanel.add(email);
        centerPanel.add(dataField[3]);
        centerPanel.add(web);
        centerPanel.add(dataField[4]);
        mainPanel.add(centerPanel);
        int selection = JOptionPane.showConfirmDialog(
                null, mainPanel, "Modificar Proveedor : "
                , JOptionPane.OK_CANCEL_OPTION
                , JOptionPane.PLAIN_MESSAGE);

        if (selection == JOptionPane.OK_OPTION)
        {
            for ( int i = 0; i < 5; i++)
            {
                datas[i] = String.valueOf(dataField[i].getText());
            }
            /*
            try {
                pDouble = Double.parseDouble(datas[5]);
            } catch (NumberFormatException e) {
                System.err.println("Invalid number format: " + datas[5]);
                pDouble = 0.0;
            }
            */
            Proveedor p1 = 
            		new Proveedor(p.getId(),datas[0],datas[1],datas[2],datas[3],datas[4], p.getDeuda());
            ctrl.updateProveedor(p1);
            JOptionPane.showMessageDialog(null
                    , "Has modificado al proveedor :" + p.getId()
                    , "Bienvenido "
                    , JOptionPane.PLAIN_MESSAGE);
        }
        else if (selection == JOptionPane.CANCEL_OPTION)
        {
            // Do something here.
        }


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
