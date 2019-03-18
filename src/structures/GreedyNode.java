package structures;

public class GreedyNode extends ScoredNode implements Comparable<GreedyNode>{

    public GreedyNode(int[][] table, int[][] objective) {
        super(table);
        int s = score(table, objective);
        this.setScore(s);
    }

    private static int score(int[][] table, int[][] objective) {
        return scoringFunction(table, objective);
    }

    @Override
    public int compareTo(GreedyNode greedyNode) {
        if(this.getScore() < greedyNode.getScore()) return -1;
        else if(this.getScore() > greedyNode.getScore()) return 1;
        else{
            if(this.getDepth() < greedyNode.getDepth()) return -1;
            else return 1;
        }
    }

    //TODO
    Node makeAstarSon(Move move, int[][] objective) throws IndexOutOfBoundsException {
        ScoredNode son = (ScoredNode) this.makeSon(move);
        int[][] thisTable = this.getTable();
        int x = this.getZeroX();
        int y = this.getZeroY();
        int scoreNewZero, scoreNewVal, scoreOldVal;
        //it's safe to do this operations since the code above
        // will have thrown the exception if there's one
        int scoreOldZero = indexScore(x, y, 0, objective);

        switch(move){
            case UP:{
                scoreNewZero = indexScore(x, y + 1, 0, objective);
                scoreNewVal = indexScore(x, y, thisTable[y+1][x], objective);
                scoreOldVal = indexScore(x, y + 1, thisTable[y+1][x], objective);
                break;
            }
            case DOWN:{
                scoreNewZero = indexScore(x, y - 1, 0, objective);
                scoreNewVal = indexScore(x, y, thisTable[y-1][x], objective);
                scoreOldVal = indexScore(x, y - 1, thisTable[y-1][x], objective);
                break;
            }
            case LEFT:{
                scoreNewZero = indexScore(x - 1, y, 0, objective);
                scoreNewVal = indexScore(x, y, thisTable[y][x-1], objective);
                scoreOldVal = indexScore(x - 1, y, thisTable[y][x-1], objective);
                break;
            }
            case RIGHT:{
                scoreNewZero = indexScore(x + 1, y, 0, objective);
                scoreNewVal = indexScore(x, y, thisTable[y][x+1], objective);
                scoreOldVal = indexScore(x + 1, y, thisTable[y][x + 1], objective);
                break;
            }
        }


        return son;
    }

}
