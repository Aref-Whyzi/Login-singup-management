import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        HashMap<String, String> info = new HashMap<String, String>();
        info.put("admin", "123456789");
        Users myUser = new Users();
        while (true) {
            System.out.println("1.Login  2.Signup  3.Exit");
            System.out.print("Your Plan: ");
            String plan = reader.nextLine();
            if (plan.equals("1")) {
                System.out.println("Enter your username: ");
                String username = reader.nextLine();
                System.out.println("Enter your password: ");
                String password = reader.nextLine();
                boolean result = myUser.login(username, password, info);
                if (result) {
                    System.out.println("Login Successful");
                    myUser.userMenu(username,password, info);
                }
                else {
                    System.out.println("login failed");
                }
            } else if (plan.equals("2")) {
                System.out.println("Enter your username: ");
                String username = reader.nextLine();
                System.out.println("Enter your password: ");
                String password = reader.nextLine();
                System.out.println("please enter your password again: ");
                String cpass = reader.nextLine();
                myUser.signup(username, password, cpass, info);

            } else if (plan.equals("3")) {
                break;
            } else {
                System.out.println("Wrong Input!!!");
            }
        }
    }
}