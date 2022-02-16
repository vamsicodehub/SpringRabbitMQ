package com.springrabbitmq.demo.Listener;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.springrabbitmq.demo.model.Person;

@Service
public class SpringRabbitMQListener {

//	@RabbitListener(queues = { "Mobile" })
	public void consumeMobileMessage(Person person) {
		System.out.println("Hi Listened to Mobile Queue :: " + person.getName());
	}

//	@RabbitListener(queues = { "TV" })
	public void consumeTVMessage(Person person) {
		System.out.println("Hi Listened to TV Queue :: " + person.getName());
	}
	
//	@RabbitListener(queues = { "AC" })
	public void consumeACMessage(Person person) {
		System.out.println("Hi Listened to AC Queue :: " + person.getName());
	}

//	@RabbitListener(queues = { "AC" })
	public void consumeACMessage(byte[] person1) {
		Person person = getPerson(person1);
		System.out.println("Hi Listened to AC Queue :: " + person.getName());
	}
	
//	@RabbitListener(queues = { "Mobile" })
	public void consumeMobileMessage(byte[] person1) {
		Person person = getPerson(person1);
		System.out.println("Hi Listened to Mobile Queue :: " + person.getName());
	}

//	@RabbitListener(queues = { "TV" })
	public void consumeTVMessage(byte[] person1) {
		Person person = getPerson(person1);
		System.out.println("Hi Listened to TV Queue :: " + person.getName());
	}

	private Person getPerson(byte[] person1) {
		Person person = null;
		InputStream in = new ByteArrayInputStream(person1);
		try {
			ObjectInputStream objectInputStream = new ObjectInputStream(in);
			person = (Person) objectInputStream.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return person;
	}

}
