package ru.medv.cubemobile.util3d;

import javax.microedition.lcdui.Graphics;


/**
 *
 * @author DimOn
 */
public class Cube8
{
	private Point3 points[];
	private Triangle triangles[];
	
	public Cube8()
	{
		points = new Point3[8];		
		points[0]=new Point3(+1.0,+1.0,-1.0); //н гр. пв
		points[1]=new Point3(-1.0,+1.0,-1.0); //н гр. лв
		points[2]=new Point3(-1.0,-1.0,-1.0); //н гр. лн
		points[3]=new Point3(+1.0,-1.0,-1.0); //н гр. пн
		points[4]=new Point3(+1.0,+1.0,+1.0); //в гр. пв
		points[5]=new Point3(-1.0,+1.0,+1.0); //в гр. лв
		points[6]=new Point3(-1.0,-1.0,+1.0); //в гр. лн
		points[7]=new Point3(+1.0,-1.0,+1.0); //в гр. пн
		triangles = new Triangle[12];                
		triangles[0]=new Triangle(0,1,2, Color.BLUE);   //н гр.
		triangles[1]=new Triangle(2,3,0, Color.BLUE);   //н гр.
		triangles[2]=new Triangle(4,5,1, Color.CYAN);   //up гр.
		triangles[3]=new Triangle(1,0,4, Color.CYAN);   //up гр.
		triangles[4]=new Triangle(5,6,2, Color.GREEN);  //lt гр.
		triangles[5]=new Triangle(2,1,5, Color.GREEN);  //lt гр.
		triangles[6]=new Triangle(6,7,3, Color.MAGENTA);//dn гр.
		triangles[7]=new Triangle(3,2,6, Color.MAGENTA);//dn гр.
		triangles[8]=new Triangle(7,4,0, Color.RED);    //rt гр.
		triangles[9]=new Triangle(0,3,7, Color.RED);    //rt гр.
		triangles[10]=new Triangle(7,6,5, Color.YELLOW); //в гр.
		triangles[11]=new Triangle(5,4,7, Color.YELLOW); //в гр.
	}
	
	public void draw( Graphics g, PhisicalSys ps )
	{
		for( int i=0; i<triangles.length; i++ )
			drawGran(g, ps, i);
	}
	
	public void rotX( double g )
	{
		double C = Math.cos(g); 
		double S = Math.sin(g);
		for( int i=0; i<points.length; i++ )
		{
			Point3 np = new Point3(); 
			np.x = points[i].x;
			np.y = points[i].y * C - points[i].z * S;
			np.z = points[i].y * S + points[i].z * C;			
			points[i] = np;
		}
	}

	public void rotY( double g )
	{
		double C = Math.cos(g); 
		double S = Math.sin(g);
		for( int i=0; i<points.length; i++ )
		{
			Point3 np = new Point3(); 
			np.x = points[i].x * C + points[i].z * S;
			np.y = points[i].y;
			np.z = -points[i].x * S + points[i].z * C;			
			points[i] = np;
		}
	}

	public void rotZ( double g )
	{
		double C = Math.cos(g); 
		double S = Math.sin(g);
		for( int i=0; i<points.length; i++ )
		{
			Point3 np = new Point3(); 
			np.x = points[i].x * C - points[i].y * S;
			np.y = points[i].x * S + points[i].y * C;
			np.z = points[i].z;			
			points[i] = np;
			/*
			Point3 pcopy = new Point3();
			pcopy = cube[i];
			cube[i].x = pcopy.x * C - pcopy.y * S;	
			cube[i].y = pcopy.x * S + pcopy.y * C;
			cube[i].z = pcopy.z;
			*/
		}
	}

	void drawGran( Graphics g, PhisicalSys ps, int gri )
	{	
		double cos = getCosPhiGran(gri);
		if( cos <= 0 ) return;
		
		Triangle cg = triangles[gri];
		
		g.setColor( Color.mul( cg.color, cos ).getRGB() );
		
		Point2 p0 = ps.fromPoint3( points[ cg.v1 ] );
		Point2 p1 = ps.fromPoint3( points[ cg.v2 ] );
		Point2 p2 = ps.fromPoint3( points[ cg.v3 ] );
		
		g.fillTriangle(
				p0.x,
				p0.y,
				p1.x,
				p1.y,
				p2.x,
				p2.y
		);
	}
	
	private Point3 getNormalGran(int gri)
	{
		Triangle cg = triangles[gri];
		
		double X1=points[cg.v1].x;
		double Y1=points[cg.v1].y;
		double Z1=points[cg.v1].z;
		double X2=points[cg.v2].x;
		double Y2=points[cg.v2].y;
		double Z2=points[cg.v2].z;
		double X3=points[cg.v3].x;
		double Y3=points[cg.v3].y;
		double Z3=points[cg.v3].z;		
		
		double xv = (Y1-Y2)*(Z1-Z3) - (Z1-Z2)*(Y1-Y3);
		double yv = (Z1-Z2)*(X1-X3) - (X1-X2)*(Z1-Z3);
		double zv = (X1-X2)*(Y1-Y3) - (Y1-Y2)*(X1-X3);
				
		return new Point3( xv, yv, zv );
	}
	
	/** Косинус (нормальграни^направлениезрения)
	 * @param gri - индекс грани
	 * @return косинус угла между нормалью к грани и вектором на наш глаз 
	 */
	double getCosPhiGran( int gri )
	{
		Point3 p3 = getNormalGran( gri );	
		Point3 pVis = new Point3(0,0,1);
		return p3.getScalar(pVis) / p3.getVectorLenght() / pVis.getVectorLenght();
	}
}
