package org.java8api.decorator;


public class DecoratorTest {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		DoThings doThings = new DoThings();

		int doThings2 = doThings.doThings("length7",

			// first decorator
			(fn, whatever) -> {
				System.out.println(whatever);
				// call "original"
				return fn.apply(whatever + "or11");
			},
			
			// second decorator
			(fn, whatever) -> {
				System.out.println(whatever);
				// call "original"
				int i = fn.apply(whatever);
				System.out.println("i = " + i + ", but I prefer " + --i);
				return i;
			});

		System.out.println("output: " + doThings2);

	}

}
