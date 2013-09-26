import java.util.ArrayList;

class Possibilities
{
	ArrayList<Integer> possibilities;
	int row, col, group;

	public Possibilities(int row, int col, int group, ArrayList<Integer> possibilities)
	{
		this.possibilities = possibilities;
		this.row	= row;
		this.col	= col;
		this.group	= group;
	}
}
