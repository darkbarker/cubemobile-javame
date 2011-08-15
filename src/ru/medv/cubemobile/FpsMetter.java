package ru.medv.cubemobile;

/**
 * Мерка фпс на основе общего кол-ва тиков от начала времён (со сброса).
 * Почему-то не шибко прилично считает...
 * @author dimon
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
