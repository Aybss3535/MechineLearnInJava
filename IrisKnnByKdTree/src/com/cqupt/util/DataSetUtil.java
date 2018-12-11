package com.cqupt.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.cqupt.entity.Node;

/**
 * 创建数据集类
 *<p>title:DataSetUtil</p>
 *<p>Decription:</p>
 * @author songxuan
 * @date  下午12:00:51
 */
public class DataSetUtil {
	/**
	 * 获取鸢尾花数的数据集
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public List<Node> getIris(String filePath) throws IOException{
		BufferedReader reader=null;
		List<Node>  pointList = new ArrayList<>();
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
			String line;
			while((line=reader.readLine())!=null){
				String[] data = line.trim().split(" ");
				double[] d = new double[4];
				for(int i=0;i<4;i++){
					d[i]=Double.parseDouble(data[i]);
				}
				Node node = new Node(d,data[4]);
				pointList.add(node);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			reader.close();
		}
		return pointList;
	}
	
}
