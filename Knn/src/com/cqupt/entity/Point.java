package com.cqupt.entity;

import java.util.Set;
import java.util.TreeSet;

public class Point {

	//点的ID
	private long id;
	//点的第一维度值
	private double x;
	//点的第二维度值
	private double y;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public Point(long id, double x, double y) {
		super();
		this.id = id;
		this.x = x;
		this.y = y;
	}
	public Point(long id, double x, double y, String type) {
		super();
		this.id = id;
		this.x = x;
		this.y = y;
		this.type = type;
	}
	public Point(){
		
	}
}
