package org.flota.project.models;

import org.flota.project.RegistroLog;

public class Producto implements  Cloneable{
    
    private String id; // uuid
    private Double largo;
    private Double ancho;
    private Double peso;


    protected Object clone1() {

    /* Clone agregado a un try catch para usar el Log */

        Object nuevo1 = null;
        try{
            // obj=super.clone();

            nuevo1 = (Mapa)super.clone();
            RegistroLog.getInstance().infoLog("Coordenada MAPA CLONADA correctamente");
            
        }catch(CloneNotSupportedException ex){
            System.out.println(" no se puede duplicar");
            RegistroLog.getInstance().errorLog("Problemas al querer clonar  " + ex.getMessage() );
        }

        return nuevo1;   
    }

    protected Object clone() throws CloneNotSupportedException {
        RegistroLog.getInstance().infoLog("Coordenada MAPA clonada correctamente");
        Mapa nuevo = new Producto();
   
        return nuevo;
    }

}