package io.github.mariazevedo88.goldgem.test;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import io.github.mariazevedo88.goldgem.dto.GenericDTO;


@Entity
@Table(name="author")
public class Author extends GenericDTO implements Serializable{
	
	private static final long serialVersionUID = 1212268556068927184L;

	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	@Column(name = "last_name", nullable = false)
	private String lastName;
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getFullName() {
		return this.firstName + " " + this.lastName; 
	}
}
