import java.security.SecureRandom;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class DrawRandomShapesController {
	private static final SecureRandom random = new SecureRandom();

	//��@FXMLע������ʾ���Կ��Ա�FXML��ʽ�ļ�����
	@FXML
	private TextField xone;
	@FXML
	private TextField yone;
	@FXML
	private TextField xtwo;
	@FXML
	private TextField ytwo;
	@FXML
	private Canvas canvas;
	@FXML
	private int count=0;
	
	@FXML
	void drawShapesButtonPressed(ActionEvent event) {
		//�����Ƴ���20��ͼ��ֹͣ����
		if(count>20) {
			GraphicsContext gc = canvas.getGraphicsContext2D();
			gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
			count=0;
		}
		else {
			GraphicsContext gc = canvas.getGraphicsContext2D();
			generateShapes(gc);//����ͼ��
			count++;
		}
	}

	//����ͼ��
	private void generateShapes(GraphicsContext gc) {
		int x1,y1,x2,y2;
		x1 = Integer.parseInt(xone.getText());
		x2 = Integer.parseInt(xtwo.getText());
		y1 = Integer.parseInt(yone.getText());
		y2 = Integer.parseInt(ytwo.getText());
		if(x1>300||x1<0) {
			xone.setText("0<x1<300");//��ʾ��Χ
			xone.selectAll();//ѡ�������ı�
			xone.requestFocus();//������ؼ�
		}
		if(x2>300||x2<0) {
			xtwo.setText("0<x2<300");//��ʾ��Χ
			xtwo.selectAll();//ѡ�������ı�
			xtwo.requestFocus();//������ؼ�
		}
		if(y1>300||y1<0) {
			yone.setText("0<y1<300");//��ʾ��Χ
			yone.selectAll();//ѡ�������ı�
			yone.requestFocus();//������ؼ�
		}
		if(y2>300||y2<0) {
			ytwo.setText("0<y2<300");//��ʾ��Χ
			ytwo.selectAll();//ѡ�������ı�
			ytwo.requestFocus();//������ؼ�
		}
		
		//����õ���ɫ
		Color strokeColor = Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));
			
		int shapeNumber = random.nextInt(3);//��0��1��2�еõ�һ�������
		switch (shapeNumber) {
			case 0://0���������
				MyShape temp1=new MyLine(x1, y1, x2, y2, strokeColor);
				//��̬�󶨣���������MyLine�е�draw����
				temp1.draw(gc);
				break;
			case 1://1�������������
				MyShape temp2=new MyRectangle(x1, y1, x2, y2, strokeColor);
				//��̬�󶨣���������MyRectangle�е�draw����
				temp2.draw(gc);
				break;
			case 2://2���������Բ
				MyShape temp3=new MyOval(x1, y1, x2, y2, strokeColor);
				//��̬�󶨣���������MyOval�е�draw����
				temp3.draw(gc);
				break;
			}
	}

}