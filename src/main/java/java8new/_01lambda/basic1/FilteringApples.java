package java8new._01lambda.basic1;

import java.util.*;

public class FilteringApples {

	//main() String[] 대신 String... (가변적 아규먼트 variable argument)
	public static void main(String... args) {

		List<Apple> inventory = 
				Arrays.asList(new Apple(80, "green"), 
							  new Apple(155, "green"), 
							  new Apple(120, "red"));

		//filter method call
		//Anonymous Inner class
		List<Apple> green = filter(inventory, new ApplePredicate() {
			@Override
			public boolean test(Apple a) {
				return a.getColor().equals("green");
			}
		});
		green.forEach(apple -> System.out.println(apple));

		System.out.println("120 이상 Apples");
		//Lambda expression
		List<Apple> apples = filter(inventory, apple -> apple.getWeight() >= 120);
		apples.forEach(System.out::println);

	}

	public static List<Apple> filterGreenApples(List<Apple> inventory) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if ("green".equals(apple.getColor())) {
				result.add(apple);
			}
		}
		return result;
	}

	public static List<Apple> filterApplesByColor(List<Apple> inventory, String color) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (apple.getColor().equals(color)) {
				result.add(apple);
			}
		}
		return result;
	}

	public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (apple.getWeight() > weight) {
				result.add(apple);
			}
		}
		return result;
	}

	public static List<Apple> filter(List<Apple> inventory, ApplePredicate p) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (p.test(apple)) {
				result.add(apple);
			}
		}
		return result;
	}

	@FunctionalInterface
	interface ApplePredicate {
		public boolean test(Apple a);
	}

	static class AppleWeightPredicate implements ApplePredicate {
		public boolean test(Apple apple) {
			return apple.getWeight() > 150;
		}
	}

	static class AppleColorPredicate implements ApplePredicate {
		public boolean test(Apple apple) {
			return "green".equals(apple.getColor());
		}
	}

	static class AppleRedAndHeavyPredicate implements ApplePredicate {
		public boolean test(Apple apple) {
			return "red".equals(apple.getColor()) && apple.getWeight() > 150;
		}
	}
}