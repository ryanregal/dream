package lab12_2;
import java.util.function.IntUnaryOperator;

public class QuestionE {

	public void getAnswer(IntUnaryOperator D,int input) {
    	System.out.println(D.applyAsInt(input));
    }
	public static void main(String[] args) {
		QuestionE e=new QuestionE();
		//���� UnaryOperator ��  Integer ��ԭʼ�����ػ���
		//����һ�������ӿڣ��亯�������� applyAsInt(Int)��
		IntUnaryOperator cube1=a->a*a*a;
		e.getAnswer(cube1, 4);
		e.getAnswer(cube1, 3);
	}	
}
