package utility.state;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static utility.join.Join.*;

/**
 * Class representing a {@code State} in a {@link StateMachine}.
 * @author Rob Duff 
 *
 */
public class State
{
    private static Logger logger = LogManager.getLogger ( State.class );
    
    protected final String name;
    protected Function<Event,List<Event>> entry;
    protected Function<Event,List<Event>> exit;
    protected Map<Event,Reaction> reactions = new HashMap<Event,Reaction>();
    protected List<StateModel> subModels;
    
    /**
     * Construct a new {@code State}.
     * @param name The name of the {@code State}.
     * @param subModels A list of {@link StateModel} objects representing sub {@code State}s.
     */
    public State ( String name, StateModel... subModels )
    {
        this ( name, null, null, Arrays.asList ( subModels ) );
    }
    
    /**
     * Construct a new {@code State}.
     * @param name The name of the {@code State}.
     * @param subModel A list of {@link StateModel} objects representing sub {@code State}s. (May be empty or {@code null})
     */
    public State ( String name, List<StateModel> subModel )
    {
        this ( name, null, null, subModel );
    }
    
    /**
     * Construct a new {@code State}.
     * @param name The name of the {@code State}.
     * @param entry The {@link Action} to take upon entering the {@code State}.  (May be {@code null}).
     * @param exit The {@link Action} to take upon exiting the {@code State}.  (May be {@code null}).
     * @param subModel A list of {@link StateModel} objects representing sub {@code State}s.
     */
    public State ( String name, Function<Event,List<Event>> entry, Function<Event,List<Event>> exit, StateModel... subModel )
    {
        this ( name, entry, exit, Arrays.asList ( subModel ) );
    }
    
    /**
     * Construct a new {@code State}.
     * @param name The name of the {@code State}.
     * @param entry The {@link Action} to take upon entering the {@code State}.  (May be {@code null}).
     * @param exit The {@link Action} to take upon exiting the {@code State}.  (May be {@code null}).
     * @param subModels A list of {@link StateModel} objects representing sub {@code State}s. (May be empty or {@code null})
     */
    public State ( String name, Function<Event,List<Event>> entry, Function<Event,List<Event>> exit, List<StateModel> subModels )
    {
        if ( name == null || name.isEmpty() )
            throw new IllegalArgumentException ( "State Name must not be null or blank." );
        
        this.name = name;
        this.entry = entry;
        this.exit = exit;
        
        this.subModels = new ArrayList<>();
        
        if ( subModels != null )
            this.subModels.addAll ( subModels );
    }
    
    /**
     * @param entry The {@link Action} to take upon entering the {@code State}.  (May be {@code null}).
     */
    public final void setEntry ( Function<Event,List<Event>> entry )
    {
        this.entry = entry;
    }
    
    /**
     * @param exit The {@link Action} to take upon exiting the {@code State}.  (May be {@code null}).
     */
    public final void setExit ( Function<Event,List<Event>> exit )
    {
        this.exit = exit;
    }

    /**
     * @return A list of {@link StateModel} objects representing sub {@code State}s.
     */
    public List<StateModel> subModels()
    {
        return subModels;
    }
    
    /**
     * @return A {@link Map} of {@link Reactions}s this {@code State} is capable of handling.
     */
    public final Map<Event,Reaction> reactions()
    {
        return reactions;
    }
    
    /**
     * @return The name of the {@code State} (Including the names of the currently active sub {@code State}s.
     */
    @Override
    public String toString()
    {
        StringBuilder nameBuilder = new StringBuilder();
        
        nameBuilder.append ( name );
        
        if ( !subModels.isEmpty() )
        {
            nameBuilder.append ( "(" );
            nameBuilder.append ( join ( ",", subModels ) );
            nameBuilder.append ( ")" );
        }
        
        return nameBuilder.toString();
    }
    
    /**
     * Enters the {@code State}.
     * @param event The {@link Event} triggering the {@code Reaction}.
     * @return A List of newly created {@link Event}s generated by the {@link Action} of entering this {@code State}.
     */
    public final List<Event> enter ( Event event )
    {
        logger.debug ( "State " + name + ": Entering State per Event " + event );
        
        List<Event> newEvents = new ArrayList<Event>();
        
        for ( StateModel model : subModels )
        {
            logger.debug ( "State " + name + ": Entering SubModel" );
            newEvents.addAll ( model.enter ( event ) );
        }
     
        if ( entry != null )
        {        
            logger.debug ( "State " + name + ": Calling Entry Action" );
            
            List<Event> actionEvents = entry.apply ( event );
            
            if ( actionEvents != null )
                newEvents.addAll ( actionEvents );
        }

        logger.debug ( "State " + name + ": Enter Returning New Events [ " + join ( ", ", newEvents ) + " ]" );
        return newEvents;
    };
    
    /**
     * Exits the {@code State}.
     * @param event The {@link Event} triggering the {@code Reaction}.
     * @return A List of newly created {@link Event}s generated by the {@link Action} of exiting this {@code State}.
     */
    public final List<Event> exit ( Event event )
    {
        logger.debug ( "State " + name + ": Exiting State per Event " + event );
        
        List<Event> newEvents = new ArrayList<Event>();
        
        for ( StateModel model : subModels )
        {
            logger.debug ( "State " + name + ": Exiting SubModel" );
            newEvents.addAll ( model.exit ( event ) );
        }
     
        if ( exit != null )
        {        
            logger.debug ( "State " + name + ": Calling Exit Action" );
            
            List<Event> actionEvents = exit.apply ( event );
            
            if ( actionEvents != null )
                newEvents.addAll ( actionEvents );
        }

        logger.debug ( "State " + name + ": Exit Returning New Events [ " + join ( ", ", newEvents ) + " ]" );
        return newEvents;
    };
    
    /**
     * Send an Event to a {@code State}'s sub {@code State}s.
     * @param event The {@link Event} triggering the {@code Reaction}.
     * @return A List of newly created {@link Event}s generated by reacting to the triggering {@code Event}.
     */
    public final List<Event> react ( Event event )
    {
        logger.debug ( "State " + name + ": Reacting per Event " + event );
        
        List<Event> newEvents = new ArrayList<>();
        
        for ( StateModel model : subModels )
        {
            logger.debug ( "State " + name + ": SubModel Reacting" );
            newEvents.addAll ( model.react ( event ) );
        }

        logger.debug ( "State " + name + ": React Returning New Events [ " + join ( ", ", newEvents ) + " ]" );
        return newEvents;
    }
    
    @Override
    public boolean equals ( Object obj )
    {
        if ( this == obj )
            return true;
        
        if ( obj == null )
            return false;
        
        if ( obj instanceof String )
            return name.equals ( obj );
        
        if ( getClass() != obj.getClass() )
            return false;
        
        State other = ( State ) obj;
        
        if ( name == null )
        {
            if ( other.name != null )
                return false;
        }
        else if ( !name.equals ( other.name ) )
            return false;
        
        return true;
    }

    @Override
    public int hashCode ()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( name == null ) ? 0 : name.hashCode () );
        return result;
    };
    
    /**
     * {@link State} representing the final {@link State} of the {@StateModel}. (For Internal Use Only!)
     */
    static final State TERMINATED_STATE = new State ( "STATE_MODEL_TERMINATED_STATE" );
}