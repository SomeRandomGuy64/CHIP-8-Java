import java.util.Stack;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;

public class CPU 
{
    private int memoryAddresses, delayTimer, soundTimer, speed, pc;
    private long memory[], v;
    private Stack<Integer> stack = new Stack<>();
    private boolean paused;
    //JavaFX stuff
    Renderer renderer = new Renderer();
    Keyboard keyboard = new Keyboard();
    Speaker speaker = new Speaker();

    public CPU(Renderer renderer, Speaker speaker, Keyboard keyboard)
    {
        //JavaFX stuff

        memoryAddresses = 0;

        delayTimer = 0;
        soundTimer = 0;

        pc = 0x200;

        paused = false;

        speed = 10;
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

    public void cycle()
    {
        for (int i = 0; i < speed; i++)
        {
            if (!paused)
            {
                long opcode;
                opcode = (memory[pc] << 8 | memory[pc + 1]);
                //executeInstruction(opcode);
            }
        }

        if (!paused)
        {
            updateTimers();
        }

        playSound();
        renderer.render();
    }

    public void updateTimers()
    {
        if (delayTimer > 0)
        {
            delayTimer -= 1;
        }
        if (soundTimer > 0)
        {
            soundTimer -=1;
        }
    }

    public void playSound()
    {
        if (soundTimer > 0)
        {
        //speaker.play(440)
        }
        else
        {
        //speaker.stop()
        }
    }

    public void loadRom(String romName)
    {
        
    }

    public void executeInstruction(int opcode)
    {
        pc += 2;

        long x = (opcode & 0x0F00) >> 8;

        long y = (opcode & 0x00F0) >> 4;

        switch (opcode & 0xF000) 
        {
            case 0x0000:
                switch (opcode)
                {
                    case 0x00E0:
                        renderer.clear();
                        break;
                    case 0x00EE:
                        pc = stack.pop();
                        break;
                }
                break;
            

        }
    }

}
