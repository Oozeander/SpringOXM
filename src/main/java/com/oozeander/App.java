package com.oozeander;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.oxm.XmlMappingException;

import com.oozeander.model.Address;
import com.oozeander.model.Employee;
import com.oozeander.model.Employee.Gender;
import com.oozeander.model.Location;
import com.oozeander.service.JaxbService;

public class App {
	private static final Logger LOGGER = LogManager.getLogger(App.class);

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws XmlMappingException, IOException, JAXBException {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-oxm.xml");
		// new AnnotationConfigApplicationContext(JavaConfig.class);
		ctx.registerShutdownHook();

		// Spring Marshaller
		Location location = new Location("Ile-de-France", "FRANCE");
		Address address = new Address(20, "Rue de Quelque Chose", 93200, location);
		Employee employee = new Employee("BKBC76BN", "Billel", "KETROUCI", new Date(), 24, Gender.MALE, String.valueOf(Gender.MALE.getGender()),
				new HashSet<>(Arrays.asList("billel.ketrouci@gmail.com", "billel.ketrouci@outlook.fr")), address);
		JaxbService<Employee> employeeJaxbService = ctx.getBean(JaxbService.class);

		employeeJaxbService.marshal(employee, "src/main/resources/marshal/employee.xml");
		LOGGER.info(employeeJaxbService.marshal(employee));
		LOGGER.info(employeeJaxbService.unmarshal("src/main/resources/marshal/employee.xml"));

		// JAXB Marshaller
		JAXBContext jaxbContext = JAXBContext.newInstance(Employee.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

		jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		jaxbMarshaller.marshal(employee, new File("src/main/resources/marshal/employee2.xml"));
		LOGGER.info(jaxbUnmarshaller.unmarshal(new File("src/main/resources/marshal/employee2.xml")));

		ctx.close();
	}
}