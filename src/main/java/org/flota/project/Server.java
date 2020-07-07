package org.flota.project;

import com.esri.arcgisruntime.ArcGISRuntimeEnvironment;

import org.flota.project.models.Ventana;

import javafx.application.Application;

public class Server {

    public static void main(String[] args) {

        // No se hace esto, ya que no interesa instanciar , solo usar el static
        RegistroLog registro = new RegistroLog();

        RegistroLog.getInstance().log("Iniciando servidor");
        registro.infoLog("Iniciando el servidor");
       
       
        // Sigue siendo la misma intancia, pero con diferente contenido
        // RegistroLog.getInstance().log("Iniciando otro objeto");

        try {

            ArcGISRuntimeEnvironment.setInstallDirectory("C:\\Java\\arcgis-runtime-sdk-java-100.8.0");
            registro.debugLog("Se pudo abrir la ventana.");
            System.out.println(RegistroLog.getInstance());
            
            Application.launch(Ventana.class, args);


        } catch (Exception e) {
            registro.errorLog("Problemas al querer abrir la ventana o  la libreria ArcGis "+e.getMessage());
        }

        // System.out.println(RegistroLog.getInstance());

    }

}