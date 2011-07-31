package ru.medv.cubemobile;

/**
 * Физическая среда, в которой обитает наш объект. Представляет собой информацию
 * обобщённую о том, где цент экрана и какого размера на нём единичный кубикъ.
 * @author dimon
 */
public class PhisicalSys
{    
    private Point2 center;    
    private double koefx;
    private double koefy;
    
    public PhisicalSys( Point2 center, double koefx, double koefy )
    {
        this.center = center;
        this.koefx = koefx;
        this.koefy = koefy;
    }	
    
    public Point2 fromPoint3( Point3 p3 )
    {	
        Point2 p = new Point2();

        p.x = (int) (p3.x * koefx + center.x);
        p.y = (int) (p3.y * koefy + center.y);
		
        return p;		
    }

//    /**
//     * @param p точка на физическом холсте
//     * @return двух(трех)мерная точка (xp,yp,0) - проекция на логику 
//     */
//    Point3 modifToLogic(Point2 p)
//    {
//        return new Point3( (p.x - center.x)/koef, (p.y - center.y)/koef, 0);
//    }
}
