package org.java8api;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MenuResolver {

	Supplier<List<MenuItem>> supplier;

	// Decorators
	private Function<MenuItem, MenuItem> decorators = Function.identity();

	/*
	 * delegating fetching available menu items More dynamic than passing List,
	 * because potentially can supply different results over time
	 */
	public MenuResolver(Supplier<List<MenuItem>> supplier) {
		this.supplier = supplier;
	}

	/*
	 * Filtering strategy
	 */
	public List<MenuItem> getItems(Predicate<MenuItem> filter) {
		return getItems().filter(filter)
				.collect(Collectors.toList());
	}

	/*
	 * Filtering strategy with more filters
	 */
	public List<MenuItem> getItems(Predicate<MenuItem>... filters) {
		Predicate<MenuItem> pred = Stream.of(filters)
				.reduce((f1, f2) -> f1.and(f2)).get();

		return getItems().filter(pred)
				.collect(Collectors.toList());
	}

	/*
	 * Declarative filtering, loan
	 *
	 * loan pattern page 82. get it, use it, return it
	 */
	public List<MenuItem> getItems2(Consumer<MenuPredicateMap> filters) {

		MenuPredicateMap predicates = new MenuPredicateMap();
		filters.accept(predicates);

		return getItems().filter(
				(item) -> predicates.getOrDefault(item.label, ($) -> false)
				.test(item))
				.collect(Collectors.toList());
	}

	private Stream<MenuItem> getItems() {
		return supplier.get().stream().map(decorators);
	}

	public void setDecorators(Function<MenuItem, MenuItem>... decorators) {
		this.decorators = Stream.of(decorators)
				.reduce((d1, d2) -> d1.compose(d2)).orElse(Function.identity());
	}
}
