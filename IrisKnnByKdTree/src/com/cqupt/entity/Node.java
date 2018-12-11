package com.cqupt.entity;

import java.io.Serializable;

public class Node implements Comparable<Node>,Serializable{

	//�洢������
	public double[] data;
	//���ݵ�����
	public String type;
	//��������ݵľ��룬��ʼ��ʱ��û�е�
	public double distance;
	//�ڵ�������ӽڵ㼰���ڵ�
	public Node left,right,parent;
	//ά��  �ýڵ��ж���ά��
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
	 * ����ָ�������ϵ���ֵ
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
	 * ����ýڵ���that�ڵ��ŷʽ����
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
