package ru.medv.cubemobile;

import java.util.Timer;
import java.util.TimerTask;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

import ru.medv.cubemobile.util3d.Cube8;
import ru.medv.cubemobile.util3d.PhisicalSys;
import ru.medv.cubemobile.util3d.Point2;

/**
 *
 * @author darkbarker <barkdarker@gmail.com>
 */
public class CubeCanvas extends Canvas
{
    private AppCubeMIDlet midlet;
    private int displayWidth, displayHeight;

    private DrawTask drawTask;
    //private TimerTask tt;
    
    //--
    private PhisicalSys mySys;
    private Cube8 myCube;	
    
    private double valRotX;
    private double valRotY;
    private double valRotZ;	
    
    /** calculations per second */
    private FpsMetter fpsm;
    /** renderings per second */
    private FpsMetter drawMetter;
    
    boolean paused;
    
    boolean fullscreen;
    
    public CubeCanvas(AppCubeMIDlet midlet)
    {
        this.midlet = midlet;
        
		// fps-metters
        fpsm = new FpsMetter();
        drawMetter = new FpsMetter();
        
        myCube = new Cube8();

        valRotX=1.0;
        valRotY=1.0;
        valRotZ=1.0;

        paused = true;
        
        fullscreen = false;
        setFullScreenMode( false );
        
        recalculateScreen();
    }
    
    // recalculates the display settings, for example, after the transfer to/from full screen mode.
    private void recalculateScreen()
    {
        displayWidth = getWidth();
        displayHeight = getHeight();
       	Point2 center = new Point2( displayWidth/2, displayHeight/2 );
       	double koef = ( Math.min(displayWidth,displayHeight) / 2.0 ) * 0.5;
       	mySys = new PhisicalSys( center, koef, -koef );   
    }
    
	public void start()
	{
		if( paused )
		{
			paused = false;
			fpsm.reset();
			drawMetter.reset();
			// start timers
			drawTask = new DrawTask();
			drawTask.start();
		}
		repaint();
	}

	/**
	 * Here is the release as many as possible of resources.
	 */
	public void pause()
	{		
        if( !paused )
        {
            paused = true;
            // stop timers
            drawTask.quit();
            drawTask = null;
        }
        repaint();
	}

	public void destroy()
	{
		pause();		
	}	

	public boolean isPaused()
	{		
		return paused;
	}   
	
	private Font font = Font.getFont( Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL );
	private int fontheight = font.getHeight();
	
    public void paint(Graphics g)
    {
        g.setColor(0, 0, 0);
        g.fillRect(0, 0, displayWidth, displayHeight);
      
		synchronized (myCube)
		{
			myCube.draw( g, mySys );
		}
        
        g.setFont( font );
        
        long fm = Runtime.getRuntime().freeMemory();
        fm = fm / 1024;
        
        g.setColor( 127, 127, 127 );
        g.drawString( "fps: " + fpsm.getFps(), 0+5, 0+fontheight*0, 0 );
        g.drawString( "dps: " + drawMetter.getFps(), 0+5, 0+fontheight*1, 0 );
        g.drawString( "frm: " + fm + "K", 0+5, 0+fontheight*2, 0 );
        if( paused )
        {
        	g.drawString( "paused", 0+5, 0+fontheight*3, 0 );
        }
        
        drawMetter.proceedevent();
    }
    
    protected void keyPressed(int keyCode)
    {
        //int action = getGameAction(keyCode);
        switch(keyCode)
        {
            case KEY_NUM1:
                valRotX += +0.5; 
                valRotY += +0.5;
                valRotZ = +0.5;
            break;
            case KEY_NUM2: 
                valRotX += +0.5;                 
                valRotY += 0;
                valRotZ = +0.5;
            break;
             case KEY_NUM3:
                valRotX += +0.5; 
                valRotY += -0.5;
                valRotZ = +0.5;
            break;
            case KEY_NUM4:
                valRotX += 0; 
                valRotY += +0.5;
                valRotZ = +0.5;
            break;
            case KEY_NUM6:               
            	valRotX += 0;
                valRotY += -0.5;
                valRotZ = +0.5;
            break;
            case KEY_NUM7:
                valRotX += -0.5; 
                valRotY += +0.5;
                valRotZ = +0.5;
            break;
             case KEY_NUM8: 
                valRotX += -0.5;
                valRotY += 0;
                valRotZ = +0.5;
            break;
             case KEY_NUM9:
                valRotX += -0.5; 
                valRotY += -0.5;
                valRotZ = +0.5;
            break;
             case KEY_STAR:
            	 fullscreen = !fullscreen;
            	 setFullScreenMode( fullscreen );
            	 recalculateScreen();
            break;
             case KEY_NUM0:
            	 if( isPaused() )
            	 {
            		 start();
            	 }
            	 else
            	 {
            		 pause();
            	 }
            break;
        }
    }
     
    private class DrawTask extends Thread
    {
    	private boolean quit = false;

        public void quit()
        {
        	quit = true;
        }
        
        public final void run()
        {
        	while( !quit )
        	{
        		long fps = fpsm.getFps();
        		if( fps==0 ) fps = Long.MAX_VALUE;
        		synchronized (myCube)
				{
            		myCube.rotX(valRotX/fps);
            		myCube.rotY(valRotY/fps);
            		myCube.rotZ(valRotZ/fps);
				}
                fpsm.proceedevent();
        		repaint();
        	}
        }
    }
}
