package lab14_3;
// Fig. 27.13: TicTacToeServer.java
// Server side of client/server Tic-Tac-Toe program.
import java.awt.BorderLayout;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.util.Formatter;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class TicTacToeServer extends JFrame 
{
   private String[] board = new String[ 9 ]; // ��������
   private JTextArea outputArea; //�����������
   private Player[] players; // �������
   private ServerSocket server; // �������׽�����ͻ�������
   private int currentPlayer; // ���ٵ�ǰ�ƶ������
   private final static int PLAYER_X = 0; //��һ����ҵĳ���
   private final static int PLAYER_O = 1; // �ڶ�����ҵĳ���
   private final static String[] MARKS = { "X", "O" }; // �������
   private ExecutorService runGame; //��һ�����
   private Lock gameLock; // ������Ϸ�Խ���ͬ��
   private Condition otherPlayerConnected; // �ȴ��������
   private Condition otherPlayerTurn; // �ȴ��ֵ��������
   
   // ���þ�����Ϸ����������ʾ��Ϣ�� GUI
   public TicTacToeServer()
   {
      super( "Tic-Tac-Toe Server" ); //���ڱ��⣬�̳���JFrame

      //Ϊÿ��������һ���̳߳�
      runGame = Executors.newFixedThreadPool( 2 );
      gameLock = new ReentrantLock(); // Ϊ��Ϸ������

      // condition variable for both players being connected
      //���condition���ڽ������ӽ׶�
      otherPlayerConnected = gameLock.newCondition();

      // condition variable for the other player's turn
      //���condition������ʽ���н׶�
      otherPlayerTurn = gameLock.newCondition();      

      for ( int i = 0; i < 9; i++ )
         board[ i ] = new String( "" ); //��ʼ������
      
      //�������������
      //Player�ǹؼ����ִ��룬�Ƕ������е��̣߳���client�򽻵�
      players = new Player[ 2 ]; // create array of players
      
      //Ĭ�ϵ�һ�����ΪX
      currentPlayer = PLAYER_X; // set current player to first player
 
      try
      {
    	  //�����Ϳͻ���ͨ�ŵ�socket
    	  //����ָ���� backlog �����������׽��ֲ�����󶨵�ָ���ı��ض˿ں�
         server = new ServerSocket( 12345, 2 ); // set up ServerSocket
      } 
      catch ( IOException ioException ) 
      {
         ioException.printStackTrace();
         System.exit( 1 );
      } 

      //�������һ���ı�����
      outputArea = new JTextArea(); // create JTextArea for output
      
      //��JTextArea��ӵ�JFrame������
      add( outputArea, BorderLayout.CENTER );
      outputArea.setText( "Server awaiting connections\n" );
      outputArea.setEditable(false);

      setSize( 300, 300 ); // ���ô��ڴ�С
      setVisible( true ); // show window
   } // end TicTacToeServer constructor

   
   // wait for two connections so game can be played
   //ServerTest���ȵ��ù��캯����Ȼ�����execute
   public void execute()
   {
      //�ȴ�ÿ���ͻ�������
	   //players.length = 2
      for ( int i = 0; i < players.length; i++ ) 
      {
         try // �ȴ����ӣ���������������ʼ����
         {
        	//server.accpet���Ϳͻ��˽������ӣ�����һ���µ��׽���
            players[ i ] = new Player( server.accept(), i );
            runGame.execute( players[ i ] ); // execute player runnable
         } 
         catch ( IOException ioException ) 
         {
            ioException.printStackTrace();
            System.exit( 1 );
         } 
      } 

      gameLock.lock(); // ������Ϸ��֪ͨ��� X ���߳�

      try
      {
         players[ PLAYER_X ].setSuspended( false ); // �ָ���� X
         otherPlayerConnected.signal(); // ������� X ���߳�
      } // end try
      finally
      {
         gameLock.unlock(); //����� X �����źź������Ϸ
      }
   }
   
   
   private void displayMessage( final String messageToDisplay )
   {
      SwingUtilities.invokeLater(
         new Runnable() 
         {
            public void run() // updates outputArea
            {
               outputArea.append( messageToDisplay ); // add message
            }
         }
      );
   }

   // determine if move is valid
   public boolean validateAndMove( int location, int player )
   {
      // while not current player, must wait for turn
      while ( player != currentPlayer ) 
      {
         gameLock.lock(); // lock game to wait for other player to go

         try 
         {
            otherPlayerTurn.await(); // wait for player's turn
         } // end try
         catch ( InterruptedException exception )
         {
            exception.printStackTrace();
         } // end catch
         finally
         {
            gameLock.unlock(); // unlock game after waiting
         } // end finally
      } // end while

      // if location not occupied, make move
      if ( !isOccupied( location ) )
      {
         board[ location ] = MARKS[ currentPlayer ]; // set move on board
         
         //���������0,��0+1��%2 = 1�����л�����1
         //���������1����1+1��%2 = 0�����л�����0
         currentPlayer = ( currentPlayer + 1 ) % 2; // change player

         // let new current player know that move occurred
         players[ currentPlayer ].otherPlayerMoved( location );

         gameLock.lock(); // lock game to signal other player to go

         try 
         {
            otherPlayerTurn.signal(); // signal other player to continue
         } // end try
         finally
         {
            gameLock.unlock(); // unlock game after signaling
         } // end finally

         return true; // notify player that move was valid
      } // end if
      else // move was not valid
         return false; // notify player that move was invalid
   } // end method validateAndMove

   // determine whether location is occupied
   public boolean isOccupied( int location )
   {
      if ( board[ location ].equals( MARKS[ PLAYER_X ] ) || 
         board [ location ].equals( MARKS[ PLAYER_O ] ) )
         return true; // location is occupied
      else
         return false; // location is not occupied
   } // end method isOccupied

   // place code in this method to determine whether game over 
   public boolean isGameOver()
   {
	   return
			   (!board[0].equals("") && board[0].equals(board[1]) && board[0].equals(board[2]))
	              ||(!board[3].equals("") && board[3].equals(board[4]) && board[3].equals(board[5]))
	              ||(!board[6].equals("") && board[6].equals(board[7]) && board[6].equals(board[8]))
	              ||(!board[0].equals("") && board[0].equals(board[3]) && board[0].equals(board[6]))
	              ||(!board[1].equals("") && board[1].equals(board[4]) && board[1].equals(board[7]))
	              ||(!board[2].equals("") && board[2].equals(board[5]) && board[2].equals(board[8]))
	              ||(!board[0].equals("") && board[0].equals(board[4]) && board[0].equals(board[8]))
	              ||(!board[2].equals("") && board[2].equals(board[4]) && board[2].equals(board[6]));
      //return false; // this is left as an exercise
   } // end method isGameOver
   
   public boolean isTie()
   {
	   for(int i = 0; i < board.length; i++)
	   {
		   if(board[i].equals(""))
			   return false;
	   }
	   return true;
   }

   // private inner class Player manages each Player as a runnable
   //˽���ڲ����ÿ�������Ϊһ���������߳�������
   private class Player implements Runnable 
   {
      private Socket connection; // connection to client
      
      //�����ӿͻ��������˷�������
      private Scanner input; // input from client
      
      //�����ӷ������ͻ��˷�������
      private Formatter output; // output to client
      
      //������
      private int playerNumber; // tracks which player this is
      
      //������÷���
      private String mark; // mark for this player
      
      
      private boolean suspended = true; // whether thread is suspended

      //������Ӧ��ҵ��߳�
      public Player( Socket socket, int number )
      {
    	  //��Ҷ�Ӧ����ţ�X��0��O��1
         playerNumber = number; // store this player's number
         
         //��Ҷ�Ӧ�ı�ǣ�X��O
         mark = MARKS[ playerNumber ]; // specify player's mark
         
         //�ͷ��������ӵ��׽���
         connection = socket; // store socket for client
         
         try // obtain streams from Socket
         {
        	 //input���ڷ��������ʾ�Ķ���
            input = new Scanner( connection.getInputStream() );
            
            //output���ڿͻ�������ʾ�Ķ���
            //Formatter�Ĳ�����ʾҪ�����Ŀ��λ�ã���ʽ�����ַ����������Ŀ��λ��
            output = new Formatter( connection.getOutputStream() );
         } // end try
         catch ( IOException ioException ) 
         {
            ioException.printStackTrace();
            System.exit( 1 );
         } // end catch
      } // end Player constructor

      // send message that other player moved
      public void otherPlayerMoved( int location )
      {
         output.format( "Opponent moved\n" );
         
         //���location�������ÿͻ���ʶ��λ�õ�
         output.format( "%d\n", location ); // send location of move
         
         if(isGameOver())
        	 output.format("Defeat\n");
         else if(isTie())
        	 output.format("Tie\n");
         
         output.flush(); // flush output
      } // end method otherPlayerMoved

      // control thread's execution
      public void run()
      {
         // send client its mark (X or O), process messages from client
         try 
         {
            displayMessage( "Player " + mark + " connected\n" );
            
            ///�����ǵ�һ����ͻ��˷�����Ϣ�������ÿͻ���ʶ�����ĸ����
            output.format( "%s\n", mark ); // send player's mark
            output.flush(); // flush output

            // if player X, wait for another player to arrive
            if ( playerNumber == PLAYER_X ) 
            {
               output.format( "%s\n%s", "Player X connected",
                  "Waiting for another player\n" );
               output.flush(); // flush output

               gameLock.lock(); // lock game to  wait for second player

               try 
               {
                  while( suspended )
                  {
                	  //��OҲ������֮��Ż�signal
                     otherPlayerConnected.await(); // wait for player O
                  } // end while
               } // end try 
               catch ( InterruptedException exception ) 
               {
                  exception.printStackTrace();
               } // end catch
               finally
               {
                  gameLock.unlock(); // unlock game after second player
               } // end finally

               // send message that other player connected
               output.format( "Other player connected. Your move.\n" );
               output.flush(); // flush output
            } // end if
            else
            {
               output.format( "Player O connected, please wait\n" );
               output.flush(); // flush output
            } // end else

            // while game not over
            while ( !isGameOver() && !isTie())
            {
               int location = 0; // initialize move location

               if ( input.hasNext() )
                  location = input.nextInt(); // get move location

               // check for valid move
               if ( validateAndMove( location, playerNumber ) ) 
               {
                  displayMessage( "\nlocation: " + location );
                  output.format( "Valid move.\n" ); // notify client
                  if(isGameOver())
                	  output.format("Victory\n");
                  else if(isTie())
                	  output.format("Tie\n");
                  output.flush(); // flush output
               } // end if
               else // move was invalid
               {
                  output.format( "Invalid move, try again\n" );
                  output.flush(); // flush output
               } // end else
               
            } // end while
         } // end try
         finally
         {
        	 if(isGameOver())
        		 displayMessage("\nWe have a Winner!");
        	 else if(isTie())
        		 displayMessage("\nTie! No winner or loser!");
            try
            {
               connection.close(); // close connection to client
            } // end try
            catch ( IOException ioException ) 
            {
               ioException.printStackTrace();
               System.exit( 1 );
            } // end catch
         } // end finally
      } // end method run

      // set whether or not thread is suspended
      public void setSuspended( boolean status )
      {
         suspended = status; // set value of suspended
      } // end method setSuspended
   } // end class Player
} // end class TicTacToeServer

/**************************************************************************
 * (C) Copyright 1992-2012 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/
