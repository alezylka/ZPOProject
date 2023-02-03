import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private List<User> users = new ArrayList<>();
    private List<Appointment> appointments;
    private User loggedInUser;

    /**
     * Przy inicjalizacji aplikacji dane z bazy danych zostają przypisane do atrybutów klasy.
     */
    public App() {
        users.addAll(DatabaseConnection.getPatients());
        users.addAll(DatabaseConnection.getDoctors());
        appointments = DatabaseConnection.getAppointments();
    }

    /**
     * Metoda obsługująca aplikację.
     */
    public void mainApp() {
        login();
    }

    /**
     * Metoda wyświetlająca menu.
     */
    public void menu(){
        if(loggedInUser instanceof Patient){
            System.out.println("1 - zapisz się na wizytę");
            System.out.println("2 - sprawdź zarezerwowane wizyty");
            System.out.println("3 - dokonaj zmiany terminu");
            System.out.println("4 - wyświetl liczbę lekarzy danej specjalizacji");
            System.out.println("5 - wyświetl wolne terminy w danym dniu dla danego/danych lekarzy");
        }
        else{
            System.out.println("1 - sprawdź terminarz");
            System.out.println("2 - dokonaj rezerwacji dla pacjenta");
            System.out.println("3 - sprawdź umówione wizyty");
        }

        System.out.println("0 - wyloguj się");


    }

    /**
     * Metoda wykonująca logowanie pacjenta lub lekarza.
     */
    public void login() {
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Podaj login:");
            String login = sc.nextLine();
            System.out.println("Podaj hasło:");
            String password = sc.nextLine();
            if (checkLoginAndPassword(login, password)) {
                if (loggedInUser instanceof Patient) {
                    System.out.print("Witaj pacjencie ");
                } else {
                    System.out.print("Witaj doktorze ");
                }
                System.out.println(loggedInUser.getName() + " " + loggedInUser.getSurname());
                menu();
                break;
            } else {
                System.out.println("Podałeś zły login lub hasło, spróbuj ponownie");
            }
        }
    }

    /**
     * Metoda sprawdzająca login i hasło użytkownika.
     * @param login - login użytkownika
     * @param password - hasło użytkownika
     * @return true jeśli istnieje użytkownik o danym loginie i haśle, false w przeciwnym przypadku.
     */
    public boolean checkLoginAndPassword(String login, String password) {
        for (User user : users) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                this.loggedInUser = user;
                return true;
            }
        }
        return false;
    }
}
