package org.flota.project.models;

import java.util.concurrent.ExecutionException;

import com.esri.arcgisruntime.concurrent.ListenableFuture;
import com.esri.arcgisruntime.geometry.CoordinateFormatter;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.Viewpoint;
import com.esri.arcgisruntime.mapping.view.Callout;
import com.esri.arcgisruntime.mapping.view.MapView;

import org.flota.project.RegistroLog;

import javafx.geometry.Point2D;
import javafx.scene.input.MouseButton;
import javafx.util.Duration;

public class Mapa implements IMapa , Cloneable {

    private MapView mapView;
    
    private double coordenadaXInicial;
    private double coordenadaYInicial;
    private double coordenadaXActual;
    private double coordenadaYActual;
    private String tipoMapa;
    private double escala;

    // Llamando a la unica instancia de RegistroLog 
    private RegistroLog registro = RegistroLog.getInstance();

    // Se creo un constructor para la implementacion de Cloneable
    public Mapa(Double coordenadaXInicial, Double coordenadaYInicial, Double coordenadaXActual, Double coordenadaYActual, String tipoMapa, double escala){
        this.coordenadaXInicial = coordenadaXInicial;
        this.coordenadaYInicial = coordenadaYInicial;
        this.coordenadaXActual = coordenadaXActual;
        this.coordenadaYActual = coordenadaYActual;
        this.tipoMapa  = tipoMapa;
        this.escala = escala;
    }


    public Mapa()   {

        // create a MapView to display the map and add it to the stack pane
        mapView = new MapView();

        // create an ArcGISMap with the default imagery basemap
        final ArcGISMap map = new ArcGISMap(Basemap.createImagery());

        // display the map by setting the map on the map view
        mapView.setMap(map);

        // latitude, longitude, scale
        //Viewpoint viewpoint = new Viewpoint(27.3805833, 33.6321389, 6E3);
        
        this.coordenadaXInicial = -12.0560;
        this.coordenadaYInicial = -77.0844;
        Viewpoint viewpoint = new Viewpoint(this.coordenadaXInicial, this.coordenadaYInicial, 12000);   // UNMSM
        // 12000
        this.tipoMapa = "Ortofotomapa";
        this.escala = 12000;

        // take 5 seconds to move to viewpoint
        final ListenableFuture<Boolean> viewpointSetFuture = mapView.setViewpointAsync(viewpoint, 5);

        // Agregando mensajes para el uso del logger, y muestre por consola
        viewpointSetFuture.addDoneListener(() -> {
            try {
                boolean completed = viewpointSetFuture.get();
                if (completed) {
                    // registro.log("Acercamiento completado");

                    registro.infoLog("Acercamiento completado");
                }
            } catch (InterruptedException e) {

                registro.infoLog("Acercamiento interrumpido");
                // registro.log("Acercamiento interrumpido");
            } catch (ExecutionException e) {

                // Deal with exception during animation...
                registro.errorLog("Deal with exception during animation");
            }
        });
        
        
        
      // click event to display the callout with the formatted coordinates of the clicked location
      mapView.setOnMouseClicked(e -> {
        // check that the primary mouse button was clicked and user is not panning
        if (e.isStillSincePress() && e.getButton() == MouseButton.PRIMARY) {
          // get the map point where the user clicked
          Point2D point = new Point2D(e.getX(), e.getY());
          //System.out.println("Coordenadas: " + e.getX() + ", " + e.getY());
          Point mapPoint = mapView.screenToLocation(point);
          // show the callout at the point with the different coordinate format strings
          showCalloutWithLocationCoordinates(mapPoint);
        }
      });        
    }


    private void showCalloutWithLocationCoordinates(Point location) {
        Callout callout = mapView.getCallout();
        callout.setTitle("Location:");

        this.coordenadaXActual = location.getX();
        this.coordenadaYActual = location.getY();

        // registro.log("Coordenadas: " + this.coordenadaXActual + ", " + this.coordenadaYActual);
        registro.debugLog("Coordenadas: " + this.coordenadaXActual + ", " + this.coordenadaYActual);

        String latLonDecimalDegrees = CoordinateFormatter.toLatitudeLongitude(location, CoordinateFormatter
            .LatitudeLongitudeFormat.DECIMAL_DEGREES, 4);
        String latLonDegMinSec = CoordinateFormatter.toLatitudeLongitude(location, CoordinateFormatter
            .LatitudeLongitudeFormat.DEGREES_MINUTES_SECONDS, 1);

        String utm = CoordinateFormatter.toUtm(location, CoordinateFormatter.UtmConversionMode.LATITUDE_BAND_INDICATORS,
            true);
        String usng = CoordinateFormatter.toUsng(location, 4, true);
        callout.setDetail(
                "Decimal Degrees: " + latLonDecimalDegrees + "\n" +
                "Degrees, Minutes, Seconds: " + latLonDegMinSec + "\n" +
                "UTM: " + utm + "\n" +
                "USNG: " + usng + "\n"
        );
        registro.infoLog("Decimal Degrees: "+latLonDecimalDegrees+" Degrees, Minutes, Seconds: "+latLonDegMinSec+" UTM: "+utm +
        "USNG: "+usng);

        mapView.getCallout().showCalloutAt(location, new Duration(500));
    }

    public MapView getMapView() {
        return mapView;
    }

    public void setMapView(MapView mapView) {
        this.mapView = mapView;
    }


    public void imprimeCoordenadasActual()  {

        //System.out.println("Coordenadas actual: [" + this.coordenadaXActual + ", " + this.coordenadaYActual + "]");
        // registro.log("Coordenadas actual: [" + this.coordenadaXActual + ", " + this.coordenadaYActual + "]");
        registro.debugLog("Coordenadas actual: [" + this.coordenadaXActual + ", " + this.coordenadaYActual + "]");
    }

    public void imprimeAtributosMapa(){

        registro.debugLog("Coordenada inicial X: "+this.coordenadaXInicial+ " Coordenada inicila Y: "+this.coordenadaYInicial+" Coordenada X Actual: "+this.coordenadaXActual+" Coordenada Y Actual: "+this.coordenadaXActual+ " p1: "+this.tipoMapa+" p2: "+this.escala);
    }

    
    @Override
    public IMapa copiar() {

        //return new Mapa(this);
        Mapa m = new Mapa();
        m.coordenadaXInicial = this.coordenadaXInicial;
        m.coordenadaYInicial = this.coordenadaYInicial;
        m.coordenadaXActual = this.coordenadaXActual;
        m.coordenadaYActual = this.coordenadaYActual;
        m.tipoMapa = this.tipoMapa;
        m.escala = this.escala;

        registro.infoLog("Coordenada MAPA copiada correctamente");

        return m;        
    }

    // Se agregao la interfaz Cloneable nativa de Java
    // protected Object clone() throws CloneNotSupportedException {
    protected Object clone1() {

          /* Clone agregado a un try catch para usar el Log */

        Object nuevo1 = null;
        try{
            // obj=super.clone();

            nuevo1 = (Mapa)super.clone();
            registro.infoLog("Coordenada MAPA CLONADA correctamente");
            
        }catch(CloneNotSupportedException ex){
            System.out.println(" no se puede duplicar");
            registro.errorLog("Problemas al querer clonar  " + ex.getMessage() );
        }
        return nuevo1;   
    }


    // Forma de Ramiro que funciona correctamente 
    public Object clone() throws CloneNotSupportedException {
        // registro.infoLog("Coordenada MAPA clonada correctamente");
        Mapa nuevo = new Mapa(this.coordenadaXInicial, this.coordenadaYInicial, this.coordenadaXActual, this.coordenadaYActual,this.tipoMapa,this.escala);
        return nuevo;
    }

    protected Object clone3() throws CloneNotSupportedException {

        registro.infoLog("Coordenada MAPA clonada correctamente");
        return super.clone();
    }

}