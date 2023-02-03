import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ViewDoctors {

    private static Connection connection;

    public ViewDoctors(Connection connection) {
        this.connection = connection;
    }

    // f)
    public static List<Appointment> getSpecialistByDate(String specialization, Date date) {
        List<Appointment> appointmentsList = new ArrayList<>();

        Appointment appointment = null;

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("select d.id, d.name," +
                            "d.surname, d.specialization a.date from Doctor d" +
                            "INNER JOIN Appointment ON Doctor.id=Appointment.Doctor.id" +
                            "AND d.specialization =? AND a.date=?");

            preparedStatement.setString(1, specialization);
            preparedStatement.setDate(2, (java.sql.Date) date);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                Appointment appointmentX = new Appointment();

                appointmentX.setDoctor((Doctor) resultSet.getObject(1));
                appointmentX.setDoctor((Doctor) resultSet.getObject(2));
                appointmentX.setDoctor((Doctor) resultSet.getObject(3));
                appointmentX.setDoctor((Doctor) resultSet.getObject(4));
                appointmentX.setDate(resultSet.getDate(5));

                appointmentsList.add(appointmentX);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return appointmentsList;
    }


    // e)
    public static List<Appointment> getSpecialist (String specialization) {
        List<Appointment> appointmentsList = new ArrayList<>();

        Appointment appointment = null;

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("select id, name," +
                            "surname from Doctor " +
                            "WHERE specialization = ?");
            preparedStatement.setString(1, specialization);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Appointment appointmentX = new Appointment();

                appointmentX.setDoctor((Doctor) resultSet.getObject(1));
                appointmentX.setDoctor((Doctor) resultSet.getObject(2));
                appointmentX.setDoctor((Doctor) resultSet.getObject(3));
                appointmentX.setDoctor((Doctor) resultSet.getObject(4));

                appointmentsList.add(appointmentX);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return appointmentsList;
    }

}
