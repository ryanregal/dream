/*ʵ��ĳ���ӿڣ��൱�ڳ�ŵ��ĳ��Լ����
���ԣ�ʵ����AD����ӿڣ��ͱ����ṩAD�ӿ��������ķ���physicAttack()
ʵ�����﷨��ʹ�ùؼ��� implements*/

/*���๹�췽���ᱻ���ã��丸��Ĺ��췽��Ҳ�ᱻ����
�����Ǹ��๹�췽���ȵ��ã����๹�췽����Ĭ�ϵ��ø���� �޲εĹ��췽��*/

/*������û���޲ι��췽����ʱ��( �ṩ���вι��췽�������Ҳ���ʾ�ṩ�޲ι��췽��)������ͻ��׳��쳣����Ϊ������ȥ���ø�����޲ι��췽����
���ʱ�򣬱���ͨ��superȥ���ø��������ġ����ڵġ��вεĹ��췽��*/

package charactor;
import property.Item;

//����
public class ADHero extends Hero implements AD,Mortal{
	
	public void die() {
		System.out.println("AD��ȥ");
	}
	
	@Override
	public void physicAttack() {
		System.out.println(name+"������һ�ι���");
	}
	
	public void attack(Hero... hero) {
		for(Hero i:hero) {
			System.out.println(name+"������"+i.name);
		}
	}
	
    public int getMoveSpeed(){
        return this.moveSpeed;
    }
     
    public int getMoveSpeed2(){
        return super.moveSpeed;
    }
    
    int moveSpeed=400; //�ƶ��ٶ�
	
	public ADHero() {}
	public ADHero(String heroname,float heroHP,float heroArmor,int heroMoveSpeed) {
		super(heroname,heroHP,heroArmor,heroMoveSpeed);//ʹ�ùؼ���super ��ʽ���ø�����εĹ��췽��
		System.out.println("AD Hero�Ĺ��췽��");
	}
	
    // ��дuseItem���������е��ø����userItem����
    public void useItem(Item i) {
        System.out.println("adhero use item");
        super.useItem(i);
    }
	
	public static void main(String[]args) {
		//new ADHero();
		ADHero bh=new ADHero();
		bh.name="�ͽ�����";
		Hero h1=new Hero();
		h1.name="����";
		Hero h2 =new Hero();
		h2.name="��Ī";
		bh.attack();
		bh.attack(h1);
		bh.attack(h1,h2);
        System.out.println(bh.getMoveSpeed());//ADHeroҲ�ṩ������moveSpeed
        System.out.println(bh.getMoveSpeed2());//ͨ��super���ø����moveSpeed����
        Item i=new Item();
        bh.useItem(i);
	}
}
