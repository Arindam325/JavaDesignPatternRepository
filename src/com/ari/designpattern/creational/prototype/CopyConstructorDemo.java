package com.ari.designpattern.creational.prototype;

class MyAddress {
	public String streetAddress, city, country;

	public MyAddress(String streetAddress, String city, String country) {
		this.streetAddress = streetAddress;
		this.city = city;
		this.country = country;
	}

	public MyAddress(MyAddress other) {
		this(other.streetAddress, other.city, other.country);
	}

	@Override
	public String toString() {
		return "MyAddress{" + "streetAddress='" + streetAddress + '\'' + ", city='" + city + '\'' + ", country='"
				+ country + '\'' + '}';
	}
}

class Employee {
	public String name;
	public MyAddress address;

	public Employee(String name, MyAddress address) {
		this.name = name;
		this.address = address;
	}

	public Employee(Employee other) {
		name = other.name;
		address = new MyAddress(other.address);
	}

	@Override
	public String toString() {
		return "Employee{" + "name='" + name + '\'' + ", address=" + address + '}';
	}
}

public class CopyConstructorDemo {
	public static void main(String[] args) {
		Employee john = new Employee("John", new MyAddress("123 London Road", "London", "UK"));

		// Employee chris = john;
		Employee chris = new Employee(john);

		chris.name = "Chris";
		System.out.println(john);
		System.out.println(chris);
	}
}