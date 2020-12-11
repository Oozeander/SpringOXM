package com.oozeander.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.oozeander.adapter.DateAdapter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data @NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "Employee")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"firstname", "lastname", "arrivalAt", "genderLong", "emails", "address"})
public class Employee implements Serializable {
	@XmlAttribute(name = "NNI")
	private String nni;
	@XmlElement(name = "Firstname")
	private String firstname;
	@XmlElement(name = "Lastname")
	private String lastname;
	@XmlElement(name = "Arrival")
	@XmlJavaTypeAdapter(DateAdapter.class)
	private Date arrivalAt;
	@XmlTransient
	private Integer age;
	@XmlElement(name = "Gender")
	private Gender genderLong;
	@XmlAttribute(name = "Gender")
	private String genderShort;
	// @XmlList
	@XmlElementWrapper(name = "emails")
	@XmlElement(name = "email")
	private Set<String> emails;
	@XmlElement(name = "Address")
	private Address address;

	@XmlType
	@XmlEnum(String.class)
	public static enum Gender {
		@XmlEnumValue("Homme") MALE('M'),
		@XmlEnumValue("Femme") FEMALE('F');

		private char gender;

		Gender(char gender) {
			this.gender = gender;
		}

		public char getGender() {
			return this.gender;
		}
	}
}