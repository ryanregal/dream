import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AdditionAppController {
	//��@FXMLע������ʾ���Կ��Ա�FXML��ʽ�ļ�����
	@FXML
	private Label resultLabel;
	@FXML
	private TextField number1TextField;
	@FXML
	private TextField number2TextField;

	@FXML
	//�������������������ӽ��
	void calculateSumButtonPressed(ActionEvent event) {
		int number1 = getNumber(number1TextField);
		int number2 = getNumber(number2TextField);
		int sum = number1 + number2;
		resultLabel.setText(String.valueOf(sum));//���������
	}
	//��ȡ�û�����
	private int getNumber(TextField numberTextField) {
		int number = 0;
		try {
			number = Integer.parseInt(numberTextField.getText().trim());
			//numberTextField�Ƕ�������ã�����һ��getText��������ȡ�ı�����
			//��String���е�trim���������������û�����Ķ���ո�
			//Integer.parseInt���һ��String�Ͷ���ת��Ϊһ�����Ͷ���
		} catch (NumberFormatException e) {
			numberTextField.setText("Enter an integer");//��ʾҪ����һ������
			numberTextField.selectAll();//ѡ�������ı�
			numberTextField.requestFocus();//������ؼ�
		}
		return number;
	}
}
