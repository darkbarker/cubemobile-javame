package ru.medv.cubemobile;

/**
 * Measurement fps based on the total number of ticks since the last reset.
 * For some reason, not very well considered...
 * @author darkbarker <barkdarker@gmail.com>
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
