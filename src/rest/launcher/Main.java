package rest.launcher;

import rest.DAO.CRUD;
import rest.DAO.ClienteDAOImpl;
import rest.DAO.PedidoDAOImpl;
import rest.DAO.ProveedorDAOImpl;
import rest.control.Controller;
import rest.factories.Builder;
import rest.factories.BuilderBasedFactory;
import rest.factories.ClienteBuilder;
import rest.factories.ProveedorBuilder;
import rest.model.*;
import rest.view.MainWindow;
import rest.view.UserLogin;

import javax.swing.*;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Vector;

public class Main {

    // default values for some parameters
    //
    private static Controller control;

    private static void initController() throws FileNotFoundException {
        control = new Controller();
    }


    private static void startGUIMode() throws Exception {
        //control.loadData();
        //SwingUtilities.invokeAndWait(() -> new MainWindow(control, 0));
        SwingUtilities.invokeLater(() -> {
			try {
				new UserLogin(control);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		});
    }

    private static void start(String[] args) throws Exception {
        startGUIMode();
    }

    public static void main(String[] args) {
        try {
            initController();
            start(args);
        } catch (Exception e) {
            System.err.println("Something went wrong ...");
            System.err.println();
            e.printStackTrace();
        }
    }
}