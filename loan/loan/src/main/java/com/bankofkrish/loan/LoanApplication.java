package com.bankofkrish.loan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableJpaAuditing( auditorAwareRef ="auditAwareImpl")
@OpenAPIDefinition(info = @Info(title = "Accounts Service of MS", 
description = "Loan details of Bank application", 
version = "v1", contact =@Contact(email =  "krish.72362@gmail.com",name = "MlpTeam",url = "local host accounts")),
externalDocs = @ExternalDocumentation(description = "KrishBank Loan details apis documentationn",url = "somthing somthing"))
public class LoanApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanApplication.class, args);
	}

}
