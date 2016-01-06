package com.data;

import java.util.ArrayList;
import java.util.List;

public class Centroid {

	public int id;
	public int[] values;
	public List<DataPoint> associatedDataPointList;
	
	public Centroid(int id, int[] values)
	{
		this.id = id;
		this.values = values;
		
		this.associatedDataPointList = new ArrayList<DataPoint>();			
	}
	
	public void asoociateDataPoint(DataPoint dataPoint)
	{
		associatedDataPointList.add(dataPoint);
	}
	
	public Centroid getAvegrageCentroid()
	{
		
		int[]values = new int[this.values.length];

		for(int i = 0; i < this.values.length; i++)
		{
			for(DataPoint dataPoint : associatedDataPointList)
			{
				values[i] = values[i] + dataPoint.values[i];
			}
			
			values[i] = values[i]/associatedDataPointList.size();
		}
		
		Centroid avgCentroid = new Centroid(this.id, values);
		avgCentroid.associatedDataPointList.addAll(this.associatedDataPointList);
		
		return avgCentroid;
	}
	
	public static double getDistance(Centroid centroid1, Centroid centriod2)
	{
		double distance = 0;
		
		for(int i = 0; i < centroid1.values.length; i++)
		{
			distance = distance + Math.pow(centroid1.values[i] - centriod2.values[i], 2);
		}
		
		distance = Math.sqrt(distance);
		
		return distance;
	}
	
	
}
