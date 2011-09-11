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

	synchronized void reset()
	{
		inittime = System.currentTimeMillis();
		curcount = 0L;
		lFps = 0L;
	}

	synchronized long getFps()
	{
		return lFps;
	}

	synchronized void proceedevent()
	{
		long cur = System.currentTimeMillis();
        if (inittime == cur)
        {
            // in case of divide-by-zero
        	inittime = cur - 1;
        }
		++curcount;
		lFps = curcount * 1000L / (cur - inittime);
	}
}
