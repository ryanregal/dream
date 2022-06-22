package Final;

public class CheckingAccount extends Account{
	
	private double overdraftProtection;
 
	public CheckingAccount(double balance) {
        super(balance);
    }
    public CheckingAccount(double balance, double overdraftProtection) {
        super(balance);
 
        this.overdraftProtection = overdraftProtection;
    }
    public void withdraw(double withdraw) throws OverdraftException {
        if (withdraw > this.getBalance() + overdraftProtection) {
            double deficit = withdraw - (this.getBalance() + overdraftProtection);
            throw new OverdraftException("͸֧��ȳ���", deficit);
        }
        this.setBalance(this.getBalance() - withdraw);
    }
    
    public static void main(String[] args) {
        //��������1000�飬ӵ��500��͸֧���
        CheckingAccount a = new CheckingAccount(1000, 500);
        System.out.println(a.getBalance());
        try {
            a.withdraw(1600);
            System.out.println(a.getBalance());
        } catch (OverdraftException e) {
            System.err.println("͸֧����:"+e.getDeficit());
            e.printStackTrace();
        }
    }
}
