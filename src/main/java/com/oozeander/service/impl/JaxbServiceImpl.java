package com.oozeander.service.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.XmlMappingException;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;

import com.oozeander.service.JaxbService;

@Service("jaxbService")
public class JaxbServiceImpl<T> implements JaxbService<T> {
	@Autowired
	private Jaxb2Marshaller jaxb2Marshaller;

	@Override
	public void marshal(T t, String location) throws XmlMappingException, IOException {
		jaxb2Marshaller.marshal(t, new StreamResult(new FileWriter(location)));
	}

	@Override
	public String marshal(T t) {
		StringWriter sw = new StringWriter();
		jaxb2Marshaller.marshal(t, new StreamResult(sw));
		return sw.toString();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T unmarshal(String location) {
		return (T) jaxb2Marshaller.unmarshal(new StreamSource(new File(location)));
	}
}