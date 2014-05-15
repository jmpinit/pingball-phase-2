package game;

import java.util.HashSet;
import java.util.Set;

import client.Sprite;
import physics.Circle;
import physics.LineSegment;
import physics.Vect;
import server.NetworkProtocol;
import server.NetworkProtocol.NetworkState;
import server.NetworkProtocol.NetworkState.Field;
import server.NetworkProtocol.NetworkState.FieldName;

/**
 * A Wall is a GamePiece that reflects balls during collisions IFF the Wall is currently not transparent.
 * 
 * That is: a Wall can either be concrete, in which case balls cannot pass through it,
 * or transparent, in which case balls can pass through it.
 * 
 * @author pkalluri
 *
 */
public class Wall implements GamePiece {
    public static final int STATICUID = Sprite.Wall.ID;
    private final int instanceUID;
    private final String name;
    boolean transparency;

    private final LineSegment wall; //based on end points
    
    private static final char SYMBOL = '.';

    
    public Wall(Vect oneEnd, Vect otherEnd) {
        this.instanceUID = NetworkProtocol.getUID();
        this.name = "wall"; //all walls are named "wall"
        this.wall = new LineSegment(oneEnd.x(),oneEnd.y(),otherEnd.x(),otherEnd.y());
        this.transparency = false; //wall is initially concrete
    }


    @Override
    public String getName() {
        checkRep();
        return name;
    }
    
    @Override
    public int getInstanceUID() {
        return instanceUID;
    }
    

    @Override
    public Set<Vect> getTiles() {
        checkRep();
        Set<Vect> tiles = new HashSet<Vect>();
        for (int i=0; i<wall.p2().x()-wall.p1().x(); i++) {
            for (int j=0; j<wall.p2().y()-wall.p1().y();j++) {
                Vect tile = new Vect(
                        Math.min(wall.p1().x(),wall.p2().x()) +i,
                        Math.min(wall.p1().y(),wall.p2().y()) +j
                        );
                tiles.add(tile);
            }
        }
        checkRep();
        return tiles;
    }

  
    @Override
    public char getSymbol() {
        checkRep();
        return SYMBOL;
    }
    

    private Vect getPosition() {
        checkRep();
        return this.wall.p1();
    }

    

    @Override
    public double getTimeTillCollision(Ball ball) {
        if (transparency) {
            return Double.POSITIVE_INFINITY;
        }else{
            final Circle ballShape = new Circle(ball.getPosition(), ball.getRadius());
            return physics.Geometry.timeUntilWallCollision(this.wall, ballShape, ball.getVelocity());
        }
    }


    @Override
    public void progressAndCollide(double amountOfTime, Ball ball) {
        checkRep();
        ball.progressIgnoringPhysicalConstants(amountOfTime);
        
        Vect velocity = physics.Geometry.reflectWall(this.wall,ball.getVelocity());
        ball.setVelocity(velocity);
        checkRep();
    }


    public void setTransparency(boolean transparency) {
        this.transparency = transparency;      
        checkRep();
    }


    @Override
    public void progress(double amountOfTime, double gravity, double mu,
            double mu2) {
        checkRep();
        //Do nothing.  
    }
    
    private void checkRep() {
        assert (this.wall.p1().x() >= 0 && this.wall.p1().x() <=Board.SIDELENGTH);
        assert (this.wall.p2().x() >= 0 && this.wall.p2().x() <=Board.SIDELENGTH);
    }

    @Override
    public NetworkState getState() {
        Field[] fields = new Field[] {
        };
        
        return new NetworkState(fields);
    }

    @Override
    public int getStaticUID() {
        return STATICUID;
    }
    
    private boolean sent = false;
    
    @Override
    public boolean hasBeenSent() {
        return sent;
    }
    
    @Override
    public void hasBeenSent(boolean v) {
        sent = v;
    }
}
