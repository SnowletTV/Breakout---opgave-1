package breakout;

/**
 * This class represents a block in a 2-dimensional integer grid.
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
	* @pre Argument {@code blockBR} is right of Argument {@code blockBR}.
	* | blockTL.getX() < blockBR.getX()
	* @pre Argument {@code blockBR} is below Argument {@code blockBR}.
	* | blockTL.getY() < blockBR.getY()
	* @pre Argument {@code blockTL} is not left of the field.
	* | blockTL.getX() >= 0
	* @pre Argument {@code blockTL} is not right of the field.
	* | blockTL.getX() < 50000
	* @pre Argument {@code blockTL} is not ontop of the field.
	* | blockTL.getY() >= 0
	* @pre Argument {@code blockTL} is not below of the field.
	* | blockTL.getY() < 30000
	* @pre Argument {@code blockBR} is not left of the field.
	* | blockBR.getX() > 0
	* @pre Argument {@code blockBR} is not right of the field.
	* | blockBR.getX() <= 50000
	* @pre Argument {@code blockBR} is not ontop of the field.
	* | blockBR.getY() > 0
	* @pre Argument {@code blockBR} is not below of the field.
	* | blockBR.getY() <= 30000
	* @post This object's topLeft equal the given topLeft.
	* | getBlockTL() == blockTL
	* @post This object's topRight equal the given topRight.
	* | getBlockBR() == blockBR
	* @post blockTL is not left of the field.
	* | getBlockTL().getX() >= 0
	* @post blockTL is not right of the field.
	* | getBlockTL().getX() < 50000
	* @post blockTL is not ontop of the field.
	* | getBlockTL().getY() >= 0
	* @post blockTL is not below of the field.
	* | getBlockTL().getY() < 30000
	* @post blockBR is not left of the field.
	* | getBlockBR().getX() > 0
	* @post blockBR is not right of the field.
	* | getBlockBR().getX() <= 50000
	* @post blockBR is not ontop of the field.
	* | getBlockBR().getY() > 0
	* @post blockBR is not below of the field.
	* | getBlockBR().getY() <= 30000
	*/
    public BlockState(Point blockTL, Point blockBR) {
        this.blockTL = blockTL;
        this.blockBR = blockBR;
    }
    
    /**
	* Returns this object's topLeft.
	* @post | result != null
	*/
    public Point getBlockTL() {
		return blockTL;
	}
    
    /**
	* Returns this object's topRight.
	* @post | result != null
	*/
    public Point getBlockBR() {
		return blockBR;
	}
}
