package com.ari.designpattern.structural.adapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;

class MyPoint {
	public int x, y;

	public MyPoint(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		MyPoint point = (MyPoint) o;

		if (x != point.x)
			return false;
		return y == point.y;
	}

	@Override
	public int hashCode() {
		int result = x;
		result = 31 * result + y;
		return result;
	}

	@Override
	public String toString() {
		return "MyPoint{" + "first=" + x + ", second=" + y + '}';
	}
}

class MyLine {
	public MyPoint start, end;

	public MyLine(MyPoint start, MyPoint end) {
		this.start = start;
		this.end = end;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Line line = (Line) o;

		if (!start.equals(line.start))
			return false;
		return end.equals(line.end);
	}

	@Override
	public int hashCode() {
		int result = start.hashCode();
		result = 31 * result + end.hashCode();
		return result;
	}
}

class MyVectorObject extends ArrayList<MyLine> {
}

class MyVectorRectangle extends MyVectorObject {
	public MyVectorRectangle(int x, int y, int width, int height) {
		add(new MyLine(new MyPoint(x, y), new MyPoint(x + width, y)));
		add(new MyLine(new MyPoint(x + width, y), new MyPoint(x + width, y + height)));
		add(new MyLine(new MyPoint(x, y), new MyPoint(x, y + height)));
		add(new MyLine(new MyPoint(x, y + height), new MyPoint(x + width, y + height)));
	}
}

class MyLineToPointAdapter implements Iterable<MyPoint> {
	private static int count = 0;
	private static Map<Integer, List<MyPoint>> cache = new HashMap<>();
	private int hash;

	public MyLineToPointAdapter(MyLine line) {
		hash = line.hashCode();
		if (cache.get(hash) != null)
			return; // we already have it

		System.out.println(String.format("%d: Generating points for line [%d,%d]-[%d,%d] (no caching)", ++count,
				line.start.x, line.start.y, line.end.x, line.end.y));

		ArrayList<MyPoint> points = new ArrayList<>();

		int left = Math.min(line.start.x, line.end.x);
		int right = Math.max(line.start.x, line.end.x);
		int top = Math.min(line.start.y, line.end.y);
		int bottom = Math.max(line.start.y, line.end.y);
		int dx = right - left;
		int dy = line.end.y - line.start.y;

		if (dx == 0) {
			for (int y = top; y <= bottom; ++y) {
				points.add(new MyPoint(left, y));
			}
		} else if (dy == 0) {
			for (int x = left; x <= right; ++x) {
				points.add(new MyPoint(x, top));
			}
		}

		cache.put(hash, points);
	}

	@Override
	public Iterator<MyPoint> iterator() {
		return cache.get(hash).iterator();
	}

	@Override
	public void forEach(Consumer<? super MyPoint> action) {
		cache.get(hash).forEach(action);
	}

	@Override
	public Spliterator<MyPoint> spliterator() {
		return cache.get(hash).spliterator();
	}
}

public class CachingAdapterDemo {
	private static final List<MyVectorObject> vectorObjects = new ArrayList<>(
			Arrays.asList(new MyVectorRectangle(1, 1, 10, 10), new MyVectorRectangle(3, 3, 6, 6)));

	public static void drawPoint(MyPoint p) {
		System.out.print(".");
	}

	private static void draw() {
		for (MyVectorObject vo : vectorObjects) {
			for (MyLine line : vo) {
				MyLineToPointAdapter adapter = new MyLineToPointAdapter(line);
				adapter.forEach(point -> drawPoint(point));
			}
		}
	}

	public static void main(String[] args) {
		draw();
		draw();
	}
}