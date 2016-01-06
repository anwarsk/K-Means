package com.data;

public class DataPoint {

	public int id;
	public int[] values;
	public int label;
	
	public DataPoint(int id, int[]values, int label)
	{
		this.id = id;
		this.values = values;
		this.label = label;
	}
	
	public static double getDistance(DataPoint dataPoint1, DataPoint dataPoint2)
	{
		double distance = 0;
		
		for(int i = 0; i < dataPoint1.values.length; i++)
		{
			distance = distance + Math.pow(dataPoint1.values[i] - dataPoint2.values[i], 2);
		}
		
		distance = Math.sqrt(distance);
		
		return distance;
	}
	
	public static double getDistance(DataPoint dataPoint, Centroid centroid)
	{
		double distance = 0;
		
		for(int i = 0; i < dataPoint.values.length; i++)
		{
			distance = distance + Math.pow(dataPoint.values[i] - centroid.values[i], 2);
		}
		
		distance = Math.sqrt(distance);
		
		return distance;
	}
}
