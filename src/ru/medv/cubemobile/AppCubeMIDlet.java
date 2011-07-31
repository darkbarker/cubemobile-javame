package ru.medv.cubemobile;
/*
 * AppCubeMIDlet.java
 *
 * Created on 7 Май 2006 г., 17:29
 */

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

/**
 *
 * @author  DimOn
 * @version
 */
public class AppCubeMIDlet extends MIDlet //implements CommandListener
{
    private Display display;
    private AppCubeCanvas myCanvas;
    
//  private Command exitCommand = new Command("Exit", Command.EXIT, 99);
    
    public AppCubeMIDlet()
    {  
        display = Display.getDisplay( this ); 
        myCanvas = new AppCubeCanvas( this );
    }

/*
    private void initApp()
    { 
//      display = Display.getDisplay( this ); 
//      myCanvas = new AppCubeCanvas( this );
//      myCanvas.addCommand(exitCommand);        
//      myCanvas.setCommandListener(this);
    }
*/
     
    public void startApp()
    {
         //if( display == null ) initApp();
         //myCanvas.start();
         display.setCurrent( myCanvas );
         
         /*
         FloatMIDlet f1 = new FloatMIDlet(1,1000);
         FloatMIDlet f2 = new FloatMIDlet(1,100);
         FloatMIDlet f3 = new FloatMIDlet(1,10);
         FloatMIDlet f4 = f1.add(f2).add(f3);
         FloatMIDlet f5 = f4.add(5);   
         FloatMIDlet f6 = new FloatMIDlet(0,9000); 
         FloatMIDlet f7 = f5.add(f6); 
         System.out.println("-------------------------------------");
         System.out.println(f1);
         System.out.println(f2);
         System.out.println(f3);
         System.out.println(f4);
         System.out.println(f5);
         System.out.println(f6);
         System.out.println(f7);
         System.out.println("-------------------------------------");
          */
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
    
    /*
     public void commandAction(Command c, Displayable s)
     {
         if(c == exitCommand)
         {
             try {
                 destroyApp(false);
                 notifyDestroyed();
             } catch (MIDletStateChangeException ex)
             {
             }
         }
     }
     */
}
