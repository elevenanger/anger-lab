package excel;

import com.baomidou.mybatisplus.core.incrementer.IKeyGenerator;
import com.baomidou.mybatisplus.extension.incrementer.PostgreKeyGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ExcelApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExcelApplication.class, args);
	}

	@Bean
	public IKeyGenerator keyGenerator() {
		return new PostgreKeyGenerator();
	}
}
