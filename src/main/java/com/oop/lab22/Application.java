package com.oop.lab22;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
Точка запуску
 */
@SpringBootApplication
public class Application {
	public static void main(String[] args){
		SpringApplication.run(Application.class, args);

		Vacancy vacancy = new Vacancy();
		try{
			vacancy.showResults();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

}
