package utility.state;

import static utility.join.Join.join;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Class representing a State reaction to an Event.
 * 
 * @author Rob Duff
 */
public class Reaction
{
    public static Logger logger = LogManager.getLogger ( Reaction.class );
    
    protected State destination;
    protected Predicate<Event> guard;
    protected Function<Event,List<Event>> action;
    
    /**
     * Construct a {@code Reaction} with no {@link Guard}, {@link Action}, or destination {@link State}.
     */
    public Reaction()
    {
        this ( null, null, null );
    }
    
    /**
     * Construct a {@code Reaction} with no {@link Guard} or destination {@link State}.
     * @param action The {@link Action} to take during this {@code Reaction}.
     */
    public Reaction ( Function<Event,List<Event>> action )
    {
        this ( null, action, null );
    }

    /**
     * Construct a {@code Reaction} with no {@link Guard} or {@link Action}.
     * @param destination The {@link State} to which to transition after this {@code Reaction}.
     */
    public Reaction ( State destination )
    {
        this ( null, null, destination );
    }
    
    /**
     * Construct a {@code Reaction} with no {@link Guard}.
     * @param action The {@link Action} to take during this {@code Reaction}.
     * @param destination The {@link State} to which to transition after this {@code Reaction}.
     */
    public Reaction ( Function<Event,List<Event>> action, State destination  )
    {
        this ( null, action, destination );
    }
    
    /**
     * Construct a {@code Reaction} with no destination {@link State}.
     * @param guard A {@link Guard} object which will determine if the {@code Reaction} is allowed to proceed.
     * @param action The {@link Action} to take during this {@code Reaction}.
     */
    public Reaction ( Predicate<Event> guard, Function<Event,List<Event>> action )
    {
        this ( guard, action, null );
    }
    
    /**
     * Construct a {@code Reaction} with no {@link Action}.
     * @param guard A {@link Guard} object which will determine if the {@code Reaction} is allowed to proceed.
     * @param destination The {@link State} to which to transition after this {@code Reaction}.
     */
    public Reaction ( Predicate<Event> guard, State destination   )
    {
        this ( guard, null, destination );
    }
    
    /**
     * Construct a {@code Reaction}.
     * @param guard A {@link Guard} object which will determine if the {@code Reaction} is allowed to proceed.
     * @param action The {@link Action} to take during this {@code Reaction}.
     * @param destination The {@link State} to which to transition after this {@code Reaction}.
     */
    public Reaction ( Predicate<Event> guard, Function<Event,List<Event>> action, State destination   )
    {
        this.guard = guard;
        this.action = action;
        this.destination = destination;
    }
    
    /**
     * @param guard A {@link Guard} object which will determine if the {@code Reaction} is allowed to proceed.
     */
    public final void setGuard ( Predicate<Event> guard )
    {
        this.guard = guard;
    }

    /**
     * @param action The {@link Action} to take during this {@code Reaction}.
     */
    public final void setAction ( Function<Event,List<Event>> action )
    {
        this.action = action;
    }

    /**
     * @param destination The {@link State} to which to transition after this {@code Reaction}.
     */
    public final void setDestination ( State destination )
    {
        this.destination = destination;
    }

    /**
     * @return The {@link State} to which to transition after this {@code Reaction}. (May be null).
     */
    public final State destination()
    {
        return destination;
    }
    
    /**
     * @param event The {@link Event} triggering this {@code Reaction}.
     * @return {@code true} if the {@code Reaction} is allowed to proceed.
     */
    public final boolean isAllowed ( Event event )
    {
        if ( guard == null )
            return true;
        
        logger.debug ( "Reaction Checking Guard Condition" );
        return guard.test ( event );
    }
    
    /**
     * Reacts to a triggering {@link Event}.
     * @param event The {@link Event} triggering this {@code Reaction}.
     * @return A List of newly created {@link Event}s generated by the {@code Reaction}.
     */
    public final List<Event> react ( Event event )
    {
        if ( action != null )
        {
            logger.debug ( "Reaction Taking Action" );
            List<Event> newEvents = action.apply ( event );
        
            if ( newEvents != null )
            {
                logger.debug ( "Reaction Returning New Events [ " + join ( ", ", newEvents ) + " ]" );
                return newEvents;
            }
        }
        
        logger.debug ( "Reaction Returning No Events" );
        return Collections.emptyList();
    }
    
    /**
     * A statically defined {@code Reaction} which unconditionally transitions to the Terminated State. (For Internal Use Only!)
     */
    static final Reaction TERMINATE_REACTION = new Reaction ( State.TERMINATED_STATE );
}
