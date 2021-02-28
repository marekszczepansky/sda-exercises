package sda.exercises.sdaexercises;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class SdaExercisesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SdaExercisesApplication.class, args);
	}

}
