package ru.medv.cubemobile;

import java.util.Timer;
import java.util.TimerTask;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

/**
 *
 * @author DimOn
 */
public class CubeCanvas extends Canvas
{
    private AppCubeMIDlet midlet;  // Главный класс мидлета
    private int displayWidth, displayHeight; // Размер экрана

    private Timer tm; // таймер
    //private TimerTask tt; // Задача для выполнения
    
    //--
    private PhisicalSys mySys;
    private Cube8 myCube;	
    
    private double valRotX, valRotY, valRotZ;	
    
    private FpsMetter fpsm;
    
    boolean paused;
    
    
    public CubeCanvas(AppCubeMIDlet midlet)
    {
        this.midlet = midlet;
        
        displayWidth = getWidth();
        displayHeight = getHeight();
        
        //--
        myCube = new Cube8();
        fpsm = new FpsMetter();
       	Point2 center = new Point2(displayWidth/2, displayHeight/2);
       	double koef = ( Math.min(displayWidth,displayHeight) /2.0 ) *0.5;
       	mySys = new PhisicalSys( center, koef, -koef );        
        valRotX=0.0075;
        valRotY=0.0095;
        valRotZ=0.03;
        //sp = new SeparateSubTask();
        //--
        
        paused = true; 

        setFullScreenMode(true);
    }
    
	public void start()
	{
		if( paused )
		{
			paused = false;
			// стартуем таймеры
			TimerTask tt = new DrawTask();
	        tm = new Timer();
	        tm.scheduleAtFixedRate(tt, 0, 1);
		}
		repaint();
	}

	/**
	 * Тут происходит освобождение ресурсов по максимуму.
	 */
	public void pause()
	{		
        if( !paused )
        {
            paused = true;
            // стопим таймеры
            tm.cancel();
            tm = null;
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
	
    public void paint(Graphics g)
    {
        g.setColor(0, 0, 0);
        g.fillRect(0, 0, displayWidth, displayHeight);
      
        myCube.draw( g, mySys ); 
        
        g.setColor( 127, 127, 127 );
        g.drawString( fpsm.toString() + " fps", 0+5, 0, 0 );     
        
        fpsm.proceedevent();
    }
    
    protected void keyPressed(int keyCode)
    {
        //int action = getGameAction(keyCode);
        switch(keyCode)
        {
            case KEY_NUM1:
                valRotX = + 1.0 /50.0; 
                valRotY = + 1.0 /50.0;
            break;
            case KEY_NUM2: 
                valRotX = + 1.0 /50.0;                 
                valRotY = 0;
            break;
             case KEY_NUM3:
                valRotX = + 1.0 /50.0; 
                valRotY = - 1.0 /50.0;
            break;
            case KEY_NUM4:
                valRotX = 0; 
                valRotY = + 1.0 /50.0;
            break;
            case KEY_NUM6:               
            	valRotX = 0;
                valRotY = - 1.0 /50.0;
            break;
            case KEY_NUM7:
                valRotX = - 1.0 /50.0; 
                valRotY = + 1.0 /50.0;
            break;
             case KEY_NUM8: 
                valRotX = - 1.0 /50.0;
                valRotY = 0;
            break;
             case KEY_NUM9:
                valRotX = - 1.0 /50.0; 
                valRotY = - 1.0 /50.0;
            break;
        }
    }  
    
    private void updateDisplay()
    {
        myCube.rotX(valRotX);
        myCube.rotY(valRotY);
        myCube.rotZ(valRotZ);
    }
     
    private class DrawTask extends TimerTask
    {
        public final void run()
        {
            updateDisplay();
            repaint();
        }
    }
}