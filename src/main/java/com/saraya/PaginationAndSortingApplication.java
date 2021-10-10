package com.saraya;

import com.saraya.entities.Employee;
import com.saraya.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Date;

@SpringBootApplication
//
@EnableJpaAuditing
public class PaginationAndSortingApplication {

	public static void main(String[] args) {

		SpringApplication.run(PaginationAndSortingApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(EmployeeRepository employeeRepository){
		return args -> {
			employeeRepository.save(new Employee("Vieux Soumare", "vsoumare@saraya.io", "+221778541204", "Web developer", "NH2", new Date(), "Senegal, Dakar", 'M', "", true));
			employeeRepository.save(new Employee("Laye Ndiaye", "ndiayelaye01@saraya.io", "+221774521478", "UI", "JH5", new Date(), "Senegal, Dakar", 'M', "", true));
			employeeRepository.save(new Employee("Bamba Ndour", "ndourbamba@saraya.io", "+221774512547", "Mobile developer", "KJ8", new Date(), "Senegal, Dakar", 'M', "", false));
			employeeRepository.save(new Employee("Coumba Ndiaye", "ndiayecoumba@saraya.io", "+221779858798", "Web designer", "NG9", new Date(), "Senegal, Dakar", 'F', "", true));
			employeeRepository.save(new Employee("Fama Aidara", "aidarafama@saraya.io", "+221778953363", "Full stack developer", "ML8", new Date(), "Senegal, Dakar", 'F', "", false));
			employeeRepository.save(new Employee("Moussa Diop", "diopmoussa@saraya.io", "+221776365487", "Web and mobile developer", "BH4", new Date(), "Senegal, Dakar", 'M', "", true));
			employeeRepository.findAll().forEach(employee -> {System.out.println(employee.toString());});
		};
	}

}
