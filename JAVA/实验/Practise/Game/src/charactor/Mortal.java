/*Ĭ�Ϸ�����JDK8�����ԣ�ָ���ǽӿ�Ҳ�����ṩ���巽���ˣ���������ǰ��ֻ���ṩ���󷽷�
Mortal ����ӿڣ�������һ��Ĭ�Ϸ��� revive�����������ʵ���壬���ұ�����Ϊ��default

�ܹ��ܺõ���չ�µ��࣬����������Ӱ��ԭ������*/

package charactor;

public interface Mortal {
	public void die();
	
    default public void revive() {
        System.out.println("��Ӣ�۸�����");
    }
}
