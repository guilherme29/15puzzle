package structures;

import constants.Const;

abstract class ScoredNode extends Node {
    private int score;

    ScoredNode(int[][] table) {
        super(table);
    }

    int getScore() {
        return score;
    }

    void setScore(int score) {
        this.score = score;
    }

    static int scoringFunction(int[][] table, int[][] objective) {
        int score = 0;
        for (int i = 0; i < Const.ROWS; i++) {
            for (int j = 0; j < Const.COLS; j++) {

                boolean calculated = false;
                for (int m = 0; m < Const.ROWS && !calculated; m++) {
                    for (int n = 0; n < Const.COLS && !calculated; n++) {
                        if (table[i][j] == objective[m][n]) {
                            score += Math.abs(i - m) + Math.abs(j - n);
                            calculated = true;
                        }
                    }
                }


            }
        }
        return score;
    }

    @Override
    public String toString() {
        int[][] table = this.getTable();
        StringBuilder string = new StringBuilder();
        for(int i=0; i<Const.ROWS; i++){
            for(int j=0; j<Const.COLS; j++){
                string.append(table[i][j])
                        .append(" ");
            }
            string.append("\n");
        }
        string.append("PATH: ")
                .append(this.getPath())
                .append("\n")
                .append("DEPTH: ")
                .append(this.getDepth())
                .append("\n")
                .append("SCORE: ")
                .append(this.getScore());
        return string.toString();
    }

}
