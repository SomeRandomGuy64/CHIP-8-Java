import java.util.Stack;
import java.util.ArrayList;
import java.lang.Math;
public class CPU 
{
    private int memoryAddresses, delayTimer, soundTimer, speed, pc;
    private int v[];
    private ArrayList<Integer> memory = new ArrayList<>();
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

        int[] sprites =
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
            memory.add(sprites[memoryPosition]);
            //System.out.println(sprites[memoryPosition]);
            //System.out.println(memory.get(memoryPosition));
            //System.out.println(memoryPosition);
        }

    }

    public void loadProgramIntoMemory(int program[])
    {
        for (int loc = 0; loc < program.length; loc++)
        {
            //may need to change to memory.add
            memory.set(0x200 + loc, program[loc]); //[0x200 + loc] = program[loc];
        }
    }

    public void cycle()
    {
        for (int i = 0; i < speed; i++)
        {
            if (!paused)
            {
                long opcode = (memory.get(pc) << 8 | memory.get(pc + 1));
                executeInstruction(opcode);
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

    public void executeInstruction(long opcode)
    {

        int iOpcode = (int) opcode;

        pc += 2;

        int x = (iOpcode & 0x0F00) >> 8;

        int y = (iOpcode & 0x00F0) >> 4;

        switch (iOpcode & 0xF000) 
        {
            case 0x0000:
                switch (iOpcode)
                {
                    case 0x00E0:
                        renderer.clear();
                        break;
                    case 0x00EE:
                        pc = stack.pop();
                        break;
                }
                break;
            case 0x1000:
                pc = (iOpcode & 0xFFF);
                break;
            case 0x2000:
                stack.push(pc);
                pc = (iOpcode & 0xFFF);
                break;
            case 0x3000:
                if (v[x] == (iOpcode & 0xFF))
                {
                    pc += 2;
                }
                break;
            case 0x4000:
                if (v[x] != (iOpcode & 0xFF))
                {
                    pc += 2;
                }
                break;
            case 0x5000:
                if (v[x] == v[y])
                {
                    pc += 2;
                }
                break;
            case 0x6000:
                v[x] = (iOpcode & 0xFF);
                break;
            case 0x7000:
                v[x] = (iOpcode & 0xFF);
                break;
            case 0x8000:
                switch (iOpcode & 0xF)
                {
                    case 0x0:
                        v[x] = v[y];
                        break;
                    case 0x1:
                        v[x] |= v[y];
                        break;
                    case 0x2:
                        v[x] &= v[y];
                        break;
                    case 0x3:
                        v[x] ^= v[y];
                        break;
                    case 0x4:
                        int sum = (v[x] += v[y]);
                        v[0xF] = 0;

                        if (sum > 0xFF)
                            {
                                v[0xF] = 1;
                            }

                            v[x] = sum;
                        break;
                    case 0x5:
                        v[0xF] = 0;

                        if (v[x] > v[y])
                        {
                            v[0xF] = 1;
                        }
                        v[x] -= v[y];
                        break;
                    case 0x6:
                        v[0xF] = (v[x] & 0x1);

                        v[x] >>= 1;
                        break;
                    case 0x7:
                        v[0xF] = 0;

                        if (v[y] > v[x])
                        {
                            v[0xF] = 1;
                        }

                        v[x] = v[y] - v[x];
                        break;
                    case 0xE:
                        v[0xF] = (v[x] & 0x80);
                        v[x] <<= 1;
                        break;
                }

                break;
            case 0x9000:
                if (v[x] != v[y])
                {
                    pc +=2;
                }
                break;
            case 0xA000:
                memoryAddresses = (iOpcode & 0xFFF);
                break;
            case 0xB000:
                pc = (iOpcode & 0xFFF) + v[0];
                break;
            case 0xC000:
                int rand = (int) (Math.random() * 0xFF);
                v[x] = rand & (iOpcode & 0xFF);
                break;
            case 0xD000:
                int width = 8;
                int height = (iOpcode & 0xF);

                v[0xF] = 0;

                for (int row = 0; row < height; row++)
                {
                    int sprite = memory.get(memoryAddresses + row);

                    for (int col = 0; col < width; col++)
                    {
                        if ((sprite & 0x80) > 0)
                        {
                            if (renderer.setPixel(v[x] + col, v[y] + row))
                            {
                                v[0xF] = 1;
                            }
                        }

                        sprite <<=1;
                    }
                }
                break;
            case 0xE000:
                switch (iOpcode & 0xFF)
                {
                    case 0x9E:
                        //if (keyboard.isKeyPressed(v[x]))
                        //{
                        //  pc += 2;
                        //}
                        break;
                    case 0xA1:
                        //if (!keyboard.isKeyPressed(v[x]))
                        //{
                        //  pc += 2;
                        //}
                        break;
                }

                break;
            case 0xF000:
                switch (iOpcode & 0xFF)
                {
                    case 0x07:
                        v[x] = delayTimer;
                        break;
                    case 0x0A:
                        paused = true;
                        //keyboard stuff, check js code
                        break;
                    case 0x15:
                        delayTimer = v[x];
                        break;
                    case 0x18:
                        soundTimer = v[x];
                        break;
                    case 0x1E:
                        memoryAddresses += v[x];
                        break;
                    case 0x29:
                        memoryAddresses = v[x]*5;
                        break;
                    case 0x33:
                        //memory.set(memoryAddresses) = 
                        break;
                    case 0x55:
                        for (int registerIndex = 0; registerIndex <= x; registerIndex++)
                        {
                            memory.set(memoryAddresses + registerIndex, v[registerIndex]);
                        }
                        break;
                    case 0x65:
                        for (int registerIndex = 0; registerIndex <= x; registerIndex++)
                        {
                            v[registerIndex] = memory.get(memoryAddresses + registerIndex);
                        }
                        break;
                }

                break;

            default:
                System.out.println("Unknown opcode " + iOpcode);
        }

    }

}
