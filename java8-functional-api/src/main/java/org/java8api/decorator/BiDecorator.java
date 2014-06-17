package org.java8api.decorator;

import java.util.function.BiFunction;
import java.util.stream.Stream;

public interface BiDecorator<T1, T2, R> {

	public R apply(BiFunction<T1, T2, R> fn, T1 arg0, T2 arg1);

	public static <T1, T2, R> R decorate(BiFunction<T1, T2, R> call, T1 ar,
	        T2 ar2,
			BiDecorator<T1, T2, R>... decorators) {
		BiDecorator<T1, T2, R> function = Stream.of(decorators)
				.reduce((acc, elem) -> {
					return (fn, arg0, arg1) -> {
						return acc.apply((_arg0, _arg1) -> {
							return elem.apply(fn, _arg0, _arg1);
						}, arg0, arg1);
					};
				}).get();

		return function.apply(call, ar, ar2);
	}

}
