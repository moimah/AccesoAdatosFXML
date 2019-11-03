package model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AccesoAdatosModel {

	
	//PROPERTIES DE ACCESO A FICHEROS
	
	private StringProperty txtRutaA  = new SimpleStringProperty();
	private StringProperty txtRutaB  = new SimpleStringProperty();
	
	private ListProperty<String>  lpFicheros = new SimpleListProperty<String>(FXCollections.observableArrayList());
	private StringProperty FicheroSeleccionado = new SimpleStringProperty();
	private StringProperty txtContenido = new SimpleStringProperty();
	
	//PROPERTIES DE ACCESO ALEATORIO
	
	private StringProperty txtIdResidencia_aleatorio  = new SimpleStringProperty();
	private StringProperty txt_nombre_aleatorio  = new SimpleStringProperty();
	private StringProperty txt_codUniversidad_aleatorio  = new SimpleStringProperty();
	private StringProperty txt_precioMensualAleatorio  = new SimpleStringProperty();
	private StringProperty txt_idResidencia_registro  = new SimpleStringProperty();
	private StringProperty txtNuevoPrecio_registro  = new SimpleStringProperty();
	
	private ListProperty<String>  lpBusquedaResidencia_aleatorio = new SimpleListProperty<String>(FXCollections.observableArrayList());
	private ListProperty<String>  lpTodasResidencias_aleatorio = new SimpleListProperty<String>(FXCollections.observableArrayList());
	public final StringProperty txtRutaAProperty() {
		return this.txtRutaA;
	}
	
	public final String getTxtRutaA() {
		return this.txtRutaAProperty().get();
	}
	
	public final void setTxtRutaA(final String txtRutaA) {
		this.txtRutaAProperty().set(txtRutaA);
	}
	
	public final StringProperty txtRutaBProperty() {
		return this.txtRutaB;
	}
	
	public final String getTxtRutaB() {
		return this.txtRutaBProperty().get();
	}
	
	public final void setTxtRutaB(final String txtRutaB) {
		this.txtRutaBProperty().set(txtRutaB);
	}
	
	public final ListProperty<String> lpFicherosProperty() {
		return this.lpFicheros;
	}
	
	public final ObservableList<String> getLpFicheros() {
		return this.lpFicherosProperty().get();
	}
	
	public final void setLpFicheros(final ObservableList<String> lpFicheros) {
		this.lpFicherosProperty().set(lpFicheros);
	}
	
	public final StringProperty FicheroSeleccionadoProperty() {
		return this.FicheroSeleccionado;
	}
	
	public final String getFicheroSeleccionado() {
		return this.FicheroSeleccionadoProperty().get();
	}
	
	public final void setFicheroSeleccionado(final String FicheroSeleccionado) {
		this.FicheroSeleccionadoProperty().set(FicheroSeleccionado);
	}
	
	public final StringProperty txtContenidoProperty() {
		return this.txtContenido;
	}
	
	public final String getTxtContenido() {
		return this.txtContenidoProperty().get();
	}
	
	public final void setTxtContenido(final String txtContenido) {
		this.txtContenidoProperty().set(txtContenido);
	}
	
	public final StringProperty txtIdResidencia_aleatorioProperty() {
		return this.txtIdResidencia_aleatorio;
	}
	
	public final String getTxtIdResidencia_aleatorio() {
		return this.txtIdResidencia_aleatorioProperty().get();
	}
	
	public final void setTxtIdResidencia_aleatorio(final String txtIdResidencia_aleatorio) {
		this.txtIdResidencia_aleatorioProperty().set(txtIdResidencia_aleatorio);
	}
	
	public final StringProperty txt_nombre_aleatorioProperty() {
		return this.txt_nombre_aleatorio;
	}
	
	public final String getTxt_nombre_aleatorio() {
		return this.txt_nombre_aleatorioProperty().get();
	}
	
	public final void setTxt_nombre_aleatorio(final String txt_nombre_aleatorio) {
		this.txt_nombre_aleatorioProperty().set(txt_nombre_aleatorio);
	}
	
	public final StringProperty txt_codUniversidad_aleatorioProperty() {
		return this.txt_codUniversidad_aleatorio;
	}
	
	public final String getTxt_codUniversidad_aleatorio() {
		return this.txt_codUniversidad_aleatorioProperty().get();
	}
	
	public final void setTxt_codUniversidad_aleatorio(final String txt_codUniversidad_aleatorio) {
		this.txt_codUniversidad_aleatorioProperty().set(txt_codUniversidad_aleatorio);
	}
	
	public final StringProperty txt_precioMensualAleatorioProperty() {
		return this.txt_precioMensualAleatorio;
	}
	
	public final String getTxt_precioMensualAleatorio() {
		return this.txt_precioMensualAleatorioProperty().get();
	}
	
	public final void setTxt_precioMensualAleatorio(final String txt_precioMensualAleatorio) {
		this.txt_precioMensualAleatorioProperty().set(txt_precioMensualAleatorio);
	}
	
	public final StringProperty txt_idResidencia_registroProperty() {
		return this.txt_idResidencia_registro;
	}
	
	public final String getTxt_idResidencia_registro() {
		return this.txt_idResidencia_registroProperty().get();
	}
	
	public final void setTxt_idResidencia_registro(final String txt_idResidencia_registro) {
		this.txt_idResidencia_registroProperty().set(txt_idResidencia_registro);
	}
	
	public final StringProperty txtNuevoPrecio_registroProperty() {
		return this.txtNuevoPrecio_registro;
	}
	
	public final String getTxtNuevoPrecio_registro() {
		return this.txtNuevoPrecio_registroProperty().get();
	}
	
	public final void setTxtNuevoPrecio_registro(final String txtNuevoPrecio_registro) {
		this.txtNuevoPrecio_registroProperty().set(txtNuevoPrecio_registro);
	}
	
	public final ListProperty<String> lpBusquedaResidencia_aleatorioProperty() {
		return this.lpBusquedaResidencia_aleatorio;
	}
	
	public final ObservableList<String> getLpBusquedaResidencia_aleatorio() {
		return this.lpBusquedaResidencia_aleatorioProperty().get();
	}
	
	public final void setLpBusquedaResidencia_aleatorio(final ObservableList<String> lpBusquedaResidencia_aleatorio) {
		this.lpBusquedaResidencia_aleatorioProperty().set(lpBusquedaResidencia_aleatorio);
	}
	
	public final ListProperty<String> lpTodasResidencias_aleatorioProperty() {
		return this.lpTodasResidencias_aleatorio;
	}
	
	public final ObservableList<String> getLpTodasResidencias_aleatorio() {
		return this.lpTodasResidencias_aleatorioProperty().get();
	}
	
	public final void setLpTodasResidencias_aleatorio(final ObservableList<String> lpTodasResidencias_aleatorio) {
		this.lpTodasResidencias_aleatorioProperty().set(lpTodasResidencias_aleatorio);
	}
	
	
	
	
	
	

}
