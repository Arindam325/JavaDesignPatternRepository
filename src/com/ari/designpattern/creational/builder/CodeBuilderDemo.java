package com.ari.designpattern.creational.builder;

import java.util.ArrayList;
import java.util.Collections;

class Field {
	public String name, type;
	public String accessModifier = "public";
	private final int indentSize = 2;
	private final String newLine = System.lineSeparator();

	public Field(String name, String type) {
		super();
		this.name = name;
		this.type = type;
	}

	private String toStringImpl(int indent) {
		StringBuilder sb = new StringBuilder();
		String i = String.join("", Collections.nCopies(indent * indentSize, " "));

		sb.append(String.format("%s %s %s %s;%s", i, this.accessModifier, this.type, this.name, this.newLine));

		return sb.toString();
	}

	@Override
	public String toString() {
		return toStringImpl(1);
	}
}

class CodeBuilder {
	public String className;
	public ArrayList<Field> fields = new ArrayList<Field>();

	public CodeBuilder(String className) {
		this.className = className;
	}

	public CodeBuilder addField(String name, String type) {
		this.fields.add(new Field(name, type));
		return this;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.join(" ", "public", "class", className));
		sb.append("\n{\n");
		fields.forEach(f -> {
			sb.append(f.toString());
		});
		sb.append("}");
		return sb.toString();
	}
	
	public static void main(String[] args) {
		CodeBuilder codeBuilder = new CodeBuilder("Person");
		codeBuilder.addField("name", "String").addField("age", "String");
		System.out.println(codeBuilder);
	}
}

public class CodeBuilderDemo {

	public static void main(String[] args) {
		CodeBuilder.main(null);
		
		//codeBuilder.addField("name", "String").addField("age", "String");
		//System.out.println(codeBuilder);
	}

}
