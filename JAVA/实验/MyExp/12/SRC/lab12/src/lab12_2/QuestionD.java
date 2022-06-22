package lab12_2;
import java.util.function.DoubleUnaryOperator;

public class QuestionD {

	public void getAnswer(DoubleUnaryOperator D,double input) {
    	System.out.println(D.applyAsDouble(input));
    }
	
	public static void main(String[] args) {
		QuestionD d=new QuestionD();
		
		//���� UnaryOperator �� double ��ԭʼ�����ػ���
		//����һ�������ӿڣ��亯�������� applyAsDouble(double)��
		DoubleUnaryOperator sqrt1=a->Math.sqrt(a);
		DoubleUnaryOperator sqrt2=Math::sqrt;
		d.getAnswer(sqrt1, 4.0);
		d.getAnswer(sqrt2, 3.0);
	}	
}
