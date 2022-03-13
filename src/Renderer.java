import java.util.ArrayList;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Renderer
{   
    //creates ints for columns, rows and scale
    private int columns, rows, scale;
    private GraphicsContext gc;
    private Canvas canvas;
    ArrayList<Integer> display = new ArrayList<>();

    //contructor
    public Renderer(Pane root)
    {   
        super();
        //64 columns on the CHIP-8 display
        columns = 64;
        //32 rows on the CHIP-8 display
        rows = 32;
        //as a resolution of 64x32 is incredibly small I'll be using scale to multiply the resolution
        //set scale for now but will change to a user input later
        scale = 10;

        canvas = new Canvas(1600, 900);
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        root.getChildren().add(canvas);
        clear();
        render();
        testRender();
    }

    //will toggle the pixels on and off
    public int setPixel(int x, int y)
    {  
        //code for pixels to wrap around the screen
        xWrapAround(x);
        yWrapAround(y);

        int pixelLoc = x + (y*columns);
        return display.get(pixelLoc) ^ 1;
    }

    public void render()
    {
        //loop through the array
        for (int i = 0; i < display.size(); i++)
        {
            int x = (i % columns) * scale;
            double y = Math.floor(i / columns) * scale;

            if (display.get(i) == 1)
            {
                gc.setFill(Color.BLACK);
            }
            else
            {
                gc.setFill(Color.WHITE);;
            }
            gc.fillRect(x, y, scale, scale);
        }
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
        for (int pixel = 0; pixel < getNumberOfPixels() - 1; pixel++)
        {
            display.add(0);
            if(display.get(pixel) != 0)
            {
                display.add(0);
            }
            else
            {
                display.set(pixel, 0);    
            }
        }
    }

    public void testRender()
    {
        //setPixel(100, 100);
    }


    public int getColumns()
    {
        return columns;
    }

    public int getRows()
    {
        return rows;
    }

    public int getColumnsArray()
    {
        return columns - 1;
    }

    public int getRowsArray()
    {
        return rows - 1;
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
