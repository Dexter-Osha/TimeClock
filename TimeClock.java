import java.util.*;
import java.util.ArrayList;



public class TimeClock{

    public static void main(String[] args) {
        //Track all employees
        ArrayList<Employee> employees = new ArrayList<>();
        //Track shifts worked
        ArrayList<Shift> shifts = new ArrayList<>();
        //Track employees on the clock
        ArrayList<Employee> onTheClock = new ArrayList<>();
        //Make object out of class
        TimeClock object = new TimeClock();
        //Variables
        int choice, shiftChoice, payChoice, removeChoice;
        String switchChoice;
        double hoursWorked;
        Scanner text = new Scanner(System.in);
        Scanner nums = new Scanner(System.in);

        //You can uncomment this code to add multiple people so you don't have to go through the process of creating multiple to test all functions
        //Sample Data
        //Employee newPerson = new Employee("Dexter", 5995, 25.0);
        //Employee newPerson2 = new Employee("Dexter", 1234, 25.0);
        //employees.add(newPerson);
        //employees.add(newPerson2);
        //employees.add(newPerson3);

        //Shift shift = new Shift("Dexter",5995, "Full-Shift", 12);
        //Shift shift2 = new Shift("Dexter",5995, "Full-Shift", 23);
        //Shift shift3 = new Shift("Dexter", 1234, "Full-Shift", 12);
        //shifts.add(shift);
        //shifts.add(shift2);
        //shifts.add(shift3);

        choice = 0;

        do {
            if(employees.size() == 0){
                System.out.println("No employees are registered on this timeclock, please register yourself now \n");
                employees.add(object.addEmployee());
            }
            object.displayClock();
            System.out.println("Please select an option:");
            String tmpChoice = nums.nextLine();
            while(!object.isPinCorrect(tmpChoice)){
                System.out.println("Invalid choice, please ensure choice is a number and between One and Five");
                tmpChoice = nums.nextLine();
            }
            choice = Integer.parseInt(tmpChoice);
            while(choice < 1 || choice > 7){
                System.out.println("Invalid choice, please ensure choice is a number and between One and Five");
                choice = nums.nextInt();
            }

            switch (choice) {
                case 1: //Clock-In/Out
                    System.out.println("1. Clock-in\n2. Clock out");
                    switchChoice = text.nextLine();
                    shiftChoice = object.switchCaseValidation(switchChoice);
                    if(shiftChoice == 1) {
                        object.clockIn(employees,onTheClock);
                    }else{
                        object.clockOut(onTheClock, shifts);
                    }
                    break;

                case 2: //View pay and shifts worked
                    System.out.println("1. View Pay\n2. Detailed Shift View");
                    switchChoice = text.nextLine();
                    payChoice = object.switchCaseValidation(switchChoice);
                    if(payChoice == 1){
                        object.payStub(employees, shifts);
                    }else{ //Display all shifts worked for employee in detailed view
                        object.displayShifts(employees, shifts, onTheClock);
                    }
                    break;

                case 3: //View all current employees
                    object.displayStaff(employees);
                    break;

                case 4: //Add employees to timeclock
                    System.out.println("1. Add Employee\n2. Remove Employee/Shifts");
                    switchChoice = text.nextLine();
                    removeChoice = object.switchCaseValidation(switchChoice);
                    if(removeChoice == 1) {
                        employees.add(object.addEmployee());
                    }else{  //Remove employees and their corresponding shifts
                        object.removeEmployee(employees, shifts);
                    }
                    break;

                default: //Exit
                    System.out.println("\n=======================");
                    System.out.println("Thanks for using the timeclock!!");
                    System.out.println("=======================");
            }

        }while(choice != 5);

    }
//!!------------------- END OF FRONTEND SECTION OF PROGRAM -------------------!!

//Void Function to display the timeclock "UI"
    public void displayClock(){
        System.out.println("=======================");
        System.out.println("1. Clock-in/Out \n2. View Pay Stub/Shifts Worked \n3. View Employees \n4. Add/Edit Employee\n5. Exit");
        System.out.println("=======================");

    }
//Function to display employees
    public void displayStaff(ArrayList<Employee> e){

        if(e.size() != 0) {
            System.out.println("=====================\n");
            for (int i = 0; i < e.size(); i++) {
                System.out.println(e.get(i).toString());
                System.out.println();
            }
            System.out.println("=====================\n");
        }
        else{
            System.out.println("\n==========================================");
            System.out.println("No employees registered on this timeclock!");
            System.out.println("==========================================\n");
        }
    }

//Function to allow user to add new employee
    public Employee addEmployee(){
        String numericRegex = "[0-9]+";
        Scanner t1 = new Scanner(System.in);
        Scanner nu = new Scanner(System.in);
        System.out.println("Enter Employee's name:" );
        String n = t1.nextLine();
            while(!isStringOnlyAlphabet(n)){
                System.out.println("Employee's name must be in english characters only, please try again");
                n = t1.nextLine();
            }

        System.out.println("Enter Employee's four digit pin: " );
        String string = t1.nextLine();
        while(!isPinCorrect(string)){
                System.out.println("Incorrect pin format, numbers only in format of XXXX");
                string = t1.nextLine();
            }
        int p = Integer.parseInt(string);

        System.out.println("Enter Employee's hourly wage");
        string = t1.nextLine();
            while(!string.matches(numericRegex)){
                System.out.println("Incorrect wage format, numbers only in format of XX");
                string = t1.nextLine();
            }
        double w = Double.parseDouble(string);

        Employee tmp = new Employee(n, p, w);
        return tmp;
    }

//Function to allow user to add shift for employee
    public void clockOut(ArrayList<Employee> onTheClock, ArrayList<Shift> s){

        Scanner nums = new Scanner(System.in);
        String tmpName = "";
        String shiftName = "";
        Employee tmp = new Employee();
       if(onTheClock.size() != 0) {
           System.out.println("Enter employee's Pin for shift you're logging");
           String p = nums.nextLine();
           while(!isPinCorrect(p)){
               System.out.println("Incorrect pin format, numbers only in format of XXXX");
               p = nums.nextLine();
           }
            int pin = Integer.parseInt(p);
           while (!findEmployee(onTheClock, pin)) {
               System.out.println("Sorry that employee isn't registered in this timeclock. Please enter Pin again");
               pin = nums.nextInt();
           }
               System.out.println("How many hours did you work?");
               String hrsWorked = nums.nextLine();
               while(!isPinCorrect(hrsWorked)){
                   System.out.println("Sorry that is the incorrect format for inputing hours worked. \nPlease enter hours worked in numbers only in the format of XX");
                   hrsWorked = nums.nextLine();
               }
               double hoursWorked = Double.parseDouble(hrsWorked);
                for(int i = 0; i < onTheClock.size(); i++){
                    if(onTheClock.get(i).getPin() == pin){
                        tmpName = onTheClock.get(i).getName();
                        tmp = onTheClock.get(i);
                        onTheClock.remove(tmp);
                    }
                }
                shiftName = determineShift(hoursWorked);
               Shift newShift = new Shift(tmpName, pin, shiftName, hoursWorked);
               s.add(newShift);
                System.out.printf("Shift Added!\n\n");

       } else{
           System.out.println("\n==========================================");
           System.out.println("No employees are currently on the clock!!");
           System.out.println("==========================================\n");
       }
    }

//Function to clock in
    public void clockIn(ArrayList<Employee> e, ArrayList<Employee> o){
        Scanner scn = new Scanner(System.in);
        Employee tmp = new Employee();
        if(e.size() != 0){ //make sure there are employee's registered on timeclock
            System.out.println("Enter your four digit pin ");
                String p = scn.nextLine();
                while(!isPinCorrect(p)){
                    System.out.println("Incorrect pin format, numbers only in format of XXXX");
                    p = scn.nextLine();
                }
                int pin = Integer.parseInt(p);
                for(int i = 0; i < e.size(); i++){
                    if(e.get(i).getPin() == pin){ //if the employee with corresponding pin is registered on timeclock
                        tmp = e.get(i);
                        o.add(tmp);
                        System.out.println("Clocked in! Get to work!!");
                        break;
                    }
                }
        }else{
            System.out.println("\n==========================================");
            System.out.println("No employees registered on this timeclock!");
            System.out.println("==========================================\n");
        }
    }

//Function to allow user to delete employee
    public void removeEmployee(ArrayList<Employee> e, ArrayList<Shift> s){
        Scanner t2 = new Scanner(System.in);
        String numericRegex = "[0-9]+";

        if(e.size() != 0) { //only allow user to remove employee's if there are any to remove
            System.out.println("Enter Employee's Pin:");
                String string = t2.nextLine();
                while(!isPinCorrect(string)) {
                    System.out.println("Incorrect pin format, numbers only in format of XXXX");
                    string = t2.nextLine();
            }
            int pin = Integer.parseInt(string);
            if (findEmployee(e, pin)) { //if the employee in question is found inside the employees array list
                for (int i = 0; i < e.size(); i++) { //iterate through the list to find the employee to be removed
                    if (e.get(i).getPin() == pin) { //check each object to see if the name is a match
                        e.remove(e.get(i)); //remove the employee from the list
                        System.out.println("Employee and any of their recorded shifts have been removed");
                    }
                }
                for (int j = 0; j < s.size(); j++) { //iterate through shift array list to find all of employee's shifts
                    if (s.get(j).getEmployeePin() == pin) { //if any shifts have been worked by employee
                        s.remove(s.get(j)); //remove them
                    }
                }
            } else { //if the employee isn't found in the array list at all
                System.out.println("Sorry that employee isn't registered in this timeclock");
            }
        } else{ //if there are no employee's registered on the timeclock at all
            System.out.println("\n==========================================");
            System.out.println("No employees registered on this timeclock!");
            System.out.println("==========================================\n");
        }

    }

//Function to search arraylist for employee
    public boolean findEmployee(ArrayList<Employee> e, int pin){
        for(int i = 0; i < e.size(); i++){
            if (e.get(i).getPin() == pin){
                return true;
            }
        }
        return false;
    }


//Function to display all shifts recorded on timeclock
    public void payStub(ArrayList<Employee> e, ArrayList<Shift> s){
        Scanner t3 = new Scanner(System.in);
        double hrsWorked = 0.0;
        double totalPay = 0.0;
        if(s.size() != 0) { //Check if there are any employee's to even look up shifts/total pay for
            System.out.println("Which employee's pay stub are you looking for? \nEnter four digit pin: ");
               String p = t3.nextLine();
               while(!isPinCorrect(p)){
                   System.out.println("Incorrect pin format, numbers only in format of XXXX");
                   p = t3.nextLine();
               }
               int pin = Integer.parseInt(p);
            System.out.println("\n=====================");
            System.out.println("Name\tHours Worked");
            for (int i = 0; i < s.size(); i++) { //iterate through shifts registered on time-clock
                if (s.get(i).getEmployeePin() == pin) { //if the employee being looked up has any shifts on the clock
                        //for(int j = 0; j < s.size(); j++) { //Iterate through all the shifts worked by the employee
                            //if(s.get(j).getEmployeePin() == pin) {
                                System.out.println(s.get(i).getEmployeeName() + "\t" + s.get(i).getTimeWorked());
                                hrsWorked += s.get(i).getTimeWorked(); //save the amount of hours that have been worked
                            //}
                        //}
                    //totalPay = (e.get(i).getSalary() * hrsWorked);
                    //hrsWorked = 0.0; //reset the hrsWorked variable after each query to make sure each employee's pay is accurate
                    //break; //break out of loops to ensure output is correct
                }
            }
            for (int j = 0; j < e.size(); j++){
                totalPay = (e.get(j).getSalary() * hrsWorked);
            }
            System.out.printf("Total pay: %.2f", totalPay);
            System.out.println("\n=====================");
        }
        else{
            System.out.println("\n==================================================");
            System.out.println("No shifts registered on this timeclock!");
            System.out.println("==================================================\n");
        }
    }

//Function to display detailed information about shifts worked
    public void displayShifts(ArrayList<Employee> e, ArrayList<Shift> s, ArrayList<Employee> o) {
        Scanner t4 = new Scanner(System.in);
        double hrsWorked = 0.0;
        if(s.size() != 0) { //Check if there are any shifts to look up
            System.out.println("Which employee's shifts are you looking for? \nEnter four digit pin: ");
            String p = t4.nextLine();
            while(!isPinCorrect(p)){
                System.out.println("Incorrect pin format, numbers only in format of XXXX");
                p = t4.nextLine();
            }
            int pin = Integer.parseInt(p);
            for (int i = 0; i < e.size(); i++) { //iterate through employee registered on timeclock
                if (e.get(i).getPin() == pin) { //if the employee being looked up has any shifts on the clock
                    System.out.println("\n=====================");
                    System.out.println("Name\tPin\t\tShift Type\tHours Worked");//if the pin that is being searched for is in the shifts array list, display that person's worked shifts


                    for (int j = 0; j < s.size(); j++) {    //iterate through the shifts that are recorded on the time clock for found employee
                        if(s.get(j).getEmployeePin() == pin){
                            hrsWorked += s.get(j).getTimeWorked(); //save the amount of hours that have been worked
                            System.out.println(s.get(j).toString());
                        }
                    }
                    System.out.println("\nTotal Hours Worked: " + hrsWorked);
                    System.out.println("=====================\n");
                }
            }
        }
        else{
            System.out.println("\n==========================================");
            System.out.println("No employees have worked any shifts!!!");
            System.out.println("==========================================\n");
        }
    }

//Function to quickly decide shift type
    public String determineShift(double num) {
        String tmp = "";
        if(num > 4.5) {
            tmp = "Full-Shift";
        } else if (num > 0.0) {
            tmp = "Half-Shift";
        } else {
            tmp = "Absence";
        }
        return tmp;
    }

//Function to ensure created employee's names can only be english letters, inspired by StackOverflow solution
    public boolean isStringOnlyAlphabet(String str)
    {

        return ((str != null) && (!str.equals(""))
                && (str.matches("^[a-zA-Z]*$")));
    }

//Cheeky way to ensure employee only enters numeric pin, found on StackOverflow
    public boolean isPinCorrect(String str){
        String numericRegex = "[0-9]+";
        return ((str != null) && (!str.equals(""))
                && (str.matches(numericRegex)));
    }

    public int switchCaseValidation(String s){
        int tmp;
        Scanner privateScan = new Scanner(System.in);
        while(!isPinCorrect(s)){
            System.out.println("Incorrect input, please only select one of the displayed options.");
                s = privateScan.nextLine();
        }
        tmp = Integer.parseInt(s);
        return tmp;
    }

}//!!----- END OF BACKEND -----!!.. duh
