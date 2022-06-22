import javafx.application.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;

public class Main extends Application {
	public static void main(String[] args) {
		//ͨ��Application�������launch()������������
		launch(args);
	}
	//����start����
	public void start(Stage stage) throws Exception {
		//ʹ��FMXLLoader��������������Ϊ��sample.fxml�����ļ�
		Parent root = (Parent)FXMLLoader.load(getClass().getResource("sample.fxml"));
		Scene scene = new Scene(root);//����һ������
		stage.setTitle("Main");//Ϊͼ�ν��洰�����ñ���
		stage.setScene(scene);//Ϊͼ�ν��洰�����ó���
		stage.show();//��ͼ�ν�������Ϊ�ɼ�
	}
}
