package IDMaker.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class IDMakerProject {
	public static void main(String[] args) {
		SpringApplication.run(IDMakerProject.class, args);
	}
}
