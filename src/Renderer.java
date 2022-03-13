
public class Renderer 
{   
    //creates ints for columns, rows and scale
    private int columns, rows, scale;

    //contructor
    public Renderer()
    {   
        //64 columns on the CHIP-8 display
        columns = 64;
        //32 rows on the CHIP-8 display
        rows = 32;
        //as a resolution of 64x32 is incredibly small I'll be using scale to multiply the resolution
        //set scale for now but will change to a user input later
        scale = 10;
    }

    //will toggle the pixels on and off
    public void setPixel(int x, int y)
    {  
        //code for pixels to wrap around the screen
        xWrapAround(x);
        yWrapAround(y);

        int pixelLoc = x + (y * columns);

        int display;


    }

    //method which wraps a pixel around the screen on the x-axis
    public void xWrapAround(int x)
    {
        if (x > columns)
        {
            x -= columns;
        }
        else if (x < columns)
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
        else if (y < rows)
        {
            y += rows;
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
}
