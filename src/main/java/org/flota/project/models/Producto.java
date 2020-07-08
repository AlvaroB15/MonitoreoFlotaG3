package org.flota.project.models;

import org.flota.project.RegistroLog;

public class Producto implements  Cloneable{
    
    private String id; // uuid
    private Double largo;
    private Double ancho;
    private Double peso;

    
    public Producto(String id, Double largo, Double ancho, Double peso) {
        this.id = id;
        this.largo = largo;
        this.ancho = ancho;
        this.peso = peso;
    }

    // protected Object clone1() {

    // /* Clone agregado a un try catch para usar el Log */

    //     Object nuevo1 = null;
    //     try{
    //         // obj=super.clone();

    //         nuevo1 = (Mapa)super.clone();
    //         RegistroLog.getInstance().infoLog("Coordenada MAPA CLONADA correctamente");
            
    //     }catch(CloneNotSupportedException ex){
    //         System.out.println(" no se puede duplicar");
    //         RegistroLog.getInstance().errorLog("Problemas al querer clonar  " + ex.getMessage() );
    //     }

    //     return nuevo1;   
    // }

    public Object clone() throws CloneNotSupportedException {
        RegistroLog.getInstance().infoLog("Producto clonada correctamente");
        Producto nuevo = new Producto(this.id,this.largo,this.ancho,this.peso);
   
        return nuevo;
    }

	@Override
	public String toString() {
		return "Producto [ancho=" + ancho + ", id=" + id + ", largo=" + largo + ", peso=" + peso + "]";
	}

}