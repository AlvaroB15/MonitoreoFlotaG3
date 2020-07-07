package org.flota.project.models;

import org.flota.project.RegistroLog;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Ventana extends Application {

    private Mapa mapaBase;

    final String IDLE_BUTTON_STYLE = "-fx-background-color: gray;-fx-background-radius: 8px, 8px, 8px, 8px;";
    final String HOVERED_BUTTON_STYLE = "-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;";

    private RegistroLog registro = RegistroLog.getInstance();

    @Override
    public void start(Stage stage) throws Exception {

        // set the title and size of the stage and show it
        stage.setTitle("Sistema de Monitoreo de Vehiculos");
        stage.setWidth(800);
        stage.setHeight(700);
        stage.show();


        /* Version Otiginal 

        // create a JavaFX scene with a stack pane as the root node and add it to the scene
        StackPane stackPane = new StackPane();
        Scene scene = new Scene(stackPane);
        stage.setScene(scene);

        // create a MapView to display the map and add it to the stack pane
        mapaBase = new Mapa();
        mapaBase.imprimeCoordenadasActual();
        
        stackPane.getChildren().add(mapaBase.getMapView());

        Button btnNuevo = new Button();
        btnNuevo.setText("Nuevo");

        /* FINAL DE VERSION OTIGINAL */




        /* Version 1 Modificada con BorderPane */

        // create a JavaFX scene with a stack pane as the root node and add it to the scene
        BorderPane stackPane = new BorderPane();
        Scene scene = new Scene(stackPane);
        stage.setScene(scene);

        // create a MapView to display the map and add it to the stack pane
        mapaBase = new Mapa();
        mapaBase.imprimeCoordenadasActual();
        
        // BorderPane.setAlignment(mapaBase.getMapView(), Pos.CENTER);
        stackPane.setCenter(mapaBase.getMapView());

        Button btnNuevo = new Button();
        btnNuevo.setStyle(IDLE_BUTTON_STYLE);
        btnNuevo.setOnMouseEntered(e -> btnNuevo.setStyle(HOVERED_BUTTON_STYLE));
        btnNuevo.setOnMouseExited(e -> btnNuevo.setStyle(IDLE_BUTTON_STYLE));
        btnNuevo.setText("Nuevo");

        BorderPane.setAlignment(btnNuevo, Pos.BOTTOM_CENTER);
        stackPane.setBottom(btnNuevo);
        
        /*FIN DE LA VERSION 1 CON BORDPANE */



        /* 
        DOC OFICIAL

            top: Pos.TOP_LEFT
            bottom: Pos.BOTTOM_LEFT
            left: Pos.TOP_LEFT
            right: Pos.TOP_RIGHT
            center: Pos.CENTER

            Pos.BASELINE_CENTER
            Pos.BASELINE_LEFT
            Pos.BASELINE_RIGHT
            Pos.BOTTOM_CENTER
            Pos.BOTTOM_LEFT
            Pos.BOTTOM_RIGHT
            Pos.CENTER
            Pos.CENTER_LEFT
            Pos.CENTER_RIGHT
            Pos.TOP_CENTER
            Pos.TOP_LEFT
            Pos.TOP_RIGHT

            ListView list = new ListView();
            BorderPane.setAlignment(list, Pos.TOP_LEFT);
            BorderPane.setMargin(list, new Insets(12,12,12,12));
            borderPane.setCenter(list);
        */




        /* Version 2 Modificada con StackPane  
     
        // create a JavaFX scene with a stack pane as the root node and add it to the scene
        StackPane stackPane = new StackPane();
        Scene scene = new Scene(stackPane);
        stage.setScene(scene);

        // create a MapView to display the map and add it to the stack pane
        mapaBase = new Mapa();
        mapaBase.imprimeCoordenadasActual();
        StackPane.setAlignment(mapaBase.getMapView(),Pos.CENTER);

        Button btnNuevo = new Button();
        btnNuevo.setText("Nuevo");
        StackPane.setAlignment(btnNuevo, Pos.BOTTOM_CENTER);


        // Agrega los elementos al Layout StackPane
        stackPane.getChildren().addAll(mapaBase.getMapView(),btnNuevo);


        /* FIN DE LA VERSION 2 CON STACKPANE */




        /* METODO DE EVENTO DE CLICK */
        
        btnNuevo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    muestraNuevaVentana();
                    registro.infoLog("Entre a otra ventana diferente");

				} catch (CloneNotSupportedException e) {
                    registro.errorLog("Problemas en abrir la ventana "+e.getMessage());
                    // e.printStackTrace();
				}
            }
        });
        
        // stackPane.getChildren().add(btnNuevo);

    }



    public void muestraNuevaVentana() throws CloneNotSupportedException {
        Stage stage = new Stage();
        StackPane stackPane = new StackPane();
        Scene scene = new Scene(stackPane);
        stage.setScene(scene);

        //  Clonacion de MapaBase VERSION ORIGINAL

        // Mapa mapaBase2 = new Mapa(mapaBase.getCoordenadaXActual(),mapaBase.getCoordenadaYActual());
        // Mapa mapaBase2 = (Mapa)mapaBase.clone();
        // mapaBase2.imprimeCoordenadasActual();
        // stackPane.getChildren().add(mapaBase2.getMapView());


    

        


        // Se creo un objeto para usar el metodo clone

        // Mapa m1 = new Mapa(20.3,30.5,66.6,78.9);
        Mapa mapaBase3 = (Mapa) mapaBase.clone();
        mapaBase3.imprimeCoordenadasActual();
        mapaBase3.imprimeAtributosMapa();

        stackPane.getChildren().add(mapaBase3.getMapView());


        stage.show();
    }    

}