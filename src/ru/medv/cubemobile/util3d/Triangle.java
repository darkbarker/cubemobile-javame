package ru.medv.cubemobile.util3d;

/**
 * Side of the triangle - the vertex indices and the color of the face.
 * Face of the cube - two triangles.
 * @author darkbarker <barkdarker@gmail.com> 
 */
class Triangle
{
	int v1;
	int v2;
	int v3;
	Color color;

	public Triangle( int v1, int v2, int v3, Color c)
	{
		this.v1 = v1;
		this.v2 = v2;
		this.v3 = v3;
		this.color = c;
	}	
}