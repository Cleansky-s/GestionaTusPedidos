package rest.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import rest.DAO.CRUD;
import rest.DAO.UserDAOImpl;
import rest.control.Controller;
import rest.factories.Builder;
import rest.factories.BuilderBasedFactory;
import rest.factories.ClienteBuilder;
import rest.factories.UserBuilder;
import rest.misc.User;
import rest.model.Persona;
import rest.model.Proveedor;

public class UserLogin extends JFrame implements ActionListener {
	
	private static CRUD<User> daoUser;
	private static BuilderBasedFactory<User> fac;

	private Map<String, Integer> keys;
	
	private Controller ctrl;
	
	private JLabel userLabel, passwordLabel;
    private JTextField userTextField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public UserLogin(Controller ctrl) throws FileNotFoundException {
    	
    	this.ctrl = ctrl; 
    	this.keys = new HashMap<String, Integer>();
    	
    	List<Builder<User>> list = new Vector<>();
    	list.add(new UserBuilder());
    	this.fac = new BuilderBasedFactory<User>(list);
    	this.daoUser = new UserDAOImpl(this.fac);
    	this.daoUser.read();
    	
    	List<User> l1 = daoUser.listAll();
    	
    	int i = 0;
    	for(User user: l1) {
    		keys.put(user.getUsername(), i);
    		i++;
    	}
    	
        setTitle("User Login");
        setLayout(new BorderLayout());
        setSize(400, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        initGUI();
    }
    
    private void initGUI() {
    	JPanel formPanel = new JPanel();
        formPanel.setLayout(new BorderLayout());

        JPanel inputLabels = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(3, 3, 3, 3);
        userLabel = new JLabel("Username:");
        inputLabels.add(userLabel, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.EAST;
        userTextField = new JTextField();
        userTextField.setColumns(20);
        inputLabels.add(userTextField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        passwordLabel = new JLabel("Password:");
        inputLabels.add(passwordLabel, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.EAST;
        passwordField = new JPasswordField();
        passwordField.setColumns(20);
        inputLabels.add(passwordField, gbc);
        
        formPanel.add(inputLabels);

        JPanel buttonPanel = new JPanel();

        loginButton = new JButton("Login");

        loginButton.addActionListener(this);
        loginButton.setSize(3, 2);
        buttonPanel.add(loginButton);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints centerGbc = new GridBagConstraints();
        centerGbc.gridx = 0;
        centerGbc.gridy = 0;
        centerGbc.weightx = 1;
        centerGbc.weighty = 1;
        centerGbc.fill = GridBagConstraints.CENTER;
        centerPanel.add(formPanel, centerGbc);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        this.add(mainPanel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String username = userTextField.getText();
        String password = String.valueOf(passwordField.getPassword());

        if (isValidCredentials(username, password)) {
			 dispose(); // close the UserLogin window
			 MainWindow mainWindow = new MainWindow(ctrl, keys.get(username));
                
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password.");
        }
    }

    private boolean isValidCredentials(String username, String password) {
       return ((UserDAOImpl) daoUser).checkPassword(username, password);
    }
    

}
