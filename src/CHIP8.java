import java.util.Date;
import javafx.*;
import javafx.application.Application;
import javafx.stage.Stage;

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
        // TODO Auto-generated method stub
        stage.setTitle("CHIP-8 Emulator");
        Pane root = new Pane();
    }

    public static void main(String[] args) 
    {
        launch(args);
    }  
}
