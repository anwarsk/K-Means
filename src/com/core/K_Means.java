package com.core;

import java.util.ArrayList;
import java.util.List;

import com.data.Centroid;
import com.data.DataPoint;

public class K_Means {
	
	private List<Centroid> intializeCentroids(int centroidCount, List<DataPoint> dataPoints)
	{
		List<Centroid> centriods = new ArrayList<Centroid>();
		
		for(int i = 0; i < centroidCount; i++)
		{
			centriods.add(new Centroid(i+1, dataPoints.get(i).values));
		}
		
		return centriods;
	}
	
	public List<Centroid> k_Means_Clustering(List<DataPoint> dataPoints, int centroidCount, double threshold)
	{
		List<Centroid> centroids = this.intializeCentroids(centroidCount, dataPoints);
		List<Centroid> newCentroids = new ArrayList<Centroid>();
		double systemDisplacement = 0;
		double newSystemDisplacement  = 0;
		
		do
		{
			
			newCentroids.clear();
			newSystemDisplacement = 0;
			
			for(Centroid centroid : centroids)
			{
				centroid.associatedDataPointList.clear();
			}
			
			for(DataPoint dataPoint : dataPoints)
			{
				double minDistance = Double.MAX_VALUE;
				Centroid minDistanceCentroid = null;

				for(Centroid centroid : centroids)
				{
					double distance = DataPoint.getDistance(dataPoint, centroid);
					if(distance < minDistance)
					{
						minDistanceCentroid = centroid;
					    minDistance = distance;
					}		
				}

				minDistanceCentroid.asoociateDataPoint(dataPoint);
			}
			
			for(Centroid centroid: centroids)
			{
				Centroid newCentroid = centroid.getAvegrageCentroid();
				newCentroids.add(newCentroid);
				newSystemDisplacement = newSystemDisplacement + Centroid.getDistance(centroid, newCentroid);
			}
			
			centroids.clear();
			centroids.addAll(newCentroids);
		}
		while(Math.abs(newSystemDisplacement - systemDisplacement) > threshold);
		
		return centroids;	
	}

}
