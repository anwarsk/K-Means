package com.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.data.Centroid;
import com.data.Constants;
import com.data.DataPoint;

public class FileHandler {
	
	public static List<DataPoint> readDataPoints(String filePath) throws IOException
	{
		List<DataPoint> dataPoints = new ArrayList<DataPoint>();

		BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));

		String line = "";
		while((line = bufferedReader.readLine()) != null)
		{

			String[] tokens = line.split(Constants.delimiter);

			try
			{

				int id = Integer.parseInt(tokens[0]);
				int label = Integer.parseInt(tokens[tokens.length-1]);
				int[] values = new int[tokens.length-2];

				// First and last columns are id and label respectively
				for(int i = 1; i < tokens.length-1; i++)
				{
					values[i-1] = Integer.parseInt(tokens[i]);
				}
				
				dataPoints.add(new DataPoint(id, values, label));
				
			}
			catch(NumberFormatException e)
			{
				//e.printStackTrace();
				System.out.println("Ignoring Row- " + tokens);
			}
		}
		
		bufferedReader.close();
		
		return dataPoints;
	}
	
	public static void writeDataPoints(String filePath, List<Centroid> centroids) throws IOException
	{
		FileWriter fileWriter = new FileWriter(filePath);
		
		/*//TEST: Adding headers to data
		for(int i = 0 ; i< 10; i++){ fileWriter.append("A,");};
		fileWriter.append("X,Y\n");*/
		
		for(Centroid centroid : centroids)
		{
			for(DataPoint dataPoint: centroid.associatedDataPointList)
			{
				fileWriter.append(String.valueOf(dataPoint.id));
				
				for(int value: dataPoint.values)
				{
					fileWriter.append(Constants.delimiter);
					fileWriter.append(String.valueOf(value));
				}
				
				fileWriter.append(Constants.delimiter);
				fileWriter.append(String.valueOf(dataPoint.label));
				
				fileWriter.append(Constants.delimiter);
				fileWriter.append(String.valueOf(centroid.id));
				
				fileWriter.append(Constants.newLineSeparator);
			}
		}
		
		fileWriter.close();
	}
	
	

}
