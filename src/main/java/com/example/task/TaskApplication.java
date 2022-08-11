package com.example.task;

import com.example.task.entity.Task;
import com.example.task.entity.Condition;
import com.example.task.mapper.TaskDtoToTask;
import com.example.task.mapper.TaskDtoToTaskUpdate;
import com.example.task.repository.TaskRepository;
import java.time.LocalDate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class TaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskApplication.class, args);
    }
    
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/tasks/**").allowedOrigins("http://localhost:3000");
            }
        };
    }
    
//
//        @Bean
//        CommandLineRunner commandLineRunner (TaskRepository taskRepository){
//            return args -> {
//                Task task = new Task(
//                        1L,
//                        "Tarea",
//                        "Lavar",
//                        LocalDate.now(),
//                        LocalDate.now(),
//                        1,
//                        false,
//                        Condition
//                );
//                
//                taskRepository.save(task);
//            };
//        }
}
