package ru.medv.cubemobile;

/**
 *
 * @author DimOn
 */

public class Point3
{
    double x;    
    double y;
    double z;
    
    public Point3(){}

    public Point3( double x, double y, double z )
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    double getVectorLenght()
    {
        return Math.sqrt( x*x + y*y + z*z );
    }
  
    double getScalar(Point3 p3)
    {
        return p3.x * x + p3.y * y + p3.z * z;
    }
}
