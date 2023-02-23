package web;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import web.config.AppConfig;
import web.entity.User;
import web.service.UserService;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        User user1 = new User("Nick","Grigorjev", 31);
        User user2 = new User("Luyda", "Sidorova", 25);
        User user3 = new User("Luydmila", "Grigorjeva", 26);

        userService.create(user1);
        userService.create(user2);
        userService.update(2,user3);
        userService.create(user3);
        userService.delete(3);
        System.out.println(userService.getAllUsers());

        context.close();
    }
}
