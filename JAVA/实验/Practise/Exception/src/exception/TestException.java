package exception;
/*1. throws �����ڷ��������ϣ���throwͨ���������ڷ������ڡ�
2. throws ��ʾ�����쳣��һ�ֿ����ԣ�����һ���ᷢ����Щ�쳣��
throw�����׳����쳣��ִ��throw��һ���׳���ĳ���쳣����*/

/*�ɲ��쳣�� CheckedException
�ɲ��쳣��������д�����쳣��Ҫôtry catchס,Ҫô�����ף�˭���ã�˭�������� FileNotFoundException
������������������Ͳ�����ͨ��*/

/*����ʱ�쳣RuntimeExceptionָ�� ���Ǳ������try catch���쳣
��������ʱ�쳣: ��������Ϊ0�쳣:ArithmeticException
�±�Խ���쳣:ArrayIndexOutOfBoundsException
��ָ���쳣:NullPointerException
�ڱ�д�����ʱ����Ȼ����ʹ��try catch throws���д�����ɲ��쳣��֮ͬ�����ڣ����㲻����try catch��Ҳ�����б������
Java֮���Ի��������ʱ�쳣��ԭ��֮һ����Щ����ʱ�쳣̫�����ձ飬�������Ҫ���в�׽������Ŀɶ��Ծͻ��ú���⡣*/

/*����Error��ָ����ϵͳ������쳣��ͨ�����ڴ��ù���
��Ĭ�������£�һ��java����������ʱ��������ʹ��16m���ڴ�
������ͣ�ĸ�StringBuffer׷���ַ����ܿ�Ͱ��ڴ�ʹ�ù��ˡ��׳�OutOfMemoryError
������ʱ�쳣һ��������Ҳ�ǲ�Ҫ��ǿ�Ʋ�׽��*/

/*����ʱ�쳣�����е�ʱ���׳����쳣��������ʱ�쳣��������Ҳ���׳�*/

/*Throwable���࣬Exception��Error���̳��˸���*/

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
 
public class TestException {
 
    public static void main(String[] args) {
    	method1();
    	System.out.println(method());
    }
    
    //method1�ͻ�ӵ����쳣�� ����취ѡ����try catch�����
    //Ҳ�����׳�ȥ
    private static void method1() {
    	try {
    		method2();
    	}catch(Throwable e) {
    		e.printStackTrace();
    	}
    }
    
    //method2���������ǰ�����쳣ͨ��throws�׳�ȥ
    private static void method2() throws FileNotFoundException{
        File f = new File("d:/LOL.exe");
        System.out.println("��ͼ�� d:/LOL.exe");
        //new FileInputStream(f);
        System.out.println("�ɹ���");
    }
    
    public static int method() {
    	try {return 1;}
    	catch(Exception e) {return 2;}
    	//��finally�з���ֵ����ô���Ḳ��try����catch�з��صĴ���
    	finally {return 3;}
    }
    
}