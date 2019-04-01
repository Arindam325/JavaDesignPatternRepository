package com.ari.designpattern.soliddesignpattern;

// Liskov Substitution Principle

/**
 * 
 * @author Arindam
 *
 *        Liskov Substitution Principle is that We can substitute a Sub-class for a Base-class.
 *         
 *         Notice We have created Rectangle class as Base-class and Square as Sub-class, 
 *         the property of Square is that Height and Width should be same always.
 *         The useIt() method checks the expected and actual output of Rectangle and Square object.
 *         It works fine for Rectangle, but fails in case of Square object. So, the substitution has failed.
 *         This shows Liskov Substitution Principle has failed in the design.
 *         
 *         However to resolve the issue, we can use the Factory design pattern. The example will be in other package.
 */


class Rectangle {
	protected int width, height;

	public Rectangle() {
	}

	public Rectangle(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getArea() {
		return width * height;
	}

	@Override
	public String toString() {
		return "Rectangle{" + "width=" + width + ", height=" + height + '}';
	}

	public boolean isSquare() {
		return width == height;
	}
}

class Square extends Rectangle {
	public Square() {
	}

	public Square(int size) {
		width = height = size;
	}

	@Override
	public void setWidth(int width) {
		super.setWidth(width);
		super.setHeight(width);
	}

	@Override
	public void setHeight(int height) {
		super.setHeight(height);
		super.setWidth(height);
	}
}

class RectangleFactory {
	public static Rectangle newSquare(int side) {
		return new Rectangle(side, side);
	}

	public static Rectangle newRectangle(int width, int height) {
		return new Rectangle(width, height);
	}
}

public class LiskovSubstitutionPrinciple {
	// maybe conform to ++
	static void useIt(Rectangle r) {
		int width = r.getWidth();
		r.setHeight(10);
		System.out.println("Expected area of " + (width * 10) + ", got " + r.getArea());
	}

	public static void main(String[] args) {
		Rectangle rc = new Rectangle(2, 3);
		useIt(rc);

		Rectangle sq = new Square();
		sq.setHeight(10);
		sq.setWidth(5);
		useIt(sq);
	}
}
