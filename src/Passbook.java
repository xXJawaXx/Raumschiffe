import static java.lang.Math.pow;

public class Passbook {
    private int accountNumber;
    private double capital;
    private double interestRate;

    public Passbook(int accountNumber, double capital, double interestRate){
        this.accountNumber = accountNumber;
        this.capital = capital;
        this.interestRate = interestRate;
    }

    public void payIn(double value){
        this.capital += value;
    }

    public void payOut(double value){
        this.capital -= value;
    }

    public double getEarnings(int years){
        return this.capital * pow((1 + interestRate/100), years);
    }

    public void interest(){
        this.capital *= (1 + interestRate/100);
    }

    public int getAccountNumber(){
        return this.accountNumber;
    }

    public double getCapital(){
        return this.capital;
    }

    public double getInterestRate(){
        return this.interestRate;
    }
}
