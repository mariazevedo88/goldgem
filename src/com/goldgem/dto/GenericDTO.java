package com.goldgem.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Class that implements an generic entity using the design pattern DTO (Data Transfer Object).
 * @author Mariana de Azevedo Santos
 * 		   Contact: mariana@bsi.ufla.br
 *
 */

@MappedSuperclass
public class GenericDTO {

	@Id
	@GeneratedValue
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
		
}
