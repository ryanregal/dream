import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

//����ֱ�ߵķ���
public class MyLine extends MyShape {

	public MyLine() {
		super();//ֱ�ӵ���ֱ�Ӹ��๹�캯����
	}

	public MyLine(double x1, double y1, double x2, double y2, Color strokeColor) {
		super(x1, y1, x2, y2, strokeColor);//ֱ�ӵ���ֱ�Ӹ��๹�캯����
	}

	@Override
	//��д�����ͼ����
	public void draw(GraphicsContext gc) {
		gc.setStroke(getStrokeColor());
		gc.strokeLine(getX1(), getY1(), getX2(), getY2());
	}
}