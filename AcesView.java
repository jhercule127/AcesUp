
package edu.buffalo.cse116;

import java.awt.Event;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

/**
 * <h1>SolitaireView class</h1>
 * <p>
 * <b>Note:</b> This is the View part of Model-View-Controller, it handles the
 * visual aspects of the game.
 * 
 * @author Mike Nicholas & Johnathan Hercules
 * @since 2017-10-30
 */
public class AcesView {
	/**
	 * HashMap for cardImages. Contains the images for the cards.
	 */
	private ArrayList<ImageView> cardImages = new ArrayList<ImageView>();

	private StackPane stockpile = new StackPane();
	private ArrayList<StackPane> tpiles = new ArrayList<StackPane>();
	private StackPane hcp = new StackPane();
	private AcesUpController aces;

	public AcesView(AcesUpController control) {
		aces = control;
	}

	public BorderPane startAcesGame() {
		BorderPane window = new BorderPane();
		HBox h = new HBox(25);
		aces.deal();

		ArrayList<Card>[] tabs = aces.getTableauPiles();
		for (int i = 0; i < 4; i++) {
			StackPane t = new StackPane();
			tpiles.add(t);
			tpiles.get(i).getChildren().add(tabs[i].get(tabs[i].size() - 1).getImage());
			addtoHome(i);
		}

		h.getChildren().addAll(tpiles);

		for (Card c : aces.getDeck()) {
			stockpile.getChildren().add(c.getImage());
		}

		// Events
		stockpile.setOnMousePressed(event -> {
			stockpile.setEffect(new DropShadow(20, Color.BLACK));
		});
		stockpile.setOnMouseReleased(event -> {
			aces.deal();
			ArrayList<Card> stockp = aces.getDeck();
			ArrayList<ImageView> removed = new ArrayList<ImageView>();
			int n = stockpile.getChildren().size() - 1;
			while (n > stockp.size() - 1) {
				removed.add((ImageView) stockpile.getChildren().get(n));
				stockpile.getChildren().remove(n);
				n--;
			}
			for (int x = 0; x < 4; x++) {
				tpiles.get(x).getChildren().add(tabs[x].get(tabs[x].size() - 1).getImage());
			}
			removed.clear();
			stockpile.setEffect(null);
		});

		
//		tpiles.get(0).setOnMousePressed(e -> {
//			tpiles.get(0).setEffect(new DropShadow(20, Color.BLACK));
//		});
//		tpiles.get(0).setOnMouseReleased(e -> {
//			tpiles.get(0).setEffect(null);
//		});

		Rectangle r = new Rectangle();
		r.setFill(Color.GRAY);
		r.setWidth(70);
		r.setHeight(100);
		hcp.getChildren().add(r);
		
		BackgroundFill fill = new BackgroundFill(Color.LIGHTGREEN, null, null);
		Background background = new Background(fill);
		window.setBackground(background);
		window.setLeft(stockpile);
		window.setTop(hcp);
		window.setRight(h);
		window.setMargin(stockpile, new Insets(0, 0, 0, 100));

		return window;

	}

	// public void addEventFromTableauToHomecell(Node pane, int p) {
	//
	// pane.setOnMousePressed(event -> {
	// pane.setEffect(new DropShadow(20, Color.AQUA));
	//
	// });
	// });
	//
	// hcp.setOnMouseReleased(event2 -> {
	// hcp.setEffect(null);
	// });
	// pane.setOnMouseReleased(event -> {
	// pane.setEffect(new DropShadow(20, Color.BLACK));
	// });
	// tpile1.setOnMousePressed(event ->{
	//
	// });
	// }
	//

	public void addtoHome(int pile){
			if(pile >=0 && pile < 4){
				tpiles.get(pile).setOnMousePressed(e1 -> {
					tpiles.get(pile).setEffect(new DropShadow(20, Color.BLACK));
				});
				hcp.setOnMousePressed(e2->{
					if (  aces.removeFromTableau(pile)== true && aces.addToHomecell() == true) {
							 int size =  tpiles.get(pile).getChildren().size() - 1;
							 hcp.getChildren().add(tpiles.get(0).getChildren().get(size));
							 } else {
							 Alert alert = new Alert(AlertType.ERROR);
							 alert.setHeaderText("Illegal movel");
							 alert.show();
							 }
				});
				tpiles.get(pile).setOnMouseReleased(e -> {
					tpiles.get(pile).setEffect(null);
				});
			}
		}

	public static void main(String[] args) {

	}

}