package io.github.mariazevedo88.goldgem.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Class that implements an generic entity using the design pattern DTO (Data Transfer Object).
 * 
 * @author Mariana de Azevedo Santos
 * @since 02/06/2013
 */

@MappedSuperclass
public class GenericDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "created_at", nullable = false)
	private Date createdAt;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}
