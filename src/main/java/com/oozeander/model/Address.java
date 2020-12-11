package com.oozeander.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data @NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"streetNumber", "streetName", "zipCode", "location"})
public class Address implements Serializable {
	@XmlElement(name = "StreetNumber")
	private Integer streetNumber;
	@XmlElement(name = "StreetName")
	private String streetName;
	@XmlElement(name = "ZipCode")
	private Integer zipCode;
	@XmlElement(name = "Location")
	private Location location;
}