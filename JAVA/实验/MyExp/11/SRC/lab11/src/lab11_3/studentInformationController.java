package lab11_3;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;

public class studentInformationController 
{
	@FXML private Button addInformationButton;
	@FXML private Button displayInformationButton;
	@FXML private Button deleteInformationButton;
	@FXML private Button searchInformationButton;
	@FXML private TextArea showTextArea;
	@FXML private Button lastInformationButton;
	@FXML private Button nextInformationButton;
	@FXML private Button modifyInformationButton;
	//��ǰģʽ
	private Status currentMode;
	//��ͬ������ö����
	//������ɾ�����޸ġ���ѯ����ʾ
	private enum Status
	{
		ADD(0),
		DELETE(1),
		CHANGE(2),
		SEARCH(3),
		DISPLAY(4);
		
		Status(int status){}
	};
	//����һ��String���͵�ArrayList���洢��Ϣ
	private ArrayList<String> information = new ArrayList<>();
	private int currentDisplayPosition;//��ǰչʾ��λ��
	private boolean isAddSuccessful = true;
	//������Ϣ��ŵ�String���͵�ArrayList
	private ArrayList<String> wrongInformation = new ArrayList<>();
	//ѧ��ƥ��
	//[]ƥ�䷽�����ڵ����ⵥ���ַ�,+��ʾ1�λ���
	//$ƥ�������ַ�����β��λ��,^ƥ�������ַ�����ʼ��λ��(�ڷ��������ʾ������)
	private Pattern studentCodePattern = Pattern.compile("^[0-9]+$");
	private Matcher studentCodeMatcher;
	//����ƥ�䣬����������
	private Pattern namePattern = Pattern.compile("[^0-9]+");
	private Matcher nameMatcher;
	//�绰����ƥ�䣬���������ֻ���-
	private Pattern phoneNumberPattern = Pattern.compile("[0-9-]+");
	private Matcher phoneNumberMatcher;
	//����ƥ��
	//����һ���ַ����Ϊ�������ַ�����ԭ���ַ�����������á���˽���ת����������� '\\' ƥ�� "\"
	//\w��������ڱ�ʶ���ַ�,'\w{1,}' �ȼ��� '\w+'
	//"()":���һ���ӱ��ʽ�Ŀ�ʼ�ͽ���λ��
	//���䲻��Ϊ�գ��������@�����š���@�����ź���Ҫ���ֶ���ɡ�. ���ָ�Ĵ�
	private Pattern emailPattern = Pattern.compile("^\\w+@\\w{1,}+(.\\w+)+$");
	private Matcher emailMatcher;
	
	public void addInformationButtonPressed(ActionEvent e)
	{
		TextInputDialog dialog = new TextInputDialog("Input New Student Information");
		dialog.setTitle("Add Student Record");
		dialog.setHeaderText("Format:ѧ�� ���� �绰 ������Ϣ");
		dialog.setContentText("Please enter student information below:");
		
		Optional<String> input = dialog.showAndWait();
		
		if(input.isPresent())
		{
			//��ȡ�����String���飬ʹ��һ�����߶���ո���зָ���input.get()��ȡ����
			String[] inputs = input.get().split("[ ]+");
			//����Ϊ��
			if(input.get().length() == 0 || input.get().equals("Input New Student Information")){
				isAddSuccessful = false;
				wrongInformation.add("Input can not be empty!");
			}
			//����������ƥ��
			else if(inputs.length != 4){
				isAddSuccessful = false;
				wrongInformation.add("Number of entries does not match!");
			}
			//������ȷ
			else if(inputs.length == 4)
			{
				//matcher����һ��ƥ���������������������ģʽƥ��
				studentCodeMatcher = studentCodePattern.matcher(inputs[0]);
				nameMatcher = namePattern.matcher(inputs[1]);
				phoneNumberMatcher = phoneNumberPattern.matcher(inputs[2]);
				emailMatcher = emailPattern.matcher(inputs[3]);
				
				//matches���ز������ͣ���ȫ��ƥ��
				//�������������ʽ�����Խ�������������ƥ��
				if(!studentCodeMatcher.matches())
				{
					isAddSuccessful = false;
					wrongInformation.add("Student ID can only consist of numbers��");
				}
				if(!nameMatcher.matches())
				{
					isAddSuccessful = false;
					wrongInformation.add("Name cannot contain numbers��");
				}
				if(!phoneNumberMatcher.matches())
				{
					isAddSuccessful = false;
					wrongInformation.add("Phone numbers can only consist of digits and\"-\"��");
				}
				if(!emailMatcher.matches())
				{
					isAddSuccessful = false;
					wrongInformation.add("Email format error! Correct format: number/letter/underscore+@+address suffix");
				}
				//û�д�����Ϣ
				if(wrongInformation.size() == 0)  isAddSuccessful = true;
			}
			
			//������󣬴�ӡ������Ϣ
			if(isAddSuccessful == false) 
			{
				String wrongText = wrongInformation.get(0);
				for(int i = 1; i < wrongInformation.size(); i++)
				{
					wrongText += ("\n" + wrongInformation.get(i));
				}
				
				showTextArea.setText("Add failed! The error message is as follows:\n" + wrongText);
				wrongInformation.clear();//�����б�����
			}
			//�����ɹ�����������
			else
			{
				information.clear();
				//��Excel�ж�ȡ֮ǰȫ��ѧ������Ϣ
				List<List<String>> resultList = ExcelUtil.readExcel("src/lab11_3/studentInformation.xlsx");
		        if (resultList != null) 
		        {
		            List<String> addList = Arrays.asList(inputs[0],inputs[1],inputs[2],inputs[3]);
		            resultList.add(addList);//��ӵ�resultList��
		        }
		        //����Excel����
		        ExcelUtil.writeExcel(resultList, "src/lab11_3/studentInformation.xlsx");
		        wrongInformation.clear();
		        showTextArea.setText("Added successfully! The new information is as follows:\n" + input.get() );
			}
		}
	}
	
	//ɾ����Ϣ
	public void deleteInformationButtonPressed(ActionEvent e)
	{
		//���û�д���ʾ����
		if(currentMode != Status.DISPLAY)
		{
			showTextArea.setText("Please click the��ʾbutton to select the information you want to delete!");
		}
		//ִ��ɾ������
		else
		{
			information.clear();
			//��ȡinformation�е����ݣ���excel�ж�ȡ
			List<List<String>> resultList = ExcelUtil.readExcel("src/lab11_3/studentInformation.xlsx");
			String deleteInformation = showTextArea.getText();
			//ɾ����Ϣ
	        if (resultList != null) 
	        {
	        	Iterator<List<String>> iter = resultList.iterator();
	        	while(iter.hasNext())
	        	{
	        		String[] str = deleteInformation.split("[ ]+");
	        		List<String> tempList = Arrays.asList(str[0],str[1],str[2],str[3]);
	      	      //���ҵ����������Ϣ��ͬ������ʱ����information�еĸ�������ɾ��
	        		if(iter.next().equals(tempList))  iter.remove();
	        	}
	        }
	        //����excle�е�����
	        ExcelUtil.writeExcel(resultList, "src/lab11_3/studentInformation.xlsx");
	     }
	     
	     currentMode = Status.DELETE;
		 showTextArea.setText("The current record has been deleted! You can click the \\\"��ʾ\\\" button "
					+ "to continue viewing the information!");
	}
	
	//�޸���Ϣ
	public void modifyInformationButtonPressed(ActionEvent e) throws IOException
	{
		//���û�д���ʾ����
		if(currentMode != Status.DISPLAY)
		{
			showTextArea.setText("Please click the ��ʾ button first to select the information to be modified!");
		}
		//ִ���޸Ĳ���
		else
		{
			TextInputDialog dialog = new TextInputDialog("Input Here");
			dialog.setTitle("Modify Student Records");
			dialog.setHeaderText("Format:ѧ�� ���� �绰 ������Ϣ");
			dialog.setContentText("Please enter student information below:");
			
			Optional<String> input = dialog.showAndWait();
			
			if(input.isPresent())
			{
				//��ȡ�����String���飬ʹ��һ�����߶���ո���зָ���input.get()��ȡ����
				String[] inputs = input.get().split("[ ]+");
				//����Ϊ��
				if(input.get().length() == 0 || input.get().equals("Input New Student Information")){
					isAddSuccessful = false;
					wrongInformation.add("Input can not be empty!");
				}
				//����������ƥ��
				else if(inputs.length != 4){
					isAddSuccessful = false;
					wrongInformation.add("Number of entries does not match!");
				}
				//������ȷ
				else if(inputs.length == 4)
				{
					//matcher����һ��ƥ���������������������ģʽƥ��
					studentCodeMatcher = studentCodePattern.matcher(inputs[0]);
					nameMatcher = namePattern.matcher(inputs[1]);
					phoneNumberMatcher = phoneNumberPattern.matcher(inputs[2]);
					emailMatcher = emailPattern.matcher(inputs[3]);
					
					//matches���ز������ͣ���ȫ��ƥ��
					//�������������ʽ�����Խ�������������ƥ��
					if(!studentCodeMatcher.matches())
					{
						isAddSuccessful = false;
						wrongInformation.add("Student ID can only consist of numbers��");
					}
					if(!nameMatcher.matches())
					{
						isAddSuccessful = false;
						wrongInformation.add("Name cannot contain numbers��");
					}
					if(!phoneNumberMatcher.matches())
					{
						isAddSuccessful = false;
						wrongInformation.add("Phone numbers can only consist of digits and\"-\"��");
					}
					if(!emailMatcher.matches())
					{
						isAddSuccessful = false;
						wrongInformation.add("Email format error! Correct format: number/letter/underscore+@+address suffix");
					}
					//û�д�����Ϣ
					if(wrongInformation.size() == 0)  isAddSuccessful = true;
				}
				
				//�����ѧ��ʧ�ܣ���ӡ������Ϣ
				if(isAddSuccessful == false) 
				{
					String wrongText = wrongInformation.get(0);
					for(int i = 1; i < wrongInformation.size(); i++)
					{
						wrongText += ("\n" + wrongInformation.get(i));
					}
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Fail to edit!");
					alert.setHeaderText("Please follow the tips below to update your registration information:");
					alert.setContentText(wrongText);
					wrongInformation.clear();
					alert.showAndWait();
				}
				//��ӳɹ��������޸Ĳ���
				else
				{
					//��ȡexcel��ȫ��ѧ����Ϣ
					List<List<String>> resultList = ExcelUtil.readExcel("src/lab11_3/studentInformation.xlsx");
					//�޸ĺ����Ϣ
					List<String> modifyElement = Arrays.asList(inputs[0],inputs[1],inputs[2],inputs[3]);
					//��ǰ��Ҫ���޸ĵ���Ϣ
					String[] tempString = showTextArea.getText().split("[ ]+");
					List<String> targetElement = Arrays.asList(tempString[0],tempString[1],tempString[2],tempString[3]);
			        if (resultList != null) 
			        {
			            for (int i = 0; i < resultList.size(); i++) 
			            {
			                List<String> cellList = resultList.get(i); 
			              //�ҵ���ǰ������ʾ�Ĵ��޸ĵ�ѧ���������޸�
			                if(cellList.equals(targetElement)){
			                	resultList.set(i, modifyElement);
			                }
			            }
			        }
			        ExcelUtil.writeExcel(resultList, "src/lab11_3/studentInformation.xlsx");
					currentMode = Status.CHANGE;
					showTextArea.setText("The current record has been modified!");
				}
			}
		}
	}
	
	//��ѯ����
	public void searchInformationButtonPressed(ActionEvent e) throws IOException
	{
		boolean isSearchSuccessful = false;
		TextInputDialog dialog = new TextInputDialog("Input Here");
		dialog.setTitle("Query Student Records");
		dialog.setHeaderText("Format:����");
		dialog.setContentText("Please enter student name below:");
		
		Optional<String> input = dialog.showAndWait();
		
		//���������ʱ�����в�ѯ
		if(input.isPresent())
		{
			information.clear();
			//��ȡexcel�е�ѧ����Ϣ
			List<List<String>> resultList = ExcelUtil.readExcel("src/lab11_3/studentInformation.xlsx");
			String targetName = input.get();//��ȡ����Ĵ���ѯ����

	        if (resultList != null) 
	        {
				//�������е�ѧ����Ϣ���ҵ�����������ѧ��
	            for (int i = 0; i < resultList.size(); i++) 
	            {
	                List<String> cellList = resultList.get(i);
	                if(cellList.get(1).equals(targetName))//�ҵ�����������ѧ��
	                {
	                	String str = cellList.get(0) + " " + cellList.get(1) + " " + cellList.get(2) + " " + cellList.get(3);
	                	information.add(str); //����information��
	                	isSearchSuccessful = true;
	                }
	            }
	        }
	        //���ɹ����ҵ�ѧ��
			if(isSearchSuccessful == true)
			{
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Find success!");
				alert.setHeaderText(null);
				alert.setContentText(information.size() + "record(s) found");
				alert.showAndWait();
				currentDisplayPosition = 0; //������0��ʼ��չʾ�ҵ��ļ�¼
				showTextArea.setText((currentDisplayPosition+1) + " st record" + "\n" + information.get(currentDisplayPosition));
				currentMode = Status.SEARCH;//����Ϊ����ģʽ
			}
			//���������ʾ
			else
			{
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("No corresponding record found!");
				alert.setHeaderText(null);
				alert.setContentText("Please confirm that your input is correct!");
				alert.showAndWait();
			}
		}
	}
	
	//��ʾ����ѧ����Ϣ
	public void displayInformationButtonPressed(ActionEvent e)
	{
		//��ȡexcel�е���Ϣ
		List<List<String>> resultList = ExcelUtil.readExcel("src/lab11_3/studentInformation.xlsx");
		currentMode = Status.DISPLAY;
		information.clear();
		//����Щ��Ϣ����information String ArrayList��
		if (resultList != null) 
        {
            for (int i = 0; i < resultList.size(); i++) 
            {
                List<String> cellList = resultList.get(i);
                String str = cellList.get(0) + " " + cellList.get(1) + " " + cellList.get(2) + " " + cellList.get(3);
                information.add(str);
            }
        }
		currentDisplayPosition = 0; //������ʼ��Ϊ0
		showTextArea.setText(information.get(currentDisplayPosition));
	}
	
	//��ʾ��һ��ѧ����Ϣ
	public void lastInformationButtonPressed(ActionEvent e) 
	{
		//������չʾģʽʱ
		if(currentMode == Status.DISPLAY)
		{
			currentDisplayPosition--;
			if(currentDisplayPosition < 0)
				currentDisplayPosition = 0;
			showTextArea.setText(information.get(currentDisplayPosition));
		}
		//�����ڲ�ѯģʽʱ
		else if(currentMode == Status.SEARCH)
		{
			currentDisplayPosition--;
			//��С��0ʱ����λ������Ϊ��һ��������
			if(currentDisplayPosition < 0) currentDisplayPosition = 0;
			showTextArea.setText("Find record number " + (currentDisplayPosition+1) + "\n" + information.get(currentDisplayPosition));
		}
		//����ģʽ������ʹ�øò���
		else showTextArea.setText("Please click the ��ʾ or ��ѯ button before doing this!");
	}
	
	//��ʾ��һ��ѧ����Ϣ
	public void nextInformationButtonPressed(ActionEvent e) 
	{
		//������չʾģʽʱ
		if(currentMode == Status.DISPLAY)
		{
			currentDisplayPosition++;
			if(currentDisplayPosition >= information.size())
				currentDisplayPosition = information.size() - 1;
			showTextArea.setText(information.get(currentDisplayPosition));
		}
		//�����ڲ�ѯģʽʱ
		else if(currentMode == Status.SEARCH)
		{
			currentDisplayPosition++;
			//������information�Ĵ�Сʱ����λ������Ϊ���һ��������
			if(currentDisplayPosition >= information.size())  currentDisplayPosition = information.size() - 1;
			showTextArea.setText("Find record number " + (currentDisplayPosition+1) + "\n" + information.get(currentDisplayPosition));
		}
		//����ģʽ������ʹ�øò���
		else showTextArea.setText("Please click the ��ʾ or ��ѯ button before doing this!");
	}

}
