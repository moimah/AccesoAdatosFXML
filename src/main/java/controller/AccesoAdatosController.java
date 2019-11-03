package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.filechooser.FileSystemView;

import com.sun.marlin.DDasher;

import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.ListView;

import javafx.scene.control.TextArea;

import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.RadioButton;

import javafx.scene.layout.VBox;
import model.AccesoAdatosModel;
import model.AccesoAleatorio;
import model.Residencia;
import javafx.scene.control.TableView;

public class AccesoAdatosController implements Initializable{
	
	//Instanciar e inicializar el model
	
	AccesoAdatosModel model = new AccesoAdatosModel();
	
	private String ruta_actual; 
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
	private ToggleGroup comedor;
	@FXML
	private RadioButton rd_no_aleatorio;
	@FXML
	private Tab tabRegistrosAleatorio;
	@FXML
	private TextField txt_idResidencia_registro;
	@FXML
	private TextField txtNuevoPrecio_registro;
	@FXML
	private Button btnBuscar_aleatorio;
	@FXML
	private Button btn_modificarPrecio_aleatorio;
	@FXML
	private Button btnMostrartodo_aleatorio;
	@FXML	
	private TableView <Residencia> tvBusquedaResidencia_aleatorio;
	@FXML
	private TableColumn <Residencia, Integer> tvBusquedaResidencia_aleatorio_id;
	@FXML
	private TableColumn <Residencia, String> tvBusquedaResidencia_aleatorio_nombre;
	@FXML
	private TableColumn <Residencia, String> tvBusquedaResidencia_aleatorio_codigo_universidad;
	@FXML
	private TableColumn  <Residencia, Integer> tvBusquedaResidencia_aleatorio_precio;
	@FXML
	private TableColumn <Residencia, Boolean> tvBusquedaResidencia_aleatorio_comedor;

	
	@FXML
	private TableView <Residencia> tvTodasResidencias_aleatorio;
	@FXML
	private TableColumn <Residencia, Integer> tvTodasResidencias_aleatorio_id;
	@FXML
	private TableColumn <Residencia, String> tvTodasResidencias_aleatorio_nombre;
	@FXML
	private TableColumn <Residencia, String> tvTodasResidencias_aleatorio_universidad;
	@FXML
	private TableColumn  <Residencia, Boolean> tvTodasResidencias_aleatorio_comedor;
	@FXML
	private TableColumn  <Residencia, Integer>tvTodasResidencias_aleatorio_precio;

	
	
	public AccesoAdatosController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AccesoAdatosView.fxml"));
		loader.setController(this);
		loader.load();
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {		
		
		
		
		//BINDEOS ACCESO A FICHEROS
		
		txtRutaA.textProperty().bindBidirectional(model.txtRutaAProperty());
		txtRutaB.textProperty().bindBidirectional(model.txtRutaBProperty());
		
				
		model.lpFicherosProperty().bindBidirectional(lvFicheros.itemsProperty()); //Bindeo de listas
		model.FicheroSeleccionadoProperty().bind(lvFicheros.getSelectionModel().selectedItemProperty()); //Bindeo del seleccionado
		model.txtContenidoProperty().bindBidirectional(txtContenido.textProperty());				
		
		
		//BINDEOS ACCESO A ALEATORIO
		

		model.txtIdResidencia_aleatorioProperty().bindBidirectional(txtIdResidencia_aleatorio.textProperty());	
		model.txt_nombre_aleatorioProperty().bindBidirectional(txt_nombre_aleatorio.textProperty());	
		txt_nombre_aleatorio.textProperty().bindBidirectional(model.txt_nombre_aleatorioProperty());
		model.txt_codUniversidad_aleatorioProperty().bindBidirectional(txt_codUniversidad_aleatorio.textProperty());
		model.txt_precioMensualAleatorioProperty().bindBidirectional(txt_precioMensualAleatorio.textProperty());
		model.txt_idResidencia_registroProperty().bindBidirectional(txt_idResidencia_registro.textProperty());
		model.txtNuevoPrecio_registroProperty().bindBidirectional(txtNuevoPrecio_registro.textProperty());
		
		
	
		
		//LISTENERS/METODOS ACCESO A FICHEROS
		
		
		textFieldFormmater(this.txtIdResidencia_aleatorio); // Permite solo la entrada de valores numericos
		textFieldFormmater(this.txt_precioMensualAleatorio); // Permite solo la entrada de valores numericos
		textFieldFormmater(this.txt_idResidencia_registro); // Permite solo la entrada de valores numericos
		textFieldFormmater(this.txtNuevoPrecio_registro); // Permite solo la entrada de valores numericos
		
		 //Listeners para activar boton de alta
		txtIdResidencia_aleatorio.textProperty().addListener((o, ov, nv) -> onActivarButtonAlta(nv));
		txt_nombre_aleatorio.textProperty().addListener((o, ov, nv) -> onActivarButtonAlta(nv));
		txt_codUniversidad_aleatorio.textProperty().addListener((o, ov, nv) -> onActivarButtonAlta(nv));
		txt_precioMensualAleatorio.textProperty().addListener((o, ov, nv) -> onActivarButtonAlta(nv));
		
		//Listeners que limitan el tamaño de los campos de texto
		txt_nombre_aleatorio.textProperty().addListener((o, ov, nv) -> onAddTextLimiterTxtNombreAleatorio(nv,ov));
		txt_codUniversidad_aleatorio.textProperty().addListener((o, ov, nv) -> onAddTextLimiterTxCodUniversidadAleatorio(nv,ov));
		
		//Listeners para activiar el boton buscar
		txt_idResidencia_registro.textProperty().addListener((o, ov, nv) -> onActivarButtonBuscar(nv));
		
		//Listeners para activar el boton modificar precio
		txtNuevoPrecio_registro.textProperty().addListener((o, ov, nv) -> onActivarButtonModificarPrecio(nv));
		txt_idResidencia_registro.textProperty().addListener((o, ov, nv) -> onActivarButtonModificarPrecio(nv));
	
			
		//Bindeos de tableView 1
	
		tvBusquedaResidencia_aleatorio_id.setCellValueFactory(new PropertyValueFactory<Residencia, Integer>("idResidencia"));
		tvBusquedaResidencia_aleatorio_nombre.setCellValueFactory(new PropertyValueFactory<Residencia, String>("nombreResidencia"));
		tvBusquedaResidencia_aleatorio_codigo_universidad.setCellValueFactory(new PropertyValueFactory<Residencia, String>("codUniversidad"));
		tvBusquedaResidencia_aleatorio_precio.setCellValueFactory(new PropertyValueFactory<Residencia, Integer>("precio"));
		tvBusquedaResidencia_aleatorio_comedor.setCellValueFactory(new PropertyValueFactory<Residencia, Boolean>("comedor"));
		
		
		tvTodasResidencias_aleatorio_id.setCellValueFactory(new PropertyValueFactory<Residencia, Integer>("idResidencia"));
		tvTodasResidencias_aleatorio_nombre.setCellValueFactory(new PropertyValueFactory<Residencia, String>("nombreResidencia"));
		tvTodasResidencias_aleatorio_universidad.setCellValueFactory(new PropertyValueFactory<Residencia, String>("codUniversidad"));
		tvTodasResidencias_aleatorio_precio.setCellValueFactory(new PropertyValueFactory<Residencia, Integer>("precio"));
		tvTodasResidencias_aleatorio_comedor.setCellValueFactory(new PropertyValueFactory<Residencia, Boolean>("comedor"));
		
		
		
		
		
				
	}
	
	
	

	//LISTERNES GENERADOS AUTOMATICOS
	
	
	// Event Listener on Button[#btnCrear].onAction
	@FXML
	public void clickBtnCrear(ActionEvent event) {
		
		int contenido = 0; // 0 Vacio 1 Contiene algo
		int tipo = 0; // 0 no tipo 1 Fichero 2 Directorio
		int confirmado = 0;
		String rutaA = "";	
		String rutaB = "";
		String path  = "";
		
		Boolean exito = false;
		
		
		//Si las rutas son null escribir ""
		if(model.getTxtRutaA()!=null) {
			rutaA = model.getTxtRutaA();
		}
		if(model.getTxtRutaB()!=null) {
			rutaB = model.getTxtRutaB();
		}
		
		//Detectar contenido en txtRutaA seguridad para no actuar sobre la carpeta raiz			
		if (rutaA.length() > 1) {
		contenido = 1; // Tiene contenido
		path = rutaA;
		System.out.println(path);
		}
		//Si la ruta contiene . "Es un fichero" 
		if (path.contains(".")) {
			tipo = 1; // Es fichero
		}
		
		//
		
		if (contenido == 0) {
			Alert alerta = new Alert(AlertType.WARNING);
			alerta.setTitle("Acceso a datos");
			alerta.setHeaderText("CREAR FICHERO");
			alerta.setContentText("No se seleccionado nada");
			alerta.showAndWait();
			
		} 
		else { // Segun su tipo enviamos una confirmación y la recogemos segun su tipo

			if (tipo == 0) {
				Alert alerta = new Alert(AlertType.CONFIRMATION);
				alerta.setTitle("Acceso a datos");
				alerta.setHeaderText("CREAR FICHERO");
				alerta.setContentText("Va a crear en: " + path + " \nTipo: DIRECTORIO");
				
				Optional<ButtonType> result = alerta.showAndWait(); // Almacena el resultado de un boton

				if (result.get() == ButtonType.OK) {
					confirmado = 1;
				}
				
			} else {
				Alert alerta = new Alert(AlertType.CONFIRMATION);
				alerta.setTitle("Acceso a datos");
				alerta.setHeaderText("CREAR FICHERO");
				alerta.setContentText("Va a crear en: " + path + " \nTipo: FICHERO");
				
				Optional<ButtonType> result = alerta.showAndWait(); // Almacena el resultado de un boton

				if (result.get() == ButtonType.OK) {
					confirmado = 2;
				}
			}

			// Crear un file y en un try catch recoger posibles excepciones

			File fichero = new File(path);

			try {

				if (!fichero.exists()) {

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
				alerta.setHeaderText("CREAR FICHERO");
				alerta.setContentText("Enhorabuena se ha creado");
				alerta.showAndWait();
			} else {
				Alert alerta = new Alert(AlertType.WARNING);
				alerta.setTitle("Acceso a datos");
				alerta.setHeaderText("CREAR FICHERO");
				alerta.setContentText("No se ha creado");
				alerta.showAndWait();
			}
		
		}
		
		
	}
	// Event Listener on Button[#btnEliminar].onAction
	@FXML
	public void clickBtnEliminar(ActionEvent event) {
		
		int contenido = 0; // 0 Vacio 1 Contiene algo		
		int confirmado = 0;
		String rutaA = "";
		String rutaB = "";
		String path = "";

		Boolean exito = false;

		// Si las rutas son null escribir ""
		if (model.getTxtRutaA() != null) {
			rutaA = model.getTxtRutaA();
		}
		if (model.getTxtRutaB() != null) {
			rutaB = model.getTxtRutaB();
		}

		// Detectar contenido en txtRutaA seguridad para no actuar sobre la carpeta raiz
		if (rutaA.length() > 1) {
			contenido = 1; // Tiene contenido
			path = rutaA;
			System.out.println(path);
		}

		if (contenido == 0) {
			Alert alerta = new Alert(AlertType.WARNING);
			alerta.setTitle("Acceso a datos");
			alerta.setHeaderText("ELIMINAR FICHERO");
			alerta.setContentText("No se seleccionado nada");
			alerta.showAndWait();
		} else {

			File fichero = new File(path);

			// Comprobar existencia del fichero y preguntar
			if (fichero.exists()) {
				// Preguntar
				Alert alerta = new Alert(AlertType.CONFIRMATION);
				alerta.setTitle("Acceso a datos");
				alerta.setHeaderText("ELIMINAR FICHERO");
				alerta.setContentText("Va a eliminar: " + path);

				Optional<ButtonType> result = alerta.showAndWait(); // Almacena el resultado de un boton

				if (result.get() == ButtonType.OK) {
					confirmado = 1;
				}

			}

			// Si se ha confirmado llamar a método de borrado recursivo
			if (confirmado == 1) {

				exito = borrarFileRecursivamente(path);

			}

			if (exito == true) {// Mensaje de se ha eliminado
				Alert alerta = new Alert(AlertType.INFORMATION);
				alerta.setTitle("Acceso a datos");
				alerta.setHeaderText("ELIMINAR FICHERO");
				alerta.setContentText("Enhorabuena se ha eliminado");
				alerta.showAndWait();
			} else { //Mensaje de no se ha eliminado
				Alert alerta = new Alert(AlertType.WARNING);
				alerta.setTitle("Acceso a datos");
				alerta.setHeaderText("ELIMINAR FICHERO");
				alerta.setContentText("No se ha eliminado");
				alerta.showAndWait();
			}

		}
		
	}
	// Event Listener on Button[#btnMover].onAction
	@FXML
	public void clickBtnMover(ActionEvent event) {
		
		
		int contenido = 0; // 0 Vacio 1 Contiene algo		
		int confirmado = 0;
		String rutaA = "";
		String rutaB = "";
		String path = "";

		Boolean exito = false;

		// Si las rutas son null escribir ""
		if (model.getTxtRutaA() != null) {
			rutaA = model.getTxtRutaA();
		}
		if (model.getTxtRutaB() != null) {
			rutaB = model.getTxtRutaB();
		}

		// Detectar contenido en txtRutaA txtRutaB  y que ruta B sea un directorio - seguridad para no actuar sobre la carpeta raiz
		if (rutaA.length()>1 && rutaB.length()>1 && (!rutaB.contains("."))) {
			contenido = 1; // Tiene contenido
			
			System.out.println(path);
		}
		//Si no hay contenido en las turas enviar mensaje
		if (contenido == 0) {
			Alert alerta = new Alert(AlertType.WARNING);
			alerta.setTitle("Acceso a datos");
			alerta.setHeaderText("MOVER FICHERO");
			alerta.setContentText("Se han de completar correctamente ambos campos");
			alerta.showAndWait();
		} else { //Si hay contenido crear un File

			File ficheroA = new File(rutaA);
			File ficheroB = new File(rutaB);

			// Comprobar existencia de los ficheros y preguntar
			if (ficheroA.exists() && ficheroB.exists()) {
				// Preguntar
				Alert alerta = new Alert(AlertType.CONFIRMATION);
				alerta.setTitle("Acceso a datos");
				alerta.setHeaderText("MOVER FICHERO");
				alerta.setContentText("Va a mover: " + rutaA + " a " + rutaB);

				Optional<ButtonType> result = alerta.showAndWait(); // Almacena el resultado de un boton

				if (result.get() == ButtonType.OK) {
					confirmado = 1;
				}

			}

			// Si se ha confirmado llamar a método de mover fichero
			if (confirmado == 1) {
				
				exito = moverFile(rutaA, rutaB);
				

			}

			if (exito == true) {// Mensaje de que ha movido
				Alert alerta = new Alert(AlertType.INFORMATION);
				alerta.setTitle("Acceso a datos");
				alerta.setHeaderText("MOVER FICHERO");
				alerta.setContentText("Enhorabuena se ha movido el fichero");
				alerta.showAndWait();
			} else { //Mensaje de que no se ha movido
				Alert alerta = new Alert(AlertType.WARNING);
				alerta.setTitle("Acceso a datos");
				alerta.setHeaderText("MOVER FICHERO");
				alerta.setContentText("No se ha movido");
				alerta.showAndWait();
			}

		}
		
		
		
		
	}
	// Event Listener on Button[#btnFicheros].onAction
	@FXML
	
	
	public void clickBtnFicheros(ActionEvent event) {		
		String path = model.getTxtRutaA();
		if(model.getTxtRutaA() != null) {
		listarFicherosYcarpetas(path);
		}
		
	}
	// Event Listener on Button[#btnContenido].onAction
	@FXML
	public void clickBtnContenido(ActionEvent event) {
		
		String seleccionado = model.FicheroSeleccionadoProperty().get(); //Ruta de entrada
		String path = ruta_actual + "\\" + seleccionado; // Concatena la ruta actual con el fichero seleccionado
		
		File fichero = new File(path);	//File a partir de la ruta
				
		//Si el fichero existe leerlo, y vargarlo en el txtContenido
		if(fichero.exists()) {  
			
			String contenido = leerFichero(path);
			
			model.setTxtContenido(contenido);
				
		
		}
		
		
	}
	// Event Listener on Button[#btnModificar].onAction
	@FXML
	public void clickBtnModificar(ActionEvent event) {
		
		 	
			
		String seleccionado = model.FicheroSeleccionadoProperty().get(); //Ruta de entrada		
		String path = ruta_actual + "\\" + seleccionado; //Ruta actual
		
		File fichero = new File(path); //File a partir de la ruta		
		
		String modificado = null; //Variable que almacena el contenido
		
		//Si el fichero existe cargar contenido a la variable y escribirlo
		if(fichero.exists()) { 		
		 modificado = model.getTxtContenido();	
	
		 
		
    	try {
			FileWriter fw = new FileWriter(fichero);
			fw.write(modificado);
			fw.close();
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
    	
    				
		}
		
		
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

			
		int contenido = 0; // 0 Vacio 1 Contiene algo		
		int confirmado = 0;
		String rutaA = "";
		String rutaB = "";
		String path = "";

		Boolean exito = false;

		// Si las rutas son null escribir ""
		if (model.getTxtRutaA() != null) {
			rutaA = model.getTxtRutaA();
		}
		if (model.getTxtRutaB() != null) {
			rutaB = model.getTxtRutaB();
		}

		// Detectar contenido en txtRutaA txtRutaB  y que ruta B sea un directorio - seguridad para no actuar sobre la carpeta raiz
		if (rutaA.length()>1 && rutaB.length()>1 && (!rutaB.contains("."))) {
			contenido = 1; // Tiene contenido
			
			System.out.println(path);
		}
		//Si no hay contenido en las turas enviar mensaje
		if (contenido == 0) {
			Alert alerta = new Alert(AlertType.WARNING);
			alerta.setTitle("Acceso a datos");
			alerta.setHeaderText("COPIAR FICHERO");
			alerta.setContentText("Se han de completar correctamente ambos campos");
			alerta.showAndWait();
		} else { //Si hay contenido crear un File

			File ficheroA = new File(rutaA);
			File ficheroB = new File(rutaB);

			// Comprobar existencia de los ficheros y preguntar
			if (ficheroA.exists() && ficheroB.exists()) {
				// Preguntar
				Alert alerta = new Alert(AlertType.CONFIRMATION);
				alerta.setTitle("Acceso a datos");
				alerta.setHeaderText("COPIAR FICHERO");
				alerta.setContentText("Va a copiar: " + rutaA + " a " + rutaB);

				Optional<ButtonType> result = alerta.showAndWait(); // Almacena el resultado de un boton

				if (result.get() == ButtonType.OK) {
					confirmado = 1;
				}

			}

			// Si se ha confirmado llamar a método de copiar fichero
			if (confirmado == 1) {
				
				exito = copiadoRecursivo(ficheroA, ficheroB);
				

			}

			if (exito == true) {// Mensaje de que ha movido
				Alert alerta = new Alert(AlertType.INFORMATION);
				alerta.setTitle("Acceso a datos");
				alerta.setHeaderText("COPIAR FICHERO");
				alerta.setContentText("Enhorabuena se ha copiado el fichero");
				alerta.showAndWait();
			} else { //Mensaje de que no se ha movido
				Alert alerta = new Alert(AlertType.WARNING);
				alerta.setTitle("Acceso a datos");
				alerta.setHeaderText("COPIAR FICHERO");
				alerta.setContentText("No se ha copiado");
				alerta.showAndWait();
			}

		}
		
		
		
		
		
		
	}
	// Event Listener on Button[#btnAlta_aleatorio].onAction
	
	
	
	///////////////ACCESO ALEATORIO////////////////
	
	
	@FXML
	public void clickBtnAlta_aleatorio(ActionEvent event) {
		boolean exito = registrarResidencia();
		
		if(exito) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Acceso a datos");
			alert.setHeaderText("Enhorabuena!");
			alert.setContentText("Se ha registrado correctamente!");
		}
		
		
	}
	// Event Listener on Button[#btnBuscar_aleatorio].onAction
	@FXML
	public void clickBtnBuscar_aleatorio(ActionEvent event) {	
		buscarResidencia();
		
	}
	// Event Listener on Button[#btn_modificarPrecio_aleatorio].onAction
	@FXML
	public void clickBtn_modificarPrecio_aleatorio(ActionEvent event) {
		modificarPrecio();
	}
	// Event Listener on Button[#btnMostrartodo_aleatorio].onAction
	@FXML
	public void clickBtnMostrartodo_aleatorio(ActionEvent event) {
		listarResidencia();
	}

	public VBox getViewAccesoAdatos() {
		return viewAccesoAdatos;
	}
	
	////////////////METODOS PROPIOS ACCESO ALEATORIO///////////////////////////////
	
		
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
	
	   /**
	 * Metodo para borrar una carpeta recursivamente Recibe por parametro path de
	 * File a eliminar Realiza borado recursivo en caso de que tenga más carpetas
	 * @param fileDel
	 * @return exito
	 */
	private boolean borrarFileRecursivamente(String path) {

		boolean exito = false;

		File fileDel = new File(path);

		System.out.println("La ruta a borrar es " + path);
		if (fileDel.isDirectory()) {

			// Si no tiene contenido borrar
			if (fileDel.list().length == 0) {
				fileDel.delete();
				exito = true;
			} else { // Si tiene contenido borrado recursivo

				for (String temp : fileDel.list()) {
					File fileDelete = new File(fileDel, temp);
					// Borardo recursivo llamando al propio metodo
					borrarFileRecursivamente(fileDelete.getPath());
					exito = true;
				}

				// Comprobar de nuevo el directorio, si esta vacio eliminar

				if (fileDel.list().length == 0)
					fileDel.delete();
				exito = true;

			}

		} else { // Si es archivo borrar

			fileDel.delete();
			exito = true;
		}

		return exito;
	}
	
	/**
	 * Metodo para mover carpetas y ficheros Recibe por parametro la ruta de origen
	 * y la de destino Devuelve true si se ha movido y false si no	 
	 * @param fromFile
	 * @param toFile
	 * @return exito
	 */

  private boolean moverFile(String fromFile, String toFile) {
		
  	boolean exito = false;
  	//Se crean los Objetos File origen y destino con las rutas recibida por parametros
  	File origen = new File(fromFile);
  	File destino = new File(toFile);    	
  	
  	try {
  	 origen.renameTo(new File(destino, origen.getName())); //Rename de encarga de mover
  	 exito = true; 
  	}catch (Exception e) {
			System.out.println("No se ha podido mover");
		}
  	return exito;
  		   
  		         
  }
  
  /**
	 * Metodo para copiar carpetas y ficheros de manera recursiva
	 * Recibe por parametro la ruta de origen y la de destino
	 * Devuelve true si se ha movido y false si no	 
	 * @param d1 File
	 * @param d2 File
	 * @return exito
	 */

  
  private boolean copiadoRecursivo(File d1, File d2) {

		boolean exito = false;
		int salida =  0;
		
		//Modificar el path de destino a través de su nombre
		String nombre = d1.getName();
		String aux = d2.getAbsolutePath(); // Variable auxiliar que guarda la ruta de destino inicial
		
		String destino = d2.getAbsolutePath() + "\\" + nombre;
		
		d2  = new File(destino); //Cambiar el File destino añadiendo el nombre	
		
		File file_aux = null;
		
		System.out.println("DESTINO " + destino);
		
		
		if (d2.exists()) { // Si existe ese fichero cambiarle el nombre de la ruta hasta que no exista
			int i = 1;
			while (true) {

				nombre = "copy (" + i + ") - " + d1.getName();
				destino = aux + "\\" + nombre;

				file_aux = new File(destino); // Crear un nuevo file con la ruta modificada

				if (!file_aux.exists()) {
					d2 = file_aux;
					break;
				}

				i++;

			}
		}
		
	
		
				
		
		try { // Logica del copiado recursivo recogemos posibles excepciones
			
						
			if (d1.isDirectory()) { // Si el origen es un directorio
				// Recorremos recursivamente el directorio
				if (!d2.exists()) { // Y no existe
					d2.mkdir(); // Crear
					System.out.println("Creando directorio " + d2.toString());
					String[] ficheros = d1.list(); // Lista de ficheros que contiene el directorio
					for (int x = 0; x < ficheros.length; x++) { // Bucle de copiado recursivo llamando al propio metodo
						copiadoRecursivo(new File(d1, ficheros[x]), new File(d2, ficheros[x]));
					}
					exito = true;
				}
			} else {

				if (d1.exists() && !d2.exists()) { // Si existe el fichero y no existe en el origen
					
						// Streams de E/S

						InputStream in = new FileInputStream(d1);
						OutputStream out = new FileOutputStream(d2);

						byte[] buf = new byte[1024];
						int len;

						while ((len = in.read(buf)) > 0) {
							out.write(buf, 0, len);
						}

						in.close();
						out.close();
						exito = true;
					
				}
			}

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		return exito;
	}
  
  
  
	/**
	 * Metodo lista el contenido de un directorio
	 * String Recibe un String con la ruta del fichero	
	 * Establece la ruta actual
	 * @param path
	 * @return exito;
	 */  
  
	private Boolean listarFicherosYcarpetas(String path) {
		
		ruta_actual = path;
		
		Boolean exito = false; // Variable de return
		
		model.getLpFicheros().clear();
		

		File directorio = new File(path); // Obj File a partir del path

		// Almacenamos en un String array los ficheros que hay dentro de la ruta
		String[] ficheros = directorio.list();

		if (ficheros != null) { // Si hay ficheros
			for (int i = 0; i < ficheros.length; i++) {
				model.getLpFicheros().add(ficheros[i]); // Añadimos los ficheros a la lista
				//System.out.println(ficheros[i]);
			}

			exito = true;
		}

		return exito;

	}
	

	
	/**
	 * Metodo que permite leer fichero de texto y almacenar su contenido en un
	 * String Recibe un String con la ruta del fichero Devuelve String con contenido
	 * del fichero	 * 
	 * @param path
	 * @return lectura
	 */

	private String leerFichero(String path) {

		String lectura = ""; // Variable de retorno

		File fich = new File(path); // Objeto file de la ruta
		try {
			// Streams
			FileReader fr = new FileReader(fich);
			BufferedReader br = new BufferedReader(fr);

			String linea = br.readLine(); // Almaceamos primera linea
			// Almacenamos contenido en lectura incluimos salto de linea para concevar
			// formato
			lectura = lectura + linea + "\n";
			while (linea != null) { // Mientras haya contenido
				linea = br.readLine();
				if (linea != null) { // Si hay linea
					lectura = lectura + linea + "\n"; // Almacenamos
				}

				// model.setSp_contenidoText(lectura);
			}
			// Cerrar Streams
			fr.close();
			br.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return lectura;

	}
	
	
	
	//////METODOS PROPIOS ACCESO ALEATORIO
	
	
	/**
	* Metodo tipo TextFormater  
	* se encarga de que solo se puedan introducir
	* valores de coma flotante en los textfield
	* @param TextField txt
	*/ 
	
	public void textFieldFormmater(TextField txt) {
		DecimalFormat format = new DecimalFormat("#.0");
		txt.setTextFormatter(new TextFormatter<>(c -> {
			if (c.getControlNewText().isEmpty()) {
				return c;
			}
			ParsePosition parsePosition = new ParsePosition(0);
			Object object = format.parse(c.getControlNewText(), parsePosition);

			if (object == null || parsePosition.getIndex() < c.getControlNewText().length()) {
				return null;
			} else {
				return c;
			}
		}));
	}
	
	
	
	/**
	* Habilita el botón btnAlta_aleatorio
	* si se han completado todos los campos de texto
	* @param  nv
	*/ 
	
	private void  onActivarButtonAlta(String nv) {
				
		
		if(model.getTxtIdResidencia_aleatorio().length()>0 && model.getTxt_nombre_aleatorio().length()>0 && model.getTxt_codUniversidad_aleatorio().length()>0  && model.getTxt_precioMensualAleatorio().length()>0) {
			
			btnAlta_aleatorio.setDisable(false);
		
		}else {
			btnAlta_aleatorio.setDisable(true);
		}
		
	
		
	}
	
	/**
	* Habilita el botón btnBuscar_aleatorio
	* si se han completado todos los campos de texto
	* @param  nv
	*/ 
	
	private void  onActivarButtonBuscar(String nv) {
				
		
		if(model.getTxt_idResidencia_registro().length()>0) {
			
			btnBuscar_aleatorio.setDisable(false);
		
		}else {
			btnBuscar_aleatorio.setDisable(true);
		}
		
			
	}
	
	
	
	private void  onActivarButtonModificarPrecio(String nv) {
				
		
		if(model.getTxt_idResidencia_registro().length()>0 && model.getTxtNuevoPrecio_registro().length()>0) {			
			btn_modificarPrecio_aleatorio.setDisable(false);
		
		}else {
			btn_modificarPrecio_aleatorio.setDisable(true);
		}
		
			
	}
	
	/**
	 * Limita el campo de texto a 10 caracteres	
	 * @param ov
	 * @param nv
	 */

	private void onAddTextLimiterTxtNombreAleatorio(String nv, String ov) {

		if (nv.length() == 10) {
			model.setTxt_nombre_aleatorio(ov);
		}
	}

	/**
	 * Limita el campo de texto a 6 caracteres	  
	 * @param ov
	 * @param nv
	 */

	private void onAddTextLimiterTxCodUniversidadAleatorio(String nv, String ov) {

		if (nv.length() == 6) {
			model.setTxt_codUniversidad_aleatorio(ov);
		}
	}
	
	/**
	 * Registra una residencia usando el metodo estatico
	 * registrarResidencia de AccesoAleatorio, devuelve true
	 * si se ha registrado
	 * @return exito
	 */
	
	
private boolean registrarResidencia() {

		
	boolean exito = false;
	
		int idResidencia = Integer.parseInt(model.getTxtIdResidencia_aleatorio());
		String nombreResidencia = model.getTxt_nombre_aleatorio();
		String codUniversidad = model.getTxt_codUniversidad_aleatorio();
		int precio = Integer.parseInt(model.getTxt_precioMensualAleatorio());
		boolean comedor;
		
		if(this.comedor.equals(rdSi_aleatorio)) {
			comedor = true;
		}else {
			comedor = false;
		}
		
		Residencia r = new Residencia(idResidencia, nombreResidencia, codUniversidad, precio, comedor);
		
			//Si no existe la residencia
		if(! AccesoAleatorio.existeResidencia("random.dat", idResidencia)) {
			
			exito = AccesoAleatorio.registrarResidencia("random.dat", r); // Registrar la residencia
			
			if (exito) { //Si se ha registrado manda mensaje
				
				Alert alerta = new Alert(AlertType.INFORMATION);
				alerta.setTitle("Acceso aleatorio");
				alerta.setHeaderText("Registrar residencia");
				alerta.setContentText("Ha registrado la residencia");
				alerta.showAndWait();
				
				model.setTxtIdResidencia_aleatorio("");
				model.setTxt_nombre_aleatorio("");
				model.setTxt_codUniversidad_aleatorio("");
				model.setTxt_precioMensualAleatorio("");
			
		}
						
		}else { //Si existe enviar mensaje de que no se ha podido registrar
			
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setTitle("Acceso aleatorio");
			alerta.setHeaderText("Registrar residencia");
			alerta.setContentText("No se ha podido registrar");
			alerta.showAndWait();
			
			model.setTxtIdResidencia_aleatorio("");
			model.setTxt_nombre_aleatorio("");
			model.setTxt_codUniversidad_aleatorio("");
			model.setTxt_precioMensualAleatorio("");
			
	}
		
		return exito;
	}

/**
 * Busca una residencia y la muestra en la tabla
 * devuelve true si se ha encontrado, si no false y envia
 * un mensaje 
 * si se ha registrado
 * @return exito
 */

private boolean buscarResidencia() {
	
	boolean exito = false; 
	
	int id= Integer.parseInt(model.getTxt_idResidencia_registro());
	
	Residencia r = AccesoAleatorio.buscarResidencia("random.dat", id);
	
	if (r!=null) {  // Si se ha encontrado la residencia, mostrar y rellenar el tableView	
		tvBusquedaResidencia_aleatorio.setVisible(true); 	//Mostrar las tablas
		tvTodasResidencias_aleatorio.setVisible(false); 	//Ocultar la tabla inferior
		
		ObservableList<Residencia> residencias = FXCollections.observableArrayList();
		residencias.add(r);	
		tvBusquedaResidencia_aleatorio.setItems(residencias);
	
	}else { //Si no se ha encontrado mensaje de error
		tvBusquedaResidencia_aleatorio.setVisible(false); 	//Ocultar las tablas
		Alert alerta = new Alert(AlertType.ERROR);
		alerta.setTitle("Acceso aleatorio");
		alerta.setHeaderText("Registrar residencia");
		alerta.setContentText("No existe la residencia");
		alerta.showAndWait();
	}
	
	return exito; 
}

/**
 * Muestra el contenido de la lista de residencias
 * devuelve true si hay registros, si no false y envia
 * un mensaje 
 * si se ha registrado
 * @return exito
 */
private boolean listarResidencia() {
	
	ArrayList lista = AccesoAleatorio.leerResidencias("random.dat");
	
	if(lista.size()>0) { //Si se han obtenido resultados cargar en el tableview la lista
		tvTodasResidencias_aleatorio.setVisible(true); 	//Mostrar las tablas
		tvBusquedaResidencia_aleatorio.setVisible(false); 	//Ocultar la tabla superior
		
		ObservableList<Residencia> residencias = FXCollections.observableArrayList(lista);
		tvTodasResidencias_aleatorio.setItems(residencias);
			
		
	}else {
		tvTodasResidencias_aleatorio.setVisible(false); 	//Ocultar las tablas
		Alert alerta = new Alert(AlertType.ERROR);
		alerta.setTitle("Acceso aleatorio");
		alerta.setHeaderText("Registrar residencia");
		alerta.setContentText("No hay registros");
		alerta.showAndWait();
	}
	
	return false;
	
}

private void modificarPrecio() {
	
	int precio = Integer.parseInt(model.getTxtNuevoPrecio_registro());	
	int id = Integer.parseInt(model.getTxtNuevoPrecio_registro());
	
	boolean modificado = AccesoAleatorio.modificarPrecioResidencia("random.dat", 1, precio);
	System.out.println(modificado);
	
	if(modificado) {	
		
		tvBusquedaResidencia_aleatorio.setVisible(false); 	//Ocultar las tablas	
		tvTodasResidencias_aleatorio.setVisible(false); //Ocultar las tablas
		
		Alert alerta = new Alert(AlertType.INFORMATION);
		alerta.setTitle("Acceso aleatorio");
		alerta.setHeaderText("Modificar precio");
		alerta.setContentText("Se ha modificado correctamente");
		alerta.showAndWait();
	}else {
		
		tvBusquedaResidencia_aleatorio.setVisible(false); 	//Ocultar las tablas	
		tvTodasResidencias_aleatorio.setVisible(false); //Ocultar las tablas
		
		Alert alerta = new Alert(AlertType.ERROR);
		alerta.setTitle("Acceso aleatorio");
		alerta.setHeaderText("Modificar precio");
		alerta.setContentText("No se ha modificado, comprueba que exista una residencia con ese índice");
		alerta.showAndWait();
	}
	
}





}


