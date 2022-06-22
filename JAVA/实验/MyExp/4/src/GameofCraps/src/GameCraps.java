import java.security.SecureRandom;
import java.util.Scanner;
 
	public class GameCraps 
	{
	   // create secure random number generator for use in method rollDice
	   private static final SecureRandom randomNumbers = new SecureRandom();
 
	   // enum type with constants that represent the game status
	   private enum Status {CONTINUE, WON, LOST};
 
	   // constants that represent common rolls of the dice
	   private static final int SNAKE_EYES = 2;
	   private static final int TREY = 3;
	   private static final int SEVEN = 7;
	   private static final int YO_LEVEN = 11;
	   private static final int BOX_CARS = 12;
	   
	   private static int winTimes = 0; //��Ϸʤ���Ĵ���
	   private static int stepsTillWinTotal = 0; //Ӯ����Ϸ�����ܲ���
	   private static int stepsTillLoseTotal = 0;//�����Ϸ�����ܲ���
	   private static int stepsContinueTotal = 0;
	   private static int stepsContinueSinglePlay = 0;
	   private static double winPercentage = 0.0; //Ӯ����Ϸ�ĸ���
	   private static double averageLength = 0.0; //ƽ��ÿ����Ϸ���ò���
	   private static int[] stepsTillWinFrequency = new int[21]; //��x����Ӯ����
	   private static int[] stepsTillLoseFrequency = new int[21]; //��y����Ӯ����
	   
	   public static void main(String[] args)
	   {
			int playTimes=0;
			Scanner input=new Scanner(System.in);
			
			do{
				initialize(); //��ʼ��
				System.out.println("---------------Start--------------------");
				System.out.print("��������Ϸ����������0��ʾ�˳�����");
				playTimes =input.nextInt();
				if(playTimes ==0){
					System.out.printf("��Ϸ����");
					break;
				}
					
				for (int i=0;i<playTimes; i++) {
					playCraps();   //ģ����Ϸ
				}	
				//����ͳ��ֵ
				winPercentage = (double)winTimes/playTimes*100;
				averageLength = (double)(stepsTillWinTotal + stepsTillLoseTotal)/playTimes;
				
				System.out.printf("��Ϸ���й�%d�֣�%n", playTimes);
				System.out.printf("��Ӯ����Ϸ%d�֣���ʤ�ĸ���Ϊ��%.2f%%%n", winTimes, winPercentage);
				System.out.printf("%nƽ��ÿ����Ϸ���ò�����%.2f%n", averageLength);
				System.out.printf("Ӯ����Ϸ�����ܲ�����%d%n", stepsTillWinTotal);
				System.out.printf("�����Ϸ�����ܲ�����%d%n", stepsTillLoseTotal);
				System.out.printf("%nӮ��/���һ����Ϸ���ò����ֲ����£�%n%n");
				for (int i=1; i<20; i++) {
					System.out.printf("��%2d����\tӮ����Ϸ��%6d��    �����Ϸ��%6d��\t%n",i,stepsTillWinFrequency[i],
							stepsTillLoseFrequency[i]);
				}
				System.out.printf("%2d���ϣ�\tӮ����Ϸ��%6d��    �����Ϸ��%6d��\t%n",20,stepsTillWinFrequency[20],
						stepsTillLoseFrequency[20]);
			}while (playTimes != -1);
			input.close();
	   }
 
	   // plays games of craps
	   public static void playCraps()
	   {
		  stepsContinueSinglePlay = 0; //ÿ����Ϸ��ʼʱ����Ϊ0
	      int myPoint = 0; // point if no win or loss on first roll
	      Status gameStatus; // can contain CONTINUE, WON or LOST
 
	      int sumOfDice = rollDice(); // first roll of the dice
 
	      // determine game status and point based on first roll 
	      switch (sumOfDice) 
	      {
	         case SEVEN: // win with 7 on first roll
	         case YO_LEVEN: // win with 11 on first roll           
	            gameStatus = Status.WON;
	            ++stepsTillWinTotal;//Ӯ����Ϸ���ò�����1
	            ++stepsTillWinFrequency[1];//Ӯ�ֵ���Ϸ������1
	            break;
	         case SNAKE_EYES: // lose with 2 on first roll
	         case TREY: // lose with 3 on first roll
	         case BOX_CARS: // lose with 12 on first roll
	            gameStatus = Status.LOST;
	            ++stepsTillLoseTotal;//�����Ϸ���ò�����1
	            ++stepsTillLoseFrequency[1];//��ֵ���Ϸ������1
	            break;
	         default: // did not win or lose, so remember point         
	            gameStatus = Status.CONTINUE; // game is not over
	            myPoint = sumOfDice; // remember the point
	            ++stepsContinueTotal;
	     	    ++stepsContinueSinglePlay;
	            break;
	      } 
 
	      // while game is not complete
	      while (gameStatus == Status.CONTINUE) // not WON or LOST
	      { 
	         sumOfDice = rollDice(); // roll dice again
 
	         // determine game status
	         if (sumOfDice == myPoint) { // win by making point
	        	gameStatus = Status.WON;
	        	 ++stepsTillWinTotal; //Ӯ�ֲ���+1
	        	//�������20������ȫ��������20�±괦
	        	 ++stepsTillWinFrequency[++stepsContinueSinglePlay<20 ? stepsContinueSinglePlay: 20];
	         }
	         else 
	            if (sumOfDice == SEVEN) {// lose by rolling 7 before point
	               gameStatus = Status.LOST;
	               ++stepsTillLoseTotal;//��ֲ���+1
	             //�������20������ȫ��������20�±괦
	               ++stepsTillLoseFrequency[++stepsContinueSinglePlay<20 ? stepsContinueSinglePlay: 20];
	            }
	            else{
	            	++stepsContinueTotal;//������Ϸ����+1
	            	++stepsContinueSinglePlay;
	            }
	      } 
 
	      // display won or lost message
	      if (gameStatus == Status.WON) {
	         stepsTillWinTotal +=stepsContinueTotal; //�������ʤ����continue״̬�ļ�����������Ӯ����Ϸ���貽��
	         stepsContinueTotal = 0; //������Ϸ������continue״̬�ļ�������0
	         ++winTimes;
	      }
	      else {
	         stepsTillLoseTotal +=stepsContinueTotal; //����������ˣ�continue״̬�ļ��������㵽�����Ϸ���貽��
	         stepsContinueTotal = 0; //������Ϸ������continue״̬�ļ�������0
	      }
	     
	   }
 
	   // roll dice, calculate sum and display results
	   public static int rollDice()
	   {
	      // pick random die values
	      int die1 = 1 + randomNumbers.nextInt(6); // first die roll
	      int die2 = 1 + randomNumbers.nextInt(6); // second die roll
	      int sum = die1 + die2; // sum of die values
	      return sum; 
	   }
	   
	   public static void initialize()
	   {
		 //ȫ�ֱ�������
		   winTimes = 0; 
		   stepsTillWinTotal = 0; 
		   stepsTillLoseTotal = 0;
		   stepsContinueTotal = 0;
 
		   for (int i=0;i<21;i++){
			  stepsTillWinFrequency[i] = 0; 
			  stepsTillLoseFrequency[i] = 0;
		   }
	   }
	} 