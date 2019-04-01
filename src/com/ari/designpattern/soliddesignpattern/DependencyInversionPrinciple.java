package com.ari.designpattern.soliddesignpattern;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.javatuples.Triplet;

/**
 * 
 * @author arindam
 * 
 * Dependency Inversion Principle states that :
 * A. High-level modules should not depend on low-level modules. Both should depend on abstractions.
 * B. Abstractions should not depend on details. Details should depend on abstractions.
 * 
 *  NOTICE that RelationShips class is Low-level class and it handles the storage of parent, child and their relationship.
 *  Research class is High-level class responsible for fetching data based on condition.
 *  
 *  In the first scenario, we have implemented the data filtration in Research class, but it breaks Dependency Inversion Principle.
 *  As, any change in Low-level data structure will break the code.
 *  
 *   So, to implement Dependency Inversion Principle, we have implemented RelationshipBrowser interface and make Relationships class implement it
 *   and override the findAllChildrenOf() method. So, data handling remains in Low-level. Any changes in underlying data structures will be 
 *   handled in Low-level.
 *   
 *   The High-level code remains same, as it uses abstraction (findAllChildrenOf method) to fetch the data.
 *
 */


//

//

enum Relationship {
	PARENT, CHILD, SIBLING
}

class Person {
	public String name;
// dob etc.

	public Person(String name) {
		this.name = name;
	}
}

interface RelationshipBrowser {
	List<Person> findAllChildrenOf(String name);
}

class Relationships implements RelationshipBrowser {
	public List<Person> findAllChildrenOf(String name) {

		return relations.stream()
				.filter(x -> Objects.equals(x.getValue0().name, name) && x.getValue1() == Relationship.PARENT)
				.map(Triplet::getValue2).collect(Collectors.toList());
	}

	// Triplet class requires javatuples
	private List<Triplet<Person, Relationship, Person>> relations = new ArrayList<>();

	public List<Triplet<Person, Relationship, Person>> getRelations() {
		return relations;
	}

	public void addParentAndChild(Person parent, Person child) {
		relations.add(new Triplet<>(parent, Relationship.PARENT, child));
		relations.add(new Triplet<>(child, Relationship.CHILD, parent));
	}
}

class Research {
	/*
	  public Research(Relationships relationships) { // high-level: find all of
	  john's children List<Triplet<Person, Relationship, Person>> relations =
	  relationships.getRelations(); relations.stream().filter(x ->
	  x.getValue0().name.equals("John") && x.getValue1() == Relationship.PARENT)
	  .forEach(ch -> System.out.println("John has a child called " +
	  ch.getValue2().name)); }
	 */
	public Research(RelationshipBrowser browser) {
		List<Person> children = browser.findAllChildrenOf("John");
		for (Person child : children)
			System.out.println("John has a child called " + child.name);
	}
}

public class DependencyInversionPrinciple {

	public static void main(String[] args) {
		Person parent = new Person("John");
		Person child1 = new Person("Chris");
		Person child2 = new Person("Matt");

		// low-level module
		Relationships relationships = new Relationships();
		relationships.addParentAndChild(parent, child1);
		relationships.addParentAndChild(parent, child2);

		new Research(relationships);

	}

}
