package model;

public class Residencia {

	private int idResidencia;
	private String nombreResidencia;
	private String codUniversidad;
	private int precio;
	private boolean comedor;

	
	
	
	
	public Residencia(int idResidencia, String nombreResidencia, String codUniversidad, int precio, boolean comedor) {
		super();
		this.idResidencia = idResidencia;
		this.nombreResidencia = nombreResidencia;
		this.codUniversidad = codUniversidad;
		this.precio = precio;
		this.comedor = comedor;
	}

	public int getIdResidencia() {
		return idResidencia;
	}

	public void setIdResidencia(int idResidencia) {
		this.idResidencia = idResidencia;
	}

	public String getNombreResidencia() {
		return nombreResidencia;
	}

	public void setNombreResidencia(String nombreResidencia) {

		// Nos aseguramos que no se puedan setear String mayores a 10 car�cteres
		if (nombreResidencia.length() < 10) {
			this.nombreResidencia = nombreResidencia;
		} else {
			System.out.println("Tama�o de nombre desmasiado grande");
		}
	}

	public String getCodUniversidad() {
		return codUniversidad;
	}

	public void setCodUniversidad(String codUniversidad) {

		// Nos aseguramos que no se puedan setear String mayores a 6 car�cteres
		if (codUniversidad.length() < 10) {
			this.codUniversidad = codUniversidad;

		} else {
			System.out.println("Tama�o de nombre desmasiado grande");
		}

	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public boolean getComedor() {
		return comedor;
	}

	public void setComedor(boolean comedor) {
		this.comedor = comedor;
	}

}
