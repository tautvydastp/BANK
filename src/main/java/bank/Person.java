package bank;

public class Person {
    private long personId;
    private String name;
    private String lastname;

    public Person(long personId, String name, String lastname) {
        this.personId = personId;
        this.name = name;
        this.lastname = lastname;
    }

    public Person() {

    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return "\nPeople:" +
                "\nAsmens kodas: " + personId +
                "\nVardas: " + name +
                "\nPavarde: " + lastname;
    }
}
