import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class llamando extends Application{

	@Override
	public void start(Stage stage) throws Exception {
        
                stage.setTitle("Sistema de Monitoreo de Vehiculos");
                stage.setWidth(800);
                stage.setHeight(700);
                stage.show();

                //BorderPane example
                BorderPane pane = new BorderPane();

                Label top = new Label("Top");

                Label right = new Label("Right");

                HBox bottom = new HBox();
                bottom.getChildren().addAll(new Label("First"), new Label("Second"));

                VBox left = new VBox();
                left.getChildren().addAll(new Label("Upper"), new Label("Lower"));

                StackPane center = new StackPane();
                center.getChildren().addAll(new Label("Lorem"), new Label("ipsum"));

                pane.setTop(top);        //The text "Top"
                pane.setRight(right);    //The text "Right"
                pane.setBottom(bottom);  //Row of two texts
                pane.setLeft(left);      //Column of two texts
                pane.setCenter(center);  //Two texts on each other        
		
	}

    

    

}