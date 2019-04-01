package com.ari.designpattern.structural.decorator;

import java.util.function.Supplier;

/*interface Shape {
	String info();
}

class Circle implements Shape {
	private float radius;

	Circle() {
	}

	public Circle(float radius) {
		this.radius = radius;
	}

	void resize(float factor) {
		radius *= factor;
	}

	@Override
	public String info() {
		return "A circle of radius " + radius;
	}
}

class Square implements Shape {
	private float side;

	public Square() {
	}

	public Square(float side) {
		this.side = side;
	}

	@Override
	public String info() {
		return "A square with side " + side;
	}
}
*/
// we are NOT altering the base class of these objects
// cannot make ColoredSquare, ColoredCircle

class MyColoredShape<T extends Shape> implements Shape {
	private Shape shape;
	private String color;

	public MyColoredShape(Supplier<? extends T> ctor, String color) {
		shape = ctor.get();
		this.color = color;
	}

	@Override
	public String info() {
		return shape + " has the color " + color;
	}
}

class MyTransparentShape<T extends Shape> implements Shape {
	private Shape shape;
	private int transparency;

	public MyTransparentShape(Supplier<? extends T> ctor, int transparency) {
		shape = ctor.get();
		this.transparency = transparency;
	}

	@Override
	public String info() {
		return shape + " has " + transparency + "% transparency";
	}
}

public class StaticDecoratorDemo {
	public static void main(String[] args) {
		Circle circle = new Circle(10);
		System.out.println(circle.info());

		MyColoredShape<Square> blueSquare = new MyColoredShape<>(() -> new Square(20), "blue");
		System.out.println(blueSquare.info());

		// ugly!
		MyTransparentShape<MyColoredShape<Circle>> myCircle = new MyTransparentShape<>(
				() -> new MyColoredShape<>(() -> new Circle(5), "green"), 50);
		System.out.println(myCircle.info());
		// cannot call myCircle.resize()
	}
}