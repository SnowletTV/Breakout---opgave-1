package breakout;

public class BlockState {
    // TODO: implement
	Point blockTL;
	Point blockBR;
	
	/**
	* Initializes this object with the given topLeft and topRight.
	*
	* @pre Argument {@code blockTL} is not {@code null}.
	* | blockTL != null
	* @pre Argument {@code blockBR} is not {@code null}.
	* | blockBR != null
	* @post This object's topLeft equal the given topLeft.
	* | getblockTL() == blockTL
	* @post This object's topRight equal the given topRight.
	* | getblockBR() == blockBR
	*/
    public BlockState(Point blockTL, Point blockBR) {
        this.blockTL = blockTL;
        this.blockBR = blockBR;
    }
    
    /**
	* Returns this object's topLeft.
	*/
    public Point getblockTL() {
		return blockTL;
	}
    
    /**
	* Returns this object's topRight.
	*/
    public Point getblockBR() {
		return blockBR;
	}
}
