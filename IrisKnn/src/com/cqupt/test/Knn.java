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
			testPoints = dataSetUtil.getTestIris("D:\\data\\irisTest.txt");//测试数据点
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CommentUtil util = new CommentUtil();
		double error=0.0;
		for(Point testPoint:testPoints){
			DisComparator disComparator = new DisComparator();//比较器
			Set<Distance> distanceSet = new TreeSet<Distance>(disComparator);
			for(Point trainPoint:trainPoints){
				//计算测试数据距离所有训练数据点的距离，存入Distance
				distanceSet.add(new Distance(trainPoint.getId(),testPoint.getId()
					,util.getOuDistance(trainPoint, testPoint)));
			}
			int k=3;
			List<Distance> distanceList = new ArrayList<Distance>(distanceSet);
			Map<String,Integer> map=util.getNumOfType(distanceList, trainPoints, k);
			System.out.println("将点("+testPoint.getX()+","+testPoint.getY()+","+
					testPoint.getZ()+","+testPoint.getK()+")预测为"+util.getMaxType(map)+"，实际为："+testPoint.getType());
			if(!testPoint.getType().equals(util.getMaxType(map))){
				error++;
			}
			
		}
		System.out.println("错误率为："+error/testPoints.size());
		
	}

}
