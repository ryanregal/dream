package lab12_2;
import java.util.function.IntConsumer;

public class QuestionA {
	
	/*��дһ�����Դ��ݸ����� IntConsumer(�ӿ�) �����ķ����� lambda�� lambda Ӧ��ʾ����������һ���ո�*/	
	public void haveFun(IntConsumer fun,int value) {
    	fun.accept(value);
    }
	
	public static void main(String[] args) {
		QuestionA one=new QuestionA();
		//��������
		IntConsumer con= new IntConsumer() {
			public void accept(int value) {
				System.out.printf("%d ",value);
			}
		};
		//�����ͷ�����֮�����->	
		IntConsumer con1= value->System.out.printf("%d ",value);;
		one.haveFun(con,2);
		one.haveFun(con1,3);
		one.haveFun(value->{System.out.printf("%d ",value);},3);
	}
}
