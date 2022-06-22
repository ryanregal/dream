import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

//���������ε���
public class MyRectangle extends MyShape {

	//�޲ι��캯��
	public MyRectangle() {
		this(0, 0, 0, 0, Color.BLACK);
	}

	//���ι��캯��
	public MyRectangle(double x1, double y1, double x2, double y2, Color strokeColor) {
		super(x1, y1, x2, y2, strokeColor);//ֱ�ӵ���ֱ�Ӹ��๹�캯��

	}
	
	//��д��ͼ����
	@Override
	public void draw(GraphicsContext gc) {
		gc.setStroke(getStrokeColor());
		gc.strokeRect(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
	}
	
	//Getters
	public double getUpperLeftX() {
		return getX1() < getX2() ? getX1() : getX2();
	}
	public double getUpperLeftY() {
		return getY1() < getY2() ? getY1() : getY2();
	}
	public double getWidth() {
		return Math.abs(getX1() - getX2());
	}
	public double getHeight() {
		return Math.abs(getY1() - getY2());
	}
}