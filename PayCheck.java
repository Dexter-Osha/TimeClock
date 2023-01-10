public class PayCheck {

    private double total;
    private String payDay;

    public PayCheck(double total, String payDay) {
        this.total = total;
        this.payDay = payDay;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getPayDay() {
        return payDay;
    }

    public void setPayDay(String payDay) {
        this.payDay = payDay;
    }
}
