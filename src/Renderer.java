import java.util.ArrayList;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Renderer extends Canvas
{   
    //creates ints for columns, rows and scale
    private int columns, rows, scale;
    private GraphicsContext gc;
    ArrayList<Integer> display = new ArrayList<>();

    //contructor
    public Renderer()
    {   
        //64 columns on the CHIP-8 display
        columns = 64;
        //32 rows on the CHIP-8 display
        rows = 32;
        //as a resolution of 64x32 is incredibly small I'll be using scale to multiply the resolution
        //set scale for now but will change to a user input later
        scale = 20;
        clear();
    }

    //will toggle the pixels on and off
    public void setPixel(int x, int y)
    {  
        //code for pixels to wrap around the screen
        xWrapAround(x);
        yWrapAround(y);

        int pixelLoc = x + (y * columns);



    }

    public void render()
    {
        //clears the display

        //loop through the array
    }

    //method which wraps a pixel around the screen on the x-axis
    public void xWrapAround(int x)
    {
        if (x > columns)
        {
            x -= columns;
        }
        else if (x < 0)
        {
            x += columns;
        }
    }

    //method which wraps a pixel around the screen on the y-axis
    public void yWrapAround(int y)
    {
        if (y > rows)
        {
            y -= rows;
        }
        else if (y < 0)
        {
            y += rows;
        }
    }

    public void clear()
    {
        for (int pixel = 0; pixel < getNumberOfPixels(); pixel++)
        {
            display.set(pixel, 0);
        }
    }



    public int getColumns()
    {
        return columns;
    }

    public int getRows()
    {
        return rows;
    }

    public int getScale()
    {
        return scale;
    }

    public int getScaledColumns()
    {
        return columns*scale;
    }

    public int getScaledRows()
    {
        return rows*scale;
    }

    public int getNumberOfPixels()
    {
        return columns*rows;
    }
}
