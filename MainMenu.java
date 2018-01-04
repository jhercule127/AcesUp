package edu.buffalo.cse116;



import java.util.List;

import javax.swing.ImageIcon;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.application.Application;
import javafx.application.Platform;


import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Skinnable;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * Make a Gui class using the classes I made. 
 * 
 * End Result: Use the launch method within Main.java.
 * @author coreyalm
 * 
 * TODO:Draw a rectangle above the background color and put a black circle above rectangle FINISHED
 * TODO: Click button to switch to a different scene.
 * 
 * Defines a GUI application that includes the New Game menu with menu items to ... 
 * and Exit
 * 
 */
public class MainMenu extends Application {
	private AcesView view;
	private BorderPane gamescene;
	
	@Override
	public void start(Stage mainWindow) throws Exception {
		AcesUpController control = new AcesUpController();;
		view = new AcesView(control);
		
		gamescene= view.startAcesGame();
	
		
		MenuItem quitMenuItm = new MenuItem("Quit");
		quitMenuItm.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	Platform.exit();
		    }
		});
		MenuItem NewMenuItm = new MenuItem("New Game");
		quitMenuItm.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	Platform.exit();
		    }
		});
	

		Menu menu = new Menu("Options");
		menu.getItems().addAll( NewMenuItm,quitMenuItm);
		MenuBar menuBar = new MenuBar(menu);
		menuBar.setPrefHeight(30);
		menuBar.prefWidthProperty().bind(mainWindow.widthProperty());
		gamescene.setBottom(menuBar);
		
		Scene scene1 = new Scene(gamescene, 900,500);  //, mainWidth, mainHeight);
		
		mainWindow.setTitle("AcesUp");

		mainWindow.setScene(scene1);
		mainWindow.show();	

	}
	
	
	public static void main(String args[]){           
		
		launch(args);      
	} 
}