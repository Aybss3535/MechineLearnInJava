package com.cqupt.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.cqupt.entity.Distance;
import com.cqupt.entity.Point;
import com.cqupt.util.CommentUtil;
import com.cqupt.util.DataSetUtil;
import com.cqupt.util.DisComparator;

public class Knn {

	public static void main(String[] args) {
		DataSetUtil dataSetUtil = new DataSetUtil();
		List<Point> trainPoints=null;
		List<Point> testPoints=null;
		try {
			trainPoints = dataSetUtil.getTrainIris("D:\\data\\irisTrain.txt");
			testPoints = dataSetUtil.getTestIris("D:\\data\\irisTest.txt");//�������ݵ�
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CommentUtil util = new CommentUtil();
		double error=0.0;
		for(Point testPoint:testPoints){
			DisComparator disComparator = new DisComparator();//�Ƚ���
			Set<Distance> distanceSet = new TreeSet<Distance>(disComparator);
			for(Point trainPoint:trainPoints){
				//����������ݾ�������ѵ�����ݵ�ľ��룬����Distance
				distanceSet.add(new Distance(trainPoint.getId(),testPoint.getId()
					,util.getOuDistance(trainPoint, testPoint)));
			}
			int k=3;
			List<Distance> distanceList = new ArrayList<Distance>(distanceSet);
			Map<String,Integer> map=util.getNumOfType(distanceList, trainPoints, k);
			System.out.println("����("+testPoint.getX()+","+testPoint.getY()+","+
					testPoint.getZ()+","+testPoint.getK()+")Ԥ��Ϊ"+util.getMaxType(map)+"��ʵ��Ϊ��"+testPoint.getType());
			if(!testPoint.getType().equals(util.getMaxType(map))){
				error++;
			}
			
		}
		System.out.println("������Ϊ��"+error/testPoints.size());
		
	}

}
