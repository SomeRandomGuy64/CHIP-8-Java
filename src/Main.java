import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.layout.Pane;

public class Main extends Application {

    Renderer renderer;

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("CHIP-8 Emulator");
        Pane root = new Pane();
        stage.setScene(new Scene(root, 1600, 900));

        renderer = new Renderer(root);
        //renderer.render();
        //renderer.testRender();

        stage.show();
    }

    public static void main(String[] args) 
    {
        launch(args);
    }  
}
