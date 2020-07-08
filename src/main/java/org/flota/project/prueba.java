package org.flota.project;

import org.flota.project.models.Mapa;
import org.flota.project.models.Producto;

import javafx.application.Application;



public class prueba {

    // private static final Logger LOGGER = LogManager.getLogger(prueba.class.getName());
    // 
    public static void main(String[] args) {
        
        RegistroLog registro = RegistroLog.getInstance();

        // launch(args);
        // Application.launch(llamando.class, args); // se debe crear una clases que se llame en el launch


        // Mapa m1 = new Mapa(20.3,30.5,66.6,78.9);

        // Mapa mapaBase3 = (Mapa) m1.copiar();
        // mapaBase3.imprimeCoordenadasActual();

        // stackPane.getChildren().add(mapaBase3.getMapView());


        // Mapa mapaBase3 = (Mapa)mapaBase.copiar();
        // mapaBase3.imprimeCoordenadasActual();
        // stackPane.getChildren().add(mapaBase3.getMapView());

        Producto p1 = new Producto("abc12", 12.5, 20.5, 30.6);
        System.out.println(p1.toString());

        try {
            Producto p2 = (Producto)p1.clone();
            registro.infoLog("Se clono correctamente");
            System.out.println(p2.toString());
		} catch (CloneNotSupportedException e) {
            registro.debugLog("No se pudo clonar, debido a "+ e.getMessage());
            ;
        }
        

        Mapa m1 = new Mapa(0.0,0.0,152.3,176.4,"Geografica",0.65);
        m1.imprimeAtributosMapa();

        try {
            Mapa m2 = (Mapa)m1.clone();
            registro.infoLog("Se clono correctamente");
            m2.imprimeAtributosMapa();
		} catch (CloneNotSupportedException e) {
            registro.debugLog("No se pudo clonar, debido a "+ e.getMessage());
            ;
        }


    }    



}