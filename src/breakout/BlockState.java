package breakout;

/**
 * Represents a block. 
 *
 * @immutable
 */
public final class BlockState {
    // TODO: implement
	private final Point blockTL;
	private final Point blockBR;
	
	/**
	* Initializes this object with the given topLeft and topRight.
	*
	* @pre Argument {@code blockTL} is not {@code null}.
	* | blockTL != null
	* @pre Argument {@code blockBR} is not {@code null}.
	* | blockBR != null
	* @post This object's topLeft equal the given topLeft.
	* | getBlockTL() == blockTL
	* @post This object's topRight equal the given topRight.
	* | getBlockBR() == blockBR
	*/
    public BlockState(Point blockTL, Point blockBR) {
        this.blockTL = blockTL;
        this.blockBR = blockBR;
    }
    
    /**
	* Returns this object's topLeft.
	*/
    public Point getBlockTL() {
		return blockTL;
	}
    
    /**
	* Returns this object's topRight.
	*/
    public Point getBlockBR() {
		return blockBR;
	}
}
