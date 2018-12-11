package com.cqupt.entity;

import java.io.Serializable;

public class Node implements Comparable<Node>,Serializable{

	//存储的数据
	public double[] data;
	//数据的类型
	public String type;
	//与测试数据的距离，初始化时是没有的
	public double distance;
	//节点的左右子节点及父节点
	public Node left,right,parent;
	//维度  该节点判定的维度
	public int dim=-1;
	
	public Node(double[] data) {
		super();
		this.data = data;
	}

	
	public Node(double[] data, String type) {
		super();
		this.data = data;
		this.type = type;
	}


	public Node(){
		
	}
	
	@Override
	public int compareTo(Node arg0) {
		if(this.distance>arg0.distance){
			return 1;
		}else if(this.distance==arg0.distance){
			return 0;
		}else{
			return -1;
		}
	}
	
	/**
	 * 返回指定索引上的数值
	 * @param index
	 * @return
	 */
	public double getData(int index){
		if(data==null||data.length<=index){
			return Integer.MIN_VALUE;
		}
		return this.data[index];
	}
	
	/**
	 * 计算该节点与that节点的欧式距离
	 * @param that
	 * @return
	 */
	public double computeDistance(Node that){
		if(this.data==null||that.data==null||this.data.length!=that.data.length){
			return Integer.MAX_VALUE;
		}
		double sum = 0.0;
		for(int i=0;i<this.data.length;i++){
			sum+=Math.pow(this.data[i]-that.data[i], 2);
		}
		return Math.sqrt(sum);
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<data.length;i++){
			sb.append(data[i]+" ");
		}
		sb.append("d="+distance);
		sb.append(" type="+type);
		return sb.toString();
	}

}
