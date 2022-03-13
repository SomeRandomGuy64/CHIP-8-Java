import java.util.Date;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.layout.Pane;

public class CHIP8 extends Application {

    Renderer renderer;
    private int fps;
    private double fpsInterval;
    private Date startTime, now, then, elapsed;

    public CHIP8()
    {
        fpsInterval = 1000/fps;
    }

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
