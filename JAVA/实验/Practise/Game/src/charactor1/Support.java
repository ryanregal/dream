package charactor1;

import charactor.Hero;

//����
public class Support extends Hero implements Healer{
	@Override
	public void heal() {
		System.out.println(name+"����������");
	}
	public void heal(Hero h) {
		h.hp++;
		System.out.println(name+"��"+h.name+"����������");
	}
	public void heal(Hero h,int hp) {
		System.out.println(name+"��"+h.name+"����������");
		System.out.println("��Ѫ"+hp);
		h.hp+=hp;
	}
	/*static���εķ���������������ʵ������Ϳ��Ե��ø÷�������static�����Ǵ洢�ھ�̬�洢���ģ�����Ҫʵ������
	��static���εķ�������Ҫ����һ�����ʵ������ſ��Ե��ø÷�����main�Ǿ�̬����
	main�����е��ú���ֻ�ܵ��þ�̬�ġ����Ҫ���÷Ǿ�̬�ģ���ô����Ҫ��ʵ��������Ȼ��ͨ�����������÷Ǿ�̬������
	*/
	public static void main(String args[]) {
		Support witch=new Support();
		witch.name="Ů��";
		Hero one=new Hero();
		one.name="Ԥ�Լ�";one.hp=0;
		witch.heal(one);
		witch.heal(one,100);
	}
}
