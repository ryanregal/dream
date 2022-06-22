// CommissionEmployee ��ʹ�÷���������˽��ʵ������

public class CommissionEmployee {
	private final String firstName;
	private final String lastName;
	private final String socialSecurityNumber;
	private double grossSales; // ÿ�������۶�
	private double commissionRate; // Ӷ��ٷֱ�

	//���������캯��
	public CommissionEmployee(String firstName, String lastName, String socialSecurityNumber, double grossSales,
			double commissionRate) {
		// ���캯������ʽ����
		// ��� GrossSales ��Ч���׳��쳣
		if (grossSales < 0.0) {
			throw new IllegalArgumentException("Gross sales must be >= 0.0");
		}
		// ���commissionRate ��Ч���׳��쳣 
		if (commissionRate <= 0.0 || commissionRate >= 1.0) {
			throw new IllegalArgumentException("Commission rate must be > 0.0 and < 1.0");
		}
		this.firstName = firstName;
		this.lastName = lastName;
		this.socialSecurityNumber = socialSecurityNumber;
		this.grossSales = grossSales;
		this.commissionRate = commissionRate;
	}

	// Getters
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}
	public double getGrossSales() {
		return grossSales;
	}
	public double getCommissionRate() {
		return commissionRate;
	}
	
	// Setters
	public void setGrossSales(double grossSales) {
		if (grossSales < 0.0) {
			throw new IllegalArgumentException("Gross sales must be >= 0.0");
		}
		this.grossSales = grossSales;
	}
	public void setCommissionRate(double commissionRate) {
		if (commissionRate <= 0.0 || commissionRate >= 1.0) {
			throw new IllegalArgumentException("Commission rate must be > 0.0 and < 1.0");
		}
		this.commissionRate = commissionRate;
	}

	// calculate earnings
	public double earnings() {
		return getCommissionRate() * getGrossSales();
	}

	// return String representation of CommissionEmployee object
	@Override
	public String toString() {
		return String.format("%s: %s %s%n%s: %s%n%s: %.2f%n%s: %.2f", "commission employee", getFirstName(),
				getLastName(), "social security number", getSocialSecurityNumber(), "gross sales", getGrossSales(),
				"commission rate", getCommissionRate());
	}
}
