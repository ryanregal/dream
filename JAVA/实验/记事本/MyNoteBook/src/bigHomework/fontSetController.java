package bigHomework;

import java.awt.GraphicsEnvironment;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class fontSetController implements Initializable
{
	public Global global;
	public notepadController controller;
	@FXML private ListView<String> fontSelector;
	@FXML private ListView<String> glyphSelector;
	@FXML private ListView<String> sizeSelector;
	@FXML private ComboBox<String> fontColorSelector;
	@FXML private TextField fontTextField;
	@FXML private TextField glyphTextField;
	@FXML private TextField sizeTextField;
	@FXML private TextArea fontShowTextArea;
	@FXML private Button fontInterfaceConfirmButton;
	@FXML private Button fontInterfaceCancelButton;
	private static final String ChineseShowText = "冰熊";
	private static final String EnglishShowText = "icebear";
	private static final String NumberShowText = "0123456789";
	
	//字体颜色
	private String fontColor;
	//字体
	private Font font;
	//存储所有字体
	private ObservableList<String> fontArray;
	//所有字形
	private ObservableList<String> glyphArray;
	//所有大小
	private ObservableList<String> sizeArray;
	private int[] sizeIntArray = {8, 9, 10, 11, 12, 14, 16, 18, 20, 22, 24, 26, 28, 36, 48, 72, 42, 36, 26, 24, 22, 18, 16, 15, 14, 12, 10, 9, 8, 7, 6, 5};
	//所有颜色
	private ObservableList<String> colorArray;
	
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		
		global = (Global)Main.globals.get("global");
		controller = (notepadController)Main.controllers.get(notepadController.class.getSimpleName());
		GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
		fontArray = FXCollections.observableArrayList(e.getAvailableFontFamilyNames());
		glyphArray = FXCollections.observableArrayList("常规","粗体","斜体");
		sizeArray = FXCollections.observableArrayList("8", "9", "10", "11", "12", "14", "16", "18", "20", 
				"22", "24", "26", "28", "36", "48", "72", "初号", "小初", "一号", "小一", "二号", "小二", "三号", "小三", 
				"四号", "小四", "五号", "小五", "六号", "小六", "七号", "八号");		
		colorArray = FXCollections.observableArrayList("BLACK", "BLUE", "GREEN", "GRAY", "RED", "WHITE", "YELLOW");
		//设置 ListView 的基础数据模型。具有必须与 ListView 本身的类型匹配的泛型类型。
		fontSelector.setItems(fontArray);
		glyphSelector.setItems(glyphArray);
		sizeSelector.setItems(sizeArray);
		fontColorSelector.setItems(colorArray);
		//为属性创建单向绑定。
		fontTextField.textProperty().bind(fontSelector.getSelectionModel().selectedItemProperty());
		glyphTextField.textProperty().bind(glyphSelector.getSelectionModel().selectedItemProperty());
		sizeTextField.textProperty().bind(sizeSelector.getSelectionModel().selectedItemProperty());
		fontSelector.getSelectionModel().select("微软雅黑");
		glyphSelector.getSelectionModel().select(0);
		sizeSelector.getSelectionModel().select(4);
		fontColorSelector.getSelectionModel().select(0);
		myFontSet();//字体设置
		
		//监听函数
		fontSelector.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>()
		{
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
			{
				myFontSet();//字体设置
			}
		});
		glyphSelector.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>()
		{
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
			{
				myFontSet();//字体设置
			}
		});
		sizeSelector.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>()
		{
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
			{
				myFontSet();
			}
		});
		fontColorSelector.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>()
		{
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
			{
				myFontSet();
			}
		});
	}
	
	//主要的功能函数，字体的设置
	private void myFontSet()
	{
		double fontSize = 20;//默认五号
		//更改字体大小
		for(int i = 0; i < sizeIntArray.length; i++)
		{
			if(sizeSelector.getSelectionModel().getSelectedItem().equals(sizeArray.get(i)))
			{
				fontSize = Double.parseDouble(String.valueOf(sizeIntArray[i]));
				break;
			}
		}
		//更改字形
		if(glyphSelector.getSelectionModel().getSelectedItem().equals("常规"))
		{
			font = Font.font(fontSelector.getSelectionModel().getSelectedItem(), FontWeight.NORMAL, FontPosture.REGULAR, fontSize);
			global.fontWeight = FontWeight.NORMAL;
			global.fontPosture = FontPosture.REGULAR;
		}
		else if(glyphSelector.getSelectionModel().getSelectedItem().equals("粗体"))
		{
			font = Font.font(fontSelector.getSelectionModel().getSelectedItem(), FontWeight.BOLD, FontPosture.REGULAR, fontSize);
			global.fontWeight = FontWeight.BOLD;
			global.fontPosture = FontPosture.REGULAR;
		}
		else if(glyphSelector.getSelectionModel().getSelectedItem().equals("斜体"))
		{
			font = Font.font(fontSelector.getSelectionModel().getSelectedItem(), FontWeight.NORMAL, FontPosture.ITALIC, fontSize);
			global.fontWeight = FontWeight.NORMAL;
			global.fontPosture = FontPosture.ITALIC;
		}
		//更改字体颜色
		fontColor = fontColorSelector.getSelectionModel().getSelectedItem();
		global.font = font;
		global.fontColor = fontColor;
		//选择字体
		global.fontFamily = fontSelector.getSelectionModel().getSelectedItem();
		global.size = fontSize;
		fontShowTextArea.setFont(font);
		fontShowTextArea.setStyle("-fx-text-fill:" + fontColor);
		fontShowTextArea.setText(ChineseShowText + "\n" + EnglishShowText + "\n" + NumberShowText);
	}
	
	@FXML
	//点击取消按钮
	private void fontInterfaceCancelButtonPressed()
	{
		controller.fontChooserClose();
	}
	
	@FXML
	//点击确认按钮
	private void fontInterfaceConfirmButtonPressed()
	{
		controller.fontChooserConfirm();
		controller.fontChooserClose();
	}
}
