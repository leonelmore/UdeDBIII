package grafica.ventanas;

import excepciones.PersistenciaException;
import excepciones.PoolDeConexionesException;
import excepciones.VentanaException;
import grafica.controladores.Due�osController;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.FlowLayout;

import javax.swing.JButton;

import persistencia.valueObjects.VODue�o;
import persistencia.valueObjects.VOMascota;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JLabel;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import javax.swing.JFormattedTextField;

import java.awt.Color;

public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;
	
	private Due�osController cont;
	private JList listDue�os = new JList();
	private JList listMascotas = new JList();
	private JTextField textFieldApodo;
	private JTextField textFieldRaza;
	private JPanel panelAgregarMascota = new JPanel();
	private JLabel label = DefaultComponentFactory.getInstance().createTitle("");
	private JLabel labelExcepciones = new JLabel("");
	private JFormattedTextField formattedTextField = new JFormattedTextField();
	private JTextField textField_2;
	private JTextField textField_3;
	private JPanel panel_1 = new JPanel();
	private JButton btnListarMascotas;
	private JButton btnListarDue�os;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (RemoteException e) {
					System.out.println("Error fatal: problema de ejecuci�n remota.");
				} catch (VentanaException e) {
					System.out.println("Error fatal: " + e.getMessage());
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws RemoteException 
	 */
	public VentanaPrincipal() throws RemoteException, VentanaException {
		this.cont = new Due�osController();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel(); 
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		btnListarDue�os = new JButton("Listar due\u00F1os");
		btnListarDue�os.setBounds(10, 23, 150, 23);
		
		/* LISTAR DUE�OS */
		btnListarDue�os.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				clearMsg();
				Due�osController dc = null;
				try {
					dc = new Due�osController();
				} catch (RemoteException e1) {
					labelExcepciones.setText("Error al crear controlador.");
				}
				catch (VentanaException e1) {
					labelExcepciones.setText("Error al crear controlador. " + e1.getMessage());
				}
				try {
					ArrayList<VODue�o> due�os = dc.listarDue�os();
					DefaultListModel modelo = new DefaultListModel();
					for(VODue�o vd : due�os){
						modelo.addElement(vd.getCedula()+" - "+vd.getNombre()+" - "+vd.getApellido());
					}
					listDue�os.setModel(modelo);
				} catch (VentanaException e1) {
					labelExcepciones.setText("Error listando due�os. "+e1.getMessage());
				}
			}
		});
		/* end LISTAR DUE�OS */
		
		/* LISTAR MASCOTAS */
		btnListarMascotas = new JButton("Listar mascotas");
		btnListarMascotas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clearMsg();
				Due�osController dc = null;
				try {
					dc = new Due�osController();
				} catch (RemoteException e1) {
					labelExcepciones.setText("Error al crear controlador.");
				}
				catch (VentanaException e1) {
					labelExcepciones.setText("Error al crear controlador. " + e1.getMessage());
				}
				if (listDue�os.getSelectedValue() != null){
					String listValues = listDue�os.getSelectedValue().toString();
					String[] listValuesSplit = listValues.split(" - ");
					if (listValuesSplit.length > 0){
						int cedula = Integer.parseInt(listValuesSplit[0]);
						VODue�o vo = new VODue�o(cedula, listValuesSplit[1], listValuesSplit[2]);
						DefaultListModel modelo = new DefaultListModel();
						try {
							for (VOMascota vom : dc.listarMascotas(vo)){
								modelo.addElement(vom.getApodo()+" - "+ vom.getRaza());
							}
						} catch (VentanaException e) {
							// TODO Auto-generated catch block
							labelExcepciones.setText("Error listando due�os. " + e.getMessage());
						}
						if (modelo.size() == 0){
							modelo.addElement("No tiene mascotas.");
						}
						listMascotas.setModel(modelo);
					}
				}
				else{
					label.setText("Error: debe seleccionar un due�o.");
				}
				
			}
		});
		
		/* end LISTAR MASCOTAS */
		

		
		/* ELIMINAR MASCOTAS de un DUE�O y el due�o */
		JButton btnEliminarMascota = new JButton("Eliminar Due�o");
		btnEliminarMascota.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clearMsg();
				Due�osController dc = null;
				try {
					dc = new Due�osController();
				} catch (RemoteException e1) {
					labelExcepciones.setText("Error al crear el controlador.");
				}
				catch (VentanaException e1) {
					labelExcepciones.setText("Error al crear el controlador." + e1.getMessage());
				}
				if (listDue�os.getSelectedValue() != null){
					String listValues = listDue�os.getSelectedValue().toString();
					String[] listValuesSplit = listValues.split(" - ");
					if (listValuesSplit.length > 0){
						int cedula = Integer.parseInt(listValuesSplit[0]);
						
							VODue�o vod = new VODue�o(cedula, listValuesSplit[1], listValuesSplit[2]);
							DefaultListModel modelo = new DefaultListModel();
							try {
								dc.borrarDue�oMascotas(vod);
								listMascotas.setModel(modelo);
								btnListarDue�os.doClick();

							} catch (VentanaException e) {
								labelExcepciones.setText("Error al eliminar mascotas. " + e.getMessage());
							}
						
					}
				}
				else{
					label.setText("Error: debe seleccionar un due�o.");
				}
				
			}
		});
		
		/* end ELIMINAR MASCOTAS de un DUE�O */
		
		contentPane.setLayout(null);
		contentPane.add(btnListarDue�os);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(240, 71, 191, 216);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(listDue�os);
		
		
		btnListarMascotas.setBounds(707, 23, 150, 23);
		contentPane.add(btnListarMascotas);
		
		
		panelAgregarMascota.setBounds(189, 318, 488, 74);
		contentPane.add(panelAgregarMascota);
		panelAgregarMascota.setLayout(null);
		
		textFieldApodo = new JTextField();
		textFieldApodo.setBounds(10, 11, 192, 20);
		panelAgregarMascota.add(textFieldApodo);
		textFieldApodo.setColumns(10);
		
		textFieldRaza = new JTextField();
		textFieldRaza.setBounds(10, 40, 192, 20);
		panelAgregarMascota.add(textFieldRaza);
		textFieldRaza.setColumns(10);
		
		
		/* INSERTAR MASCOTA */
		JButton btnInsertarMascota = new JButton("Insertar mascota");
		btnInsertarMascota.setBounds(323, 37, 155, 23);
		panelAgregarMascota.add(btnInsertarMascota);
		
		JLabel lblApodo = new JLabel("Apodo");
		lblApodo.setBounds(208, 11, 55, 20);
		panelAgregarMascota.add(lblApodo);
		
		JLabel lblRaza = new JLabel("Raza");
		lblRaza.setBounds(212, 40, 68, 20);
		panelAgregarMascota.add(lblRaza);
		btnInsertarMascota.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Due�osController dc = null;
				try {
					dc = new Due�osController();
				} catch (RemoteException e1) {
					labelExcepciones.setText("Error al crear el controlador.");
				} catch (VentanaException e) {
					labelExcepciones.setText("Error al crear el controlador. " + e.getMessage());
				}
				clearMsg();
				if (listDue�os.getSelectedValue() != null){
					String listValues = listDue�os.getSelectedValue().toString();
					String[] listValuesSplit = listValues.split(" - ");
					if (listValuesSplit.length > 0){
						int cedula = Integer.parseInt(listValuesSplit[0]);
						if (!textFieldApodo.getText().equals("") && !textFieldApodo.getText().equals("")){
							VODue�o vod = new VODue�o(cedula, listValuesSplit[1], listValuesSplit[2]);
							VOMascota vom = new VOMascota(textFieldApodo.getText(), textFieldRaza.getText(), cedula);
							try {
								dc.nuevaMascota(vod, vom);
								textFieldApodo.setText("");
								textFieldRaza.setText("");
								panelAgregarMascota.setVisible(false);
								btnListarMascotas.doClick();
							} catch (VentanaException e) {
								labelExcepciones.setText("Error al crear una nueva mascota. " + e.getMessage());
							}
						}
						else{
							label.setText("Error: debe ingresar datos.");
						}
					}
				}
				else{
					label.setText("Error: debe seleccionar un due�o.");
				}
			}
		});
		/* end INSERTAR MASCOTA */
		
		/* INSERTAR DUE�O */
		JButton btnInsertarDueo = new JButton("Insertar due\u00F1o");
		btnInsertarDueo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Due�osController dc = null;
				try {
					dc = new Due�osController();
				} catch (RemoteException e1) {
					labelExcepciones.setText("Error al crear el controlador.");
				} catch (VentanaException e) {
					labelExcepciones.setText("Error al crear el controlador." + e.getMessage());
				}
				clearMsg();
				if (!formattedTextField.getText().equals("") && !textField_2.getText().equals("") && !textField_3.getText().equals("")){
					int cedula = Integer.parseInt(formattedTextField.getText());
					VODue�o vod = new VODue�o(cedula, textField_2.getText(), textField_3.getText());
					try {
						dc.nuevoDue�o(vod);
						textField_2.setText("");
						textField_3.setText("");
						formattedTextField.setText("");
						panel_1.setVisible(false);
						btnListarDue�os.doClick();
					} catch (VentanaException e) {
						// TODO Auto-generated catch block
						labelExcepciones.setText("Error al crear un nuevo due�o. " + e.getMessage());
					}
					
				}
				else{
					label.setText("Debe ingresar todos los datos.");
				}
				
			}
		});
		/* end INSERTAR DUE�O */
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(504, 71, 150, 216);
		contentPane.add(scrollPane_1);
		scrollPane_1.setViewportView(listMascotas);
		

		btnEliminarMascota.setBounds(10, 91, 150, 23);
		contentPane.add(btnEliminarMascota);
		
		JButton btnAgregarMascota = new JButton("Agregar Mascota");
		btnAgregarMascota.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clearMsg();
				if (listDue�os.getSelectedValue() != null){
					panelAgregarMascota.setVisible(true);
				}
				else{
					label.setText("Debe seleccionar un due�o");
				}
			}
		});
		btnAgregarMascota.setBounds(707, 57, 150, 23);
		contentPane.add(btnAgregarMascota);
		label.setBounds(10, 213, 210, 26);
		contentPane.add(label);
		
		
		panel_1.setBounds(189, 437, 488, 98);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblCdulaDeIdentidad = DefaultComponentFactory.getInstance().createLabel("C\u00E9dula de identidad");
		lblCdulaDeIdentidad.setBounds(246, 14, 191, 14);
		panel_1.add(lblCdulaDeIdentidad);
		
		
		formattedTextField.setBounds(10, 11, 197, 20);
		
		panel_1.add(formattedTextField);
		
		textField_2 = new JTextField();
		textField_2.setBounds(10, 42, 197, 20);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(10, 67, 197, 20);
		panel_1.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNombre = DefaultComponentFactory.getInstance().createLabel("Nombre");
		lblNombre.setBounds(246, 45, 92, 14);
		panel_1.add(lblNombre);
		
		JLabel lblApellido = DefaultComponentFactory.getInstance().createLabel("Apellido");
		lblApellido.setBounds(246, 70, 92, 14);
		panel_1.add(lblApellido);
		
		
		
		btnInsertarDueo.setBounds(335, 66, 143, 23);
		panel_1.add(btnInsertarDueo);
		
		JButton btnAgregarDueo = new JButton("Agregar Due\u00F1o");
		btnAgregarDueo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel_1.setVisible(true);
			}
		});
		btnAgregarDueo.setBounds(10, 57, 150, 23);
		contentPane.add(btnAgregarDueo);
		
		JLabel lblDueos = new JLabel("Due\u00F1os:");
		lblDueos.setBounds(240, 37, 79, 23);
		contentPane.add(lblDueos);
		
		JLabel lblMascotas = new JLabel("Mascotas:");
		lblMascotas.setBounds(504, 37, 70, 23);
		contentPane.add(lblMascotas);
		
		
		labelExcepciones.setForeground(Color.RED);
		labelExcepciones.setBounds(257, 11, 342, 26);
		contentPane.add(labelExcepciones);
		panelAgregarMascota.setVisible(false);
		panel_1.setVisible(false);
		
	}
	
	public void clearMsg(){
		label.setText("");
		labelExcepciones.setText("");
	}
}
