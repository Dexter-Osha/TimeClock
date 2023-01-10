/*
* Class to create the employee object to be used with the timeclock

* */


public class Employee {

    private String name;
    private int pin;
    private double salary;

    public Employee() {
    }

    public Employee(String name, int pin, double salary) {
        this.name = name;
        this.pin = pin;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    @Override
    public String toString() {
        return "Name: " + this.name + "\nPin: " + this.pin + "\nSalary(Per Hour): " + this.salary;
    }


}

