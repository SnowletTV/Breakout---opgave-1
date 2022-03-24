package breakout;

public class BlockState {
    // TODO: implement
	public Point blockTL;
	public Point blockBR;
	
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
	*
	* @post This object's topLeft equal the returned topLeft.
	* | result == blockTL
	*/
    public Point getblockTL() {
		return blockTL;
	}
    
    /**
	* Returns this object's topRight.
	*
	* @post This object's topRight equal the returned topRight.
	* | result == blockBR
	*/
    public Point getblockBR() {
		return blockBR;
	}
}
