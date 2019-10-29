package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import javafx.scene.control.ToggleGroup;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.filechooser.FileSystemView;

import javafx.event.ActionEvent;

import javafx.scene.control.ListView;

import javafx.scene.control.TextArea;

import javafx.scene.control.Tab;

import javafx.scene.control.RadioButton;

import javafx.scene.layout.VBox;
import model.AccesoAdatosModel;
import javafx.scene.control.TableView;

public class AccesoAdatosController implements Initializable{
	
	//Instanciar e inicializar el model
	
	AccesoAdatosModel model = new AccesoAdatosModel();
	
	@FXML
	private VBox viewAccesoAdatos;
	@FXML
	private Tab tabAccesoFicheros;
	@FXML
	private Button btnCrear;
	@FXML
	private Button btnEliminar;
	@FXML
	private Button btnMover;
	@FXML
	private Button btnFicheros;
	@FXML
	private ListView lvFicheros;
	@FXML
	private Button btnContenido;
	@FXML
	private Button btnModificar;
	@FXML
	private TextArea txtContenido;
	@FXML
	private Button btnBuscarRutaA;
	@FXML
	private TextField txtRutaA;
	@FXML
	private Button btnBuscarRutaB;
	@FXML
	private TextField txtRutaB;
	@FXML
	private Button btnCopiar;
	@FXML
	private RadioButton rdFichero;
	@FXML
	private ToggleGroup tgFicheros;
	@FXML
	private RadioButton rdCarpeta;
	@FXML
	private Tab tabAccesoAleatorio;
	@FXML
	private Tab tabAltasAleatorio;
	@FXML
	private TextField txtIdResidencia_aleatorio;
	@FXML
	private TextField txt_nombre_aleatorio;
	@FXML
	private TextField txt_codUniversidad_aleatorio;
	@FXML
	private TextField txt_precioMensualAleatorio;
	@FXML
	private Button btnAlta_aleatorio;
	@FXML
	private RadioButton rdSi_aleatorio;
	@FXML
	private RadioButton rd_no_aleatorio;
	@FXML
	private Tab tabRegistrosAleatorio;
	@FXML
	private TextField txtIdesidencia_aleatorioAltas;
	@FXML
	private Button btnBuscar_aleatorio;
	@FXML
	private Button btn_modificarPrecio_aleatorio;
	@FXML
	private Button btnMostrartodo_aleatorio;
	@FXML
	private TableView tvBusquedaResidencia_aleatorio;
	@FXML
	private TableView tvTodasResidencias_aleatorio;
	@FXML
	private TextField txtNuevoPrecio_aleatorio;
	
	
	public AccesoAdatosController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AccesoAdatosView.fxml"));
		loader.setController(this);
		loader.load();
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {		
		
		//BINDEOS
		
		txtRutaA.textProperty().bindBidirectional(model.txtRutaAProperty());
		txtRutaB.textProperty().bindBidirectional(model.txtRutaBProperty());
		
	}
	
	
	

	
	//LISTERNES GENERADOS AUTOMATICOS
	
	
	// Event Listener on Button[#btnCrear].onAction
	@FXML
	public void clickBtnCrear(ActionEvent event) {
		
		int contenido = 0; // 0 Vacio 1 Contiene algo
		int tipo = 0; // 0 no tipo 1 Fichero 2 Directorio
		int confirmado = 0;
		String rutaA = model.getTxtRutaA();
		String rutaB = model.getTxtRutaB();
		String path = rutaA.concat("\\".concat(rutaA));
		Boolean exito = false;

		if (model.getTxtRutaA().length() > 1) {
			contenido = 1; // Tiene contenido

		}
		if (model.getTxtRutaB().contains(".")) {
			contenido = 1; // Es fichero
		}

		// Preguntar si se desea crear

		if (contenido == 0) {
			// Mensaje de no se ha escrito
		} else { // Segun su tipo enviamos una confirmación y la recogemos segun su tipo

			if (tipo == 0) {
				Alert alerta = new Alert(AlertType.CONFIRMATION);
				alerta.setTitle("Acceso a datos");
				alerta.setHeaderText("BORRAR FICHERO");
				alerta.setContentText("Va a crear: " + path + " de tipo (directorio)");
				alerta.showAndWait();

				Optional<ButtonType> result = alerta.showAndWait(); // Almacena el resultado de un boton

				if (result.get() == ButtonType.OK) {
					confirmado = 1;
				}
			} else {
				Alert alerta = new Alert(AlertType.CONFIRMATION);
				alerta.setTitle("Acceso a datos");
				alerta.setHeaderText("BORRAR FICHERO");
				alerta.setContentText("Va a crear: " + path + " de tipo (carpeta)");
				alerta.showAndWait();

				Optional<ButtonType> result = alerta.showAndWait(); // Almacena el resultado de un boton

				if (result.get() == ButtonType.OK) {
					confirmado = 2;
				}
			}

			// Crear un file y en un try catch recoger posibles excepciones

			File fichero = new File(path);

			try {

				if (fichero.exists()) {

				} else {

					switch (confirmado) {
					case 1:

						exito = fichero.mkdir();

						break;

					case 2:

						exito = fichero.createNewFile();

						break;
					}
				}

			} catch (Exception e) {
				exito = false;
			}

			if (exito == true) {
				Alert alerta = new Alert(AlertType.INFORMATION);
				alerta.setTitle("Acceso a datos");
				alerta.setHeaderText("Crear fichero");
				alerta.setContentText("Enhorabuena se ha creado el fichero)");
				alerta.showAndWait();
			} else {
				Alert alerta = new Alert(AlertType.WARNING);
				alerta.setTitle("Acceso a datos");
				alerta.setHeaderText("Crear fichero");
				alerta.setContentText("No se ha creado el fichero)");
				alerta.showAndWait();
			}
		
		}
		
		
	}
	// Event Listener on Button[#btnEliminar].onAction
	@FXML
	public void click(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#btnMover].onAction
	@FXML
	public void clickBtnMover(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#btnFicheros].onAction
	@FXML
	public void clickBtnFicheros(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#btnContenido].onAction
	@FXML
	public void clickBtnContenido(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#btnModificar].onAction
	@FXML
	public void clickBtnModificar(ActionEvent event) {
		// TODO Autogenerated
	}
	
	// Event Listener on Button[#btnBuscarRutaA].onAction
	@FXML
	/**
	 * Metodo setea el textRutaA con la ruta seleccionada, 		
	 * invocando al metodo JFileChooser	
	 */ 
	
	public void clickBtnBuscarRuta(ActionEvent event) {
		String ruta = jFileChooser();		
		model.setTxtRutaA(ruta);
		
	}
	
	// Event Listener on Button[#btnBuscarRutaB].onAction
	@FXML
	/**
	 * Metodo setea el textRutaB con la ruta seleccionada, invocando al metodo
	 * JFileChooser
	 */
	public void clickBtnBuscarRutaB(ActionEvent event) {
		String ruta = jFileChooser();		
		model.setTxtRutaB(ruta);
	}
	// Event Listener on Button[#btnCopiar].onAction
	@FXML
	public void clickBtnCopiar(ActionEvent event) {

		
		
	}
	// Event Listener on Button[#btnAlta_aleatorio].onAction
	@FXML
	public void clickBtnAlta_aleatorio(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#btnBuscar_aleatorio].onAction
	@FXML
	public void clickBtnBuscar_aleatorio(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#btn_modificarPrecio_aleatorio].onAction
	@FXML
	public void clickBtn_modificarPrecio_aleatorio(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#btnMostrartodo_aleatorio].onAction
	@FXML
	public void clickBtnMostrartodo_aleatorio(ActionEvent event) {
		// TODO Autogenerated
	}

	public VBox getViewAccesoAdatos() {
		return viewAccesoAdatos;
	}
	
	//METODOS 
	
    /**
	 * Metodo que crea un file and directory chooser, 		
	 * Cambia estilo por defecto a tipo windows
	 * Devuelve true si se ha escrito correctamente	 * 
	 * @return path
	 */ 
	
	private String jFileChooser() {
					
			String path = null;;
		try {

		    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); //Cambiar estilo java a estilo windows

		} 
		catch (Exception e) {
		   // handle exception
		}
				
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

		int returnValue = jfc.showOpenDialog(null);
		// int returnValue = jfc.showSaveDialog(null); //Tipo guardar

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
			path = selectedFile.getAbsolutePath();
			System.out.println(selectedFile.getAbsolutePath());
		}
		
		return path;
	}
	
}
