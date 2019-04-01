package com.ari.designpattern.soliddesignpattern;

/**
 * 
 * @author arindam
 * 
 * Interface Segregation Principle states that we should put multiple methods in same interface when absolutely necessary.
 * 
 *  Notice, We have created interface called Machine, with all the methods for printer and scanner.
 *  It worked perfectly for MultiFunctionPrinter, but did not worked for OldFashionedPrinter. 
 *  The problem arises because we did not follow, Interface Segregation Principle.
 *  
 *   To Solve the problem We have created specific interface like Printer, IScanner for printing and scanning.
 *   And generic interface like MultiFunctionDevice. 
 *   Now, the users have variety of options to choose from.
 *
 */


// *	Document class refers to the object that should be printed. 
class Document {
}

// Machine interface is the parent interface that users will implement for specific vendors 
interface Machine {
	void print(Document d);

	void fax(Document d);

	void scan(Document d);
}

//ok if you need a multifunction machine
class MultiFunctionPrinter implements Machine {
	public void print(Document d) {
		//
	}

	public void fax(Document d) {
		//
	}

	public void scan(Document d) {
		//
	}
}

/**
 * 
 * @author arindam NOTICE: We don't need fax and Ascan method for
 *         OldFashionPrinter. But again, we can't throw exception if we don't
 *         have source for Machine, because of inheritance principle
 *
 *         Here we have to use Interface Segregation Principle.
 */
class OldFashionedPrinter implements Machine {
	public void print(Document d) {
		// yep
	}

	public void fax(Document d) {
		// throw new Exception();
	}

	public void scan(Document d) {
		// throw new Exception();
	}
}

// We have segregated interface based on specific functionality Printer and IScanner 
interface Printer {
	void Print(Document d) throws Exception;
}

interface IScanner {
	void Scan(Document d) throws Exception;
}

//JustAPrinter implements only Printer as it does not know Scanning 
class JustAPrinter implements Printer {
	public void Print(Document d) {

	}
}

// Photocopier implements both Printer and IScanner properties 
class Photocopier implements Printer, IScanner {
	public void Print(Document d) throws Exception {
		throw new Exception();
	}

	public void Scan(Document d) throws Exception {
		throw new Exception();
	}
}

// We can merge interface to create generic interface for users to implement directly 
interface MultiFunctionDevice extends Printer, IScanner //
{

}

// MultifucntionMachine implemented the MultifunctionDevice interface
class MultiFunctionMachine implements MultiFunctionDevice {
	// compose this out of several modules
	private Printer printer;
	private IScanner scanner;

	public MultiFunctionMachine(Printer printer, IScanner scanner) {
		this.printer = printer;
		this.scanner = scanner;
	}

	public void Print(Document d) throws Exception {
		printer.Print(d);
	}

	public void Scan(Document d) throws Exception {
		scanner.Scan(d);
	}
}

public class InterfaceSegregationPrinciple {

	public static void main(String[] args) {

	}

}
