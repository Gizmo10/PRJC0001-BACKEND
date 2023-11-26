package spring.patient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[]args){
        SpringApplication.run(Application.class, args);
        //All below is new
      /* ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

       Rand r = context.getBean(Rand.class);


       r.setAge(2);
       System.out.println(r.getAge());*/
        }
}


