package ru.medv.cubemobile;

/**
 * @author DimOn
 */
public class FpsMetter2
{
	private final static int NUMBER = 10;
	private final static int COUNT = 1000;
	private long[] times = new long[NUMBER];
	private int curArrTimesIdx = 0;
	private int curArrTimesCount = 0;
	private long pred;
	private long lFps;

	public FpsMetter2()
	{
		reset();
	}

	public void reset()
	{
		pred = System.currentTimeMillis();
		curArrTimesIdx = -1;
		curArrTimesCount = -1;
		lFps = -1;
	}

	public void proceedevent()
	{
		if( (curArrTimesCount = ++curArrTimesCount % COUNT) != 0 ) return;
		long cur = System.currentTimeMillis();
		curArrTimesIdx = ++curArrTimesIdx % NUMBER;
		times[curArrTimesIdx] = cur - pred;
		pred = cur;
		lFps = -1;
	}

	public String toString()
	{
		if( lFps == -1 )
		{
			long numbersSum = 0;
			long lastNoZeroVal = COUNT * NUMBER * 1000L;
			for( int i = 0; i < NUMBER; i++ )
			{
				long val = times[i];
				numbersSum += val > 0 ? val : lastNoZeroVal;
				if( val > 0) lastNoZeroVal = val;
			}
			lFps = COUNT * NUMBER * 1000L / numbersSum;
		}
		return String.valueOf( lFps );
	}
}
