public class Doctor extends User{
    private String specialization;

    public Doctor(int id, String name, String surname, String login, String password, String specialization) {
        super(id, name, surname, login, password);
        this.specialization = specialization;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", surname='" + getSurname() + '\'' +
                ", login='" + getLogin() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", specialization='" + specialization + '\'' +
                '}';
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
