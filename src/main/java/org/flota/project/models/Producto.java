package org.flota.project.models;

public class Producto implements  Cloneable{
    
    private String id; // uuid
    private Double largo;
    private Double ancho;
    private Double peso;

    // protected Object clone1() {

    // /* Clone agregado a un try catch para usar el Log */

    //     Object nuevo1 = null;
    //     try{
    //         // obj=super.clone();

    //         nuevo1 = (Mapa)super.clone();
    //         registro.infoLog("Coordenada MAPA CLONADA correctamente");
            
    //     }catch(CloneNotSupportedException ex){
    //         System.out.println(" no se puede duplicar");
    //         registro.errorLog("Problemas al querer clonar  " + ex.getMessage() );
    //     }
    //     return nuevo1;   

    }

}