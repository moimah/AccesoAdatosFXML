package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class AccesoAleatorio {
		


	/**
	 * Permite registrar residencias insertando 
	 * una ruta de fichero y un objeto residencia
	 * @param path
	 * @param residencia
	 * @return exito
	 */
	
	public static boolean registrarResidencia(String path, Residencia residencia) {
		
		boolean exito = false; 
		
		//Variables de lectura
		
		File fichero = new File(path);
		char[] nombreResidencia = new char[10];
		char[] codUniversidad = new char[6];
		
		//Comprobar que exista el fichero y que el tama�o sea el adecuad
		if(fichero.exists()) {	
						
	
			  //Convertir a char[] nombrResidencia							
				for (int i = 0; i < residencia.getNombreResidencia().length(); i++) {
					nombreResidencia[i] = residencia.getNombreResidencia().charAt(i);					
				}
				
				//Convertir a char[] codUniversidad
				for(int i = 0; i<residencia.getCodUniversidad().length();i++) {
					codUniversidad[i] = residencia.getCodUniversidad().charAt(i);
				}
				
				
					
				
				try {
					
					RandomAccessFile raf = new RandomAccessFile(fichero, "rw");
					
					char coma = ',';
					//Escribir id
					
					
					int index = (int) raf.length();
			      	raf.seek(index);
			      	raf.writeInt(residencia.getIdResidencia());
			      	index = index + 4; // (4) Indice + 4(int)
			      	
			      	//Escribir coma
			      	raf.seek(index);
			      	raf.writeChar(coma);
			      	index = index + 2; // (6) Indice + 2(int)
			      	
			      	//Escribir nombreResidencia
			      	for(int i=0; i<nombreResidencia.length; i++) {
			      		raf.seek(index);
			      		raf.writeChar(nombreResidencia[i]);
			      		index = index + 2;
			      	} //Index: 26
			   		      	
			      				      	
			      	//Escribir coma
			      	raf.seek(index);
			      	raf.writeChar(coma);
			      	index = index + 2; // (28) Indice + 2(int)
			      	
			      	//Escribir codUniversidad
			      	for(int i=0; i<codUniversidad.length;i++) {
			      		raf.seek(index);
				      	raf.writeChar(codUniversidad[i]);
				      	index = index + 2; 
			      	} //Index 40
			      	
			    			      	
			        //Escribir coma
			      	raf.seek(index);
			      	raf.writeChar(coma);
			      	index = index + 2; // (42) Indice + 2(int)
			      	
			      		      	
			      	 //Escribir precio
			      	raf.seek(index);
			      	raf.writeInt(residencia.getPrecio());
			      	index = index + 4; // (44) Indice + 4(int)
			      	
			      	 //Escribir comedor
			      	raf.seek(index);
			      	raf.writeBoolean(residencia.getComedor());
			      	index = index + 1; // (44) Indice + 4(int)
			      	
			      	//Escribir coma
			      	raf.seek(index);
			      	raf.writeChar(coma);
			      	index = index + 2; // (28) Indice + 2(int)
					
					raf.close();
					
					exito = true; 
					
					
				} catch (Exception e) {
					System.out.println("Error en la escritura");
				}
				
				

		
		}else {
			System.out.println("No existe el fichero");
		}
		
		return exito; 
	
		
	}
	
	
	
	/**
	 * Permite a�adir residencias a una lista 
	 * insertando la ruta de un fichero
	 * @param path	
	 * @return lista
	 */
	
	public static ArrayList<Residencia> leerResidencias(String path) {

		File file = new File(path);

		ArrayList<Residencia> lista = new ArrayList<Residencia>();

		if (file.exists()) { // Si existe el fichero leer

			try {
				RandomAccessFile raf = new RandomAccessFile(path, "rw");

				// Variables de lectura
				int idResidencia = 5;
				char[] chNombreResidencia = new char[10];
				char[] chCodUniversidad = new char[6];
				int precio;
				boolean comedor;

				int index = 0; // Indice del lectura
				int size = (int) raf.length(); // Tama�o del fichero

				// Bucle que lee el fichero aumentando su �ndice seg�n el tama�o del fichero
				while (index <= size) {

					// Leer id
					raf.seek(index);
					idResidencia = raf.readInt();
					index = index + 4;

					index = index + 2; // Coma

					// Leer nombreResidencia
					raf.seek(index);
					for (int i = 0; i < chNombreResidencia.length; i++) {
						chNombreResidencia[i] = raf.readChar();
						index = index + 2;
					}

					String nombreResidencia = new String(chNombreResidencia); // Conversi�n char[]a String

					index = index + 2; // Coma

					// leer codUniversidad
					raf.seek(index);
					for (int i = 0; i < chCodUniversidad.length; i++) {
						chCodUniversidad[i] = raf.readChar();
						index = index + 2;
					}

					String codUniversidad = new String(chCodUniversidad); // Conversi�n char[]a String

					index = index + 2; // coma

					// Leer precio
					raf.seek(index);
					precio = raf.readInt();
					index = index + 4;

					// Leer comedor
					raf.seek(index);
					comedor = raf.readBoolean();
					index = index + 1;

					index = index + 2; // Coma

					// Rellenar objeto residencia y a�adir a la lista

					Residencia r = new Residencia(idResidencia, nombreResidencia, codUniversidad, precio, comedor);

					lista.add(r);

				}

				raf.close();

			} catch (Exception e) {

			}

		} else {
			System.out.println("No existe el fichero");
		}

		return lista;

	}

	/**
	 * Permite buscar una residencia insertando la
	 * ruta de un fichero y el Id de residencia
	 * @param path	
	 * @return lista
	 */
	
	public static Residencia buscarResidencia(String path, int id) {

		File file = new File(path);

		Residencia r = null;

		if (file.exists()) { // Si existe el fichero leer

			try {
				RandomAccessFile raf = new RandomAccessFile(path, "rw");

				// Variables de lectura
				int idResidencia;
				char[] chNombreResidencia = new char[10];
				char[] chCodUniversidad = new char[6];
				int precio;
				boolean comedor;

				int index = 0; // Indice del lectura
				int size = (int) raf.length(); // Tama�o del fichero

				// Bucle que lee el fichero aumentando su �ndice seg�n el tama�o del fichero
				while (index <= size) {

					// Leer id
					raf.seek(index);
					idResidencia = raf.readInt();
					index = index + 4;

					index = index + 2; // Coma

					// Leer nombreResidencia
					raf.seek(index);
					for (int i = 0; i < chNombreResidencia.length; i++) {
						chNombreResidencia[i] = raf.readChar();
						index = index + 2;
					}

					String nombreResidencia = new String(chNombreResidencia); // Conversi�n char[]a String

					index = index + 2; // Coma

					// leer codUniversidad
					raf.seek(index);
					for (int i = 0; i < chCodUniversidad.length; i++) {
						chCodUniversidad[i] = raf.readChar();
						index = index + 2;
					}

					String codUniversidad = new String(chCodUniversidad); // Conversi�n char[]a String

					index = index + 2; // coma

					// Leer precio
					raf.seek(index);
					precio = raf.readInt();
					index = index + 4;

					// Leer comedor
					raf.seek(index);
					comedor = raf.readBoolean();
					index = index + 1;

					index = index + 2; // Coma

					// Si el ID introducido es igual que el ID residencia rellenar el objeto
					// residencia y romper el bucle

					if (id == idResidencia) {
						r = new Residencia(idResidencia, nombreResidencia, codUniversidad, precio, comedor);
						break;
					}

				}

				raf.close();

			} catch (Exception e) {

			}

		} else {
			System.out.println("No existe el fichero");
		}

		return r;

	}

	/**
	 * Permite comprobar si existe una residencia
	 * insertando la ruta de un fichero y el Id de residencia	
	 * @return boolean exito
	 */
	
	public static boolean existeResidencia(String path, int id) {
		
		boolean exito = false; 
		
		Residencia r =  buscarResidencia(path, id);
		
		if(r!=null) {
			exito = true; 
		}
		
		return exito; 
		
	}
	
	public static boolean modificarPrecioResidencia(String path, int id, int nuevoPrecio) {

		boolean exito = false;

		File file = new File(path);
		Residencia r = null;

		if (file.exists()) { // Si existe el fichero leer

			try {
				RandomAccessFile raf = new RandomAccessFile(path, "rw");

				// Variables de lectura
				int idResidencia;
				char[] chNombreResidencia = new char[10];
				char[] chCodUniversidad = new char[6];
				int precio;
				boolean comedor;

				int index = 0; // Indice del lectura
				int size = (int) raf.length(); // Tama�o del fichero

				// Bucle que lee el fichero aumentando su �ndice seg�n el tama�o del fichero
				while (index <= size) {

					// Leer id
					raf.seek(index);
					idResidencia = raf.readInt();
					index = index + 4;

					index = index + 2; // Coma

					// Leer nombreResidencia
					raf.seek(index);
					for (int i = 0; i < chNombreResidencia.length; i++) {
						chNombreResidencia[i] = raf.readChar();
						index = index + 2;
					}

					String nombreResidencia = new String(chNombreResidencia); // Conversi�n char[]a String

					index = index + 2; // Coma

					// leer codUniversidad
					raf.seek(index);
					for (int i = 0; i < chCodUniversidad.length; i++) {
						chCodUniversidad[i] = raf.readChar();
						index = index + 2;
					}

					String codUniversidad = new String(chCodUniversidad); // Conversi�n char[]a String

					index = index + 2; // coma

					// Leer precio
					raf.seek(index);
					precio = raf.readInt();
					index = index + 4;

					// Leer comedor
					raf.seek(index);
					comedor = raf.readBoolean();
					index = index + 1;

					index = index + 2; // Coma

					// Si el ID introducido es igual que el ID del registro a modificar, modificar y
					// romper bucle

					if (id == idResidencia) {
						r = new Residencia(idResidencia, nombreResidencia, codUniversidad, precio, comedor);
						raf.seek(index - 7); // Nos colocamos en el espacio de memoria del precio y lo sobreescribimos
						raf.writeInt(nuevoPrecio);
						exito = true;

						break;
					}

				}

				raf.close();

			} catch (Exception e) {

			}

		} else {
			System.out.println("No existe el fichero");
		}

		return exito;

	}
	

}
