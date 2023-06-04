import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args){
        System.out.println(checkUser("login", "password", "password"));

    }
    public static boolean checkUser(String login, String password, String confirmPassword){
        boolean result;
        try {
            result = checkLogin(login) && checkPassword(password,confirmPassword);
        }catch (WrongLoginException | WrongPasswordException e){
            System.out.println(e.getMessage());
            result =false;
        }catch (Exception e){
            System.out.println("Что-то пошло не так ");
            result =false;
        }finally {
            System.out.println(" Проверка логина/пароля  завершена  ");
        }
        return result;


    }
    private static boolean checkLogin(String login) throws WrongLoginException {
        Pattern p = Pattern.compile("^[a-z0-9_-]{1,20}$");
        if (!p.matcher(login).matches()) {
            throw new WrongLoginException(" Неверный логин: " + login);
        }
        return true;

    }
    private static boolean checkPassword(String password , String confirmPassword) throws WrongPasswordException {
        Pattern p = Pattern.compile("^[a-z0-9_-]{1,19}$");
        if (!p.matcher(password).matches()) {
            throw new WrongPasswordException(" Пароль: " + password + " не подходит по требаваниям ");
        }
        if (!password.equals(confirmPassword)){
            throw new WrongPasswordException(" Пароли не совпадают ");
        }
            return true;

    }
}