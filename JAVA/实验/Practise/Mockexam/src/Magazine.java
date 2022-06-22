
public class Magazine extends Item{
	
	public Magazine(String[] inputs) {
		super(inputs);
		this.year=Integer.parseInt(inputs[3]);
		this.issueNumber=inputs[4];
		this.setPublisher(inputs[5]);
	}

	private int year;//������
	private String issueNumber;//�����ں�
	
	//Getters Setters
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getIssueNumber() {
		return issueNumber;
	}
	public void setIssueNumber(String issueNumber) {
		this.issueNumber = issueNumber;
	}
	
	//��д��־���
	@Override
	public void printItem()
	{
		System.out.printf("%s����ţ�%s ISBN��%s ��־����%s", getType(), getNumber(),
				getIsbn(), getName());
		System.out.printf("  �����꣺%s  �ںţ�%s  �����磺%s  ����״̬��%s\n",  
				getYear(), getIssueNumber(),getPublisher(),getStatus());
	}
	
}
