import java.io.*;
import java.util.*;

@SuppressWarnings("unchecked")
    public class Users {
    private Set<String> blockedUsers = new HashSet<>();
    private Map<String, Integer> failedAttempts = new HashMap<>();
        public boolean login(String username, String password, HashMap info) {
            if (blockedUsers.contains(username)) {
                System.out.println("Username is blocked");
                return false;
            }
            if (info.containsKey(username) && info.get(username).equals(password)) {
                failedAttempts.put(username, 0);
                logUser(username);
                return true;
            } else {
                int attempts = failedAttempts.getOrDefault(username,0) + 1;
                failedAttempts.put(username, attempts);

                if (attempts >= 3) {
                    blockedUsers.add(username);
                    System.out.println("This account has been blocked due to 3 unsuccessful login attempts!");
                }
                return false;
            }
        }
    private void logUser(String username) {
        String logFile = "Users.log";
        try (FileWriter fw = new FileWriter(logFile, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(logFile + "\n");
            bw.newLine();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void signup(String username, String password, String cpss, HashMap info) {
            boolean reslut = validate(username, password, cpss, info);
            if (!reslut) {
                System.out.println("Invalid username or password");
            }
            else {
                info.put(username, password);
                System.out.println("signed up successfuly");
            }
        }

        public boolean validate(String username, String password, String cpss, HashMap info) {
            if (info.containsKey(username)) {
                return false;
            }
            else if (!password.equals(cpss)) {
                return false;
            }
            else if (password.length() < 8)
                return false;
            else
                return true;
        }


        public void userMenu(String username, String password, HashMap info) {
            Scanner reader = new Scanner(System.in);
            while (true) {
                System.out.println("1.Delete Account 2.Change Password 3.Logout");
                if (username.equals("admin")) {
                    System.out.println("4.View All Accounts 5.View Login Log 6.View Blocked Users");
                }
                System.out.println("your plan");
                String plan2 = reader.nextLine();

                if (plan2.equals("1")) {
                    System.out.println("are you sure?(yes/no)");
                    String answer = reader.nextLine();
                    if (answer.equals("yes")) {
                        info.remove(username);
                        System.out.println("success delete account");
                        break;
                    }
                    else {
                        System.out.println("deleting the account failed");
                    }

                }
                else if (plan2.equals("2")) {
                    System.out.println("are you sure you want to change your password?(yes/no)");
                    String answer = reader.nextLine();
                    if (answer.equals("yes")) {
                        System.out.println("please enter your old password");
                        String oldPassword = reader.nextLine();
                        if (oldPassword.equals(password)) {
                            System.out.println("please enter your new password");
                            String newPassword = reader.nextLine();
                            if (newPassword.length() < 8) {
                                System.out.println("your password must be at least 8 characters");
                            }
                            else if (newPassword.equals(oldPassword)) {
                                System.out.println("your password can not be the old password");
                            }
                            else {
                                System.out.println("please enter your new password again");
                                String cnewpass = reader.nextLine();
                                if (cnewpass.equals(newPassword)) {
                                    password = newPassword;
                                    info.put(username, password);
                                    System.out.println("success change password");
                                }
                                else
                                    System.out.println("your new passwords don't match");
                            }
                        }
                        else {
                            System.out.println("your passwords is incorrect");
                        }
                    }
                    else {
                        System.out.println("password change canceled.");
                    }
                }
                else if (plan2.equals("3")) {
                    break;
                }
                else if (plan2.equals("4") && username.equals("admin")) {
                    System.out.println("**ALL ACCOUNTS**");
                    for (Object user : info.keySet()) {
                        System.out.println("Username: " + user + " \nPassword: " + info.get(user));
                    }
                }
                else if (plan2.equals("5") && username.equals("admin")) {
                    System.out.println("*** Login Log ***");
                    for (Object user : info.keySet()) {
                        if (!blockedUsers.contains(user))
                            System.out.println("Username: " + user);
                        }
                }
                else if (plan2.equals("6") && username.equals("admin")) {
                    System.out.println("*** Blocked Users ***");
                    if (blockedUsers.isEmpty()) {
                        System.out.println("No blocked users.");
                    }
                    else {
                        for (String user : blockedUsers) {
                            System.out.println(user);
                        }
                    }
                }
                else {
                    System.out.println("Invalid input");
                }
            }
        }
    }