package com.cqupt.entity;

/**
 * 距离实体类
 *<p>title:Distance</p>
 *<p>Decription:</p>
 * @author songxuan
 * @date  下午12:00:14
 */
public class Distance {

	//训练集的ID
	private long id;
	//测试集的ID
	private long nid;
	//距离
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
