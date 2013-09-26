import java.util.ArrayList;

class Puzzle
{
	final static int ANSWER = 0;
	final static int ONE	= 1;
	final static int TWO	= 2;
	final static int THREE	= 3;
	final static int FOUR	= 4;
	final static int FIVE	= 5;
	final static int SIX	= 6;
	final static int SEVEN	= 7;
	final static int EIGHT	= 8;
	final static int NINE	= 9;

	final static int POSSIBLE = 1;
	final static int IMPOSSIBLE = 0;

	ArrayList<Integer> block = new ArrayList<Integer>();
	int group, row, col;

	public Puzzle(int row, int col)
	{
		for(int i=0; i<10; i++)
		{
			if(i==ANSWER)
				block.add(IMPOSSIBLE);
			else
				block.add(POSSIBLE);
		}	
		this.row = row;
		this.col = col;
		group = calculateGroup(row, col);
	}

	int calculateGroup(int row, int col)
	{
		if(row < 3)
		{
			if(col < 3)
				return 1;
			if(col < 6)
				return 2;
			if(col < 9)
				return 3;
		}
		if(row < 6)
		{
			if(col < 3)
				return 4;
			if(col < 6)
				return 5;
			if(col < 9)
				return 6;
		}
		if(row < 9)
		{
			if(col < 3)
				return 7;
			if(col < 6)
				return 8;
			if(col < 9)
				return 9;
		}
		return 0;
	}

	public void putPossibilities(int index)
	{
		block.set(index, POSSIBLE);
	}

	public boolean removePossibilities(int index)
	{
		boolean returnValue = false;
		if(block.get(index) == POSSIBLE)
			returnValue = true;
		block.set(index, IMPOSSIBLE);
		return returnValue;
	}

	public int putAnswer()
	{
		block.set(ANSWER, 0);
		return 0;
	}
	
	public int putAnswer(int answer)
	{
		block.set(ANSWER, answer);
		return answer;
	}

	public int get(int index)
	{
		return block.get(index);
	}

	public boolean isResolved()
	{
		return block.get(ANSWER)!=IMPOSSIBLE;
	}

	public boolean isUnresolved()
	{
		return block.get(ANSWER)==IMPOSSIBLE;
	}

	public void putAllPossibilities()
	{
		for(int i=ONE;i<=NINE; i++)
		{
			putPossibilities(i);
		}
	}

	public void removeAllPossibilities()
	{
		for(int i=ONE; i<=NINE; i++)
		{
			removePossibilities(i);
		}
	}

	public void showPossibilities()
	{
		System.out.print("block col "+col+" row "+row+" might be number : ");
		for(int i = 1; i < 10; i++)
		{
			if(block.get(i)==1)
				System.out.print(i+", ");
		}
	}

	public ArrayList<Integer> getPossibilities()
	{
		ArrayList<Integer> value = new ArrayList<Integer>();
		for(int i = ONE; i <= NINE; i++)
		{
			if(block.get(i)==POSSIBLE)
				value.add(i);
		}
		return value;
	}

	public int isOnlyHaveOnePossibilities()
	{
		int possibilitiesCount = 0;
		int foundNumber = 0;
		for(int i= 1; i < 10; i++)
		{
			if(block.get(i) == 1)
			{
				possibilitiesCount++;
				foundNumber = i;
			}
		}

		if(possibilitiesCount > 1)
		{
			return 0;
		}
		return foundNumber;
	}
}
