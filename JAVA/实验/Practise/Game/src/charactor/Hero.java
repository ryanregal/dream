package charactor;
import property.Armor;
import property.Item;

//���á����췽����thisָ��,�������η�,��ʼ��

/*�����ԣ�����ڶ������ԣ�����һ�����Ա�static���ε�ʱ�򣬾ͽ��������ԣ��ֽ�����̬����
��һ�����Ա������������ԣ���ô���еĶ��󣬶�����һ��ֵ*/

/*�෽���ֽ�����̬���������󷽷��ֽ�ʵ���������Ǿ�̬����
����һ�����󷽷������뽨������һ�������ǰ��Ļ�����
�����෽��������Ҫ����Ĵ��ڣ�ֱ�Ӿͷ���*/

/*�������Գ�ʼ����3�� 1. ���������Ե�ʱ���ʼ��  2. ���췽���г�ʼ��
3. ��ʼ����
��������ʼ��������췽��*/

/*��ת��Ϊ�ӿڣ�����ת�ͣ�
   �ӿ�ת����ʵ����(����ת��)*/

/*��Ķ�̬:��������ָ���������,��Ҫ��������
1. ���ࣨ�ӿڣ�����ָ���������
2. ���õķ�������д*/

/*��������д�����า�Ǹ���Ķ��󷽷������ؾ������า�Ǹ�����෽��
 �����������ഴ�����Ǿ�̬���������������������ڵĸ��෽����
�����������ഴ�����ǷǾ�̬��������������������ָ������෽����*/

/*����һ�����ʱ��Ĭ���Ǽ̳���Object
 Object���ṩһ��toString�������������е��඼��toString����
toString()����˼�Ƿ��ص�ǰ������ַ������*/

/*��һ������û���κ�����ָ���ʱ�����������������յ�����
�������ѻ��ıȽ϶��ʱ�򣬾ͻᴥ����������,finalize() �����ͻᱻ���á�
finalize() ���ǿ�����Ա�������õķ����������������JVM���õġ�*/

/*equals() �����ж���������������Ƿ���ͬ*/

/*final�����಻�ܱ��̳У����η������ܱ���д
 ���λ������ͱ�������ʾ�ñ���ֻ��һ�θ�ֵ����
final�������ã���ʾ������ֻ��1��ָ�����Ļ���
String����final���εģ����ܱ��̳�*/

/*�ڲ����Ϊ���֣��Ǿ�̬�ڲ���,��̬�ڲ���
������,������*/

/*��Ǿ�̬�ڲ��಻ͬ����̬�ڲ����ʵ���� ����Ҫһ���ⲿ���ʵ��Ϊ����������ֱ��ʵ����
�﷨��new �ⲿ��.��̬�ڲ���();
��Ϊû��һ���ⲿ���ʵ���������ھ�̬�ڲ������治���Է����ⲿ���ʵ�����Ժͷ���
���˿��Է����ⲿ���˽�о�̬��Ա�⣬��̬�ڲ������ͨ��ûʲô�������*/

/*������ָ����������һ�����ͬʱʵ��������ʹ������Ӽ�ྫ��
ͨ������£�Ҫʹ��һ���ӿڻ��߳����࣬�����봴��һ������
�е�ʱ��Ϊ�˿���ʹ�ã�ֱ��ʵ����һ�������࣬����������ʵ������󷽷���
��Ȼʵ���˳��󷽷�����ô����һ���µ��ֻ࣬������࣬û��������
�������࣬����������

�������Hero�ǳ����࣬attack�ǳ��󷽷�����ô������main����������
Hero h1=new Hero{public void attack() {System.out.println("�µĽ����ֶ�");}}
����h�����������ϵͳ�Զ��������������*/

/*������������Ϊ�����ֵ�������
�ڲ����������಻һ�����ǣ��ڲ�����������ڳ�Ա��λ�ã��������Ժͷ���ƽ�ȵ�λ�á�
�������������һ����ֱ�������ڴ�������棬��������������forѭ����ȵȵط�
 class SomeHero extends Hero{public void attack() {System.out.println( name+ " �µĽ����ֶ�"); }}*/

/*����������ʹ���ⲿ�ľֲ��������ⲿ�ľֲ�������������Ϊfinal
 jdk���æ�ӣ�����Ϊ���������е��Ǹ��ֲ�����ֻ�ǿ�ʼ����ֵ���ⲿ�ı�����ֵ
 �ı���ֵ��������ı��ⲿ���������Լ���final���������������ܱ��޸�*/


public class Hero {
	
    /*�����η�������package/friendly/default,�Լ����Է���
           ͬ��������Լ̳�,��ͬ�����಻�ܼ̳�
           ͬ������Է���,��ͬ���಻�ܷ���*/
	
    /* protected�η�������,�Լ����Է���
            ͬ��������Լ̳�,��ͬ��������Լ̳�
            ͬ������Է���,��ͬ���಻�ܷ���*/

	//���Թ�����ֱ�ӷ��ʣ�����仯��ֵ
	public static final int itemTotalNumber = 6;//��Ʒ��������
	public String name="default"; //���������Ե�ʱ���ʼ��
	public float hp; //Ѫ��
	float maxHP=100;	
	{maxHP=200;}//��ʼ����
	public static int itemCapacity=8;
	static{itemCapacity=6;}//��̬��ʼ���� ��ʼ��
	public float armor; //����  
	public int moveSpeed=123; //�ƶ��ٶ�
	public Armor InitItem;//��ʼ���� 
	static String copyright;//�����ԣ���̬����
	
	public void attackHero(Hero h) throws EnemyHeroIsDeadException{
		if(h.hp==0) {
			throw new EnemyHeroIsDeadException(h.name +"�Ѿ�����,����Ҫʩ�ż���" );
		}
	}
	
    //�з���ˮ��,��̬�ڲ���
    static class EnemyCrystal{
        int hp=5000;
         
        //���ˮ����Ѫ��Ϊ0��������ʤ��
        public void checkIfVictory(){
            if(hp==0){
                Hero.battleWin();
                //��̬�ڲ��಻��ֱ�ӷ����ⲿ��Ķ�������
            }
        }
    }
	
    // �Ǿ�̬�ڲ��ֻ࣬��һ���ⲿ�������ڵ�ʱ�򣬲�������
    // ս���ɼ�ֻ����һ��Ӣ�۶�����ڵ�ʱ���������
    class BattleScore {
        int kill;
        int die;
        int assit;
 
        public void legendary() {
            if (kill >= 8)
                System.out.println(name + "����");
            else
                System.out.println(name + "��δ����");
        }
    }
	
	//�����useItem����
    public void useItem(Item i) {
        System.out.println("hero use item");
    }
	
	//ʵ������,���󷽷����Ǿ�̬����
    //�����ж�����ܹ�����
	public void die() {
		hp=0;
	}
	
	public void kill(Mortal m) {
		m.die();
	};
	
	//�෽������̬����
    //ͨ����Ϳ���ֱ�ӵ���
	public static void battleWin() {
		System.out.println("battle win");
	}
	
    //��ӡ�ڴ��е������ַ
    public void showAddressInMemory() {
    	System.out.println("��ӡthis�����������ַ��"+this);
    }
    
    //��������������һ��
    //�ڷ������У�ֻ�ܷ��ʵ�����name
   //Ϊ�˱������⣬���������ò�ʹ������������
   public void setName2(String heroName){
       name = heroName;
   }
   //Ҳ����ͨ��this��������
   public void setName3(String name){
       //name������ǲ���name
       //this.name�����������name
       this.name = name;
   }
    
	public Hero() {
		System.out.println("Hero�Ĺ��췽�� ");
	}
   
   public Hero(String name,float hp) {
       System.out.println("���������Ĺ��췽��");
       this.name = name;
       this.hp=hp;
   }
   
    //�вεĹ��췽��
    //Ĭ�ϵ��޲εĹ��췽����ʧЧ��
    public Hero(String heroname,float heroHP,float heroArmor,int heroMoveSpeed) {
    	this(heroname,heroHP);//�����������캯�������������ǰ��
    	System.out.println("�ĸ������Ĺ��췽��");
    	armor=heroArmor;
    	moveSpeed=heroMoveSpeed;
    }
    
    public static void main(String[] args) {
        //ʹ��һ��������ָ���������
        Hero h = new Hero("����",100,50,100); 
    	System.out.println("ʵ����һ������:"+" "+h.name+" "+h.hp+" "+h.armor+" "+h.moveSpeed);
    	
    	// ʵ�����ڲ���
        // BattleScore����ֻ����һ��Ӣ�۶�����ڵ�ʱ���������
        // ������ʵ�������뽨����һ���ⲿ�����Ļ���֮��
        BattleScore score = h.new BattleScore();
        score.kill = 9;
        score.legendary();
        
        //ʵ������̬�ڲ���
        Hero.EnemyCrystal crystal = new Hero.EnemyCrystal();
        crystal.checkIfVictory();
    	
    	Hero.copyright="��Ȩ��OUAT��������";
    	System.out.println(Hero.copyright);
    	
    	h.die();//������һ��������ܵ���
    	System.out.println(h.name+" "+h.hp+" "+h.armor+" "+h.moveSpeed);
    	Hero.battleWin(); //�������ֱ��ͨ�������
    	System.out.println(Math.random());//random()����һ���෽����ֱ��ͨ����Math���е��ã���û��һ��Math��ʵ�����ڡ�
    	
    	Hero newh=h; 
    	System.out.println(newh.maxHP);
    	
    	//��һ��ADHero����AD��ʹ�ã���AD�ӿ�ֻ��һ��physicAttack�����������ζ��ת������п���Ҫ����physicAttack������
    	//��ADHeroһ������physicAttack�����ģ�����ת�����ܳɹ��ġ�
    	ADHero ad=new ADHero();
    	//�ж϶�һ��������ָ��Ķ����Ƿ���Hero���ͣ�����Hero������
    	System.out.println(ad instanceof ADHero);
    	
    	System.out.println(h.toString());
    	
    	System.out.println(h.equals(newh));
    	
    	h.hp=0;
    	
    	try {
    		h.attackHero(newh);
    	}catch(EnemyHeroIsDeadException e) {
    		System.out.println("�쳣�ľ���ԭ��"+e.getMessage());
    		e.printStackTrace();
    	}
    }  
}
