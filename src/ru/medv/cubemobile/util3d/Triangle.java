package ru.medv.cubemobile.util3d;

/**
 * грань треугольник - индексы вершин и цвет грани.
 * квадрат куба - два треугольника
 * @author dimon 
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