
public class Fish extends Animal implements Pet{
	
	private String name;
	public void walk() {
		System.out.println("�㲻������·��");
	}
	public Fish() {
		super();
	}
	public void eat() {
		System.out.println("��ԣ�");
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name=name;
	}
	public void play() {
		System.out.println("���棡");
	}
}
