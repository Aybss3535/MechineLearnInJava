package com.cqupt.entity;

/**
 * ����ʵ����
 *<p>title:Distance</p>
 *<p>Decription:</p>
 * @author songxuan
 * @date  ����12:00:14
 */
public class Distance {

	//ѵ������ID
	private long id;
	//���Լ���ID
	private long nid;
	//����
	private double distance;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getNid() {
		return nid;
	}
	public void setNid(long nid) {
		this.nid = nid;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public Distance(){
		
	}
	public Distance(long id, long nid, double distance) {
		super();
		this.id = id;
		this.nid = nid;
		this.distance = distance;
	}
	

}
