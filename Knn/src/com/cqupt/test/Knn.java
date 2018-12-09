package com.cqupt.test;

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
		List<Point> pointList=dataSetUtil.getDataSet();
		Point testPoint = new Point(7,1.2,1.2);//测试数据点
		DisComparator disComparator = new DisComparator();//比较器
		Set<Distance> distanceSet = new TreeSet<Distance>(disComparator);
		CommentUtil util = new CommentUtil();
		for(Point p:pointList){
			//计算测试数据距离所有训练数据点的距离，存入Distance
			distanceSet.add(new Distance(p.getId(),testPoint.getId()
					,util.getOuDistance(p, testPoint)));
		}
		int k=3;
		List<Distance> distanceList = new ArrayList<Distance>(distanceSet);
		Map<String,Integer> map=util.getNumOfType(distanceList, pointList, k);
		System.out.println("将点("+testPoint.getX()+","+testPoint.getY()+")预测为"+util.getMaxType(map));
	}

}
