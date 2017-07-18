package binaryclock;

import java.net.URL;
import java.util.ResourceBundle;

import binaryclock.ClockRenderer.Orientation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import utility.javafx.ResizableCanvas;


public class BinaryClock implements Initializable
{
	@FXML
	public VBox vbox;
	
	@FXML
	public CheckMenuItem hour24CheckMenuItem;
	
	@FXML 
	public MenuItem colorMenuItem;
	
	@FXML
	public StackPane pane;
	
	private ResizableCanvas canvas;
	private BinaryTime binaryTime;
	private ClockRenderer renderer;
	private ColorDialog colorDialog = new ColorDialog();
	
	@Override
	public void initialize ( URL location, ResourceBundle resources )
	{
		binaryTime = new BinaryTime();
		binaryTime.get24HourProperty ().bind ( hour24CheckMenuItem.selectedProperty () );
		
		binaryTime.update ();
		
		renderer = new ClockRenderer ()
				.setOrientation ( Orientation.PORTRAIT )
				.setBitShape ( BitShape.DOT )
				.setPadding ( 2 )
				.setBits ( binaryTime.getBits () );
		
		renderer.getBackgroundColorProperty ().bind ( colorDialog.getBackgroundColorPicker ().valueProperty () );
		renderer.getOffColorProperty ().bind ( colorDialog.getOffColorPicker ().valueProperty () );
		renderer.getOnColorProperty ().bind ( colorDialog.getOnColorPicker ().valueProperty () );
		
		colorDialog.getBackgroundColorPicker ().setValue ( Color.BLACK );
		colorDialog.getBackgroundColorPicker ().setValue ( Color.WHITE );
		colorDialog.getBackgroundColorPicker ().setValue ( Color.WHITE );
		
		canvas = new ResizableCanvas ( pane, renderer );
		canvas.draw ();
				
		Timeline timeline = new Timeline ( new KeyFrame ( Duration.seconds ( 1 ), e -> { binaryTime.update(); canvas.draw(); } ) );
		timeline.setCycleCount ( Timeline.INDEFINITE );
		timeline.play ();
		
		colorMenuItem.setOnAction ( e -> colorDialog.showAndWait () );
	}	
}
