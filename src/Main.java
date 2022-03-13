import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.layout.Pane;

public class Main extends Application {

    Renderer renderer;

    @Override
    public void start(Stage stage) throws Exception {
        renderer = new Renderer();


        stage.setTitle("CHIP-8 Emulator");
        Pane root = new Pane();
        stage.setScene(new Scene(root, renderer.getScaledColumns(), renderer.getScaledRows()));
        stage.show();
    }

    public static void main(String[] args) 
    {
        launch(args);
    }  
}
