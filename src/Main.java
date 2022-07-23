import java.util.Date;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.layout.Pane;

public class Main extends Application {

    Renderer renderer;
    CPU cpu;

    private long fps = 60, fpsInterval, startTime, now, then, elapsed, loop;

    @Override
    public void start(Stage stage) throws Exception {

        fpsInterval = 1000 / fps;
        then = System.currentTimeMillis();
        startTime = then;

        //cpu.loadSpritesIntoMemory();
        //cpu.loadROM('BLINKY');
        //javascript code
        //loop = requestAnimationFrame(step);


        stage.setTitle("CHIP-8 Emulator");
        Pane root = new Pane();
        renderer = new Renderer(root, 15);

        //testing purposes, remove when not testing
        renderer.testRender();
        renderer.render();

        stage.setScene(new Scene(root, renderer.getScaledColumns(), renderer.getScaledRows()));
        stage.show();
    }

    public static void main(String[] args) 
    {
        launch(args);
    }  

    public void step()
    {
        now = System.currentTimeMillis();
        elapsed = now - then;

        if (elapsed > fpsInterval)
        {
            cpu.cycle();
        }

        //javascript code
        //loop = requestAnimationFrame(step);
    }
}
