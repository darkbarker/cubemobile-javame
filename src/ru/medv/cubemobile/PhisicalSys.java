package ru.medv.cubemobile;

/**
 *
 * @author DimOn
 */
public class PhisicalSys
{    
    Point center;    
    double koef;
    
    public PhisicalSys( Point c, double k )
    {
        center = c;
        koef = k;
    }	
    
    Point fromPoint3(Point3 p3)
    {	
        double xpr = p3.x;
        double ypr = -p3.y;		
		
        Point p = new Point();
        p.x = (int) (xpr * koef + center.x);
        p.y = (int) (ypr * koef + center.y);
		
        return p;		
    }

    /**
     * @param p точка на физическом холсте
     * @return двух(трех)мерная точка (xp,yp,0) - проекция на логику 
     */
    Point3 modifToLogic(Point p)
    {
        return new Point3( (p.x - center.x)/koef, (p.y - center.y)/koef, 0);
    }
}
