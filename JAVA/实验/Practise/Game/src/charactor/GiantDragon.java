//����ģʽ:1. ���췽��˽�л� 2. ��̬����ָ��ʵ�� 3. public static�� getInstance���������صڶ����ľ�̬����
//����ʽ����ģʽ��������ζ��ᴴ��һ��ʵ��
//����ʽ����ģʽ��ֻ���ڵ���getInstance��ʱ�򣬲Żᴴ��ʵ��
package charactor;

public class GiantDragon {
	
	//˽�л����췽��ʹ�ø����޷����ⲿͨ��new ����ʵ����
    private GiantDragon(){ }
 
    //׼��һ��������(��ֻ֤��һ����������)��ָ��һ��ʵ��������
    private static GiantDragon instance ;
     
    //public static �������ṩ�������߻�ȡinstance����
    public static GiantDragon getInstance(){
    	if(instance==null) instance=new GiantDragon();
        return instance;
    }
}
