/*������ָ����������һ�����ͬʱʵ��������ʹ������Ӽ�ྫ��
ͨ������£�Ҫʹ��һ���ӿڻ��߳����࣬�����봴��һ������

�е�ʱ��Ϊ�˿���ʹ�ã�ֱ��ʵ����һ�������࣬����������ʵ������󷽷���
��Ȼʵ���˳��󷽷�����ô����һ���µ��ֻ࣬������࣬û��������
�������࣬����������*/

package property;

public abstract class Anonymous {

	 String name;
	 
	 public abstract boolean disposable();//���󷽷�
	 
	 public static void main(String[] args) {
	    Anonymous test=new Anonymous() {
	    	public boolean disposable() {//����Ͳ��ǳ��󷽷���
	    		return true;
	    	}
	    };
	    System.out.println(test.disposable());
	 }
}
