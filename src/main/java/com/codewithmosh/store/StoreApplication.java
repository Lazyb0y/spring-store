package com.codewithmosh.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class StoreApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(StoreApplication.class, args);

		OrderService orderService = context.getBean(OrderService.class);
		orderService.placeOrder();

		NotificationManager notificationManager = context.getBean(NotificationManager.class);
		notificationManager.sendNotification("This is a test");

		UserService userService = context.getBean(UserService.class);
		userService.registerUSer(new User(1L, "John Doe", "john@gmail.com", "123456"));
		userService.registerUSer(new User(1L, "John Doe", "john@gmail.com", "123456"));

		context.close();
	}

}
