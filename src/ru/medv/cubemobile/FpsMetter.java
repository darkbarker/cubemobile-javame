package ru.medv.cubemobile;

/**
 * 
 * @author DimOn
 */
public class FpsMetter
{
	private long pred;
	private long curit;
	double dFps;

	FpsMetter()
	{
		reset();
	}

	void reset()
	{
		pred = System.currentTimeMillis();
		curit = 0;
		dFps = 0.0;
	}

	void proceedevent()
	{
		long cur = System.currentTimeMillis();
		++curit;
		dFps = curit * 1000.0 / (cur - pred);
		// pred = cur;
	}

	public String toString()
	{
		return String.valueOf((int) dFps);
	}
}
