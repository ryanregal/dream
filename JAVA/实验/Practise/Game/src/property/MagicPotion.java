package property;

public class MagicPotion extends Item{

	@Override
	public void effect() {
		 System.out.println("��ƿʹ�ú󣬿��Ի�ħ");
	}
    public static void main(String[] args) {
        MagicPotion magicPotion = new MagicPotion();
        magicPotion.effect();
    }
}
