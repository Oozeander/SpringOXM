package com.oozeander.spring;

import java.util.Map;

import javax.xml.bind.Marshaller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
@ComponentScan(basePackages = {"com.oozeander.service"})
public class JavaConfig {
	@Bean Jaxb2Marshaller jaxb2Marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setPackagesToScan("com.oozeander.model");
		marshaller.setMarshallerProperties(Map.of(
				Marshaller.JAXB_ENCODING, "UTF-8",
				Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE
				));
		return marshaller;
	}
}