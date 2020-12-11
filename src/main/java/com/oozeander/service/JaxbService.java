package com.oozeander.service;

import java.io.IOException;

import org.springframework.oxm.XmlMappingException;

public interface JaxbService<T> {
	public void marshal(T t, String location) throws XmlMappingException, IOException;
	public String marshal(T t);
	public T unmarshal(String location);
}