import java.util.Random;

public class WumpusMap {

    public static final int NUM_ROWS = 10;
    public static final int NUM_COLUMNS = 10;
    public static final int NUM_PITS = 10;

    private WumpusSquare[][] grid;
    private int ladderC;
    private int ladderR;
    private int wumpusR;
    private int wumpusC;

    public WumpusMap() {
        createMap();
    }

    public void createMap() {
        //initialize and fill grid
        grid = new WumpusSquare[NUM_ROWS][NUM_COLUMNS];
        for(int r = 0; r < grid.length; r++) {
            for(int c = 0; c < grid[0].length; c++) {
                grid[r][c] = new WumpusSquare();
            }
        }
        //creates random row and column
        Random random = new Random();
        int r = random.nextInt(grid.length);
        int c = random.nextInt(grid[0].length);
        //sets gold
        do {
            r = random.nextInt(grid.length);
            c = random.nextInt(grid[0].length);
        }while(grid[r][c].getPit() || grid[r][c].getLadder());
        grid[r][c].setGold(true);
        //sets wumpus and stenches
        do {
            r = random.nextInt(grid.length);
            c = random.nextInt(grid[0].length);
        }while(grid[r][c].getPit() || grid[r][c].getLadder());
        grid[r][c].setWumpus(true);
        if(r > 0 && !grid[r-1][c].getPit())
            grid[r-1][c].setStench(true);
        if(r < grid.length-1 && !grid[r+1][c].getPit())
            grid[r+1][c].setStench(true);
        if(c > 0 && !grid[r][c-1].getPit())
            grid[r][c-1].setStench(true);
        if(c < grid[0].length-1 && !grid[r][c+1].getPit())
            grid[r][c+1].setStench(true);
        wumpusR = r;
        wumpusC = c;
        //sets ladder
        while(grid[r][c].getPit() || grid[r][c].getWumpus() || grid[r][c].getGold()) {
            r = random.nextInt(grid.length);
            c = random.nextInt(grid[0].length);
        }
        grid[r][c].setLadder(true);
        ladderC = c;
        ladderR = r;
        //sets pits and breezes
            for (int i = 0; i < NUM_PITS; i++) {
                //finds new random position
                r = random.nextInt(grid.length);
                c = random.nextInt(grid[0].length);
                if (!grid[r][c].getPit() && !grid[r][c].getGold() && !grid[r][c].getWumpus() && !grid[r][c].getLadder()) {
                    grid[r][c].setPit(true);
                    if (r > 0 && !grid[r - 1][c].getPit())
                        grid[r - 1][c].setBreeze(true);
                    if (r < grid.length - 1 && !grid[r + 1][c].getPit())
                        grid[r + 1][c].setBreeze(true);
                    if (c > 0 && !grid[r][c - 1].getPit())
                        grid[r][c - 1].setBreeze(true);
                    if (c < grid[0].length - 1 && !grid[r][c + 1].getPit())
                        grid[r][c + 1].setBreeze(true);
                } else
                    i--;
            }
            for (int i = 0; i < NUM_ROWS; i++) {
                for (int j = 0; j < NUM_COLUMNS; j++) {
                    if (grid[i][j].getPit() && grid[i][j].getBreeze())
                        grid[i][j].setBreeze(false);
                    if (grid[i][j].getPit() && grid[i][j].getStench())
                        grid[i][j].setStench(false);
                }
            }
    }

    public int getLadderC() {
        return ladderC;
    }

    public int getLadderR() {
        return ladderR;
    }

    public int getWumpusC() {
        return wumpusC;
    }

    public int getWumpusR() {
        return wumpusR;
    }

    public WumpusSquare getSquare(int row, int col) {
        return grid[row][col];
    }

    @Override
    public String toString() {
        String s = "";
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c].getPit())
                    s += "P";
                else if (grid[r][c].getWumpus() && grid[r][c].getGold())
                    s += "W";
                else if (grid[r][c].getLadder())
                    s += "L";
                else if (grid[r][c].getGold())
                    s += "G";
                else if (grid[r][c].getWumpus())
                    s += "w";
                else
                    s += "*";
            }
            s += "\n";
        }
        return s;
    }
}
