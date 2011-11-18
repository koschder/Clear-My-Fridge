package ch.bfh.cmf.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.*;

/**
 * Entity implementation class for Entity: Ingredient
 * 
 */
@Entity
public class Ingredient {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private long id;
	private String name;
	private String picture;

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
