//ѧϰ�˷�����1.0
import java.util.Scanner;
import java.util.Random;

public class ComputerAssistedInstruction{
	
    private static Random randomGenerator = new Random();//�����������
    //main����
    public static void main(String[] args){
        Scanner answer = new Scanner(System.in);
        int[] digits = new int[2];//�洢��λҪ��˵���
        char iscontinue = 'y';

        while(iscontinue == 'y'){//���û�����y��ʾҪ��������
            digits = newQuestion();//�����鴢��õ���������
            while(true){
            	//�������
                System.out.printf("How much is %d times %d? ", digits[0], digits[1]);
                if(checkAnswer(digits[0], digits[1], answer.nextInt())){
                    System.out.println("Very good!");//�ش���ȷ
                    break;
                }else{
                    System.out.println("No. Please try again.");//�ش����
                }
            }
            System.out.print("Continue? (y/n) ");
            iscontinue = answer.next().charAt(0);//��������0����charֵ
        }
        answer.close();//�ر�scanner
    }
    
    //ʹ��һ�������ķ���������ÿ���µ�����
    public static int[] newQuestion(){
    	//���������������һλ������
        int[] tmp = {randomGenerator.nextInt(9), randomGenerator.nextInt(9)};
        return tmp;
    }
    
    // ����
    public static boolean checkAnswer(int x, int y, int answer){
        return (answer == x * y) ? true : false;
    }
}