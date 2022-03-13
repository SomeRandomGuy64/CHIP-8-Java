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
        scale = 20;

        canvas = new Canvas(this.getScaledColumns(), this.getScaledRows());
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        root.getChildren().add(canvas);
        clear();
        testRender();
        render();
    }

    //will toggle the pixels on and off
    public void setPixel(int x, int y)
    {  
        //code for pixels to wrap around the screen
        xWrapAround(x);
        yWrapAround(y);

        int pixelLoc = x + (y * columns);
        display.set(pixelLoc, display.get(pixelLoc) ^ 1);

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
                gc.setFill(Color.rgb(0,255,51));
            }
            else
            {
                gc.setFill(Color.BLACK);;
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
        for (int pixel = 0; pixel < getNumberOfPixels() ; pixel++)
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
        //smiley face
        //y = 1
        setPixel(9,1);
        setPixel(10,1);
        setPixel(11,1);
        setPixel(12,1);
        setPixel(13,1);
        setPixel(14,1);
        setPixel(15,1);

        //y=2
        setPixel(7,2);
        setPixel(8,2);
        setPixel(16,2);
        setPixel(17,2);

        //y=3
        setPixel(5,3);
        setPixel(6,3);
        setPixel(18,3);
        setPixel(19,3);

        //y=4
        setPixel(4,4);
        setPixel(20,4);

        //y=5
        setPixel(4,5);
        setPixel(20,5);

        //y=6
        setPixel(3,6);
        setPixel(5,6);
        setPixel(6,6);
        setPixel(7,6);
        setPixel(13,6);
        setPixel(14,6);
        setPixel(15,6);
        setPixel(16,6);
        setPixel(21,6);

        //y = 7
        setPixel(3,7);
        setPixel(4,7);
        setPixel(7,7);
        setPixel(8,7);
        setPixel(12,7);
        setPixel(15,7);
        setPixel(16,7);
        setPixel(17,7);
        setPixel(21,7);

        //y=8
        setPixel(2,8);
        setPixel(4,8);
        setPixel(7,8);
        setPixel(8,8);
        setPixel(12,8);
        setPixel(16,8);
        setPixel(17,8);
        setPixel(22,8);

        //y=9
        setPixel(2,9);
        setPixel(4,9);
        setPixel(8,9);
        setPixel(12,9);
        setPixel(17,9);
        setPixel(22,9);

        //y=10
        setPixel(2,10);
        setPixel(5,10);
        setPixel(6,10);
        setPixel(7,10);
        setPixel(8,10);
        setPixel(13,10);
        setPixel(14,10);
        setPixel(15,10);
        setPixel(16,10);
        setPixel(17,10);
        setPixel(22,10);

        //y=11
        setPixel(2,11);
        setPixel(22,11);

        //y=12
        setPixel(2,12);
        setPixel(5,12);
        setPixel(6,12);
        setPixel(7,12);
        setPixel(8,12);
        setPixel(9,12);
        setPixel(10,12);
        setPixel(11,12);
        setPixel(12,12);
        setPixel(13,12);
        setPixel(14,12);
        setPixel(15,12);
        setPixel(16,12);
        setPixel(17,12);
        setPixel(22,12);

        //y=13
        setPixel(2,13);
        setPixel(6,13);
        setPixel(18,13);
        setPixel(22,13);

        //y=14
        setPixel(3,14);
        setPixel(6,14);
        setPixel(18,14);
        setPixel(21,14);

        //y=15
        setPixel(3,15);
        setPixel(6,15);
        setPixel(13,15);
        setPixel(14,15);
        setPixel(15,15);
        setPixel(16,15);
        setPixel(17,15);
        setPixel(18,15);
        setPixel(21,15);

        //y=16
        setPixel(4,16);
        setPixel(7,16);
        setPixel(11,16);
        setPixel(12,16);
        setPixel(17,16);
        setPixel(20,16);

        //y=17
        setPixel(4,17);
        setPixel(8,17);
        setPixel(10,17);
        setPixel(16,17);
        setPixel(20,17);

        //y=18
        setPixel(5,18);
        setPixel(6,18);
        setPixel(9,18);
        setPixel(10,18);
        setPixel(11,18);
        setPixel(12,18);
        setPixel(13,18);
        setPixel(14,18);
        setPixel(15,18);
        setPixel(18,18);
        setPixel(19,18);

        //y=19
        setPixel(7,19);
        setPixel(8,19);
        setPixel(16,19);
        setPixel(17,19);

        //y=20
        setPixel(9,20);
        setPixel(10,20);
        setPixel(11,20);
        setPixel(12,20);
        setPixel(13,20);
        setPixel(14,20);
        setPixel(15,20);
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
