package ru.medv.cubemobile.util3d;

/**
 * Three-dimensional fractional point.
 * @author darkbarker <barkdarker@gmail.com>
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
     * Length of the vector.
     * @return fractional number.
     */
    public double getVectorLenght()
    {
        return Math.sqrt( x*x + y*y + z*z );
    }

    /**
     * Scalar product on another point.
     * @param p3 another point
     * @return the scalar product.
     */
    public double getScalar( Point3 p3 )
    {
        return p3.x * x + p3.y * y + p3.z * z;
    }
}
