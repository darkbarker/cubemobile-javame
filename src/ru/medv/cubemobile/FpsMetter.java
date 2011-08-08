package ru.medv.cubemobile;

/**
 * @author DimOn
 */
public class FpsMetter
{
	private long pred;
	private long curit;
	long lFps;

	FpsMetter()
	{
		reset();
	}

	void reset()
	{
		pred = System.currentTimeMillis();
		curit = 0L;
		lFps = 0L;
	}

	void proceedevent()
	{
		long cur = System.currentTimeMillis();
		++curit;
		lFps = curit * 1000L / (cur - pred);
	}

	public String toString()
	{
		return String.valueOf(lFps);
	}
}
