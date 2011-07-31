package ru.medv.cubemobile;

/**
 *
 * @author DimOn
 */
public class PhisicalSys
{    
    private Point2 center;    
    private double koef;
    
    public PhisicalSys( Point2 center, double koef )
    {
        this.center = center;
        this.koef = koef;
    }	
    
    Point2 fromPoint3(Point3 p3)
    {	
        double xpr = p3.x;
        double ypr = -p3.y;		
		
        Point2 p = new Point2();
        p.x = (int) (xpr * koef + center.x);
        p.y = (int) (ypr * koef + center.y);
		
        return p;		
    }

    /**
     * @param p точка на физическом холсте
     * @return двух(трех)мерная точка (xp,yp,0) - проекция на логику 
     */
    Point3 modifToLogic(Point2 p)
    {
        return new Point3( (p.x - center.x)/koef, (p.y - center.y)/koef, 0);
    }
}
