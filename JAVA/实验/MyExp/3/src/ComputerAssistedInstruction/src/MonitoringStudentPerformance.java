//ѧϰ�˷�����3.0
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class MonitoringStudentPerformance{
    private Random randomGenerator = new Random();//�����������
  //�������ȷ�����ʱ���ܴ�ӡ�ķ���
    private ArrayList<String> success = new ArrayList<String>();
    private ArrayList<String> failure = new ArrayList<String>();

    private int[] values = new int[2];//�洢��λҪ��˵���
    private int score = 0;//����һ�ֵķ���
    
    //main����
    public static void main(String[] args){
        Scanner answer = new Scanner(System.in);
        MonitoringStudentPerformance round = new MonitoringStudentPerformance();
        int count = 0;
        char iscontinue = 'y';
        while(iscontinue == 'y'){
            //һ������Ϊ10��
            while(count != 10){
            	round.getQuestion();//��ӡ����
            	round.checkAnswer(answer.nextInt());
                ++count;
            }
            round.getResults();//��ӡ���
            System.out.print("Continue? (y/n) ");
            iscontinue = answer.next().charAt(0);//��������0����charֵ
            if(iscontinue == 'y') {
            	count=0;//�ѻش�����������û�0
            	round.reset();
            }
        }
        answer.close();//�ر�scanner   
    }
    // ���캯��
    public MonitoringStudentPerformance(){
        success.add("Very Good!");
        success.add("Excellent!");
        success.add("Nice Work!");
        success.add("Keep up the good work!");
        failure.add("No. Please try again.");
        failure.add("Wrong. Try once more.");
        failure.add("Don't give up.");
        failure.add("No. Keep trying.");
        setQuestion(); // �����µ��������������
    }
    // �����µ��������������
    private void setQuestion(){
        values[0] = randomGenerator.nextInt(9);
        values[1] = randomGenerator.nextInt(9);
    }
    // ��ӡ����
    public void getQuestion(){
        System.out.printf("How much is %d times %d? ", values[0], values[1]);
    }
    //��ӡ���
    public void getResults(){
        System.out.printf("You scored %d out of 10.\n", score);
        if(score >= (10 * 0.75)){
            System.out.println("Congratulations you are ready to go the next level!");
        }else{
            System.out.println("Please ask your teacher for extra help.");
        }
    }
    //���𰸣�������ɷ�Ӧ
    public void checkAnswer(int answer){
        if(answer == values[0] * values[1]){
            System.out.println(success.get(randomGenerator.nextInt(success.size())));
            setQuestion();//�����ȷ������������
            ++score;//������1
        }
        else System.out.println(failure.get(randomGenerator.nextInt(failure.size())));
    }
    // ���÷���������ʼ�µ�һ��
    public void reset(){
    	score = 0;
        setQuestion();
    }
}