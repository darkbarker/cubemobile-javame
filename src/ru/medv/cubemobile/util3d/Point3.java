package ru.medv.cubemobile.util3d;

/**
 * Трёхмерная дробная точка.
 * @author dimon
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

    /**
     * Длина вектора.
     * @return дробное число.
     */
    public double getVectorLenght()
    {
        return Math.sqrt( x*x + y*y + z*z );
    }

    /**
     * Скалярное произведение на другую точку.
     * @param p3 другая точка
     * @return скалярное произведение.
     */
    public double getScalar( Point3 p3 )
    {
        return p3.x * x + p3.y * y + p3.z * z;
    }
}
