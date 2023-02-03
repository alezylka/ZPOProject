import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorsVisits {

    private Connection connection;

    public DoctorsVisits(Connection connection) {
        this.connection = connection;
    }

    // sprawdzenie terminarza lekarza - wyswietlenie listy wizyt umówionych do niego
    // sprawdzanych po id danego doktora
    public List<Appointment> getAllDoctorsAppointments(int loggedDoctorId) throws SQLException {
        List<Appointment> appointmentsList = new ArrayList<>();

        Appointment appointment = null;

        try {
            PreparedStatement statement =
                    this.connection.prepareStatement("select * from Appointment where doctor_id =");
            statement.setInt(1, loggedDoctorId);

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                Appointment appointmentX = new Appointment();

                appointmentX.setDoctor((Doctor) resultSet.getObject(1));
                appointmentX.setPatient((Patient) resultSet.getObject(2));
                appointmentX.setDate(resultSet.getDate(3));

                appointmentsList.add(appointmentX);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return appointmentsList;
    }

    // po zakonczonej wizycie lekarz moze usunac pacjenta z kolejki
    public void VisitExpired(int loggedDoctorId, int patientId) {
        List<Appointment> appointmentList = new ArrayList<>();

        Appointment appointment = null;

        try {
            PreparedStatement preparedStatement =
                    this.connection.prepareStatement("delete from" +
                            "Appointment where doctor_id = ? and patient_id = ?");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Appointment appointmentX = new Appointment();

                appointmentX.setDoctor(resultSet.getObject(1));
                appointmentX.setPatient(resultSet.getObject(2));
                appointmentX.setDate(resultSet.getDate(3));

                appointmentList.add(appointmentX);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
