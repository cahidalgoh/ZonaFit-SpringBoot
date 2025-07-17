package com.hidalgocarlos.zonafitspringboot;

import com.hidalgocarlos.zonafitspringboot.model.Customer;
import com.hidalgocarlos.zonafitspringboot.service.ICustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class ZonaFitSpringBootApplication implements CommandLineRunner {

	@Autowired
	private ICustomerService customerService;

	private static final Logger LOGGER = LoggerFactory.getLogger(ZonaFitSpringBootApplication.class);
	// Salto de línea
	private String nl = System.lineSeparator();

	public static void main(String[] args) {

		String startSystem = """
				
				
				*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
				*-*-*																								*-*
				*-*-*								*-*-*-*-*-* Starting system *-*-*-*-*-*							*-*
				*-*-*																								*-*
				*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
				
				""";


		String endSystem = """
				
				*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
				*-*-*																								*-*
				*-*-*								 *-*-*-*-*-* Logging out *-*-*-*-*-*							*-*
				*-*-*																								*-*
				*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
				""";

		LOGGER.info(startSystem);

		// Levantando la app
		SpringApplication.run(ZonaFitSpringBootApplication.class, args);

		LOGGER.info(endSystem);
	}

	/**
	 * @param args incoming main method arguments
	 * @throws Exception
	 */
	@Override
	public void run(String... args) throws Exception {

		// Declaración de variables necesarias
		var exit = false;

		// Consola para la entrada de datos
		Scanner inputData = new Scanner(System.in);

		while (!exit){
			showMenu();

			// Convertirmos a tipo entero la opción elegida por el usuario
			var selectedOption = Integer.parseInt(inputData.nextLine());

			switch (selectedOption){
				case 1 -> showCustomers(inputData);
				case 2 -> findCustomer(inputData);
				case 3 -> addCustomer(inputData);
				case 4 -> editCustomer(inputData);
				case 5 -> deleteCustomer(inputData);
				case 6 -> exit = true;
				default -> {
                    LOGGER.info("{}\tInvalid option!...{}", nl, nl);
					pressEnterToContinue(inputData);
				}
			}
		}
	}


	private void showMenu(){

		String title = """
                
                
                *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
                *-*-*                                                                                         *-*-*
                *-*-*                                      Fit Zone System                                    *-*-*
                *-*-*                                                                                         *-*-*
                *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
                """;

		LOGGER.info(title);

		String menu = """
                    
                    ----------------------
                    | 1. Show customers  |
                    ----------------------
                    | 2. Search customer |
                    ----------------------
                    | 3. Add customer    |
                    ----------------------
                    | 4. Edit customer   |
                    ----------------------
                    | 5. Delete customer |
                    ----------------------
                    | 6. Log off         |
                    ----------------------
                    -> Enter an option:\s""";

		LOGGER.info(menu);
	}

	private void showCustomers(Scanner inputData){
		// Obtenemos los clientes de la base de datos
		List<Customer> customerList = customerService.getAllCustomers();

		LOGGER.info("{}*-*-*-* Customers *-*-*-*{}*-*-*-*-*-*-*-*-*-*-*-*-*{}{}", nl, nl, nl, nl);
		customerList.forEach(System.out::println);

		pressEnterToContinue(inputData);
	}

	private void findCustomer(Scanner inputData){

		// Buscamos los datos de un usuario
		LOGGER.info("{}*-*-*-* Find customer data *-*-*-*{}",nl, nl);

		// Pedimos el id del cliente a buscar
		var customerId = Integer.parseInt(getDataEntry(inputData, "Please enter the user ID: "));

		// Buscamos el usuario
		Customer customer = customerService.getCustomerById(customerId);

		// Esiste el cliente?
		if(null != customer){
			LOGGER.info("{}\tCustomer's data:\n\t----------------{}", nl, nl);

			// Datos del cliente recuperado
			LOGGER.info(customer.toString());
		} else{
			// El cliente no existe en la base de datos
            LOGGER.info("{}\tThe customer with ID: {}, does not exist in the database!", nl, customerId);
		}

		pressEnterToContinue(inputData);
	}

	private void addCustomer(Scanner inputData){

		// Añadimos un usuario nuevo
		LOGGER.info("{}*-*-*-* Register new customer *-*-*-*{}", nl, nl);

		var name = getDataEntry(inputData, "\tFirst name: ");
		var last_name = getDataEntry(inputData, "\tLast name: ");
		var dni = getDataEntry(inputData, "\tID number: ");
		var membership = Integer.parseInt(getDataEntry(inputData, "\tMembership: "));
		var cellphone = getDataEntry(inputData, "\tMobile number: ");
		var address = getDataEntry(inputData, "\tAddress: ");

		// Creamos el objeto customer para guardarlo en la base de datos
		Customer customer = new Customer(null, name, last_name, dni, membership, cellphone, address);

		// Guardanos el customer a través del dao
		customerService.saveOrUpdateCustomer(customer);

		// Mostrar el listado de clientes?
		var showCustomersList = getDataEntry(inputData, "\n\tShow the list of clients? y/n: ");

		LOGGER.info("{}\tUser created successfully!{}", nl, nl);

		if(showCustomersList.equalsIgnoreCase("y"))
			showCustomers(inputData);
	}

	private void editCustomer(Scanner inputData) {

		// Editamos Los datos de un usuario
		LOGGER.info("{}*-*-*-* Edit customer data *-*-*-*{}", nl, nl);

		// Pedimos el id del cliente a modificar
		var customerId = Integer.parseInt(getDataEntry(inputData, "Please enter the user ID: "));

		// Buscamos el cliente a editar
		Customer customer = customerService.getCustomerById(customerId);

		// Existe cliente?
		if(null != customer){

			LOGGER.info("{}\tCustomer's data:\n\t----------------{}", nl, nl);

			// Datos del cliente recuperado
			LOGGER.info(customer.toString());

			LOGGER.info("{}\tINFO:{}\t-----{}\tIf you don't want to edit a field, press Enter without entering any characters.{}{}", nl, nl, nl, nl, nl);

			var name = getDataEntry(inputData, "\tNew first name: ");
			var last_name = getDataEntry(inputData, "\tNew Last name: ");
			var dni = getDataEntry(inputData, "\tID number: ");
			var membership = getDataEntry(inputData, "\tNew membership: ");
			var cellphone = getDataEntry(inputData, "\tNew mobile number: ");
			var address = getDataEntry(inputData, "\tNew address: ");

			// Establecemos los nuevos valores del cliente
			customer.setName(!name.trim().isEmpty()? name.trim() : customer.getName());
			customer.setLastName(!last_name.trim().isEmpty()? last_name.trim() : customer.getLastName());
			customer.setDni(!dni.trim().isEmpty()? dni.trim() : customer.getDni());
			customer.setMembership(!membership.trim().isEmpty()? Integer.parseInt(membership.trim()) : customer.getMembership());
			customer.setCellphone(!cellphone.trim().isEmpty()? cellphone.trim() : customer.getCellphone());
			customer.setAddress(!address.trim().isEmpty()? address.trim() : customer.getAddress());

			// Guardamos los cambios del cliente en la base de datos
			customerService.saveOrUpdateCustomer(customer);

			LOGGER.info("{}\tCustomer data has been successfully updated!{}", nl, nl);

		} else{
			LOGGER.info("{}\tThe customer cannot be modified, the client with id: {} does not exist in the database!", nl, customerId);
		}

		pressEnterToContinue(inputData);
	}

	private void deleteCustomer(Scanner inputData) {

		// Eliminamos un usuario
		LOGGER.info("{}*-*-*-* Delete all customer data *-*-*-*{}", nl, nl);

		// Pedimos el id del cliente a eliminar
		var customerId = Integer.parseInt(getDataEntry(inputData, "Please enter the user ID: "));

		// Buscamos el cliente a editar
		Customer customer = customerService.getCustomerById(customerId);

		// Existe cliente?
		if(null != customer) {

			LOGGER.info("{}\tCustomer's data:{}\t----------------{}", nl, nl, nl);

			// Datos del cliente recuperado
			LOGGER.info(customer.toString());

			LOGGER.info("{}\tWarning:{}\t-------", nl, nl);
			var deleteCustomerData = getDataEntry(inputData, "\n\tCustomer: " +customer.getName() + " with id: " + customer.getIdCustomer() + " will be deleted, do you want to continue? y/n: ");


			// Borramos el cliente?
			if(deleteCustomerData.equalsIgnoreCase("y")){
				// Eliminamos el cliente
				customerService.deleteCustomer(customer);

				LOGGER.info("{}\tCustomer data has been successfully deleted!", nl);

			} else{
				// Mensaje al usuario
				LOGGER.info("{}\tOperation cancelled!{}", nl, nl);
			}

		} else{
			LOGGER.info("{}\tThe customer cannot be deleted, there is no client with id: {} in the database!", nl, customerId);
		}

		pressEnterToContinue(inputData);
	}

	private String getDataEntry(Scanner inputData, String requestInformation) {

		System.out.print(requestInformation);
		return inputData.nextLine();
	}

	private void pressEnterToContinue(Scanner inputData){

		LOGGER.info("{}\tPress Enter to continue...{}{}", nl, nl, nl);
		inputData.nextLine();

	}

}
