package Ejemplos.src.main.java.com.javabycode.hibernate;

import java.util.Date;
import java.util.List;

import Ejemplos.src.main.java.com.javabycode.hibernate.model.ClassRoom;
import Ejemplos.src.main.java.com.javabycode.hibernate.model.Student;
import org.hibernate.Session;



public class HibernateExample {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		Student student1 = new Student("David Pham", new Date(), "USA", "1234566");
		Student student2 = new Student("Bill Murray", new Date(), "USA", "1234567");
		Student student3 = new Student("Steve Carell", new Date(), "USA", "1234568");

		ClassRoom classRoom = new ClassRoom("Math");

		student1.setClassRoom(classRoom);
		student2.setClassRoom(classRoom);
		student3.setClassRoom(classRoom);

		System.out.println(System.getProperty("java.classpath"));

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		session.persist(classRoom);

		session.persist(student1);
		session.persist(student2);
		session.persist(student3);

		List<Student> students = (List<Student>) session.createQuery("from Student ").list();
		for (Student s : students) {
			System.out.println("Student Details : " + s);
			System.out.println("Student ClassRoom Details: " + s.getClassRoom());
		}

		session.getTransaction().commit();
		session.close();
	}

}
