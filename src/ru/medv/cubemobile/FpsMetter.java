package ru.medv.cubemobile;

/**
 * @author DimOn
 */
public class FpsMetter
{
	private long inittime;
	private long curcount;
	private long lFps;

	FpsMetter()
	{
		reset();
	}

	void reset()
	{
		inittime = System.currentTimeMillis();
		curcount = 0L;
		lFps = 0L;
	}

	void proceedevent()
	{
		long cur = System.currentTimeMillis();
		++curcount;
		lFps = curcount * 1000L / (cur - inittime);
	}

	public String toString()
	{
		return String.valueOf(lFps);
	}
}
