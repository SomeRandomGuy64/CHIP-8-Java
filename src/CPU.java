import java.util.Stack;
import java.util.ArrayList;

public class CPU 
{
    private int memoryAddresses, delayTimer, soundTimer;
    private long pc, memory[], v;
    private Stack<Integer> stack = new Stack<>();
    private boolean paused;

    public CPU(Renderer renderer)
    {
        memoryAddresses = 0;

        delayTimer = 0;
        soundTimer = 0;

        pc = 0x200;

        paused = false;

    }

    public void loadSpritesIntoMemory() 
    {

        int memoryPosition = 0;

        long sprites[] = new long[]
        {
            0xF0, 0x90, 0x90, 0x90, 0xF0, //0
            0x20, 0x60, 0x20, 0x20, 0x70, //1
            0xF0, 0x10, 0xF0, 0x80, 0xF0, //2
            0xF0, 0x10, 0xF0, 0x10, 0xF0, //3
            0x90, 0x90, 0xF0, 0x10, 0x10, //4
            0xF0, 0x80, 0xF0, 0x10, 0xF0, //5
            0xF0, 0x80, 0xF0, 0x90, 0xF0, //6
            0xF0, 0x10, 0x20, 0x40, 0x40, //7
            0xF0, 0x90, 0xF0, 0x90, 0xF0, //8
            0xF0, 0x90, 0xF0, 0x10, 0xF0, //9
            0xF0, 0x90, 0xF0, 0x90, 0x90, //A
            0xE0, 0x90, 0xE0, 0x90, 0xE0, //B
            0xF0, 0x80, 0x80, 0x80, 0xF0, //C
            0xE0, 0x90, 0x90, 0x90, 0xE0, //D
            0xF0, 0x80, 0xF0, 0x80, 0xF0, //E
            0xF0, 0x80, 0xF0, 0x80, 0x80 //F
        };

        for (memoryPosition = 0; memoryPosition < sprites.length; memoryPosition++)
        {
            memory[memoryPosition] = sprites[memoryPosition];
        }
    }

    public void loadProgramIntoMemory(long program[])
    {

        int loc;

        for (loc = 0; loc < program.length; loc++)
        {
            memory[0x200 + loc] = program[loc];
        }
    }

}
