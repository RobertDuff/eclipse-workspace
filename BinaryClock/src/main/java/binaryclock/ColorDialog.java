package binaryclock;

import java.io.IOException;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Dialog;


public class ColorDialog extends Dialog<Void>
{
	protected ColorPicker backgroundColor;
	protected ColorPicker offColor;
	protected ColorPicker onColor;
	
	ColorDialog()
	{
		super();
		
		ButtonType loginButtonType = new ButtonType ( "OK", ButtonData.OK_DONE );
		getDialogPane().getButtonTypes().add ( loginButtonType );
		getDialogPane().lookupButton(loginButtonType).setDisable ( false );
		
		Parent content = null;
		
		try
		{
			content = FXMLLoader.load ( ClassLoader.getSystemResource ( "ColorDialog.fxml" ) );
		}
		catch ( IOException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		ObservableList<Node> nodes = content.getChildrenUnmodifiable ();
		
		backgroundColor = ( ColorPicker ) nodes.get ( 3 );
		offColor        = ( ColorPicker ) nodes.get ( 4 );
		onColor         = ( ColorPicker ) nodes.get ( 5 );
		
		getDialogPane().setContent ( content );
	}
	
	public ColorPicker getBackgroundColorPicker()
	{
		return backgroundColor;
	}
	
	public ColorPicker getOffColorPicker()
	{
		return offColor;
	}
	
	public ColorPicker getOnColorPicker()
	{
		return onColor;
	}
}
