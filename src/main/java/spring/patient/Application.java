package spring.patient;
import  org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[]args){
         SpringApplication.run(Application.class, args);
         String date = "2024-10-12";
         System.out.println(Integer.parseInt(date.substring(8)));
         System.out.println(Integer.parseInt(date.substring(5,7)));
         System.out.println(Integer.parseInt(date.substring(0,4)));
        }
}


