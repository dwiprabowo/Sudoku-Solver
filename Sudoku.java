import javax.swing.JOptionPane;
import java.util.ArrayList;

class Sudoku
{
	final static boolean TESTING = true;
	public static Puzzle blocks[][] = new Puzzle[9][9];
	public static void main(String[]args)
	{
		for(int i = 0; i < blocks.length; i++)
		{
			for(int j = 0; j < blocks[i].length; j++)
			{
				blocks[i][j] = new Puzzle(i, j);
			}
		}
		boolean stopGivingValue = false;
		if(TESTING)
		{
			Template.generateBlocksValue();
		}
		else
		{
			//nothing
		}
		System.out.println("before generated . . .");
		showBlocks();
		System.out.println("-----------------------------------------------");
		breakThePuzzle();
		showBlocks();
	}

	static boolean convertJOptionPaneShowConfirmDialog(int scd)
	{
		switch(scd)
		{
			case JOptionPane.YES_OPTION:
					return true;
			case JOptionPane.NO_OPTION:
					return false;
		}
		return false;
	}

	static void showBlocks()
	{
		for(int i = 0; i < blocks.length; i++)
		{
			if(i % 3 == 0)
			{
				System.out.println("");
			}
			for(int j = 0; j < blocks[i].length; j++)
			{
					if(j % 3 == 0)
					{
						System.out.print(" ");
					}
					System.out.print(blocks[i][j].get(Puzzle.ANSWER)+" ");
			}
			System.out.println("");
		}
		
	}

	static void breakThePuzzle()
	{
		boolean solved = false;
		while(!solved)
		{
			int removePossibilitiesCount = 0;
			for(int i = 0; i < blocks.length; i++)
			{
				for(int j = 0; j < blocks[i].length; j++)
				{
					if(blocks[i][j].isResolved())
					{
						blocks[i][j].removeAllPossibilities();
					}
					else
					{
						ArrayList<Integer> findInGroup = findGroupMembers(blocks[i][j].group);	
						for(int k = 0; k < findInGroup.size(); k++)
						{
							if(blocks[i][j].removePossibilities(findInGroup.get(k)))
								removePossibilitiesCount++;
						}

						ArrayList<Integer> findInRow = findRowMembers(blocks[i][j].col);
						for(int k = 0; k < findInRow.size(); k++)
						{
							if(blocks[i][j].removePossibilities(findInRow.get(k)))
								removePossibilitiesCount++;
						}

						ArrayList<Integer> findInCol = findColMembers(blocks[i][j].row);
						for(int k = 0; k < findInCol.size(); k++)
						{
							if(blocks[i][j].removePossibilities(findInCol.get(k)))
								removePossibilitiesCount++;
						}
					}
				}
			}
			if(removePossibilitiesCount < 1)
			{
				// force solve
				System.out.println("couldn't solved the puzzle");
				showAllPossibilities(getAllPossibilities());
				break;
			}
			System.out.println("trying and get the possibilities . . . .");
			for(int i = 0; i < blocks.length; i++)
			{
				for(int j = 0; j < blocks[i].length; j++)
				{
					if(blocks[i][j].isUnresolved())
					{
						blocks[i][j].showPossibilities();
						System.out.println("");
					}
				}
			}
			for(int i = 0; i < blocks.length; i++)
			{
				for(int j = 0; j < blocks[i].length; j++)
				{
					if(blocks[i][j].isUnresolved())
					{
						if(blocks[i][j].isOnlyHaveOnePossibilities() != 0)
						{
							blocks[i][j].putAnswer(blocks[i][j].isOnlyHaveOnePossibilities());
						}
					}
				}
			}
			solved = true;
			for(int i = 0; i < blocks.length; i++)
			{
				for(int  j = 0; j < blocks[i].length; j++)
				{
					if(blocks[i][j].isUnresolved())
						solved = false;
				}
			}
		}
	}

	static void putAllPossibilities(int row, int col)
	{
		for(int i=1; i<10; i++)
		{
			blocks[row][col].putPossibilities(i);
		}
	}

	static void calculatePossibilities(int row, int col)
	{
		for(int i = 0; i < blocks.length; i++)
		{
			if(i==col)continue;
			if(blocks[i][col].isResolved())
			{
				blocks[row][col].putPossibilities(blocks[i][col].get(Puzzle.ANSWER));
				System.out.println(blocks[i][col].get(Puzzle.ANSWER)+"");
			}
		}
	}

	static ArrayList<Integer> findGroupMembers(int group)
	{
		ArrayList<Integer> resolvedMemberAnswer = new ArrayList<Integer>();
		for(int i = 0; i < 9; i++)
		{
			for(int j = 0; j<9; j++)
			{
				if(blocks[i][j].group == group && blocks[i][j].isResolved())
				{
					resolvedMemberAnswer.add(blocks[i][j].get(Puzzle.ANSWER));
				}
			}
		}
		return resolvedMemberAnswer;
	}

	static ArrayList<Integer> findRowMembers(int col)
	{
		ArrayList<Integer> resolvedMemberAnswer = new ArrayList<Integer>();
		for(int i = 0; i < 9; i++)
		{
			if(blocks[i][col].isResolved())
				resolvedMemberAnswer.add(blocks[i][col].get(Puzzle.ANSWER));
		}
		return resolvedMemberAnswer;
	}

	static ArrayList<Integer> findColMembers(int row)
	{
		ArrayList<Integer> resolvedMemberAnswer = new ArrayList<Integer>();
		for(int i = 0; i < 9; i++)
		{
			if(blocks[row][i].isResolved())
				resolvedMemberAnswer.add(blocks[row][i].get(Puzzle.ANSWER));
		}
		return resolvedMemberAnswer;
	}

	static ArrayList<Possibilities> getAllPossibilities()
	{
		ArrayList<Possibilities> value = new ArrayList<Possibilities>();
		for(int i = 0; i < blocks.length; i++)
		{
			for(int j = 0; j < blocks[i].length; j++)
			{
				if(blocks[i][j].isUnresolved())
				{
					value.add(new Possibilities(i, j, blocks[i][j].group, blocks[i][j].getPossibilities()));
				}
			}
		}
		return value;
	}

	static void showAllPossibilities(ArrayList<Possibilities> possibilities)
	{
		for(int i = 0; i < possibilities.size(); i++)
		{
			System.out.print(" possibilities for block row "+possibilities.get(i).row+" col "+possibilities.get(i).col+" are ");
			for(int j = 0; j < possibilities.get(i).possibilities.size(); j++)
			{
				System.out.print(possibilities.get(i).possibilities.get(j)+", ");
			}
			System.out.println("");
		}
	}
}
