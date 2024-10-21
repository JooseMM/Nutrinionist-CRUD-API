package com.example.nutrionist_api;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.nutrionist_api.service.AppointmentRepository;
import com.example.nutrionist_api.model.Appointment;

@Configuration 
/**
 * LoadDB
 */
public class LoadDB {
    private static final Logger log = LoggerFactory.getLogger(LoadDB.class);
	
    @Bean
    CommandLineRunner initDB(AppointmentRepository repository) {
	return args -> {
	    log.info("Preloading " + repository.save(new Appointment(
			    "Jose Moreno",
			    26,
			    "Masculino",
			    null,
			    "Culturismo"
			    )));
	};
    }
}
