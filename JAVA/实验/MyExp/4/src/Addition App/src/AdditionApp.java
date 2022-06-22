import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AdditionApp extends Application {
	@Override
	//��дstart����
	public void start(Stage stage) throws IOException {
		//ʹ��FMXLLoader��������������ΪAdditionApp.fxml�����ļ�
		Parent root = FXMLLoader.load(getClass().getResource("AdditionApp.fxml"));
		Scene scene = new Scene(root);//����һ������
		stage.setTitle("Add");//Ϊͼ�ν��洰�����ñ���
		stage.setScene(scene);//Ϊͼ�ν��洰�����ó���
		stage.show();//��ͼ�ν�������Ϊ�ɼ�
	}
	public static void main(String[] args) {
		//ͨ��Application�������launch()������������
		launch(args);
	}
}
