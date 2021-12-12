package com.example.fireapiv1;

import com.example.fireapiv1.Helpers.State;
import com.example.fireapiv1.Model.*;
import com.example.fireapiv1.Repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@SpringBootApplication
public class FireAPiV1Application {

    public static void main(String[] args) {
        SpringApplication.run(FireAPiV1Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(
            PostRepository postRepository,
            ScaleRepository scaleRepository,
            UserRepository userRepository,
            FireRepository fireRepository,
            ClientRepository clientRepository,
            FirmanRepository firmanrepository,
            AdminRepository adminRepository) {
        return args -> {
            Client u1 = new Client("cheikh boukal", "ouail nazim", "wail@gmail.com", "123", 1);
            Client u2 = new Client("amri", "karim", "kira@gmail.com", "123", 2);
            u2.setDob(LocalDate.of(1999, Month.OCTOBER, 29));
            u2.setPhone_number("0540037061");
            Fireman f1 = new Fireman("mehdi", "bendali", "mehdi@gmail.com", "123", "IFA");
            Fireman f2 = new Fireman("nouri", "charaf", "nouri@gmail.com", "123", "TLSI");
            Admin a1 = new Admin("Administrator", "Call 911", "admin@gmail.com", "123", "138.209.100.28");
            Fire fire1 = new Fire(34.54, 4.5, "bousbaa moussa", "didouche mourad", "25034", "Algeria", u2);
            Post post1 = new Post("post num 1",u1);

            fireRepository.save(fire1);
            postRepository.save(post1);
            clientRepository.saveAll(List.of(u1, u2));
            firmanrepository.saveAll(List.of(f1, f2));
            adminRepository.saveAll(List.of(a1));

            Client u3 = new Client("test", "test", "test@gmail.com", "123", 1);
            Fire fire3 = new Fire(34.54, 4.5, "bousbaa moussa", "didouche mourad", "25034", "Algeria", u3);
            Scale scale1 = new Scale(u3,fire3, State.LOW);
            fire3.setClientsConfirm(u3);
            u3.setFireConfirmed(fire3);
            scaleRepository.save(scale1);
            clientRepository.save(u3);
            fireRepository.save(fire3);
            System.out.println("fire"+ fire3.getId() + " is confirmed by : " + fire3.getCountConfirmed() + " person");

            System.out.println("Number of users: " + userRepository.count());
            System.out.println("Number of fires: " + fireRepository.count());
            System.out.println("Number of posts: " + fireRepository.count());
        };
    }

}
