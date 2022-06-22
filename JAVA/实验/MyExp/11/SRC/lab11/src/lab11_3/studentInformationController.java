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
	//当前模式
	private Status currentMode;
	//不同操作的枚举型
	//新增、删除、修改、查询、显示
	private enum Status
	{
		ADD(0),
		DELETE(1),
		CHANGE(2),
		SEARCH(3),
		DISPLAY(4);
		
		Status(int status){}
	};
	//创建一个String类型的ArrayList，存储信息
	private ArrayList<String> information = new ArrayList<>();
	private int currentDisplayPosition;//当前展示的位置
	private boolean isAddSuccessful = true;
	//错误信息存放的String类型的ArrayList
	private ArrayList<String> wrongInformation = new ArrayList<>();
	//学号匹配
	//[]匹配方括号内的任意单个字符,+表示1次或多次
	//$匹配输入字符串结尾的位置,^匹配输入字符串开始的位置(在方括号则表示不接受)
	private Pattern studentCodePattern = Pattern.compile("^[0-9]+$");
	private Matcher studentCodeMatcher;
	//姓名匹配，不能是数字
	private Pattern namePattern = Pattern.compile("[^0-9]+");
	private Matcher nameMatcher;
	//电话号码匹配，可以是数字或者-
	private Pattern phoneNumberPattern = Pattern.compile("[0-9-]+");
	private Matcher phoneNumberMatcher;
	//邮箱匹配
	//将下一个字符标记为或特殊字符、或原义字符、或向后引用、或八进制转义符。如序列 '\\' 匹配 "\"
	//\w代表可用于标识的字符,'\w{1,}' 等价于 '\w+'
	//"()":标记一个子表达式的开始和结束位置
	//邮箱不能为空，需包含”@”符号。”@”符号后需要出现多个由”. ”分割的词
	private Pattern emailPattern = Pattern.compile("^\\w+@\\w{1,}+(.\\w+)+$");
	private Matcher emailMatcher;
	
	public void addInformationButtonPressed(ActionEvent e)
	{
		TextInputDialog dialog = new TextInputDialog("Input New Student Information");
		dialog.setTitle("Add Student Record");
		dialog.setHeaderText("Format:学号 姓名 电话 邮箱信息");
		dialog.setContentText("Please enter student information below:");
		
		Optional<String> input = dialog.showAndWait();
		
		if(input.isPresent())
		{
			//存取输入的String数组，使用一个或者多个空格进行分隔，input.get()获取输入
			String[] inputs = input.get().split("[ ]+");
			//输入为空
			if(input.get().length() == 0 || input.get().equals("Input New Student Information")){
				isAddSuccessful = false;
				wrongInformation.add("Input can not be empty!");
			}
			//输入项数不匹配
			else if(inputs.length != 4){
				isAddSuccessful = false;
				wrongInformation.add("Number of entries does not match!");
			}
			//输入正确
			else if(inputs.length == 4)
			{
				//matcher创建一个匹配器，将给定的输入与此模式匹配
				studentCodeMatcher = studentCodePattern.matcher(inputs[0]);
				nameMatcher = namePattern.matcher(inputs[1]);
				phoneNumberMatcher = phoneNumberPattern.matcher(inputs[2]);
				emailMatcher = emailPattern.matcher(inputs[3]);
				
				//matches返回布尔类型，是全部匹配
				//编译给定正则表达式并尝试将给定输入与其匹配
				if(!studentCodeMatcher.matches())
				{
					isAddSuccessful = false;
					wrongInformation.add("Student ID can only consist of numbers！");
				}
				if(!nameMatcher.matches())
				{
					isAddSuccessful = false;
					wrongInformation.add("Name cannot contain numbers！");
				}
				if(!phoneNumberMatcher.matches())
				{
					isAddSuccessful = false;
					wrongInformation.add("Phone numbers can only consist of digits and\"-\"！");
				}
				if(!emailMatcher.matches())
				{
					isAddSuccessful = false;
					wrongInformation.add("Email format error! Correct format: number/letter/underscore+@+address suffix");
				}
				//没有错误消息
				if(wrongInformation.size() == 0)  isAddSuccessful = true;
			}
			
			//输入错误，打印错误消息
			if(isAddSuccessful == false) 
			{
				String wrongText = wrongInformation.get(0);
				for(int i = 1; i < wrongInformation.size(); i++)
				{
					wrongText += ("\n" + wrongInformation.get(i));
				}
				
				showTextArea.setText("Add failed! The error message is as follows:\n" + wrongText);
				wrongInformation.clear();//错误列表置零
			}
			//新增成功，后续处理
			else
			{
				information.clear();
				//从Excel中读取之前全部学生的信息
				List<List<String>> resultList = ExcelUtil.readExcel("src/lab11_3/studentInformation.xlsx");
		        if (resultList != null) 
		        {
		            List<String> addList = Arrays.asList(inputs[0],inputs[1],inputs[2],inputs[3]);
		            resultList.add(addList);//添加到resultList中
		        }
		        //更新Excel内容
		        ExcelUtil.writeExcel(resultList, "src/lab11_3/studentInformation.xlsx");
		        wrongInformation.clear();
		        showTextArea.setText("Added successfully! The new information is as follows:\n" + input.get() );
			}
		}
	}
	
	//删除信息
	public void deleteInformationButtonPressed(ActionEvent e)
	{
		//如果没有打开显示界面
		if(currentMode != Status.DISPLAY)
		{
			showTextArea.setText("Please click the显示button to select the information you want to delete!");
		}
		//执行删除操作
		else
		{
			information.clear();
			//获取information中的内容，从excel中读取
			List<List<String>> resultList = ExcelUtil.readExcel("src/lab11_3/studentInformation.xlsx");
			String deleteInformation = showTextArea.getText();
			//删除信息
	        if (resultList != null) 
	        {
	        	Iterator<List<String>> iter = resultList.iterator();
	        	while(iter.hasNext())
	        	{
	        		String[] str = deleteInformation.split("[ ]+");
	        		List<String> tempList = Arrays.asList(str[0],str[1],str[2],str[3]);
	      	      //当找到和输入的信息相同的数据时，将information中的该条数据删除
	        		if(iter.next().equals(tempList))  iter.remove();
	        	}
	        }
	        //更新excle中的内容
	        ExcelUtil.writeExcel(resultList, "src/lab11_3/studentInformation.xlsx");
	     }
	     
	     currentMode = Status.DELETE;
		 showTextArea.setText("The current record has been deleted! You can click the \\\"显示\\\" button "
					+ "to continue viewing the information!");
	}
	
	//修改信息
	public void modifyInformationButtonPressed(ActionEvent e) throws IOException
	{
		//如果没有打开显示界面
		if(currentMode != Status.DISPLAY)
		{
			showTextArea.setText("Please click the 显示 button first to select the information to be modified!");
		}
		//执行修改操作
		else
		{
			TextInputDialog dialog = new TextInputDialog("Input Here");
			dialog.setTitle("Modify Student Records");
			dialog.setHeaderText("Format:学号 姓名 电话 邮箱信息");
			dialog.setContentText("Please enter student information below:");
			
			Optional<String> input = dialog.showAndWait();
			
			if(input.isPresent())
			{
				//存取输入的String数组，使用一个或者多个空格进行分隔，input.get()获取输入
				String[] inputs = input.get().split("[ ]+");
				//输入为空
				if(input.get().length() == 0 || input.get().equals("Input New Student Information")){
					isAddSuccessful = false;
					wrongInformation.add("Input can not be empty!");
				}
				//输入项数不匹配
				else if(inputs.length != 4){
					isAddSuccessful = false;
					wrongInformation.add("Number of entries does not match!");
				}
				//输入正确
				else if(inputs.length == 4)
				{
					//matcher创建一个匹配器，将给定的输入与此模式匹配
					studentCodeMatcher = studentCodePattern.matcher(inputs[0]);
					nameMatcher = namePattern.matcher(inputs[1]);
					phoneNumberMatcher = phoneNumberPattern.matcher(inputs[2]);
					emailMatcher = emailPattern.matcher(inputs[3]);
					
					//matches返回布尔类型，是全部匹配
					//编译给定正则表达式并尝试将给定输入与其匹配
					if(!studentCodeMatcher.matches())
					{
						isAddSuccessful = false;
						wrongInformation.add("Student ID can only consist of numbers！");
					}
					if(!nameMatcher.matches())
					{
						isAddSuccessful = false;
						wrongInformation.add("Name cannot contain numbers！");
					}
					if(!phoneNumberMatcher.matches())
					{
						isAddSuccessful = false;
						wrongInformation.add("Phone numbers can only consist of digits and\"-\"！");
					}
					if(!emailMatcher.matches())
					{
						isAddSuccessful = false;
						wrongInformation.add("Email format error! Correct format: number/letter/underscore+@+address suffix");
					}
					//没有错误消息
					if(wrongInformation.size() == 0)  isAddSuccessful = true;
				}
				
				//当添加学生失败，打印错误信息
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
				//添加成功，进行修改操作
				else
				{
					//读取excel中全部学生信息
					List<List<String>> resultList = ExcelUtil.readExcel("src/lab11_3/studentInformation.xlsx");
					//修改后的信息
					List<String> modifyElement = Arrays.asList(inputs[0],inputs[1],inputs[2],inputs[3]);
					//当前需要被修改的信息
					String[] tempString = showTextArea.getText().split("[ ]+");
					List<String> targetElement = Arrays.asList(tempString[0],tempString[1],tempString[2],tempString[3]);
			        if (resultList != null) 
			        {
			            for (int i = 0; i < resultList.size(); i++) 
			            {
			                List<String> cellList = resultList.get(i); 
			              //找到当前界面显示的待修改的学生，进行修改
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
	
	//查询功能
	public void searchInformationButtonPressed(ActionEvent e) throws IOException
	{
		boolean isSearchSuccessful = false;
		TextInputDialog dialog = new TextInputDialog("Input Here");
		dialog.setTitle("Query Student Records");
		dialog.setHeaderText("Format:姓名");
		dialog.setContentText("Please enter student name below:");
		
		Optional<String> input = dialog.showAndWait();
		
		//当输入存在时，进行查询
		if(input.isPresent())
		{
			information.clear();
			//读取excel中的学生信息
			List<List<String>> resultList = ExcelUtil.readExcel("src/lab11_3/studentInformation.xlsx");
			String targetName = input.get();//获取输入的带查询姓名

	        if (resultList != null) 
	        {
				//遍历所有的学生信息，找到符合条件的学生
	            for (int i = 0; i < resultList.size(); i++) 
	            {
	                List<String> cellList = resultList.get(i);
	                if(cellList.get(1).equals(targetName))//找到符合条件的学生
	                {
	                	String str = cellList.get(0) + " " + cellList.get(1) + " " + cellList.get(2) + " " + cellList.get(3);
	                	information.add(str); //加入information中
	                	isSearchSuccessful = true;
	                }
	            }
	        }
	        //当成功查找到学生
			if(isSearchSuccessful == true)
			{
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Find success!");
				alert.setHeaderText(null);
				alert.setContentText(information.size() + "record(s) found");
				alert.showAndWait();
				currentDisplayPosition = 0; //索引从0开始，展示找到的记录
				showTextArea.setText((currentDisplayPosition+1) + " st record" + "\n" + information.get(currentDisplayPosition));
				currentMode = Status.SEARCH;//设置为查找模式
			}
			//输出错误提示
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
	
	//显示所有学生消息
	public void displayInformationButtonPressed(ActionEvent e)
	{
		//读取excel中的信息
		List<List<String>> resultList = ExcelUtil.readExcel("src/lab11_3/studentInformation.xlsx");
		currentMode = Status.DISPLAY;
		information.clear();
		//将这些信息存入information String ArrayList中
		if (resultList != null) 
        {
            for (int i = 0; i < resultList.size(); i++) 
            {
                List<String> cellList = resultList.get(i);
                String str = cellList.get(0) + " " + cellList.get(1) + " " + cellList.get(2) + " " + cellList.get(3);
                information.add(str);
            }
        }
		currentDisplayPosition = 0; //索引初始化为0
		showTextArea.setText(information.get(currentDisplayPosition));
	}
	
	//显示上一条学生消息
	public void lastInformationButtonPressed(ActionEvent e) 
	{
		//当处于展示模式时
		if(currentMode == Status.DISPLAY)
		{
			currentDisplayPosition--;
			if(currentDisplayPosition < 0)
				currentDisplayPosition = 0;
			showTextArea.setText(information.get(currentDisplayPosition));
		}
		//当处于查询模式时
		else if(currentMode == Status.SEARCH)
		{
			currentDisplayPosition--;
			//当小于0时，将位置设置为第一条的索引
			if(currentDisplayPosition < 0) currentDisplayPosition = 0;
			showTextArea.setText("Find record number " + (currentDisplayPosition+1) + "\n" + information.get(currentDisplayPosition));
		}
		//其他模式不可以使用该操作
		else showTextArea.setText("Please click the 显示 or 查询 button before doing this!");
	}
	
	//显示下一条学生消息
	public void nextInformationButtonPressed(ActionEvent e) 
	{
		//当处于展示模式时
		if(currentMode == Status.DISPLAY)
		{
			currentDisplayPosition++;
			if(currentDisplayPosition >= information.size())
				currentDisplayPosition = information.size() - 1;
			showTextArea.setText(information.get(currentDisplayPosition));
		}
		//当处于查询模式时
		else if(currentMode == Status.SEARCH)
		{
			currentDisplayPosition++;
			//当超过information的大小时，将位置设置为最后一条的索引
			if(currentDisplayPosition >= information.size())  currentDisplayPosition = information.size() - 1;
			showTextArea.setText("Find record number " + (currentDisplayPosition+1) + "\n" + information.get(currentDisplayPosition));
		}
		//其他模式不可以使用该操作
		else showTextArea.setText("Please click the 显示 or 查询 button before doing this!");
	}

}
