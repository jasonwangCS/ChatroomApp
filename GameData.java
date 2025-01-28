import java.util.*;
public class GameData
{
    public ArrayList<String> messages;
    private char[][] grid = {{' ',' ', ' ', ' ', ' ', ' ', ' '},
            {' ',' ', ' ', ' ', ' ', ' ', ' '},
            {' ',' ', ' ', ' ', ' ', ' ', ' '},
            {' ',' ', ' ', ' ', ' ', ' ', ' '},
            {' ',' ', ' ', ' ', ' ', ' ', ' '},
            {' ',' ', ' ', ' ', ' ', ' ', ' '}};

    public char[][] getGrid()
    {
        return grid;
    }

    public void reset()
    {
        grid = new char[6][7];
        for(int r=0;r<grid.length; r++)
            for(int c=0; c<grid[0].length; c++)
                grid[r][c]=' ';
    }
    public boolean isCat()
    {
        for(int i = 0; i < 6; i++)
        {
            for(int j = 0; j < 7; j++)
            {
                if(grid[i][j] == ' ')
                    return false;
            }
        }
        if(isWinner('B') || isWinner('R'))
            return false;
        return true;
    }
    public boolean isWinner(char letter) {
        System.out.println(check1(letter) + "check 1");
        System.out.println( check2(letter) + "Check 2" );
        System.out.println( check3(letter) + "Check 3" );
        System.out.println( check4(letter) + "Check 4" );
        return check1(letter) || check2(letter) || check3(letter) || check4(letter);
    }
    public boolean check1(char letter) // check horizontal
    {
        for(int i = 0; i < grid.length; i++)
        {
            for (int j = 0; j <= 3; j++)
            {
                if (grid[i][j] == letter && grid[i][j + 1] == letter && grid[i][j + 2] == letter && grid[i][j + 3] == letter)
                {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean check2(char letter) //check vertical
    {
        for (int i = 0; i < grid[0].length; i++)
        {
            for (int j = 0; j <= 2; j++)
            {
                if (grid[j][i] == letter && grid[j+ 1][i] == letter && grid[j+2][i] == letter && grid[j+3][i] == letter)
                {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean check3(char letter) {
        for (int row = 0; row <= grid.length - 4; row++) {
            for (int col = 0; col <= grid[0].length - 4; col++) {
                if (grid[row][col] == letter && grid[row + 1][col + 1] == letter &&
                        grid[row + 2][col + 2] == letter && grid[row + 3][col + 3] == letter) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean check4(char letter) {
        for (int row = 3; row < grid.length; row++) {
            for (int col = 0; col <= grid[0].length - 4; col++) {
                if (grid[row][col] == letter && grid[row - 1][col + 1] == letter &&
                        grid[row - 2][col + 2] == letter && grid[row - 3][col + 3] == letter) {
                    return true;
                }
            }
        }
        return false;
    }

}
