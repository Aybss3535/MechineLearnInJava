package com.cqupt.util;

import java.util.ArrayList;
import java.util.List;

import com.cqupt.entity.Point;

/**
 * 创建数据集类
 *<p>title:DataSetUtil</p>
 *<p>Decription:</p>
 * @author songxuan
 * @date  下午12:00:51
 */
public class DataSetUtil {
	public List<Point> getDataSet(){
		List<Point> pointList = new ArrayList<Point>();
		Point point1 = new Point(1, 1.0, 1.1, "A");
        Point point2 = new Point(2, 1.0, 1.0, "A");
        Point point3 = new Point(3, 1.0, 1.2, "A");
        Point point4 = new Point(4, 0, 0, "B");
        Point point5 = new Point(5, 0, 0.1, "B");
        Point point6 = new Point(6, 0, 0.2, "B");
        pointList.add(point1);
        pointList.add(point2);
        pointList.add(point3);
        pointList.add(point4);
        pointList.add(point5);
        pointList.add(point6);
        return pointList;
	}
}
