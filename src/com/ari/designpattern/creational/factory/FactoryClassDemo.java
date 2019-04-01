package com.ari.designpattern.creational.factory;

class MyPoint {
	private double x, y;

	private MyPoint(double x, double y) {
		this.x = x;
		this.y = y;
	}

	// factory class
	public static class Factory {
		public static MyPoint newCartesianPoint(double x, double y) {
			return new MyPoint(x, y);
		}

		public static MyPoint newPolarPoint(double rho, double theta) {
			return new MyPoint(rho * Math.cos(theta), rho * Math.sin(theta));
		}
	}
}

public class FactoryClassDemo {

	public static void main(String[] args) {
		MyPoint point = MyPoint.Factory.newCartesianPoint(4, 5);
	}
}
