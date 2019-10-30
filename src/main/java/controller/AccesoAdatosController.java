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
import java.util.Optional;
import java.util.ResourceBundle;

import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.filechooser.FileSystemView;

import com.sun.marlin.DDasher;

import javafx.collections.ListChangeListener;
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
		
				
		model.lpFicherosProperty().bindBidirectional(lvFicheros.itemsProperty()); //Bindeo de listas
		model.FicheroSeleccionadoProperty().bind(lvFicheros.getSelectionModel().selectedItemProperty()); //Bindeo del seleccionado
		model.txtContenidoProperty().bindBidirectional(txtContenido.textProperty());
		
		
		
		//LISTENERS PROPIOS
		
	
			
				
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
		listarFicherosYcarpetas(path);
		
		
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
	
	
	
	
	
}
