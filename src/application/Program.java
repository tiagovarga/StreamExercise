package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter full file path: ");
		String path = sc.nextLine();
		System.out.print("Enter salary: ");
		Double minSalary = sc.nextDouble();
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))){
			
			List<Employee> list = new ArrayList<>();
			String line = br.readLine();
			while(line != null) {
				String[] fields = line.split(",");
				String name = fields[0];
				String email = fields[1];
				Double salary = Double.parseDouble(fields[2]);
				list.add(new Employee(name, email, salary));
				line = br.readLine();
				
			}
			
			System.out.println("Email of people whose salary is more than " + minSalary);
			List<String> emails = list.stream()
					.filter(e -> e.getSalary() > minSalary)
					.map(e -> e.getEmail())
					.sorted()
					.collect(Collectors.toList());
					
			emails.forEach(System.out::println);
			
			char c = 'M';
			
			Double sum = list.stream()
					.filter(e -> e.getName().charAt(0) == c)
					.map(e -> e.getSalary())
					.reduce(0.0, (x,y) -> x + y);
			
			System.out.println("Sum of salary of people whose name starts with '" + c + "': " + sum);
			
		}
		catch(IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		

	}

}
