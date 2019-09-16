package MJ;

public abstract class RankTile extends Tile
{
	/**
	 * tile rank
	 */
	protected int rank;
	
	public RankTile(int rank)
	{
		this.rank = rank;
	}
	
	/**
	 * returns true if the tiles have the same rank
	 */
	public boolean matches(Tile other)
	{
		if(super.matches(other))
		{
			RankTile o = (RankTile)other;
			if(this.rank == o.rank)
			{
				return true;
			}
		}
		return false;
	}
}
