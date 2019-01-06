package io.github.mariazevedo88.goldgem.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Class that implements an generic entity using the design pattern DTO (Data Transfer Object).
 * @author Mariana de Azevedo Santos
 * @since
 */

@MappedSuperclass
public class GenericDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
		
}
