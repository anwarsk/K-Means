package com.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.data.Centroid;
import com.data.Constants;
import com.data.DataPoint;
import com.file.FileHandler;

public class Operation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<DataPoint> dataPoints;
		try 
		{
			dataPoints = FileHandler.readDataPoints(Constants.sourceFilePath);
		
		K_Means k_means = new K_Means();
		List<Centroid> centriods = k_means.k_Means_Clustering(dataPoints, Constants.centriodCount, Constants.threshold);
		
		FileHandler.writeDataPoints(Constants.destinationFilePath, centriods);
		
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/*
	 * DIFFERENT MAIN FOR V-FOLD VALIDATION
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<DataPoint> dataPoints;
		try 
		{
			dataPoints = FileHandler.readDataPoints(Constants.sourceFilePath);
		
		K_Means k_means = new K_Means();
		int testDataLength = dataPoints.size()/10;
		for(int i = 0; i< 10; i++)
		{
			int startPointTestData = testDataLength * i;
			int endPointTestData = testDataLength * (i+1);
			List<DataPoint> testDataPoints = dataPoints.subList(startPointTestData, endPointTestData);

			List<DataPoint> trainingDataPoints = new ArrayList<DataPoint>();
			trainingDataPoints.addAll(dataPoints.subList(0, startPointTestData));
			trainingDataPoints.addAll(dataPoints.subList(endPointTestData, dataPoints.size()));

			List<Centroid> centroids = k_means.k_Means_Clustering(trainingDataPoints, Constants.centriodCount, Constants.threshold);

			for(Centroid centroid: centroids)
			{
				centroid.associatedDataPointList.clear();
			}

			for(DataPoint testDataPoint: testDataPoints)
			{
				double minCentroidDistance = Integer.MAX_VALUE;
				Centroid minDistanceCentroid = null;

				for(Centroid centroid: centroids)
				{
					double distance = DataPoint.getDistance(testDataPoint, centroid);
					if(distance < minCentroidDistance)
					{
						minCentroidDistance = distance;
						minDistanceCentroid = centroid;
					}
				}

				minDistanceCentroid.asoociateDataPoint(testDataPoint);
			}

			FileHandler.writeDataPoints(Constants.destinationFilePath + i + ".csv", centroids);
		}

		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	*/

}
