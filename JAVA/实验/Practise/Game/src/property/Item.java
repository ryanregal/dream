
/*������Լ̳и���Ķ��󷽷�,�ڼ̳к��ظ��ṩ�÷������ͽ�����������д
�ֽи��� override*/

package property;

public class Item {
    String name;
    int price;
    
    public void buy(){
        System.out.println("����");
    }
    public void effect() {
        System.out.println("��Ʒʹ�ú󣬿�����Ч��");
    }
    @Override
    public String toString() {
    	return name+price;
    }
    @Override
    public void finalize(){
        System.out.println("Item���ڻ���");
    }
    @Override
    public boolean equals(Object o){
        if (!(o instanceof Item)){
            return false;
        }else{
            Item h = (Item)o;
            return this.price == h.price;
        }
    }
    
    public static void main(String[] args) {
        Item i = new Item();
        i.effect();
         
        //�̳�
        LifePotion lp =new LifePotion();
        lp.effect();
        MagicPotion mp = new MagicPotion();
        mp.effect();

        //��̬
        Item i1 = new LifePotion();
        Item i2 = new MagicPotion();
        i1.effect();
        i2.effect();

        i1.price = 20;
        i2.price = 20;

        //�Լ���д��equals
        System.out.println(i1.equals(i2));
        
    }
}