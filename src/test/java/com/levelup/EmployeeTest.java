package com.levelup;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class EmployeeTest {

	List<Employee> employees;

	@Before
	public void setup() {

		employees = new ArrayList<>();
		employees.add(new Employee(123, "Jack", "Johnson", LocalDate.of(1988, Month.APRIL, 12)));
		employees.add(new Employee(345, "Cindy", "Bower", LocalDate.of(2011, Month.DECEMBER, 15)));
		employees.add(new Employee(567, "Perry", "Node", LocalDate.of(2005, Month.JUNE, 07)));
		employees.add(new Employee(467, "Pam", "Krauss", LocalDate.of(2005, Month.JUNE, 07)));
		employees.add(new Employee(435, "Fred", "Shak", LocalDate.of(1988, Month.APRIL, 17)));
		employees.add(new Employee(678, "Ann", "Lee", LocalDate.of(2007, Month.APRIL, 12)));
	}

	Comparator<Employee> byHireDate = new Comparator<Employee>() {
		public int compare(Employee left, Employee right) {
			if (left.getHireDate().isBefore(right.getHireDate())) {
				return -1;
			} else {
				return 1;
			} 
		}
	};

	@Test
	public void with_comarator() {

		Collections.sort(employees, byHireDate);

		System.out.println(employees);
	}

	@Test
	public void anonymous_inner_class() {

		Collections.sort(employees, new Comparator<Employee>() {
			@Override
			public int compare(Employee o1, Employee o2) {
				return o1.getEmployeeFirstName().compareTo(
						o2.getEmployeeFirstName());
			}
		});

		System.out.println(employees);
	}

	@Test
	public void java_8() {
		
		Comparator<Employee> byEmployeeNumber = (e1, e2) -> Integer.compare(e1.getEmployeeNumber(),
				e2.getEmployeeNumber());
		
		employees
				.stream()
				.sorted((e1, e2) -> Integer.compare(e1.getEmployeeNumber(),
						e2.getEmployeeNumber()))
				.forEach(e -> System.out.println(e));
		
	}

	@Test
	public void filter_get_first() {
		
//		employees
//				.stream()
//				.sorted((e1, e2) -> e1.getHireDate()
//						.compareTo(e2.getHireDate()))
//				.forEach(e -> System.out.println(e));

		java.util.Optional<Employee> employee = employees
				.stream()
				.sorted((e1, e2) -> e1.getHireDate()
						.compareTo(e2.getHireDate())).findFirst();

		System.out.println(employee.get());
	}

	@Test
	public void multiple_sort() {
		
		Comparator<Employee> byFirstName = (e1, e2) -> e1
				.getEmployeeFirstName().compareTo(e2.getEmployeeFirstName());

		Comparator<Employee> byLastName = (e1, e2) -> e1.getEmployeeLastName()
				.compareTo(e2.getEmployeeLastName());

		employees.stream().sorted(byFirstName.thenComparing(byLastName))
				.forEach(e -> System.out.println(e));
	}

}
