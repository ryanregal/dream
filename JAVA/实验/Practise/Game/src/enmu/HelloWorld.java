package enmu;

public class HelloWorld {
	public static void main(String args[]) {
		Season season=Season.SPRING;
		switch(season) {
		case SPRING:
			 System.out.println("����");
			 break;
		case SUMMER:
			System.out.println("����");
			break;
		case AUTUMN:
            System.out.println("����");
            break;
        case WINTER:
            System.out.println("����");
            break;
        default:break;
		}
		for(Season i:Season.values()) {
			System.out.println(i);
		}
		
		HeroType type=HeroType.TANK;
		switch(type) {
			case TANK:
				System.out.println("̹��");
				break;
			case WIZARD:
                System.out.println("��ʦ");
                break;
            case ASSIST:
                System.out.println("����");
                break;
            case WARRIOR:
                    System.out.println("��ս");
                break;
            case RANGED:
                System.out.println("Զ��");
                break;
            case PUSH:
                System.out.println("�ƽ�");
                break;
            case FARMING:
                 System.out.println("��Ұ");
                 break;
		}
	}
}
