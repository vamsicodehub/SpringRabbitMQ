package com.springrabbitmq.demo.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springrabbitmq.demo.model.Person;

@RestController
@RequestMapping("/rest/v1")
public class SpringRabbitMQDemoController {

//	@Autowired
	RabbitTemplate rabbitTemplate;

	@GetMapping("/hello/{name}")
	public String hello(@PathVariable("name") String name) {

		Person person = new Person(1L, name);

		/*rabbitTemplate.convertAndSend("Mobile", person);
		rabbitTemplate.convertAndSend("Fanout", "", person);
		rabbitTemplate.convertAndSend("Direct", "mobile", person);
		rabbitTemplate.convertAndSend("Topic", "mobile.tv.ac", person);
		*/

		rabbitTemplate.send("Headers", "", getValue(person));

		System.out.println("Inside hello publisher....");

		return "Success for " + name;
	}

	private Message getValue(Person person) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream os;
		byte[] byteArray = null;
		try {
			os = new ObjectOutputStream(bos);
			os.writeObject(person);
			os.flush();
			os.close();

			byteArray = bos.toByteArray();
			bos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Message message = MessageBuilder.withBody(byteArray).setHeader("item1", "mobile").setHeader("item2", "televison")
				.build();
		return message;
	}

}
