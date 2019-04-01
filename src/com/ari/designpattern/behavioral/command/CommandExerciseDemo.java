package com.ari.designpattern.behavioral.command;

public class CommandExerciseDemo {

}

class Command {
	enum Action {
		DEPOSIT, WITHDRAW
	}

	public Action action;
	public int amount;
	public boolean success;

	public Command(Action action, int amount) {
		this.action = action;
		this.amount = amount;
	}
}

class Account {
	public int balance;

	public void process(Command c) {
		switch (c.action) {
		case DEPOSIT:
			c.success = deposit(c.amount);
			break;
		case WITHDRAW:
			c.success = withdraw(c.amount);
			break;

		}
	}

	public boolean deposit(int amount) {
		balance += amount;
		return true;
	}

	public boolean withdraw(int amount) {
		if (balance - amount < 0) {
			return false;
		}
		balance = balance - amount;
		return true;
	}
}