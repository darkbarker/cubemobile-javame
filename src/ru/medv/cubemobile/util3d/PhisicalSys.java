package ru.medv.cubemobile.util3d;

/**
 * Physical environment in which we live our object. It is a summarized information
 * about where the center of the screen and the size of the unit cube on it.
 * Физическая среда, в которой обитает наш объект. Представляет собой информацию
 * обобщённую о том, где цент экрана и какого размера на нём единичный кубикъ.
 * @author darkbarker <barkdarker@gmail.com>
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

    /**
     * @param p a point on the physical canvas.
     * @return two-(three-)dimensional point (xp,yp,0) - the projection on the logical environment
     */
	public Point3 modifToLogic(Point2 p)
    {
        return new Point3( (p.x - center.x)/koefx, (p.y - center.y)/koefy, 0);
    }
}
