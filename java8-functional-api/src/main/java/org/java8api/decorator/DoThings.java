package org.java8api.decorator;

public class DoThings {

	public int doThings(String whatever,
			Decorator<String, Integer>... decorators) {

		Integer decorate = Decorator.decorate(
				what -> this.doThings(what),
				whatever,
				decorators);

		return decorate;
	}

	private int doThings(String whatever) {
		return whatever.length();
	}
}
