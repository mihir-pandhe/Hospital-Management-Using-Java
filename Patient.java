public class Patient {
    private int id;
    private String name;
    private int age;
    private String gender;
    private String ailment;
    private double billAmount;
    private boolean isPaid;

    public Patient(int id, String name, int age, String gender, String ailment) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.ailment = ailment;
        this.billAmount = 0.0;
        this.isPaid = false;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAilment() {
        return ailment;
    }

    public void setAilment(String ailment) {
        this.ailment = ailment;
    }

    public double getBillAmount() {
        return billAmount;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void addBillAmount(double amount) {
        this.billAmount += amount;
    }

    public void markAsPaid() {
        this.isPaid = true;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Age: " + age + ", Gender: " + gender +
                ", Ailment: " + ailment + ", Bill Amount: $" + billAmount + ", Paid: " + isPaid;
    }
}
