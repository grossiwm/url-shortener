package com.urlshortener.apirest.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;


@Entity
@Table(
		name="TB_PAIR"
)
public class Pair implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@URL
	@NotNull(message = "url não pode ser nula.")
	@Size(min=6, max=2000)
	@Column(unique = true)
	private String original;
	
	@NotNull(message = "url encurtada não pode ser nula.")
	@Size(min=1)
	@Column(unique = true)
	private String shortened;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getOriginal() {
		return original;
	}
	public void setOriginal(String original) {
		this.original = original;
	}
	public String getShortened() {
		return shortened;
	}
	public void setShortened(String shortened) {
		this.shortened = shortened;
	}


}
