package com.example.imagemPecas;

import com.example.imagemPecas.domain.entity.Image;
import com.example.imagemPecas.domain.enums.ImageExtension;
import com.example.imagemPecas.infra.repository.imageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ImagemPecasApplication {

/* TESTAR O QUE TEM NO BANCO

    @Bean
    public CommandLineRunner comandLineRunner(@Autowired imageRepository repository){
        return args->{
            Image image = Image.builder()
                    .extension(ImageExtension.PNG)
                    .name("myImage")
                    .tags("teste")
                    .size(1000L)
                    .build();
            repository.save(image);
        };
    }
*/
	public static void main(String[] args) {
		SpringApplication.run(ImagemPecasApplication.class, args);
	}

}

