
public abstract class Animal {
	protected int legs;//�����ȵ���Ŀ
	
	protected Animal(int legs) {
		this.legs=legs;
		System.out.println("�ȵ�������"+legs);
	}
	
    public Animal() {
        System.out.println("�������û����");
    }
	
	public abstract void eat() ;
	
	public void walk() {
		System.out.println("������"+legs+"��");
	}
	
}
