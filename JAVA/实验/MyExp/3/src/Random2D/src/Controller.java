import java.security.SecureRandom;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Controller {
	//��@FXMLע������ʾ���Կ��Ա�Fxml��ʽ�ļ�����
	@FXML Pane pane; 
	private SecureRandom random = new SecureRandom();//�����������
	
	//����ԲȦ 
	public void initialize () {
		for (int i = 0; i < 10; i++) {
			Circle circle = new Circle();//�½�һ��ԲȦ
			circle.setCenterX((this.random.nextInt(500) + 201));//�������ԲȦ�ĺ�����
			circle.setCenterY((this.random.nextInt(300) + 201));//�������ԲȦ��������
			circle.setRadius(this.random.nextInt(100));//�������ԲȦ�İ뾶
			circle.setFill((Paint)randomColor());//������ԲȦ����ɫ
			this.pane.getChildren().add(circle);//���ԲȦ����Ļ
		} 
	}
	//���������ɫ
	private Color randomColor() {
		//�����ֱ����red, green, blue, opacity)
		return Color.rgb(this.random.nextInt(256), this.random.nextInt(256), this.random.nextInt(256), 
				this.random .nextInt(101) / 100.0D);
	}
}

