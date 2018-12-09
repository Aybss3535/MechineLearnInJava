package com.cqupt.entity;

public class Point {

	//点的ID
	private long id;
	//点的第一维度值
	private double x;
	//点的第二维度值
	private double y;
	private double z;
	private double k;
	//点的类型
	private String type;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getZ() {
		return z;
	}
	public void setZ(double z) {
		this.z = z;
	}
	public double getK() {
		return k;
	}
	public void setK(double k) {
		this.k = k;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Point(long id, double x, double y, double z, double k, String type) {
		super();
		this.id = id;
		this.x = x;
		this.y = y;
		this.z = z;
		this.k = k;
		this.type = type;
	}
	public Point(long id, double x, double y, double z, double k) {
		super();
		this.id = id;
		this.x = x;
		this.y = y;
		this.z = z;
		this.k = k;
	}
	public Point(){
		
	}
	@Override
	public String toString() {
		return "Point [id=" + id + ", x=" + x + ", y=" + y + ", z=" + z + ", k=" + k + ", type=" + type + "]";
	}
	
}
