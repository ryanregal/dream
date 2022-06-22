//ѧϰ�˷�����2.0
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class ReducingStudentFatigue{
    private static Random randomGenerator = new Random();//�����������
    //�������ȷ�����ʱ���ܴ�ӡ�ķ���
    private static ArrayList<String> success = new ArrayList<String>();
    private static ArrayList<String> failure = new ArrayList<String>();
    //main����
    public static void main(String[] args){
        Scanner answer = new Scanner(System.in);
        int[] digits = new int[2];//�洢��λҪ��˵���
        char iscontinue = 'y';
        success.add("Very Good!");
        success.add("Excellent!");
        success.add("Nice Work!");
        success.add("Keep up the good work!");
        failure.add("No. Please try again.");
        failure.add("Wrong. Try once more.");
        failure.add("Don't give up.");
        failure.add("No. Keep trying.");

        while(iscontinue == 'y'){//���û�����y��ʾҪ��������
            digits = newQuestion();//�����鴢��õ���������
            while(true){
            	//�������
                System.out.printf("How much is %d times %d? ", digits[0], digits[1]);
                if(checkAnswer(digits[0], digits[1], answer.nextInt())){
                    printResponse("success");//�ش���ȷ������������
                    break;
                }
                else  printResponse("failure");//�ش������������ο
                
            }
            System.out.print("Continue? (y/n) ");
            iscontinue= answer.next().charAt(0);//��������0����charֵ
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
    // ���������ѡ��ظ�����ӡ������̨
    public static void printResponse(String condition){
        switch(condition){
            case "success":
                System.out.println(success.get(randomGenerator.nextInt(success.size())));
                break;
            case "failure":
                System.out.println(failure.get(randomGenerator.nextInt(failure.size())));
                break;
            default:
                break;
        }
    }
}