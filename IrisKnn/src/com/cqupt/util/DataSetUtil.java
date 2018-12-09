package com.cqupt.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
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
	/**
	 * 获取鸢尾花数的训练集
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public List<Point> getTrainIris(String filePath) throws IOException{
		long id=1;
		BufferedReader reader=null;
		List<Point>  pointList = new ArrayList<>();
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
			String line;
			while((line=reader.readLine())!=null){
				String[] data = line.trim().split(" ");
				Point p = new Point();
				pointList.add(p);
				p.setId(id);
				id++;
				p.setX(Double.parseDouble(data[0]));
				p.setY(Double.parseDouble(data[1]));
				p.setZ(Double.parseDouble(data[2]));
				p.setK(Double.parseDouble(data[3]));
				p.setType(data[4]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			reader.close();
		}
		return pointList;
	}
	
	/**
	 * 获取鸢尾花数的测试集
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public List<Point> getTestIris(String filePath) throws IOException{
		long id=113;
		BufferedReader reader=null;
		List<Point>  pointList = new ArrayList<>();
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
			String line;
			while((line=reader.readLine())!=null){
				String[] data = line.trim().split(" ");
				Point p = new Point();
				pointList.add(p);
				p.setId(id);
				id++;
				p.setX(Double.parseDouble(data[0]));
				p.setY(Double.parseDouble(data[1]));
				p.setZ(Double.parseDouble(data[2]));
				p.setK(Double.parseDouble(data[3]));
				p.setType(data[4]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			reader.close();
		}
		return pointList;
	}
	
	
}
