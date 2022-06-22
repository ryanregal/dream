
public abstract class Item {
	private String number;//���
	private String isbn;
	private String name;//����
	private String status;//����״̬
	private String publisher;//������
	private String type;//��־������
	
	//���캯��
	public Item(String[] inputs)
	{
		this.type = inputs[0];
		this.isbn = inputs[1];
		this.name = inputs[2];
		this.status="δ���";
	}
	
	//��ӡ�鼮
	public abstract void printItem();
	
	//Getters Setters
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
