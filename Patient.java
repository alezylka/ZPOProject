public class Patient extends User{
    public Patient(int id, String name, String surname, String login, String password) {
        super(id, name, surname, login, password);
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", surname='" + getSurname() + '\'' +
                ", login='" + getLogin() + '\'' +
                ", password='" + getPassword() + '\'' +
                '}';
    }

}
