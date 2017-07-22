package utility.geometry;

import java.lang.Math;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TreeMap;

/**
 * A turtle capable of performing the geometric calculations that form the basis
 * of a turtle graphics application.
 * <p>
 * This turtle object is not oriented toward any particular graphics package. It
 * is intended to perform the geometry calculations only.
 * </p>
 * A turtle consists of the following information:
 * <ul>
 * <li>"Point" - A {@link Point} representing the location of the turtle in 2
 * dimensions.</li>
 * <li>"Theta" - A value representing the angle at which the turtle is
 * pointing.</li>
 * <li>"Radius" - The default distance to travel for all movement operations
 * that don't specify an explicit distance.</li>
 * <li>"Phi" - The default angle to turn by for all turn operations that don't
 * specify an explicit angle.</li>
 * <li>"Mirror" - A boolean value which will invert the sign of the Phi angle
 * for turn operations.</li>
 * </ul>
 * <p>
 * The turtle also maintains a stack of stored internal states. A turtle may be
 * moved to a location/angle, push the current state onto the stack, then
 * continue on. When the stack is popped, the turtle returns to the exact
 * location/angle last pushed onto the stack.
 * </p>
 * <p>
 * Additionally, a turtle may mark specific locations/angles with a {@link String} name, so that it may jump to these locations later.
 * </p>
 * <p>
 * The default angle unit is Radians, but the turtle may be configured to work in Degrees.
 * </p>
 * @author Robert Duff
 *
 */
public class Turtle
{
	/**
	 * The angle units (either RADIANS or DEGRESS) in use by the Turtle.
	 * @author Robert Duff
	 *
	 */
	public static enum AngleUnit
	{
		/**
		 * Specify all angles using Radians.
		 */
		RADIANS
		{
			@Override
			public double toInternal ( double theta )
			{
				return theta;
			}

			@Override
			public double toExternal ( double theta )
			{
				return theta;
			}
		},
		/**
		 * Specify all angles using Degrees.
		 */
		DEGREES
		{
			@Override
			public double toInternal ( double theta )
			{
				return Math.toRadians ( theta );
			}

			@Override
			public double toExternal ( double theta )
			{
				return Math.toDegrees ( theta );
			}
		};
		
		/**
		 * Convert an angle value for use in internal calculations (always Radians).
		 * @param theta The angle, in either Radians or Degrees.
		 * @return The angle in Radians.
		 */
		public abstract double toInternal ( double theta );
		
		/**
		 * Convert an internal angle value (always in Radians) to the appropriate external representation.
		 * @param theta An internal angle value (in Radians).
		 * @return The angle value in either Radians or Degrees.
		 */
		public abstract double toExternal ( double theta );
	}
	
	/**
	 * The State of the Turtle
	 * @author Robert Duff
	 *
	 */
	private class State
	{
		/**
		 * The location of the turtle.
		 */
		public Point   point;
		
		/**
		 * The default radius of movement for the turtle.
		 */
		public double  radius;
		
		/**
		 * The current angle the turtle is pointing toward.
		 */
		public double  theta;
		
		/**
		 * The default angle of turn for the turtle.
		 */
		public double  phi;
		
		/**
		 * TRUE if all angles are "mirrored" (i.e. All angles have their signs inverted)
		 */
		public boolean mirror;
		
		/**
		 * Construct a State
		 * <br><br>
		 * The turtle will be at the origin, pointing right (Theta==0), with a radius of 1 and a Phi of 90 degrees (PI/2), and not mirrored.  
		 */
		public State()
		{
			point  = new Point ( 0, 0 );
			radius = 1;
			theta  = 0;
			phi    = Math.PI / 2;
			mirror = false;
		}
		
		/**
		 * Copy Constructor
		 * @param s The State to copy.
		 */
		public State ( State s )
		{
			point = new Point ( s.point );
			radius = s.radius;
			theta  = s.theta;
			phi    = s.phi;
			mirror = s.mirror;
		}
	};
	
	/**
	 * The angle units for all angle measurements.
	 */
	AngleUnit angleUnits;
	
	/**
	 * The current state of the turtle.
	 */
	State myState;
	
	/**
	 * The State stack
	 */
	Deque<State> stateDeque;
	
	/**
	 * The collection of marked states.
	 */
	Map<String,State> marks;
	
	/**
	 * Construct a new Turtle. <br>
	 * <br>
	 * The turtle will be at the origin, pointing right (Theta==0), with a radius of
	 * 1 and a Phi of 90 degrees (PI/2), and not mirrored. The angle units will be
	 * Radians.
	 */
	public Turtle()
	{
		angleUnits = AngleUnit.RADIANS;

		stateDeque = new LinkedList<State>();
		myState = new State();
		
		marks = new TreeMap<String,State>();
	}
	
	// Getters
	
	/**
	 * @return The turtle's current angle.<br>
	 * <br>
	 * In either Radians or Degrees per the specified angle units.
	 */
	public double theta()
	{
		return angleUnits.toExternal ( myState.theta );
	}
	
	/**
	 * @return The turtle's default turn angle.<br>
	 * <br>
	 * In either Radians or Degrees per the specified angle units.
	 */

	public double phi()
	{
		return angleUnits.toExternal ( myState.phi );
	}
	
	/**
	 * @return The turtle's current position
	 */
	public final Point point()
	{
		return myState.point;
	}
	
	/**
	 * @return The turtle's default distance of movement.
	 */
	public double radius()
	{
		return myState.radius;
	}
		
	/**
	 * @return TRUE if the turtle is mirrored.<br>
	 * <br>
	 * That is, all angles will have their signs inverted.
	 */
	public boolean isMirrored()
	{
		return myState.mirror;
	}
	
	/**
	 * @return The current angle units in effect.
	 */
	public AngleUnit angleUnits()
	{
		return angleUnits;
	}
	
	// General Setters
	
	/**
	 * @param units The angle units to use.
	 */
	public void setAngleUnits ( AngleUnit units )
	{
		angleUnits = units;
	}
	
	// PHI Setters
	
	/**
	 * @param phi The default turn angle for the turtle.
	 */
	public void setPhi ( double phi )
	{
		myState.phi = angleUnits.toInternal ( phi );
	}
	
	/**
	 * Toggle mirroring.
	 */
	public void invert()
	{
		myState.mirror = !myState.mirror;
	}
	
	// Theta Setters
	
	/**
	 * @param theta An explicit angle to point the turtle.
	 */
	private void setTheta ( double theta )
	{
		myState.theta = angleUnits.toInternal ( theta );
	}
	
	/**
	 * @param delta A amount by which to adjust the Theta angle.
	 */
	private void adjustTheta ( double delta )
	{
		myState.theta += angleUnits.toInternal ( delta );
	}
	
	/**
	 * Points the turtle in an explicit direction.
	 * @param theta The angle to point toward. (In either Radians or Degrees, based on the current angle units in effect).
	 */
 	public void turnTo ( double theta )
	{
		setTheta ( theta );
	}
	
 	/**
 	 * Turns the turtle by a given angle.
 	 * @param delta The angle to turn. (In either Radians or Degrees, based on the current angle units in effect).
 	 */
	public void turnBy ( double delta )
	{
		delta *= ( myState.mirror? -1 : 1 );
		adjustTheta ( delta );
	}
	
	/**
	 * Turns the turtle by the default angle Phi, scaled by a scaling factor.
	 * @param scale The scaling factor by which to multiply Phi.
	 */
	public void turn ( double scale )
	{
		double delta = myState.phi * scale * ( myState.mirror? -1 : 1 );
		myState.theta += delta;
	}
	
	/**
	 * Turns the turtle by the default angle Phi.
	 */
	public void turn()
	{
		turn ( 1 );
	}
	
	/**
	 * Points the turtle at a specific {@link Point}.
	 * @param p The point to aim at.
	 */
	public void aim ( final Point p )
	{
		aim ( p.x(), p.y() );
	}
	
	/**
	 * Points the turtle to a specific point.
	 * @param x The X coordinate of the point.
	 * @param y The Y coordinate of the point.
	 */
	public void aim ( double x, double y )
	{
		myState.theta = Math.atan2 ( y-myState.point.y (), x-myState.point.x() );
	}
	
	// Radius Setters
	
	/**
	 * @param radius The default radius of movement.
	 */
	public void setRadius ( double radius )
	{
		myState.radius = radius;
	}
	
	/**
	 * Sets the turtle's default radius of movement to the distance between the turtle's current position and the specified {@link Point}.
	 * @param p The other point.
	 */
	public void setRadiusTo ( Point p )
	{
		setRadiusTo ( p.x(), p.y() );
	}
	
	/**
	 * Sets the turtle's default radius of movement to the distance between the turtle's current position and the specified point.
	 * @param x The X coordinate of the point.
	 * @param y The Y coordinate of the point.
	 */
	public void setRadiusTo ( double x, double y )
	{
		setRadiusFromTo ( myState.point.x(), myState.point.y(), x, y );
	}
	
	/**
	 * Sets the turtle's default radius of movement to the length of a given {@link LineSegment}.
	 * @param l The LineSegment.
	 */
	public void setRadiusFromTo ( final LineSegment l )
	{
		setRadius ( l.length () );
	}
	
	/**
	 * Sets the turtle's default radius of movement to the distance between two {@link Point}s.
	 * @param p The first point.
	 * @param q The second point.
	 */
	public void setRadiusFromTo ( final Point p, final Point q )
	{
		setRadiusFromTo ( p.x(), p.y(), q.x(), q.y() );
	}
	
	/**
	 * Sets the turtle's default radius of movement to the distance between two points.
	 * @param x1 The X coordinate of the first point.
	 * @param y1 The Y coordinate of the first point.
	 * @param x2 The X coordinate of the second point.
	 * @param y2 The Y coordinate of the second point.
	 */
	public void setRadiusFromTo ( double x1, double y1, double x2, double y2 )
	{
		setRadius ( Math.sqrt ( Math.pow ( x2-x1, 2 ) + Math.pow ( y2-y1, 2 ) ) );
	}
	
	/**
	 * Scale the default radius of movement.
	 * @param scale The scaling factor by which to multiply the default radius of movement.
	 */
	public void scaleRadius ( double scale )
	{
		myState.radius *= scale;
	}
	
	// Position Setters
	
	/**
	 * Perform a move of the turtle.
	 * @param theta The angle at which to move.
	 * @param radius The distance of move.
	 */
	private void doMove ( double theta, double radius )
	{
		moveTo ( myState.point.x() + radius * Math.cos ( theta ),
				 myState.point.y() + radius * Math.sin ( theta )
				);
	}

	/**
	 * Moves the turtle to a specific point.
	 * @param x The X coordinate of the destination.
	 * @param y The Y coordinate of the destination.
	 * @return A LineSegment from the original location of the turtle to the new location of the turtle.
	 */
	public LineSegment moveTo ( double x, double y )
	{
		Point begin = myState.point;
		myState.point = new Point ( x, y );
		return new LineSegment ( begin, myState.point );
	}
	
	/**
	 * Moves the turtle to a specific {@link Point}.
	 * @param p The destination point.
	 * @return A LineSegment from the original location of the turtle to the new location of the turtle.
	 */
	public LineSegment moveTo ( final Point p )
	{
		Point begin = myState.point;
		myState.point = new Point ( p );
		return new LineSegment ( begin, myState.point );
	}
	
	/**
	 * Move the turtle by a given distance in a given direction.
	 * @param theta The direction to travel. (In either Radians or Degrees, based on the current angle units in effect).
	 * @param radius The distance to travel.
	 * @return A LineSegment from the original location of the turtle to the new location of the turtle.
	 */
	public LineSegment move ( double theta, double radius )
	{
		Point begin = myState.point;
		doMove ( angleUnits.toInternal ( theta ), radius );
		return new LineSegment ( begin, myState.point );
	}
	
	/**
	 * Move the turtle by the default radius of movement in the given direction.
	 * @param theta The direction to travel. (In either Radians or Degrees, based on the current angle units in effect).
	 * @return A LineSegment from the original location of the turtle to the new location of the turtle.
	 */
	public LineSegment moveDir ( double theta )
	{
		Point begin = myState.point;
		doMove ( angleUnits.toInternal ( theta ), myState.radius );
		return new LineSegment ( begin, myState.point );
	}
	
	/**
	 * Move the turtle by the default radius of movement in the direction of the given {@link Point}.
	 * @param p The point to move toward.
	 * @return A LineSegment from the original location of the turtle to the new location of the turtle.
	 */
	public LineSegment moveToward ( Point p )
	{
		return moveToward ( p, myState.radius );
	}
	
	/**
	 * Move the turtle by the default radius of movement in the direction of the given point.
	 * @param x The X coordinate of the point to move toward.
	 * @param y The Y coordinate of the point to move toward.
	 * @return A LineSegment from the original location of the turtle to the new location of the turtle.
	 */
	public LineSegment moveToward ( double x, double y )
	{
		return moveToward ( new Point ( x, y ) );
	}
	
	/**
	 * Move the turtle by the given distance in the direction of the given {@link Point}.
	 * @param p The point to move toward.
	 * @param distance The distance to move.
	 * @return A LineSegment from the original location of the turtle to the new location of the turtle.
	 */
	public LineSegment moveToward ( Point p, double distance )
	{
		Point begin = myState.point;
		doMove ( myState.point.directionTo ( p ), distance );
		return new LineSegment ( begin, myState.point );
	}
	
	/**
	 * Move the turtle by the given distance in the direction of the given point.
	 * @param x The X coordinate of the point to move toward.
	 * @param y The Y coordinate of the point to move toward.
	 * @param distance The distance to move.
	 * @return A LineSegment from the original location of the turtle to the new location of the turtle.
	 */
	public LineSegment moveToward ( double x, double y, double distance )
	{
		return moveToward ( new Point ( x, y ), distance );
	}
	
	/**
	 * Move the turtle along its current direction by the given distance.
	 * @param radius The distance to move.
	 * @return A LineSegment from the original location of the turtle to the new location of the turtle.
	 */
	public LineSegment move ( double radius )
	{
		Point begin = myState.point;
		doMove ( myState.theta, radius );
		return new LineSegment ( begin, myState.point );
	}
	
	/**
	 * Move the turtle by the default radius of movement along its current direction.
	 * @return A LineSegment from the original location of the turtle to the new location of the turtle.
	 */
	public LineSegment move()
	{
		return move ( myState.radius );
	}

	// Stack Operations

	/**
	 * Push the current state of the turtle onto the stack.<br>
	 * <br>
	 * The current state consists of:
	 * <ul>
	 * <li>The turtle's current location.</li>
	 * <li>The turtle's current angle (Theta).</li>
	 * <li>The turtle's current default radius of movement.</li>
	 * <li>The turtle's current default angle of turn (Phi)</li>
	 * <li>If the turtle is currently mirrored or not.</li>
	 * </ul>
	 */
	public void push()
	{
		stateDeque.push ( new State ( myState ) );
	}
	
	/**
	 * Pops a state off the stack and returns the turtle to that state.
	 * @return A LineSegment from the original location of the turtle to the new location of the turtle.
	 */
	public LineSegment pop()
	{
		if ( stateDeque.size () == 0 )
			throw new NoSuchElementException ( "Stack is empty" );
		
		State begin = myState;
		myState = stateDeque.pop();
			
		return new LineSegment ( begin.point, myState.point );
	}
	
	// Marks
	
	/**
	 * Marks the current state of the turtle.<br>
	 * <br>
	 * The current state consists of:
	 * <ul>
	 * <li>The turtle's current location.</li>
	 * <li>The turtle's current angle (Theta).</li>
	 * <li>The turtle's current default radius of movement.</li>
	 * <li>The turtle's current default angle of turn (Phi)</li>
	 * <li>If the turtle is currently mirrored or not.</li>
	 * </ul>
	 * @param label The name of the state.
	 */
	public void mark ( String label )
	{
		State state = new State ( myState );
		marks.put ( label, state );
	}
	
	/**
	 * Jumps the turtle to a saved state.
	 * @param label The name of the state to jump to.
	 * @return A LineSegment from the original location of the turtle to the new location of the turtle.
	 */
	public LineSegment jump ( String label )
	{
		if ( !marks.containsKey ( label ) )
			throw new NoSuchElementException ( "There is no mark named '" + label + "' currently defined" );
		
		State begin = myState;
		myState = marks.get ( label );
		
		return new LineSegment ( begin.point, myState.point );
	}

	/**
	 * Remove a marked state.
	 * @param label The name of the state.
	 */
	public void unmark ( String label )
	{
		marks.remove ( label );
	}
}
