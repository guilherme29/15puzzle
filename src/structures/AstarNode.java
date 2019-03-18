package structures;

public class AstarNode extends ScoredNode implements Comparable<AstarNode>{

    public AstarNode(int[][] table, int[][] objective) {
        super(table);
        int s = score(table, objective, this.getDepth());
        this.setScore(s);
    }

    static int score(int[][] table, int[][] objective, int depth) {
        return scoringFunction(table, objective) + depth;
    }

    @Override
    public int compareTo(AstarNode astarNode) {
        if(this.getScore() < astarNode.getScore()) return -1;
        else if(this.getScore() > astarNode.getScore()) return 1;
        else{
            if(this.getDepth() < astarNode.getDepth()) return -1;
            else return 1;
        }
    }


}
