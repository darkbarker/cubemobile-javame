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
    private AppCubeCanvas myCanvas;
    
    public AppCubeMIDlet()
    {  
        display = Display.getDisplay( this ); 
        myCanvas = new AppCubeCanvas( this );
    }

    public void startApp()
    {
         display.setCurrent( myCanvas );
    }
    
    public void pauseApp()
    {
    }
    
    public void destroyApp(boolean unconditional)
    {
    }

    public void exitMIDlet()
    {
        destroyApp(true);
        notifyDestroyed();
    }
}
