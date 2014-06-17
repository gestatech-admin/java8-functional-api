package org.java8api.decorator;

import java.util.function.Function;
import java.util.stream.Stream;

public interface Decorator<T, R> {
	public R apply(Function<T, R> fn, T arg);

	@SafeVarargs
	public static <T, R> R decorate(Function<T, R> call, T ar,
			Decorator<T, R>... decorators) {

		Decorator<T, R> function = Stream.of(decorators)
		.reduce((acc, elem) -> {
			// return new accumulator
	        return (origCall, arg1) -> { 
	        	
	        	Function<T,R> elemCall = (nextArg1) -> {
	        		// call current decorator passing
	        		// next decorator and argument
					return elem.apply(origCall, nextArg1);
		        };
		        
		        // call previous decorator
		        // by passing this decorator and argument
		        return acc.apply(elemCall, arg1);
			};
		}).get();

		return function.apply(call, ar);
	}
}