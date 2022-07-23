
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.layout.Pane;

public class Main extends Application {

    Renderer renderer;
    CPU cpu;
    Keyboard keyboard;
    Speaker speaker;

    private long fps = 60, fpsInterval, startTime, now, then, elapsed, loop;

    @Override
    public void start(Stage stage) throws Exception {


        fpsInterval = 1000 / fps;
        then = System.currentTimeMillis();
        startTime = then;

        stage.setTitle("CHIP-8 Emulator");
        Pane root = new Pane();

        renderer = new Renderer(root, 15);
        cpu = new CPU(renderer, speaker, keyboard);
        keyboard = new Keyboard();
        speaker = new Speaker();

        cpu.loadSpritesIntoMemory();
        //cpu.loadROM('BLINKY');

        //javascript code
        //loop = requestAnimationFrame(step);

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
