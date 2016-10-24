package petshop;

import java.util.IllegalFormatException;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;

public class App {
	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("petshop/beans/beans.xml");

		PetsDAO petsDao = (PetsDAO) context.getBean("petsDao");
		String operation;
		String animal;
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("enter an operation (inquire | buy | exit )");
			operation = scanner.nextLine();
			try {
				if (operation.equals("exit")) {
					break;
				} else if (operation.equals("buy")) {
					System.out.println("enter an animal");
					animal = scanner.nextLine();
					if (petsDao.exists(animal)) {
						if (petsDao.getPet(animal).getQty() > 0) {
							if (petsDao.updateQty(animal)) {
								System.out.println(animal + " bought");
								System.out.println(petsDao.getPet(animal));
							}

						}

					}

				} else if (operation.equals("inquire")) {
					System.out.println("enter an animal");
					animal = scanner.nextLine();
					if (petsDao.exists(animal)) {
						System.out.println(petsDao.getPet(animal));
					}
				}
			} catch (IllegalFormatException ex) {
			}
		}

		System.out.println("ended");

		Pet pet = new Pet();

		// boolean test ;
		// test = petsDao.exists("cat");
		// System.out.println(test);

		((ClassPathXmlApplicationContext) context).close();

	}

}
