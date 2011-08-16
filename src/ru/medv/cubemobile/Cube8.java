package ru.medv.cubemobile;

import javax.microedition.lcdui.Graphics;

/**
 *
 * @author DimOn
 */
public class Cube8
{
	private Point3 points[];
	private Cube8Gran grans[];
	
	Cube8()
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
		grans = new Cube8Gran[6];                
		grans[0]=new Cube8Gran(0,1,2,3, Color.BLUE);   //н гр.
		grans[1]=new Cube8Gran(4,5,1,0, Color.CYAN);   //up гр.
		grans[2]=new Cube8Gran(5,6,2,1, Color.GREEN);  //lt гр.
		grans[3]=new Cube8Gran(6,7,3,2, Color.MAGENTA);//dn гр.
		grans[4]=new Cube8Gran(7,4,0,3, Color.RED);    //rt гр.
		grans[5]=new Cube8Gran(7,6,5,4, Color.YELLOW); //в гр.
	}
	
	void draw( Graphics g, PhisicalSys ps )
	{
		for( int i=0; i<6; i++ )
			drawGran(g, ps, i);
	}
	
	void rotX( double g )
	{
		double C = Math.cos(g); 
		double S = Math.sin(g);
		for( int i=0; i<8; i++ )
		{
			Point3 np = new Point3(); 
			np.x = points[i].x;
			np.y = points[i].y * C - points[i].z * S;
			np.z = points[i].y * S + points[i].z * C;			
			points[i] = np;
		}
	}

	void rotY( double g )
	{
		double C = Math.cos(g); 
		double S = Math.sin(g);
		for( int i=0; i<8; i++ )
		{
			Point3 np = new Point3(); 
			np.x = points[i].x * C + points[i].z * S;
			np.y = points[i].y;
			np.z = -points[i].x * S + points[i].z * C;			
			points[i] = np;
		}
	}

	void rotZ( double g )
	{
		double C = Math.cos(g); 
		double S = Math.sin(g);
		for( int i=0; i<8; i++ )
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
		
		Cube8Gran cg = grans[gri];
		
		g.setColor( Color.mul( cg.color, cos ).getRGB() );
		g.fillTriangle(
				ps.fromPoint3( points[ cg.indexVert[0] ] ).x,
				ps.fromPoint3( points[ cg.indexVert[0] ] ).y,
				ps.fromPoint3( points[ cg.indexVert[1] ] ).x,
				ps.fromPoint3( points[ cg.indexVert[1] ] ).y,
                ps.fromPoint3( points[ cg.indexVert[2] ] ).x,
                ps.fromPoint3( points[ cg.indexVert[2] ] ).y
		);
		g.fillTriangle(
				ps.fromPoint3( points[ cg.indexVert[2] ] ).x,
				ps.fromPoint3( points[ cg.indexVert[2] ] ).y,
				ps.fromPoint3( points[ cg.indexVert[3] ] ).x,
				ps.fromPoint3( points[ cg.indexVert[3] ] ).y,
				ps.fromPoint3( points[ cg.indexVert[0] ] ).x,
				ps.fromPoint3( points[ cg.indexVert[0] ] ).y
		);
	}
	
	private Point3 getNormalGran(int gri)
	{
		Cube8Gran cg = grans[gri];
		
		double X1=points[cg.indexVert[0]].x;
		double Y1=points[cg.indexVert[0]].y;
		double Z1=points[cg.indexVert[0]].z;
		double X2=points[cg.indexVert[1]].x;
		double Y2=points[cg.indexVert[1]].y;
		double Z2=points[cg.indexVert[1]].z;
		double X3=points[cg.indexVert[2]].x;
		double Y3=points[cg.indexVert[2]].y;
		double Z3=points[cg.indexVert[2]].z;		
		
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


/** Грань куба - индексы вершин и цвет грани. */
class Cube8Gran
{
	int indexVert[];
	Color color;

	public Cube8Gran( int v1, int v2, int v3, int v4, Color c)
	{
		this.indexVert = new int[4];
		this.indexVert[0] = v1;
		this.indexVert[1] = v2;
		this.indexVert[2] = v3;
		this.indexVert[3] = v4;
		this.color = c;
	}	
}
