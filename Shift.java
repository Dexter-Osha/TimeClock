public class Shift {

    private String type; //1 for full, 2 for half, 3 for absence
    private double timeWorked;
    private int employeePin;
    private String employeeName;

    public Shift(String name, int pin, String type, double hrs) {
        this.employeeName = name;
        this.employeePin = pin;
        this.type = type;
        this.timeWorked = hrs;
    }

    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type;
    }

    public double getTimeWorked() {
        return timeWorked;
    }

    public void setTimeWorked(double timeWorked) {
        this.timeWorked = timeWorked;
    }

    public int getEmployeePin() {
        return employeePin;
    }

    public void setEmployeePin(int employeePin) {
        this.employeePin = employeePin;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    @Override
    public String toString(){
        return this.employeeName + "\t" + this.employeePin + "\t" + this.type + "\t\t" +  this.timeWorked;
    }
}
