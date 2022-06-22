import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DrawRandomShapes extends Application {
	@Override
	//��дstart����
	public void start(Stage stage) throws Exception {
		//ʹ��FMXLLoader��������������ΪDrawRandomShapes.fxml�����ļ�
		Parent root = FXMLLoader.load(getClass().getResource("DrawRandomShapes.fxml"));
		Scene scene = new Scene(root); //����һ������
		stage.setTitle("Draw"); //Ϊͼ�ν��洰�����ñ���
		stage.setScene(scene); //Ϊͼ�ν��洰�����ó���
		stage.show();//��ͼ�ν�������Ϊ�ɼ�
	}

	public static void main(String[] args) {
		//ͨ��Application�������launch()������������
		launch(args);
	}
}