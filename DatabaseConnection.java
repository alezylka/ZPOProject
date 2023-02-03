import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {
    //czy jest okej ze w kazdej klasie tworzymy connection i statement

    /**
     * Metoda pobierajaca z bazy danych listę pacjentów.
     *
     * @return lista pacjentów
     */
    public static List<Patient> getPatients() {
        List<Patient> patients = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic", "root", "studia123");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from patient");

            while (resultSet.next()) {
                patients.add(
                        new Patient(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getString("surname"),
                                resultSet.getString("login"),
                                resultSet.getString("password")));
            }
            //czy to potrzebne i czy dac do final
            connection.close();
        } catch (SQLException e) {
        }
        return patients;
    }

    /**
     * Metoda pobierajaca z bazy danych listę lekarzy.
     *
     * @return lista lekarzy
     */
    public static List<Doctor> getDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        try {

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic", "root", "studia123");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from doctor");

            while (resultSet.next()) {
                doctors.add(
                        new Doctor(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getString("surname"),
                                resultSet.getString("login"),
                                resultSet.getString("password"),
                                resultSet.getString("specialization")));
            }
        } catch (SQLException e) {
        }
        return doctors;
    }

    /**
     * Metoda pobierajaca z bazy danych listę wizyt.
     *
     * @return lista wizyt
     */
    public static List<Appointment> getAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic", "root", "studia123");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from appointment");

            while (resultSet.next()) {
                appointments.add(
                        new Appointment(
                                getDoctorById(resultSet.getInt("doctor_id")),
                                getPatientById(resultSet.getInt("patient_id")),
                                resultSet.getDate("date")));
            }
            //czy to potrzebne
            connection.close();
        } catch (SQLException e) {
        }
        return appointments;
    }

    /**
     * Metoda zwracająca lekarza po jego id.
     *
     * @param id - id lekarza
     * @return Lekarz
     */
    public static Doctor getDoctorById(int id) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic", "root", "studia123");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from doctor where id = " + id);
            while (resultSet.next()) {
                return new Doctor(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("specialization"));
            }
        } catch (SQLException e) {
        }
        return null;
    }

    /**
     * Metoda zwracająca pacjenta po jego id.
     *
     * @param id - id pacjenta
     * @return Pacjent
     */
    public static Patient getPatientById(int id) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic", "root", "studia123");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from patient where id = " + id);
            while (resultSet.next()) {
                return new Patient(

                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getString("login"),
                        resultSet.getString("password"));
            }
        } catch (SQLException e) {
        }
        return null;
    }
}


