
public class Cat extends Animal implements Pet{
	
	private String name;
	
	public Cat(String name,int legs) {
		super(legs);
		this.name=name;	
	}
	public Cat() {
		super();
	}
	public void eat() {
		System.out.println("è�Ե���");
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name=name;
	}
	public void play() {
		System.out.println("èè�棡");
	}
	
}
