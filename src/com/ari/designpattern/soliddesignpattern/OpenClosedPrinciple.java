package com.ari.designpattern.soliddesignpattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

//OpenClosed Principle:
/**
 * 
 * @author Arindam
 *
 *         Open Close Principle is extendible but not modifiable approach of
 *         designing. It requires multiple Specifications for providing logics.
 *         Based on new Specification, existing design and classes remains the
 *         same.
 * 
 *         Notice We have created Multiple Specification class for different
 *         filter mechanism. But, We do not have to modify existing Product,
 *         Specification or Filter classes.
 */

interface Specification<T> {
	boolean isSatisfied(T item);
}

interface Filter<T> {
	Stream<T> filter(List<T> items, Specification<T> specification);
}

class ColorSpecification implements Specification<Product> {
	private Color color;

	public ColorSpecification(Color color) {
		this.color = color;
	}

	@Override
	public boolean isSatisfied(Product item) {
		return this.color == item.getColor();
	}

}

class SizeSpecification implements Specification<Product> {
	private Size size;

	public SizeSpecification(Size size) {
		this.size = size;
	}

	@Override
	public boolean isSatisfied(Product item) {
		return this.size == item.getSize();
	}
}

class AndSpecification implements Specification<Product> {
	private Specification<Product> specification1;
	private Specification<Product> specification2;

	public AndSpecification(Specification<Product> specification1, Specification<Product> specification2) {
		this.specification1 = specification1;
		this.specification2 = specification2;
	}

	@Override
	public boolean isSatisfied(Product item) {
		return this.specification1.isSatisfied(item) && this.specification2.isSatisfied(item);
	}

}

class AnyFilter implements Filter<Product> {

	@Override
	public Stream<Product> filter(List<Product> items, Specification<Product> specification) {
		return items.stream().filter(p -> specification.isSatisfied(p));
	}

}

public class OpenClosedPrinciple {

	public static void main(String[] args) {
		List<Product> products = new ArrayList<>();
		Product tree = new Product("Tree", Color.GREEN, Size.LARGE);
		Product apple = new Product("Apple", Color.GREEN, Size.SMALL);
		Product house = new Product("House", Color.BlUE, Size.LARGE);
		products = Arrays.asList(tree, apple, house);

		System.out.println("****************************\n");
		ColorSpecification colorSpecification = new ColorSpecification(Color.GREEN);
		AnyFilter anyFilter = new AnyFilter();
		System.out.println("Color Specification filters::");
		anyFilter.filter(products, colorSpecification)
				.forEach(p -> System.out.println(p.getName() + " - " + p.getColor() + ", " + p.getSize()));
		System.out.println("****************************\n");
		System.out.println("Size Specification filters::");
		SizeSpecification sizeSpecification = new SizeSpecification(Size.LARGE);
		// AnyFilter sizeFilter = new AnyFilter();
		anyFilter.filter(products, sizeSpecification)
				.forEach(p -> System.out.println(p.getName() + " - " + p.getColor() + ", " + p.getSize()));
		System.out.println("****************************\n");
		System.out.println("And Specification filters::");
		AndSpecification andSpecification = new AndSpecification(colorSpecification, sizeSpecification);
		// AnyFilter sizeFilter = new AnyFilter();
		anyFilter.filter(products, andSpecification)
				.forEach(p -> System.out.println(p.getName() + " - " + p.getColor() + ", " + p.getSize()));

	}
}

enum Color {
	WHITE, BlUE, GREEN;
}

enum Size {
	SMALL, MEDIUM, LARGE;
}

class Product {
	private String name;
	private Size size;
	private Color color;

	public Product(String name, Color color, Size size) {
		this.name = name;
		this.color = color;
		this.size = size;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}