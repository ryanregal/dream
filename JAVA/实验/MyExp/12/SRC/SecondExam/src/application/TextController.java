package application;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;

public class TextController {

    @FXML
    private RadioButton differentCountButton;
    @FXML
    private RadioButton showMostButton;
    @FXML
    private RadioButton allCountButton;
    @FXML
    private TextArea inputTextArea;
    @FXML
    private TextArea showTextArea;
    @FXML
    private RadioButton showAllButton;
    @FXML
    private Button saveButton;
    @FXML private ToggleGroup tg;

	//��ʼ��
	public void initialize() 
	{
	    tg = new ToggleGroup();
	    //��ѡ��ť
	    differentCountButton.setToggleGroup(tg);
	    showMostButton.setToggleGroup(tg);
	    allCountButton.setToggleGroup(tg);
	    showAllButton.setToggleGroup(tg);
	}
    

    //ͳ�Ʋ�ͬ���ʵ������������ִ�Сд��
    @FXML
    void differentCountButtonSelected(ActionEvent event) {
    	showTextArea.clear();
    	int differTimes = 0;
    	String line=inputTextArea.getText().toUpperCase();//�����ִ�Сд
    	//����һ��String��Integer��ֵ�Ĺ�ϣ��
		HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
		//ʹ��Pattern.compile������ʵ�ֶ�ָ���ַ����Ľ�ȡ
		//����Pattern����p���ұ���������ʽ
		Pattern p=Pattern.compile("[;.,\"\\?!:������(){}']");//

		String[] lineWords = line.split("[ -]+");
		for(int i = 0; i < lineWords.length; i++)
		{
		    //��Pattern���matcher()��������һ��Matcher����
			Matcher m= p.matcher(lineWords[i]);
			//����Щ�����ַ���ɾ��
			lineWords[i] = m.replaceAll("");
			if(!lineWords[i].isEmpty())
			{
				//�����Ϊ�գ���û���ظ�����ͬ����������һ��
				if(!hashMap.containsKey(lineWords[i])){
					hashMap.put(lineWords[i], 1);
					differTimes++;
				}
			}	
		}
		showTextArea.setText("��ͬ��������Ϊ��"+differTimes);
    }
    
    //ͳ�����е��ʵ�����
    @FXML
    void allCountButtonSelected(ActionEvent event) {
    	showTextArea.clear();
    	String line=inputTextArea.getText();
    	int times=0;
		//ʹ��Pattern.compile������ʵ�ֶ�ָ���ַ����Ľ�ȡ
		//����Pattern����p���ұ���������ʽ
		Pattern p=Pattern.compile("[;.,\"\\?!:������(){}']");//
		String[] lineWords = line.split("[ -]+");
		for(int i = 0; i < lineWords.length; i++)
		{
		    //��Pattern���matcher()��������һ��Matcher����
			Matcher m= p.matcher(lineWords[i]);
			//����Щ�����ַ���ɾ��
			lineWords[i] = m.replaceAll("");
			if(!lineWords[i].isEmpty())
			{
				times++;
			}	
		}
		showTextArea.setText("���е�������Ϊ��"+times);
    }
    
    //��������ĸ������ʾ���е��ʼ�����ִ���
    @FXML
    void showAllButtonSelected(ActionEvent event) {
    	showTextArea.clear();
    	String line=inputTextArea.getText();
    	//����һ��String��Integer��ֵ�Ĺ�ϣ��
		HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
		//ʹ��Pattern.compile������ʵ�ֶ�ָ���ַ����Ľ�ȡ
		//����Pattern����p���ұ���������ʽ
		Pattern p=Pattern.compile("[;.,\"\\?!:������(){}']");//

		String[] lineWords = line.split("[ -]+");
		for(int i = 0; i < lineWords.length; i++)
		{
		    //��Pattern���matcher()��������һ��Matcher����
			Matcher m= p.matcher(lineWords[i]);
			//����Щ�����ַ���ɾ��
			lineWords[i] = m.replaceAll("");
			if(!lineWords[i].isEmpty())
			{
				//�����Ϊ�գ��򽫸õ��ʼ��뵽��ϣ���key�У���ӦΪ1
				if(!hashMap.containsKey(lineWords[i])){
					hashMap.put(lineWords[i], 1);
				}
				else{
					int times = hashMap.get(lineWords[i]);
					times++;//��¼���ʳ����˼���
					hashMap.put(lineWords[i], times);
				}
			}	
		}
		//������ĸ�����������
		//entrySet����ӳ���а�����ӳ��ļ�����ͼ��������ӳ��֧�֣���˶�ӳ��ĸ��Ļᷴӳ�ڼ����У���֮��Ȼ��
		List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(hashMap.entrySet());
		list.sort(new Comparator<Map.Entry<String, Integer>>()
		{
			@Override
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2)
			{
				return o1.getKey().toUpperCase().compareTo(o2.getKey().toUpperCase());//��д����ϣ���ǱȽ���ĸ
			}
		});
		
		int[] a= new int[26];
		String output = "";
		for(Map.Entry<String, Integer> entry : list)
		{
			//ʹ��keySet()������ȡ���е�keyֵ
			char letter=Character.toUpperCase(entry.getKey().charAt(0));//�����ִ�Сд
			if(a[letter-'A']==0) {
				output=String.join("", output,"\n",String.valueOf(letter),":\n");
				a[letter-'A']=1;
			}
			output=String.join("", output,entry.getKey()+":"+entry.getValue()+"\n" );
		}	
		showTextArea.setText(output);
	}
    	
    //Ƶ�����20�����ʼ������
    @FXML
    void showMostButtonSelected(ActionEvent event) {
    	showTextArea.clear();
    	String line=inputTextArea.getText();
    	//����һ��String��Integer��ֵ�Ĺ�ϣ��
		HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
		//ʹ��Pattern.compile������ʵ�ֶ�ָ���ַ����Ľ�ȡ
		//����Pattern����p���ұ���������ʽ
		Pattern p=Pattern.compile("[;.,\"\\?!:������(){}']");//

		String[] lineWords = line.split("[ -]+");
		for(int i = 0; i < lineWords.length; i++)
		{
		    //��Pattern���matcher()��������һ��Matcher����
			Matcher m= p.matcher(lineWords[i]);
			//����Щ�����ַ���ɾ��
			lineWords[i] = m.replaceAll("");
			if(!lineWords[i].isEmpty())
			{
				//�����Ϊ�գ��򽫸õ��ʼ��뵽��ϣ���key�У���ӦΪ1
				if(!hashMap.containsKey(lineWords[i])){
					hashMap.put(lineWords[i], 1);
				}
				else
				{
					int times = hashMap.get(lineWords[i]);
					times++;//��¼���ʳ����˼���
					hashMap.put(lineWords[i], times);
				}
			}	
		}
		//����Ƶ���������
		//entrySet����ӳ���а�����ӳ��ļ�����ͼ��������ӳ��֧�֣���˶�ӳ��ĸ��Ļᷴӳ�ڼ����У���֮��Ȼ��
		List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(hashMap.entrySet());
		list.sort(new Comparator<Map.Entry<String, Integer>>()
		{
			@Override
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2)
			{
				return o2.getValue().compareTo(o1.getValue());//��д����ϣ���ǱȽ�Ƶ��
			}
		});
		int count=0;
		String output="";
		for(Map.Entry<String, Integer> entry : list)
		{
			output=String.join("", output,entry.getKey()+":"+entry.getValue()+"\n");
			count++;
			if(count>=20) break;
		}	
		showTextArea.setText(output);
    }
    
    //�������ͳ�ƽ��
    @FXML
    void saveButtonClick(ActionEvent event) {
		FileChooser chooser = new FileChooser();
        chooser.setTitle("����");
        chooser.setInitialDirectory(new File("C:\\"));
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("�ı��ļ�(*.txt)", "*.txt");
        FileChooser.ExtensionFilter filter2 = new FileChooser.ExtensionFilter("�����ļ�(*.*)", "*.*");
        chooser.getExtensionFilters().add(filter);
        chooser.getExtensionFilters().add(filter2);
 
        if(showTextArea.getText().isEmpty()) {
        	 Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("����");
             alert.setHeaderText("���ļ��޷�����");
             alert.setContentText("");
	         alert.showAndWait();
	         return;
        }
        else {
	        File file = chooser.showSaveDialog(null);
	        if (file == null) {
	            Alert alert = new Alert(Alert.AlertType.INFORMATION);
	            alert.setTitle("����");
	            alert.setHeaderText("��ǰ�ļ�δ����");
	            alert.setContentText("");
	            alert.showAndWait();
	        }
	        else{
	        	FileWriter fw = null;
	        	try
	        	{
	        		fw = new FileWriter(file);
	        		fw.write(showTextArea.getText());
	        		fw.flush();
	        	}
	        	catch(Exception e){}
	        	finally{
	        		try{
	        			if(fw != null) fw.close();
	        		}
	        		catch(Exception e){}
	        	}
	        }
        }
    }
}
