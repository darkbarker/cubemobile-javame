package ru.medv.cubemobile;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;

/**
 * @author  DimOn
 */
public class AppCubeMIDlet
	extends MIDlet
{
    private Display display;
    private CubeCanvas cubeCanvas;
    
    public AppCubeMIDlet()
    {  
        display = Display.getDisplay( this ); 
        cubeCanvas = new CubeCanvas( this );
    }

    public void startApp()
    {
    	cubeCanvas.start();
        display.setCurrent( cubeCanvas );
    }
    
    public void pauseApp()
    {
    	cubeCanvas.pause();
    }
    
    public void destroyApp(boolean unconditional)
    {
    	cubeCanvas.destroy();
    }

    public void exitMIDlet()
    {
        destroyApp(true);
        notifyDestroyed();
    }
}
