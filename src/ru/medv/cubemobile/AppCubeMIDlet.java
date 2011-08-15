package ru.medv.cubemobile;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.midlet.MIDlet;

/**
 * @author dimon
 */
public class AppCubeMIDlet
	extends MIDlet
	implements CommandListener
{
    private Display display;
    private CubeCanvas cubeCanvas;
    private Command cmExit = new Command( "Exit", Command.EXIT, 99 );
    private Command cmToggle = new Command("Stop/Go", Command.SCREEN, 1);
    private Command cmHelp = new Command( "Help", Command.HELP, 2 );
    private Form helpScreen;
    
    public AppCubeMIDlet()
    {  
        display = Display.getDisplay( this ); 
        cubeCanvas = new CubeCanvas( this );
        cubeCanvas.addCommand( cmExit );
        cubeCanvas.addCommand( cmToggle );
        cubeCanvas.addCommand( cmHelp );
        cubeCanvas.setCommandListener( this );
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

    public void commandAction(Command c, Displayable s)
    {        
        if ( c == cmToggle )
        {
            if( cubeCanvas.isPaused() )
            {
            	cubeCanvas.start();
            }
            else
            {
            	cubeCanvas.pause();
            }
            display.setCurrent( cubeCanvas );
        } 
        else if( c == cmExit )
        {
            destroyApp(true);
            notifyDestroyed();
        }
        else if ( c == cmHelp )
        {
        	cubeCanvas.pause();
            showHelp();
        }
    }
    
    void showHelp()
    {
        if (helpScreen == null)
        {
            helpScreen = new Form("AppCubeMIDlet Help");
            helpScreen.append("^ = faster\n");
            helpScreen.append("v = slower\n");
            helpScreen.append("< = fewer\n");
            helpScreen.append("> = more\n");
        }
        helpScreen.addCommand(cmToggle);
        helpScreen.setCommandListener(this);
        display.setCurrent(helpScreen);
    }
}
