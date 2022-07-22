import java.util.Stack;
import java.util.ArrayList;

public class CPU 
{
    private int memoryAddresses, delayTimer, soundTimer;
    private Stack<Integer> stack = new Stack<>();
    private boolean paused;

    public CPU(Renderer renderer)
    {
        memoryAddresses = 0;

        delayTimer = 0;
        soundTimer = 0;


    }

    public void loadSpritesIntoMemory() 
    {
        ArrayList<Integer> sprites = new ArrayList<>();
        
    }

}
