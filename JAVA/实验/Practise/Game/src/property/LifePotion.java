package property;


public class LifePotion extends Item{

	@Override
	public void effect() {
		 System.out.println("Ѫƿʹ�ú󣬿��Ի�Ѫ");
	}
	
	public static void main(String[] args) {
		Item i=new Item();
		i.effect();
		LifePotion lp =new LifePotion();
		lp.effect();
		lp.buy();
	}
}
