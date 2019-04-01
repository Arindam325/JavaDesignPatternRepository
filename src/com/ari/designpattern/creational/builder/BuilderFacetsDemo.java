package com.ari.designpattern.creational.builder;

class MyPerson {
	// address
	public String streetAddress, postcode, city;

	// employment
	public String companyName, position;
	public int annualIncome;

	@Override
	public String toString() {
		return "MyPerson{" + "streetAddress='" + streetAddress + '\'' + ", postcode='" + postcode + '\'' + ", city='"
				+ city + '\'' + ", companyName='" + companyName + '\'' + ", position='" + position + '\''
				+ ", annualIncome=" + annualIncome + '}';
	}
}

// builder facade
class MyPersonBuilder {
	// the object we're going to build
	protected MyPerson person = new MyPerson(); // reference!

	public PersonJobBuilder works() {
		return new PersonJobBuilder(person);
	}

	public PersonAddressBuilder lives() {
		return new PersonAddressBuilder(person);
	}

	public MyPerson build() {
		return person;
	}
}

class PersonAddressBuilder extends MyPersonBuilder {
	public PersonAddressBuilder(MyPerson person) {
		this.person = person;
	}

	public PersonAddressBuilder at(String streetAddress) {
		person.streetAddress = streetAddress;
		return this;
	}

	public PersonAddressBuilder withPostcode(String postcode) {
		person.postcode = postcode;
		return this;
	}

	public PersonAddressBuilder in(String city) {
		person.city = city;
		return this;
	}
}

class PersonJobBuilder extends MyPersonBuilder {
	public PersonJobBuilder(MyPerson person) {
		this.person = person;
	}

	public PersonJobBuilder at(String companyName) {
		person.companyName = companyName;
		return this;
	}

	public PersonJobBuilder asA(String position) {
		person.position = position;
		return this;
	}

	public PersonJobBuilder earning(int annualIncome) {
		person.annualIncome = annualIncome;
		return this;
	}
}

public class BuilderFacetsDemo {
	public static void main(String[] args) {
		MyPersonBuilder pb = new MyPersonBuilder();
		MyPerson person = pb.lives().at("123 London Road").in("London").withPostcode("SW12BC").works().at("Fabrikam")
				.asA("Engineer").earning(123000).build();
		System.out.println(person);
	}
}