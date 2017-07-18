package binaryclock;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import utility.javafx.ResizableCanvasHelper;


public class ClockRenderer implements ResizableCanvasHelper
{
	public static enum Orientation
	{
		LANDSCAPE,
		PORTRAIT,
	}

	protected Orientation orientation = Orientation.LANDSCAPE;
	protected double padding;	
	protected boolean[][] bits = new boolean[][] { { false, false, false, false }, { false, false, false, false }, { false, false, false, false }, { false, false, false, false }, { false, false, false, false }, { false, false, false, false } };
	protected Property<Color> backgroundColor = new SimpleObjectProperty<Color> ( Color.BLACK );
	protected Property<Color> offColor = new SimpleObjectProperty<Color> ( Color.BLACK );
	protected Property<Color> onColor = new SimpleObjectProperty<Color> ( Color.BLACK );
	protected BitShape bitShape;
	
	public ClockRenderer setOrientation ( Orientation orientation )
	{
		this.orientation = orientation;
		return this;
	}
	
	public ClockRenderer setPadding ( double padding )
	{
		this.padding = padding;
		return this;
	}
	
	public ClockRenderer setBits ( boolean[][] bits )
	{
			this.bits = bits;
			return this;
	}
	
	public Property<Color> getBackgroundColorProperty()
	{
		return backgroundColor;
	}
	
	public Property<Color> getOffColorProperty()
	{
		return offColor;
	}
	
	public Property<Color> getOnColorProperty()
	{
		return onColor;
	}
		
	public ClockRenderer setBitShape ( BitShape bitShape )
	{
		this.bitShape = bitShape;
		return this;
	}
	
	@Override
	public void draw ( Canvas canvas )
	{
		GraphicsContext gc = canvas.getGraphicsContext2D ();
		
		gc.setFill ( backgroundColor.getValue () );
		gc.fillRect ( 0, 0, canvas.getWidth(), canvas.getHeight() );
		
		double blockWidth = 0.0;
		double blockHeight = 0.0;
		
		switch ( orientation )
		{
			case LANDSCAPE:
				blockWidth = canvas.getWidth () / 6;
				blockHeight = canvas.getHeight () / 4;
				break;
				
			case PORTRAIT:
				blockWidth = canvas.getWidth () / 4;
				blockHeight = canvas.getHeight () / 6;
				break;
		}
		
		for ( int digit = 0; digit < bits.length; digit++ )
			for ( int bit = 0; bit < bits[ digit ].length; bit++ )
			{
				double xOffset = 0;
				double yOffset = 0;
				
				switch ( orientation )
				{
					case LANDSCAPE:						
						xOffset = digit * blockWidth;
						yOffset = bit * blockHeight;
						break;
						
					case PORTRAIT:						
						xOffset = bit * blockWidth;
						yOffset = digit * blockHeight;
						break;
				}

				Bounds bounds = new BoundingBox ( xOffset + padding, yOffset + padding, blockWidth - ( 2 * padding ), blockHeight - ( 2 * padding ) );
				
				switch ( bitShape )
				{
					case DOT:
						if ( bits[ digit ][ bit ] )
						{
							gc.setFill ( onColor.getValue () );
							gc.fillOval ( bounds.getMinX(), bounds.getMinY(), bounds.getWidth(), bounds.getHeight() );
						}
						else
						{
							gc.setStroke ( offColor.getValue () );
							gc.strokeOval ( bounds.getMinX(), bounds.getMinY(), bounds.getWidth(), bounds.getHeight() );
						}
						break;
						
					case BOX:
						if ( bits[ digit ][ bit ] )
						{
							gc.setFill ( onColor.getValue () );
							gc.fillRect ( bounds.getMinX(), bounds.getMinY(), bounds.getWidth(), bounds.getHeight() );
						}
						else
						{
							gc.setStroke ( offColor.getValue () );
							gc.strokeRect ( bounds.getMinX(), bounds.getMinY(), bounds.getWidth(), bounds.getHeight() );
						}
						break;
						
					default:
						break;
				}
			}
	}
}
