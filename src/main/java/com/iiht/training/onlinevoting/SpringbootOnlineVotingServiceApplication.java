package com.iiht.training.onlinevoting;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.HibernateTemplate;

@SpringBootApplication
public class SpringbootOnlineVotingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootOnlineVotingServiceApplication.class, args);
	}

	// Please add model mapper bean here if you want only then
}
