package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      User user4 = new User("User4", "Lastname4", "user4@mail.ru");
      Car car1 = new Car ("Subaru",5);
      Car car2 = new Car("Ferrari", 5);
      Car car3 = new Car("Lada", 6);
      Car car4 = new Car("BMW", 7);
      user1.setCar(car1);
      user2.setCar(car2);
      user3.setCar(car3);
      user4.setCar(car4);

      userService.add(user1,car1);
      userService.add(user2,car2);
      userService.add(user3,car3);
      userService.add(user4,car4);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = " + user.getCar().getModel() + " " + user.getCar().getSeries() + " series");
         System.out.println();
      }
User myUser = userService.getUser( car3.getModel(), car3.getSeries());
      System.out.println("Id = "+myUser.getId());
      System.out.println("First Name = "+myUser.getFirstName());
      System.out.println("Last Name = "+myUser.getLastName());
      System.out.println("Email = "+myUser.getEmail());
      System.out.println("Car=" + myUser.getCar().getModel() + " " +myUser.getCar().getSeries() + "series");



      context.close();
   }
}
