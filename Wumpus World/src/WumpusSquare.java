public class WumpusSquare {

    private boolean gold;
    private boolean ladder;
    private boolean pit;
    private boolean breeze;
    private boolean wumpus;
    private boolean deadWumpus;
    private boolean stench;
    private boolean visited;

    public WumpusSquare() {
        gold = false;
        ladder = false;
        pit = false;
        breeze = false;
        wumpus = false;
        deadWumpus = false;
        stench = false;
        visited = false;
    }

    public boolean getGold() {
        return gold;
    }

    public boolean getLadder() {
        return ladder;
    }

    public boolean getPit() {
        return pit;
    }

    public boolean getBreeze() {
        return breeze;
    }

    public boolean getWumpus() {
        return wumpus;
    }

    public boolean getDeadWumpus() {
        return deadWumpus;
    }

    public boolean getStench() {
        return stench;
    }

    public boolean getVisited() {
        return visited;
    }

    public void setGold(boolean gold) {
        this.gold = gold;
    }

    public void setLadder(boolean ladder) {
        this.ladder = ladder;
    }

    public void setPit(boolean pit) {
        this.pit = pit;
    }

    public void setBreeze(boolean breeze) {
        this.breeze = breeze;
    }

    public void setWumpus(boolean wumpus) {
        this.wumpus = wumpus;
    }

    public void setDeadWumpus(boolean deadWumpus) {
        this.deadWumpus = deadWumpus;
    }

    public void setStench(boolean stench) {
        this.stench = stench;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}
