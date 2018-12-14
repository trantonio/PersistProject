package Ejemplos.src.main.java.com.javabycode.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CLASS_ROOM")
public class ClassRoom {

	@Id
	@GeneratedValue
	@Column(name = "CLASS_ROOM_ID")
	private long id;

	@Column(name = "NAME")
	private String name;

	public ClassRoom() {

	}

	public ClassRoom(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	@Override
	public String toString() {
		return "ClassRoom [id=" + id + ", name=" + name + "]";
	}

}
