package charactor;
/*һ��Ӣ�۹�����һ��Ӣ�۵�ʱ�����������һ��Ӣ���Ѿ����ˣ�
 * �ͻ��׳�EnemyHeroIsDeadException*/

/*����һ����EnemyHeroIsDeadException*/

public class EnemyHeroIsDeadException extends Exception{
	public EnemyHeroIsDeadException() {
	}
	//���εĹ��췽���������ø���Ķ�Ӧ�Ĺ��췽��
	public EnemyHeroIsDeadException(String msg) {
		super(msg);
	}
}
