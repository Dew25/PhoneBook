package ee.ivkhkdev.phonebook;

import ee.ivkhkdev.phonebook.input.Input;
import ee.ivkhkdev.phonebook.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PhoneBookApplication implements CommandLineRunner {
	@Autowired
	private Input input;
	@Autowired
	private PersonService personService;
	public static void main(String[] args) {
		SpringApplication.run(PhoneBookApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		boolean repeat = true;
		do{
			System.out.println("Список задач: ");
			System.out.println("0. Выход");
			System.out.println("1. Добавить contact");
			System.out.println("2. Список контактов");
			System.out.println("3. Контакт по имени и фамилии");
			System.out.println("Введите номер задачи: ");
			int task = input.nextInt();
			switch (task){
				case 0:
					repeat= false;
					break;
				case 1:
					if(personService.add()){
						System.out.println("Контакт добавлен");
					}else{
						System.out.println("Контакт добавить не удалось");
					}
					break;
				case 2:
					personService.printAll();
					break;
				case 3:
					personService.printOne();
					break;
				default:
					System.out.println("Выберите номер из списка!");
			}
		}while(repeat);
		System.out.println("До свидания");
	}
}