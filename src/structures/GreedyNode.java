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

    @Override
    public String toString() {
        System.out.println("allalalalalallalal");
        return super.toString();
    }
}
