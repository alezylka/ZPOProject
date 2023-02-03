import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        App app = new App();
        app.mainApp();

        Scanner scan = new Scanner(System.in);
        int z = scan.nextInt();
        while(z <= 5 & z > 0) {
            if (scan.equals(4)) {
                System.out.println("Podaj nazwę specjalisty");
                ViewDoctors.getSpecialist(scan.nextLine());
            } else if (scan.equals(5)) {
                System.out.println("Podaj dzień wizyty");
                int a = scan.nextInt();
                System.out.println("Podaj specjalistę");
                String b = scan.nextLine();
                ViewDoctors.getSpecialistByDate(b, new Date(a));
            }
        }
    }
}
