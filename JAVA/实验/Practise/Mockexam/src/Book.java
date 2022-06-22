
public class Book extends Item{
	
	private String author;//����
	
	public Book(String[] inputs) {
		super(inputs);
		this.author=inputs[3];
		this.setPublisher(inputs[4]);
	}

	//Getters Setters
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	//��д�鼮���
	@Override
	public void printItem()
	{
		System.out.printf("%s����ţ�%s ISBN��%s ������%s", getType(), getNumber(),
				getIsbn(), getName());
		System.out.printf("  ���ߣ�%s  �����磺%s  ����״̬��%s\n",  
				getAuthor(), getPublisher(),getStatus());
	}
}
