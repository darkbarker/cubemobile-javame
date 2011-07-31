package ru.medv.cubemobile;

import javax.microedition.lcdui.Graphics;

/**
 *
 * @author DimOn
 */
public class Cube8
{
	Point3 cube[];
	Cube8Gran gran[];
	
	Cube8()
	{
		cube=new Point3[8];		
		cube[0]=new Point3(+1.0,+1.0,-1.0); //н гр. пв
		cube[1]=new Point3(-1.0,+1.0,-1.0); //н гр. лв
		cube[2]=new Point3(-1.0,-1.0,-1.0); //н гр. лн
		cube[3]=new Point3(+1.0,-1.0,-1.0); //н гр. пн
		cube[4]=new Point3(+1.0,+1.0,+1.0); //в гр. пв
		cube[5]=new Point3(-1.0,+1.0,+1.0); //в гр. лв
		cube[6]=new Point3(-1.0,-1.0,+1.0); //в гр. лн
		cube[7]=new Point3(+1.0,-1.0,+1.0); //в гр. пн
		gran=new Cube8Gran[6];                
		gran[0]=new Cube8Gran(0,1,2,3, Color.BLUE);   //н гр.
		gran[1]=new Cube8Gran(4,5,1,0, Color.CYAN);   //up гр.
		gran[2]=new Cube8Gran(5,6,2,1, Color.GREEN);  //lt гр.
		gran[3]=new Cube8Gran(6,7,3,2, Color.MAGENTA);//dn гр.
		gran[4]=new Cube8Gran(7,4,0,3, Color.RED);    //rt гр.
		gran[5]=new Cube8Gran(7,6,5,4, Color.YELLOW); //в гр.
	}
	
	void draw( Graphics g, PhisicalSys ps )
	{
		for(int i=0;i<6;i++)
			if(isGranVisible(i))
				drawGran(g, ps, i);
	}
	
	void rotX(double g)
	{
		double C = Math.cos(g); 
		double S = Math.sin(g);
		for(int i=0;i<8;i++)
		{
			Point3 np = new Point3(); 
			np.x = cube[i].x;
			np.y = cube[i].y * C - cube[i].z * S;
			np.z = cube[i].y * S + cube[i].z * C;			
			cube[i] = np;
		}
	}

	void rotY(double g)
	{
		double C = Math.cos(g); 
		double S = Math.sin(g);
		for(int i=0;i<8;i++)
		{
			Point3 np = new Point3(); 
			np.x = cube[i].x * C + cube[i].z * S;
			np.y = cube[i].y;
			np.z = -cube[i].x * S + cube[i].z * C;			
			cube[i] = np;
		}
	}

	void rotZ(double g)
	{
		double C = Math.cos(g); 
		double S = Math.sin(g);
		for(int i=0;i<8;i++)
		{
			Point3 np = new Point3(); 
			np.x = cube[i].x * C - cube[i].y * S;
			np.y = cube[i].x * S + cube[i].y * C;
			np.z = cube[i].z;			
			cube[i] = np;
			/*
			Point3 pcopy = new Point3();
			pcopy = cube[i];
			cube[i].x = pcopy.x * C - pcopy.y * S;	
			cube[i].y = pcopy.x * S + pcopy.y * C;
			cube[i].z = pcopy.z;
			*/
		}
	}

	void drawGran(Graphics g, PhisicalSys ps, int gri)
	{	
		Cube8Gran cg = gran[gri];
		
		g.setColor( Color.mul( cg.color, getCosPhiGran(gri) ).getRGB() );
		g.fillTriangle(
				ps.fromPoint3( cube[ cg.indexVert[0] ] ).x,
				ps.fromPoint3( cube[ cg.indexVert[0] ] ).y,
				ps.fromPoint3( cube[ cg.indexVert[1] ] ).x,
				ps.fromPoint3( cube[ cg.indexVert[1] ] ).y,
                ps.fromPoint3( cube[ cg.indexVert[2] ] ).x,
                ps.fromPoint3( cube[ cg.indexVert[2] ] ).y
		);
		g.fillTriangle(
				ps.fromPoint3( cube[ cg.indexVert[2] ] ).x,
				ps.fromPoint3( cube[ cg.indexVert[2] ] ).y,
				ps.fromPoint3( cube[ cg.indexVert[3] ] ).x,
				ps.fromPoint3( cube[ cg.indexVert[3] ] ).y,
				ps.fromPoint3( cube[ cg.indexVert[0] ] ).x,
				ps.fromPoint3( cube[ cg.indexVert[0] ] ).y
		);
	}
	
	private Point3 getNormalGran(int gri)
	{
		Cube8Gran cg = gran[gri];
		
		double X1=cube[cg.indexVert[0]].x;
		double Y1=cube[cg.indexVert[0]].y;
		double Z1=cube[cg.indexVert[0]].z;
		double X2=cube[cg.indexVert[1]].x;
		double Y2=cube[cg.indexVert[1]].y;
		double Z2=cube[cg.indexVert[1]].z;
		double X3=cube[cg.indexVert[2]].x;
		double Y3=cube[cg.indexVert[2]].y;
		double Z3=cube[cg.indexVert[2]].z;		
		
		double xv = (Y1-Y2)*(Z1-Z3) - (Z1-Z2)*(Y1-Y3);
		double yv = (Z1-Z2)*(X1-X3) - (X1-X2)*(Z1-Z3);
		double zv = (X1-X2)*(Y1-Y3) - (Y1-Y2)*(X1-X3);
				
		return new Point3( xv, yv, zv );
	}
	
	/** Косинус (нормальграни^направлениезрения)
	 * @param gri - индекс грани
	 * @return косинус угла между нормалью к грани и вектором на наш глаз 
	 */
	double getCosPhiGran(int gri)
	{
		Point3 p3 = getNormalGran(gri);	
		Point3 pVis = new Point3(0,0,1);
		return p3.getScalar(pVis) / p3.getVectorLenght() / pVis.getVectorLenght();
	}
	
	boolean isGranVisible( int gri )
	{		
		return ( getCosPhiGran(gri) > 0 );		
	}
}


/** Грань куба - индексы вершин и цвет грани. */
class Cube8Gran
{
	int indexVert[];
	Color color;
	public Cube8Gran( int v1, int v2, int v3, int v4, Color c)
	{
		indexVert = new int[4];
		indexVert[0] = v1;
		indexVert[1] = v2;
		indexVert[2] = v3;
		indexVert[3] = v4;
		color = c;
	}	
}
