package org.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyConfig.class);
        Communication communication = applicationContext.getBean("communication", Communication.class);
        List<String> cookie = communication.getCookie();
        System.out.println("Cookie: "+cookie);

        User user1 = new User(3L,"James","Brown",(byte) 16);
        String codeOne = communication.saveUser(user1,cookie);
        System.out.println("Add user code: " + codeOne);

        User user2 = new User(3L,"Thomas","Shelby",(byte) 16);
        String codeTwo = communication.editUser(user2,cookie);
        System.out.println("Edit user code: " + codeTwo);

        String codeThree = communication.deleteUser(3,cookie);
        System.out.println("Delete user code: " + codeThree);
        System.out.println("CODE :  " + codeOne+codeTwo+codeThree);
    }
}
