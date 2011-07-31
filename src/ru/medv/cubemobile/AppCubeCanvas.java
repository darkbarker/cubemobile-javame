package ru.medv.cubemobile;

import java.util.Timer;
import java.util.TimerTask;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;

/**
 *
 * @author DimOn
 */
public class AppCubeCanvas extends Canvas implements CommandListener
{
    private Command cmExit; // команда Выход
    private AppCubeMIDlet midlet;  // Главный класс мидлета
    private int displayWidth, displayHeight; // Размер экрана
    
    //private boolean initComplete = false; // Был ли экран инициализирован
    
    private Timer tm; // таймер
    private TimerTask tt; // Задача для выполнения
    
    //--
    private PhisicalSys mySys;
    private Cube8 myCube;	
    
    private double valRotX, valRotY, valRotZ;	
    
    private FpsMetter fpsm;
    
    //private SeparateSubTask sp = null;
    
    
    public AppCubeCanvas(AppCubeMIDlet midlet)
    {
        this.midlet = midlet;
        
        cmExit = new Command("Exit", Command.EXIT, 0);
        addCommand(cmExit);
        setCommandListener(this);
        
        displayWidth = getWidth();
        displayHeight = getHeight();
        
        //--
        myCube = new Cube8();
        fpsm = new FpsMetter();
       	Point2 center = new Point2(displayWidth/2, displayHeight/2);
       	double koef = ( Math.min(displayWidth,displayHeight) /2.0 ) *0.5;
       	mySys = new PhisicalSys(center,koef);        
        valRotX=0.0075;
        valRotY=0.0095;
        valRotZ=0.03;
        //sp = new SeparateSubTask();
        //--
        
        tm = new Timer();
        tt = new DrawTask();
        tm.scheduleAtFixedRate(tt, 0, 1);
    }
    
    public void paint(Graphics g)
    {
        g.setColor(0, 0, 0);
        g.fillRect(0, 0, displayWidth, displayHeight);
      
        myCube.draw( g, mySys ); 
        
        g.setColor( 127, 127, 127 );
        g.drawString( fpsm.toString() + " fps", 0+5, /*displayHeight-5*/0, 0 );     
        
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
     
    public void commandAction(Command c, Displayable s)
    {
        if(c == cmExit) midlet.exitMIDlet();
    }  
    
    private class DrawTask extends TimerTask
    {
        public final void run()
        {
            updateDisplay();
            repaint();
        }
    }    
     
    /* 
    private class SeparateSubTask extends Thread
    {
        SeparateSubTask()
        {
            start();
        }

        public void run()
        {
            while (true)
            {
                updateDisplay();
                repaint();   			
            }
        }
    }
    */
}
