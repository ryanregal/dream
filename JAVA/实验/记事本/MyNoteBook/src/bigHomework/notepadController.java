package bigHomework;

import java.io.InputStreamReader;
import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Optional;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

@SuppressWarnings("unused")
public class notepadController extends Stage implements Initializable
{
	@FXML private Stage stage;
	@FXML private Stage fontStage;
	@FXML private Stage turntoStage;
	private Global global;
	private String fontColor = "BLACK";
	private Font font;
	private double size = 20;
	private int zoomPercentage = 100;
	@FXML public TextArea textArea;
	@FXML public Menu firstMenu;
	@FXML public Menu secondMenu;
	@FXML public Menu thirdMenu;
	@FXML public Menu fourthMenu;
	@FXML public Menu fifthMenu;
	@FXML public ContextMenu textAreaContextMenu;
	@FXML public MenuItem firstMenu_NewFile;
	@FXML public MenuItem firstMenu_Open;
	@FXML public MenuItem firstMenu_Save;
	@FXML public MenuItem firstMenu_SaveAs;
	@FXML public MenuItem firstMenu_PageSet;
	@FXML public MenuItem firstMenu_Exit;
	@FXML public MenuItem secondMenu_Undo;
	@FXML public MenuItem secondMenu_Redo;
	@FXML public MenuItem secondMenu_Cut;
	@FXML public MenuItem secondMenu_Copy;
	@FXML public MenuItem secondMenu_Paste;
	@FXML public MenuItem secondMenu_Delete;
	@FXML public MenuItem secondMenu_Search;
	@FXML public MenuItem secondMenu_PreviousSearch;
	@FXML public MenuItem secondMenu_NextSearch;
	@FXML public MenuItem secondMenu_Replace;
	@FXML public MenuItem secondMenu_TurnTo;
	@FXML public MenuItem secondMenu_SelectAll;
	@FXML public MenuItem secondMenu_TimeAndDate;
	@FXML public MenuItem secondMenu_bingSearch;
	@FXML public MenuItem contextMenuUndo;
	@FXML public MenuItem contextMenuRedo;
	@FXML public MenuItem contextMenuCut;
	@FXML public MenuItem contextMenuCopy;
	@FXML public MenuItem contextMenuPaste;
	@FXML public MenuItem contextMenuDelete;
	@FXML public MenuItem contextMenuSelectAll;
	@FXML public MenuItem contextMenubingSearch;
	@FXML public MenuItem thirdMenu_FontChooser;
	@FXML public CheckMenuItem thirdMenu_ChangeLine;
	@FXML public CheckMenuItem fourthMenu_Status;
	@FXML public MenuItem fifthMenu_Help;
	@FXML public MenuItem fifthMenu_About;
	@FXML public MenuBar menuBar;
	@FXML public Label statusLabel;
	
	//�����ļ�����
	private ExtensionFilter fileChooserFilter1 = new FileChooser.ExtensionFilter("�ı��ĵ�", "*.txt");
	private ExtensionFilter fileChooserFilter2 = new FileChooser.ExtensionFilter("�����ļ�", "*.*");
	
	//���״̬
	//1���½� 2�����޸� 3���ѱ���
	private int status = 1;		
	
	public int getStatus()
	{
		return status;
	}
	private String currentPath;
	
	@SuppressWarnings("unused")
	private String currentFileName;
	public String getCurrentPath()
	{
		return currentPath;
	}	
	public void mainStage(Stage stage)
	{
		this.stage = stage;
	}

	@Override
	//��ʼ��
	public void initialize(URL location, ResourceBundle resources) 
	{
		global = (Global)Main.globals.get("global");
		Main.controllers.put(this.getClass().getSimpleName(), this);
		font = Font.font("΢���ź�", FontWeight.NORMAL, FontPosture.REGULAR, size);
		textArea.setFont(font);
		statusLabel.setText("    �� " + 1 + " ��, �� " + 1 + " ��  " + " �� " + 0 + " �� " + " ���ţ�" + zoomPercentage + "%");
		myEdit();
		fourthMenu_Status.setSelected(true);
		
		//��������
		textArea.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event)
			{
				statusLabelUpdate();//����״̬��
				myEdit();//���ñ༭�˵��ﲿ��ѡ���Ƿ��ѡ
			}
		});
		
		//��ؼ��̰��£���alt�л��˵���ѡ��
		textArea.setOnKeyPressed(new EventHandler<KeyEvent>()
		{
			@Override
			public void handle(KeyEvent event)
			{
				//getCode()��������»���ͷ��¼��еļ������ļ����롣
				if(event.getCode() == KeyCode.ALT)
					menuBar.requestFocus();
			}
		});
		
		//���������
		textArea.setOnScroll(new EventHandler<ScrollEvent>()
		{
			@Override
			public void handle(ScrollEvent event)
			{
				//etDeltaY��ȡ��ֱ������
				if(event.isControlDown() && event.getDeltaY() < 0) zoomout();
				else if(event.isControlDown() && event.getDeltaY() > 0) zoomin();
			}
		});

		//��ؼ��̰���̧��
		textArea.setOnKeyReleased(new EventHandler<KeyEvent>()
		{
			@Override
			public void handle(KeyEvent event)
			{
				if(event.getCode() == KeyCode.ALT) menuBar.requestFocus();
				else  statusLabelUpdate();//����״̬��
			}
		});
		
		//����ı��仯
		textArea.textProperty().addListener(new ChangeListener<String>()
		{
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
			{
				myEdit();//���ñ༭�˵��ﲿ��ѡ���Ƿ��ѡ
			}
		});
	}
	
	//�½��ļ�
	@FXML
	private void newFile()
	{
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("���ܼ��±�");
        alert.setHeaderText("������ı�����");
        alert.setContentText("");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
        	if (getCurrentPath() == null)  exitSave();
            else save();
        }
        else{
            textArea.clear();
            stage.setTitle("�ޱ���");
            status = 1;
            currentPath = null;
        }
	}
	
	//���ļ�
	@FXML
	private void open()
	{
		FileChooser chooser = new FileChooser();
		chooser.setTitle("���ܼ��±�");
		chooser.setInitialDirectory(new File("C:\\"));
		chooser.getExtensionFilters().add(fileChooserFilter1);
		chooser.getExtensionFilters().add(fileChooserFilter2);
		File file = chooser.showOpenDialog(this);
		if(file == null)
		{
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("���ļ�");
            alert.setHeaderText("δѡ���κ��ļ�");
            alert.setContentText("");
            alert.showAndWait();
		}
		else
		{
			status = 2;
			currentPath = file.getAbsolutePath();
			stage.setTitle(file.getName());
			BufferedReader bufferRead = null;
			try
			{
				//InputStreamReader �Ǵ��ֽ������ַ���������������ȡ�ֽڲ�ʹ��ָ�����ַ��������ǽ���Ϊ�ַ��� 
				//��ʹ�õ��ַ�������������ָ����Ҳ������ʽ���������߿��Խ���ƽ̨��Ĭ���ַ�����
				InputStreamReader streamReader = new InputStreamReader(new FileInputStream(file));
				//BufferedReader:����һ��ʹ��Ĭ�ϴ�С�����뻺�����Ļ����ַ�������
				bufferRead = new BufferedReader(streamReader);
		
				StringBuffer strBuffer = new StringBuffer();
				String line;
				
				while((line = bufferRead.readLine()) != null)
				{
					strBuffer.append(line).append(System.getProperty("line.separator"));
				}
				//�ڼ��±���ʾ
				textArea.setText(strBuffer.toString());
			}
			catch(Exception e){}
			finally
			{
				try{
					if(bufferRead != null)  bufferRead.close();
				}
				catch(Exception e1){}
			}
		}
	}
	
	//����
	@FXML
	public void save()
	{
		status = 3;
		if(currentPath == null) saveAs();
		else{
			FileWriter fw = null;
			try{
				fw = new FileWriter(new File(currentPath));
				fw.write(textArea.getText());//д��textArea������
				fw.flush();//ˢ��
				stage.setTitle(currentPath);//title����Ϊ��ǰ·��
			}
			catch(Exception e){}
			finally
			{
				 try {
					 if (fw != null) fw.close();
	             } 
				 catch (IOException e) {}
			}
		}
	}
	
	//���Ϊ
	@FXML
	private void saveAs()
	{
		FileChooser chooser = new FileChooser();
        chooser.setTitle("���ܼ��±�");
        chooser.setInitialDirectory(new File("C:\\"));
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("�ı��ļ�(*.txt)", "*.txt");
        FileChooser.ExtensionFilter filter2 = new FileChooser.ExtensionFilter("�����ļ�(*.*)", "*.*");
        chooser.getExtensionFilters().add(filter);
        chooser.getExtensionFilters().add(filter2);
        File file = chooser.showSaveDialog(this);
        if (file == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("����");
            alert.setHeaderText("��ǰ�ļ�δ���� ��( �� �� ��|||)��");
            alert.setContentText("");
            alert.showAndWait();
        }
        else{
        	status = 3;//��ʾ����
        	FileWriter fw = null;
        	try
        	{
        		fw = new FileWriter(file);
        		fw.write(textArea.getText());
        		currentFileName = file.getName();
        		currentPath = file.getAbsolutePath();
        		fw.flush();
        		stage.setTitle(currentPath);
        	}
        	catch(Exception e){}
        	finally
        	{
        		try{
        			if(fw != null) fw.close();
        		}
        		catch(Exception e){}
        	}
        }
	}
	
	//ֱ���˳�ʱ�ı���
	public void exitSave(){
		saveAs();
	}
	
	//ҳ������
	@FXML
	private void pageSet()
	{
		//PageFormat ������Ҫ��ӡ��ҳ��Ĵ�С�ͷ���
		PageFormat pf = new PageFormat();
		PrinterJob.getPrinterJob().pageDialog(pf);
	}
	
	//�˳�
	@FXML
	private void exit()
	{
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("�ر�");
        alert.setHeaderText("ȷ��Ҫ�뿪��");
        alert.setContentText("");
        Optional<ButtonType> result = alert.showAndWait();
        
        if (result.get() == ButtonType.OK) 
        {
        	//����ļ�δ����
            if (getStatus()!= 3 ) 
            {
        		Alert alert0 = new Alert(AlertType.CONFIRMATION);
        		alert0.setTitle("�ļ�δ����");
        		alert0.setHeaderText("");
        		alert0.setContentText("���뱣���ļ���");
        		ButtonType buttonTypeOne = new ButtonType("��������");
        		ButtonType buttonTypeTwo = new ButtonType("����һ��");
        		alert0.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
        		Optional<ButtonType> result1 = alert0.showAndWait();
        		
        		if (result1.get() == buttonTypeTwo) {
    			  if (getCurrentPath() == null)  exitSave();
                  else  save();
        		}
        		else stage.close();
            }
           //������棬��ֱ�ӹر�
           else stage.close();
        } 
	}
	
	//���ñ༭�˵��ﲿ��ѡ���Ƿ��ѡ
	private void myEdit()
	{
		//����
		if(!textArea.isUndoable())
		{
			secondMenu_Undo.setDisable(true);
			contextMenuUndo.setDisable(true);
		}
		else if(textArea.isUndoable())
		{
			secondMenu_Undo.setDisable(false);
			contextMenuUndo.setDisable(false);
		}
		//�ָ�
		if(!textArea.isRedoable())
		{
			secondMenu_Redo.setDisable(true);
			contextMenuRedo.setDisable(true);
		}
		else if(textArea.isRedoable())
		{
			secondMenu_Redo.setDisable(false);
			contextMenuRedo.setDisable(false);
		}
		//���ѡ��Ϊ�ղ������Ƽ���
		if(textArea.getSelectedText().isEmpty())
		{
			secondMenu_Cut.setDisable(true);
			contextMenuCut.setDisable(true);
			secondMenu_Copy.setDisable(true);
			contextMenuCopy.setDisable(true);
		}
		else if(!textArea.getSelectedText().isEmpty())
		{
			secondMenu_Cut.setDisable(false);
			contextMenuCut.setDisable(false);
			secondMenu_Copy.setDisable(false);
			contextMenuCopy.setDisable(false);
		}
	}
	
	//����״̬��
	private void statusLabelUpdate()
	{
		status = 2;
		int row;	//��
		int line;	//��
		int findStartPos;	//��¼��ǰָ��λ��
		
		String str1 = textArea.getText();
		findStartPos = textArea.getCaretPosition();
		String str2 = str1.substring(0, findStartPos);
		
		String[] strings = str2.split("\n");
		line = strings.length;
		
		if(line == 0)
		{
			str1 += "*";
			str2 = str1.substring(0, findStartPos + 1);
			strings = str2.split("\n");
			line = strings.length;
			row = strings[line - 1].length() - 1;
		}
		else
			row = strings[line - 1].length();

		statusLabel.setText("    �� " + line + " ��, �� " + (row + 1) + " ��  " + " �� " + str1.length() + " �� "  + " ���ţ�" + zoomPercentage + "%");
	}
	
	//����
	@FXML
	private void undo(){
		textArea.undo();
	}
	//��ԭ
	@FXML
	private void redo(){
		textArea.redo();
	}
	//����
	@FXML
	private void cut(){
		textArea.cut();
	}
	//����
	@FXML
	private void copy(){
		textArea.copy();
	}
	//ճ��
	@FXML
	private void paste(){
		textArea.paste();
	}
	
	//ɾ��
	@FXML
	private void delete(){
		try
		{
			textArea.replaceSelection("");
		}
		catch(Exception e){}
	}
	
	//���ҡ��滻����
	@FXML
	public void mySearch() throws IOException
	{
		Parent searchRoot = FXMLLoader.load(getClass().getResource("search.fxml"));
		Scene searchScene = new Scene(searchRoot);
		Stage searchStage = new Stage();
		searchStage.setScene(searchScene);
		searchStage.setTitle("�������滻");
		searchStage.setResizable(false);
		searchStage.show();
	}
	
	//���ҹ��ܵ�ʵ��
	@FXML
	public void search()
	{
		int a = 0, b = 0;
		//��ʼ���ҵ�λ�ã����λ�ã�
		int findStartPos = textArea.getCaretPosition();
		
		String strA, strB;
		strA = textArea.getText();	//�洢�ı�
		strB = global.search;		//��global��Ҫ���ҵ��ı�������
		//����������Ϊ��ʱ
		if(strB.length() == 0)
		{
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("��ѯ���滻");
            alert.setHeaderText("���ҽ��");
            alert.setContentText("�������ݲ���Ϊ�գ�");
            alert.showAndWait();
            return;
		}
		
		//�������λ��
		if(findStartPos >= strA.length()) findStartPos = 0;
		
		if(textArea.getSelectedText().isEmpty())
		{
			////����ʱ���Դ�Сд
			if(global.isCaseIgnored == true)
			{
				//�����ı���ԭ�ı�ȫ���滻�ɴ�д
				String caseIgnoredStrA = strA.toLowerCase();
				String caseIgnoredStrB = strB.toLowerCase();
				//���ش��ַ����е�һ�γ���ָ�����ַ�������������ָ��������ʼ��
				a = caseIgnoredStrA.indexOf(caseIgnoredStrB, findStartPos);
			}
			else a = strA.indexOf(strB, findStartPos);
		}
		//���ض�ѡ���ı�
		else
		{
			//���Դ�Сд
			if(global.isCaseIgnored == true)
			{
				String caseIgnoredStrA = strA.toLowerCase();
				String caseIgnoredStrB = strB.toLowerCase();
				a = caseIgnoredStrA.indexOf(caseIgnoredStrB, findStartPos - caseIgnoredStrB.length() + 1);
			}
			else
				a = strA.indexOf(strB, findStartPos - strB.length() + 1);
		}
		
		
		if(a > -1)
		{
			textArea.positionCaret(a);
			b = strB.length();
			textArea.selectRange(a, a + b);
		}
		//����ʧ��ʱ��������ֵΪ-1
		else
		{
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("��ѯ���滻");
            alert.setHeaderText("���ҽ��");
            alert.setContentText("����û����Ҫ���ҵ����ݣ�");
            alert.showAndWait();
		}
	}
	
	@FXML
	public void previousSearch()
	{
		int a = 0, b = 0;
		
		//��ʼ���ҵ�λ�ã����λ�ã�
		int findStartPos = textArea.getCaretPosition();
		
		String strA, strB;
		strA = textArea.getText();	//�洢�ı�
		strB = global.search;//��global��Ҫ���ҵ��ı�������
		
		if(strB.length() == 0)
		{
			Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("��ѯ���滻");
            alert.setHeaderText("���ҽ��");
            alert.setContentText("�������ݲ���Ϊ��");
            alert.showAndWait();
            return;
		}
		
		String strTemp = strA.substring(0, findStartPos);
		
		if(textArea.getSelectedText().isEmpty())
		{
			strTemp = strA.substring(0, findStartPos);
			if(global.isCaseIgnored == true)
			{
				String caseIgnoredStrTemp = strTemp.toLowerCase();
				String caseIgnoredStrB = strB.toLowerCase();
				a = caseIgnoredStrTemp.lastIndexOf(caseIgnoredStrB);
			}
			else
				a = strTemp.lastIndexOf(strB);
		}
			
		else
		{
			//�����ǰ��ѡ�е��ı���˵���ϴε��Ѿ��ҵ����ͽ���������
			strTemp = strA.substring(0, strTemp.length() - strB.length());
			if(global.isCaseIgnored == true)
			{
				String caseIgnoredStrTemp = strTemp.toLowerCase();
				String caseIgnoredStrB = strB.toLowerCase();
				a = caseIgnoredStrTemp.lastIndexOf(caseIgnoredStrB);
			}
			else
				a = strTemp.lastIndexOf(strB);
		}
		
		//���ҳɹ�
		if(a > -1)
		{
			textArea.positionCaret(a);
			b = strB.length();
			textArea.selectRange(a, a + b);
		}
		//����ʧ��ʱ��������ֵΪ-1
		else
		{
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("��ѯ���滻");
            alert.setHeaderText("���ҽ��");
            alert.setContentText("�Ҳ�����������");
            alert.showAndWait();
		}
	}
	
	//�滻����
	public void replace()
	{
		int a = 0, b = 0;
		//��ʼ���ҵ�λ�ã����λ�ã�
		int findStartPos = textArea.getCaretPosition();
		
		String strA, strB, strC;
		strA = textArea.getText();//ȫ���ı�
		strB = global.search;//����
		strC = global.replace;//�滻
		
		//�����ҵ�����Ϊ��ʱ
		if(strB.length() == 0)
		{
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("��ѯ���滻");
            alert.setHeaderText("�滻���");
            alert.setContentText("�滻������Ϊ��");
            alert.showAndWait();
            return;
		}

		//�������λ��
		if(findStartPos >= strA.length())  findStartPos = 0;
		//ѡ����ı�Ϊ�գ�����ȫ�ֲ���
		if(textArea.getSelectedText().isEmpty())
		{
			//���Դ�Сд
			if(global.isCaseIgnored == true)
			{
				String caseIgnoredStrA = strA.toLowerCase();
				String caseIgnoredStrB = strB.toLowerCase();
				a = caseIgnoredStrA.indexOf(caseIgnoredStrB, findStartPos);
			}
			else a = strA.indexOf(strB, findStartPos);
		}
		//��ѡ����������滻
		else
		{
			if(global.isCaseIgnored == true)
			{
				String caseIgnoredStrA = strA.toLowerCase();
				String caseIgnoredStrB = strB.toLowerCase();
				a = caseIgnoredStrA.indexOf(caseIgnoredStrB, findStartPos - caseIgnoredStrB.length() + 1);
			}
			else a = strA.indexOf(strB, findStartPos - strB.length() + 1);
		}
		
		//���ҳɹ��������滻
		if(a > -1)
		{
			//��������Ŷ�λ��aָʾ��λ�á�
			textArea.positionCaret(a);
			b = strB.length();
			textArea.selectRange(a, a + b);//ѡ������
			//�����滻
			try
			{
				//û���滻�ı������������
				if(strC.length() == 0 && !textArea.getSelectedText().isEmpty()){
					textArea.replaceSelection("");
				}
				//���滻�ı��������滻�ı�����
				if(strC.length() > 0 && !textArea.getSelectedText().isEmpty())
				{
					textArea.replaceSelection(strC);
				}
			}
			catch(Exception e){}
		}
		//����ʧ��ʱ��������ֵΪ-1
		else
		{
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("��ѯ���滻");
            alert.setHeaderText("���ҽ��");
            alert.setContentText("�Ҳ�����������");
            alert.showAndWait();
		}
	}
	
	//ȫ���滻����
	public void replaceAll()
	{
		textArea.positionCaret(0); // �����ŵ��༭����ͷ
        int a = 0, b = 0, replaceCount = 0;
        while (a > -1) 
        {
            int findStartPos = textArea.getCaretPosition();
            String strA, strB, strC;
            strA = textArea.getText();
            strB = global.search;
            strC = global.replace;
            if (strB.length() == 0) 
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("��ѯ���滻");
                alert.setHeaderText("�滻���");
                alert.setContentText("�������ݲ���Ϊ�գ�");
                alert.showAndWait();
                return;
            }
            
    		if(textArea.getSelectedText().isEmpty())
    		{
    			if(global.isCaseIgnored == true)
    			{
    				String caseIgnoredStrA = strA.toLowerCase();
    				String caseIgnoredStrB = strB.toLowerCase();
    				a = caseIgnoredStrA.indexOf(caseIgnoredStrB, findStartPos);
    			}
    			else  a = strA.indexOf(strB, findStartPos);
    		}

    		else
    		{
    			if(global.isCaseIgnored == true)
    			{
    				String caseIgnoredStrA = strA.toLowerCase();
    				String caseIgnoredStrB = strB.toLowerCase();
    				a = caseIgnoredStrA.indexOf(caseIgnoredStrB, findStartPos - caseIgnoredStrB.length() + 1);
    			}
    			else   a = strA.indexOf(strB, findStartPos - strB.length() + 1);
    		}
            
            if (a > -1) 
            {
            	textArea.positionCaret(a);
                b = strB.length();
                textArea.selectRange(a, a + b);
            } 
            else {
                if (replaceCount == 0) //û�п����滻��
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION); // ����һ����Ϣ�Ի���
                    alert.setTitle("��ѯ���滻");
                    alert.setHeaderText("�滻���");
                    alert.setContentText("�Ҳ������ҵ�����");
                    alert.showAndWait(); //ģ̬��ʾ�Ի���
                }
                else 
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION); // ����һ����Ϣ�Ի���
                    alert.setTitle("��ѯ���滻");
                    alert.setHeaderText("�滻���");
                    alert.setContentText("�ɹ��滻 " + replaceCount + "��ƥ������");
                    alert.showAndWait(); //ģ̬��ʾ�Ի���
                }
            }
            if (strC.length() == 0 && !textArea.getSelectedText().isEmpty()) 
            {
            	textArea.replaceSelection("");
                replaceCount++;//��¼�滻����
            }
            if (strC.length() > 0 && !textArea.getSelectedText().isEmpty()) 
            {
            	textArea.replaceSelection(strC);
                replaceCount++;//��¼�滻����
            }
        }
	}
	
	//ת�����ܽ���
	@FXML
	public void myTurnTo() throws IOException
	{
		Parent turntoRoot = FXMLLoader.load(getClass().getResource("turnto.fxml"));
		Scene turntoScene = new Scene(turntoRoot);
		turntoStage = new Stage();
		turntoStage.setScene(turntoScene);
		turntoStage.setTitle("ת��ָ����");
		turntoStage.setResizable(false);
		turntoStage.show();
	}
	
	//ת�����ܵ�ʵ��
	public void turnto()
	{
		int turnto = global.turnto;
		int line;
		
        String strA, strB;
        String[] strings;
        strA = textArea.getText();
        strings = strA.split("\n");
        line = strings.length + 1;
        
        textArea.positionCaret(0);
        //�����볬��������ʱ
        if(turnto >= line)
        {
        	Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("����");
        	alert.setHeaderText("");
        	alert.setContentText("����������������");
        	alert.showAndWait();
        	return;
        }
        
        int i = 0;
        int findStartPos = textArea.getCaretPosition();
        //��겻Խ��
        while (findStartPos <= strA.length()) 
        {
        	//��ȡ����λ�õ�ֵ
        	findStartPos = textArea.getCaretPosition();
            textArea.selectRange(0, findStartPos);
            strB = textArea.getSelectedText();
            strings = strB.split("\n");
            i = strings.length;//һ���ж�����
            if (i == turnto) //��ת������
            {
            	textArea.positionCaret(findStartPos);
                break;
            }
            findStartPos++;
            textArea.positionCaret(findStartPos);
        }
	}
	
	//ȫѡ
	@FXML
	private void selectAll()
	{
		textArea.selectAll();
	}
	
	//����ʱ������
	@FXML
	private void timeAndDate()
	{
		GregorianCalendar calendar = new GregorianCalendar();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int min = calendar.get(Calendar.MINUTE);
		
		String str = 
					(hour + ":" + min + " " 
					+ calendar.get(Calendar.YEAR) + "/" 
					+ (calendar.get(Calendar.MONTH) + 1) 
					+ "/" + calendar.get(Calendar.DAY_OF_MONTH));
		textArea.insertText(textArea.getCaretPosition(), str);
	}
	
	//bing��������
	@FXML
	private void bingSearch()
	{
		String searchString = textArea.getSelectedText();
		try
		{
			//ʹ��bing��searchString ��������
			java.net.URI uri = java.net.URI.create("https://cn.bing.com/search?q=" + searchString);
			java.awt.Desktop dp = java.awt.Desktop.getDesktop();
			if(dp.isSupported(java.awt.Desktop.Action.BROWSE))
			{
				try
				{
					dp.browse(uri);//���ҳ��
				}
				catch(IOException e){}
			}
		}
		catch(Exception e){
            try
            {
            	java.net.URI uri = java.net.URI.create("https://cn.bing.com/");
    			java.awt.Desktop dp = java.awt.Desktop.getDesktop();
    			if(dp.isSupported(java.awt.Desktop.Action.BROWSE))
    			{
    				try
    				{
    					dp.browse(uri);
    				}
    				catch(IOException e1){}
    			}
            }
            catch(Exception e2){}
		}
	}
	
	//����״̬���Ƿ�ɼ�
	@FXML
	private void statusLabelSet()
	{
		statusLabel.setVisible(fourthMenu_Status.isSelected());
	}
	
	//�Ŵ�
	@FXML
	private void zoomin()
	{
		size++;
		zoomPercentage += 10;
		if(size >= 72) size = 72;
		if(zoomPercentage >= 500) zoomPercentage = 500;
		font = Font.font(global.fontFamily, global.fontWeight, global.fontPosture, size);
		textArea.setFont(font);
	}
	
	//��С
	@FXML
	private void zoomout()
	{
		size--;
		zoomPercentage -= 10;
		if(size <= 1) size = 1;
		if(zoomPercentage <= 10) zoomPercentage = 10;
		font = Font.font(global.fontFamily, global.fontWeight, global.fontPosture, size);
		textArea.setFont(font);
	}
	
	//��ʼ������
	@FXML
	private void zoominit()
	{
		size=12;
		zoomPercentage = 100;
		font = Font.font(global.fontFamily, global.fontWeight, global.fontPosture, size);
		textArea.setFont(font);
	}
	
	//�Զ�����
	@FXML
	private void nextLine()
	{
		textArea.setWrapText(thirdMenu_ChangeLine.isSelected());
	}
	
	//�鿴����
	@FXML
	private void help()
	{
		java.net.URI uri = java.net.URI.create("https://www.bing.com/search?q=%e8%8e%b7%e5%8f%96%e6%9c%89%e5%85%b3+windows+10+%e4%b8%ad%e7%9a%84%e8%ae%b0%e4%ba%8b%e6%9c%ac%e7%9a%84%e5%b8%ae%e5%8a%a9&filters=guid:%224466414-zh-hans-dia%22%20lang:%22zh-hans%22&form=T00032&ocid=HelpPane-BingIA");
        java.awt.Desktop dp = java.awt.Desktop.getDesktop();
        if (dp.isSupported(java.awt.Desktop.Action.BROWSE)) 
        {
            try 
            {
                dp.browse(uri);
            } 
            catch (IOException e) {}
        }
	}
	
	//���ڼ��±�
	@FXML
	private void about()
	{
		Alert alert = new Alert(Alert.AlertType.INFORMATION); // ����һ����Ϣ�Ի���
        alert.setTitle("���ܼ��±�");
        alert.setHeaderText("����һ��ģ��Windows notepad��Java���±�����");
        alert.setGraphic(new ImageView(this.getClass().getResource("about.png").toString()));
        alert.showAndWait();
	}
	
	//����ѡ��
	@FXML
	private void fontChooser()
	{
		Parent fontChooserRoot = null;
		try
		{
			fontChooserRoot = FXMLLoader.load(getClass().getResource("fontSet.fxml"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		Scene fontChooserScene = null;
		if(fontChooserRoot != null)
			fontChooserScene = new Scene(fontChooserRoot);
		fontStage = new Stage();
		fontStage.setTitle("����");
		fontStage.setResizable(false);
		fontStage.setScene(fontChooserScene);
		fontStage.show();
	}
	
	//����ѡ�����ȡ����ť����
	public void fontChooserClose()
	{
		fontStage.close();
	}
	
	//����ѡ�����ȷ����ť����
	public void fontChooserConfirm()
	{
		fontColor = global.fontColor;
		font = global.font;
		size = global.size;
		textArea.setFont(font);
		textArea.setStyle("-fx-text-fill:" + fontColor);
	}
	
	//ת������ȡ����ť����
	public void turntoClose()
	{
		turntoStage.close();
	}
}
