package ch.bfh.cmf.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Entity implementation class for Entity: Ingredient
 * 
 */
@Entity
public class Ingredient implements Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private long id;
	private String name;
	private String picture;
	private static final long serialVersionUID = 1L;

	public Ingredient() {
		super();
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPicture() {
		return this.picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

}
