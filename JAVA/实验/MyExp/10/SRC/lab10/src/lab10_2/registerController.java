package lab10_2;

import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.stage.Stage;

public class registerController 
{
	@FXML private TextField userNameTextField;
	@FXML private PasswordField passwordTextField;
	@FXML private TextField emailTextField;
	@FXML private Button confirmationButton;
	@FXML private Button closeButton;
	
	private String userName;
	private String password;
	private String email;
	
	static ArrayList<String> wrongInformation = new ArrayList<>();
	static ArrayList<Throwable> exceptionList = new ArrayList<>();
	
	//�û���Ҫ�󣺲���Ϊ�գ�ֻ������ĸ�����ֺ�_��ɣ���һλ����Ϊ���֡�
	//[]ƥ�䷽�����ڵ����ⵥ���ַ�,+��ʾ1�λ���
	//$ƥ�������ַ�����β��λ��,^ƥ�������ַ�����ʼ��λ��(�ڷ��������ʾ������)
	private Pattern userNamePattern = Pattern.compile("^[A-Za-z0-9_]+$");//ƥ����Ӣ����ĸ��������ɵ��ַ��� 
	private Pattern userNamePatternStartByNum = Pattern.compile("^[0-9]+[A-Za-z0-9_]+$");
	private Matcher userNameMatcher;
	//����Ϊ�գ����볤������8λ������ĸ�����֡��»�����ɡ�
	private Pattern passwordPattern = Pattern.compile("^[A-Za-z0-9_]+$");
	private Matcher passwordMatcher;
	//����Ϊ�գ��������@�����š���@�����ź���Ҫ���ֶ���ɡ�. ���ָ�Ĵʡ�
	//\w��������ڱ�ʶ���ַ�,'\w{1,}' �ȼ��� '\w+'
	//����һ���ַ����Ϊ�������ַ�����ԭ���ַ�����������á���˽���ת����������� '\\' ƥ�� "\"
	//"()":���һ���ӱ��ʽ�Ŀ�ʼ�ͽ���λ�á�
	private Pattern emailPattern = Pattern.compile("^\\w+@\\w{1,}+(.\\w+)+$");
	private Matcher emailMatcher;
	
	private Boolean isRegisterSuccessful = false;
	
	@FXML
	private void confirmationButtonPressed(ActionEvent e)
	{
		//��ȡ�û���
		try
		{
			userName = userNameTextField.getText();
			userNameMatcher = userNamePattern.matcher(userName);
			if(userName.length() == 0){
				wrongInformation.add("Username can not be empty��");
				throw new userNameException1();
			}
			if(userNameMatcher.find()){
				userNameMatcher = userNamePatternStartByNum.matcher(userName);
				//��������û����Ŀ�ͷ������
				if(userNameMatcher.find())
				{
					wrongInformation.add("Username cannot start with a number��");
					throw new userNameException2();
				}
			}
			//�����û�������Ӣ����ĸ�������»��߻��������ַ�
			else{
				wrongInformation.add("Username can only consist of letters, numbers, and underscores��");
				throw new userNameException2();
			}
		}
		catch(userNameException1 e1){
			exceptionList.add(e1);
		}
		catch(userNameException2 e2){
			exceptionList.add(e2);
		}
		//��ȡ����
		try
		{
			password = passwordTextField.getText();
			passwordMatcher = passwordPattern.matcher(password);
			if(password.length() == 0){
				wrongInformation.add("Password can not be blank��");
				throw new passwordException();
			}
			if(passwordMatcher.find())
			{
				//����С��8λ
				if(password.length() < 8){
					wrongInformation.add("Password cannot be less than 8 characters��");
					throw new passwordException1();
				}
			}
			else
			{
				wrongInformation.add("The password can only consist of letters, numbers, and underscores��");
				throw new passwordException1();
			}
		}
		catch(passwordException e1){
			exceptionList.add(e1);
		}
		catch(passwordException1 e2){
			exceptionList.add(e2);
		}
		//��ȡ����
		try
		{
			email = emailTextField.getText();
			emailMatcher = emailPattern.matcher(email);
			
			if(email.length() == 0){
				wrongInformation.add("E-mail can not be empty��");
				throw new emailException1();
			}
			if(!emailMatcher.matches())
			{
				wrongInformation.add("Email format error! \nCorrect format: number/letter/underscore+@+address suffix");
				throw new emailException2();
			}
		}
		catch(emailException1 e1){
			exceptionList.add(e1);
		}
		catch(emailException2 e2){
			exceptionList.add(e2);
		}
		
		//�жϵ�¼�Ƿ�ɹ�
		try
		{
			if(exceptionList.size() > 0){
				isRegisterSuccessful = false;
				throw new Exception();
			}
			else isRegisterSuccessful = true;
		}
		catch(Exception e1)
		{
			//���洰��
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Registration failed��");
			alert.setHeaderText("Please follow the tips below to update your registration information:");
			String contextText = wrongInformation.get(0);
			for(int i = 1; i < wrongInformation.size(); i++){
				contextText += ("\n" + wrongInformation.get(i));
			}
			//��ӡ������Ϣ
			alert.setContentText(contextText);
			exceptionList.clear();
			wrongInformation.clear();
			alert.showAndWait();
		}
		
		//ע��ɹ�
		if(isRegisterSuccessful == true)
		{
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Registration success��");
			alert.setHeaderText("Your user information is as follows:");
			//������������
			StringBuilder passwordHidden = new StringBuilder(password.length());
			for(int i = 0; i < password.length(); i++)
			{
				passwordHidden.append('*');
			}
			alert.setContentText("User��" + userName + "\n" + "Password��" + passwordHidden + "\n" + "Email��" + email);
			ButtonType buttonTypeOne = new ButtonType("Show Password");
			ButtonType buttonTypeCancel = new ButtonType("OK", ButtonData.CANCEL_CLOSE);
			 
			alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);
			 
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == buttonTypeOne)
			{
				alert.setContentText("User��" + userName + "\n" + "Password��" + password + "\n" + "Email��" + email);
				alert.getButtonTypes().clear();
				alert.getButtonTypes().setAll(buttonTypeCancel);
				alert.showAndWait();
			} 
		}
	}
	//�رհ�ť
	@FXML
	private void closeButtonPressed(ActionEvent e)
	{
		Stage stage = (Stage) closeButton.getScene().getWindow();
	    stage.close();
	}
}
