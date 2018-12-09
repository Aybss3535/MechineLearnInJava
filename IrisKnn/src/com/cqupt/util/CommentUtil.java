package com.cqupt.util;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cqupt.entity.Distance;
import com.cqupt.entity.Point;

/**
 * ��ͨ������
 *<p>title:CommentUtil</p>
 *<p>Decription:</p>
 * @author songxuan
 * @date  ����11:28:27
 */
public class CommentUtil {
	
	/**
	 * ��ȡŷʽ����
	 * @param pointer1
	 * @param pointer2
	 * @return
	 */
	public double getOuDistance(Point pointer1,Point pointer2){
		double sum=Math.pow(pointer1.getX()-pointer2.getX(),2)+Math.pow(pointer1.getY()-pointer2.getY(),2)
		+Math.pow(pointer1.getZ()-pointer2.getZ(), 2)+Math.pow(pointer1.getK()-pointer2.getK(),2);
		return Math.sqrt(sum);
	}
	
	/**
	 * ��ȡ������Ե������k��������ͼ�����
	 * @param distanceList
	 * @param pointList
	 * @param k
	 * @return
	 */
	public Map<String,Integer> getNumOfType(List<Distance> distanceList,List<Point> pointList,int k){
		int i=0;
		Map<String,Integer> map = new HashMap<String,Integer>();
		for(Distance dis:distanceList){
			for(Point point:pointList){
				if(point.getId()==dis.getId()){
					if(map.get(point.getType())!=null){
						map.put(point.getType(),map.get(point.getType())+1);
					}else{
						map.put(point.getType(), 1);
					}
				}
			}
			i++;
			if(i==k){
				break;
			}
		}
		return map;
	}
	
	/**
	 * ��ȡ���������K�����г���Ƶ����������
	 * @param map
	 * @return
	 */
	public String getMaxType(Map<String,Integer> map){
		String maxType=null;
		int maxNum=0;
		for(Map.Entry<String,Integer> entry:map.entrySet()){
			if(entry.getValue()>maxNum){
				maxNum=entry.getValue();
				maxType=entry.getKey();
			}
		}
		return maxType;
	}

}
