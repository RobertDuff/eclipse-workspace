package binaryclock;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{

	public static void main ( String[] args )
	{
		launch ( args );
	}

	@Override
	public void start ( Stage stage ) throws Exception
	{
		Parent root = FXMLLoader.load ( ClassLoader.getSystemResource ( "BinaryClock.fxml" ) );
    
		Scene scene = new Scene ( root, 600, 400 );
		stage.setScene ( scene );
		stage.setTitle ( "Binary Clock" );
				
		stage.show();
	}

}
