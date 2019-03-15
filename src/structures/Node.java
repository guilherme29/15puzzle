package structures;

import constants.Const;
import java.util.LinkedList;

enum Move{
    UP, DOWN, LEFT, RIGHT
}

public class Node {
    private int[][] table = new int[Const.ROWS][Const.COLS];
    private int zeroX, zeroY, depth = 0;
    private String path = "";

    public Node(int[][] table) {
        for(int i=0; i<Const.ROWS; i++){
            for(int j=0; j<Const.COLS; j++){
                this.table[i][j] = table[i][j];
                if(table[i][j] == 0){
                    this.zeroX = j;
                    this.zeroY = i;
                }
            }
        }
    }

    int getZeroX() {
        return zeroX;
    }

    int getZeroY() {
        return zeroY;
    }

    public void setZeroX(int zeroX) {
        this.zeroX = zeroX;
    }

    public void setZeroY(int zeroY) {
        this.zeroY = zeroY;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getDepth() { return depth; }

    public String getPath() {
        return path;
    }

    int[][] getTable(){ return table; }

    private Node makeCopy(){
        Node copy = new Node(this.getTable());
        copy.path = this.path;
        copy.depth = this.depth;
        return copy;
    }

    Node makeSon(Move move) throws IndexOutOfBoundsException {
        Node son = this.makeCopy();
        int[][] sonTable = son.getTable();
        int x = son.getZeroX();
        int y = son.getZeroY();

        switch(move){
            case UP:{
                if(y == 0) throw new IndexOutOfBoundsException();
                sonTable[y][x] = sonTable[y-1][x];
                sonTable[y-1][x] = 0;
                son.setZeroY(son.getZeroY() - 1);// son.zeroY--;
                son.setPath(son.getPath() + "U");//son.path = this.path + "U";
                break;
            }
            case DOWN:{
                if(y == Const.ROWS-1) throw new IndexOutOfBoundsException();
                sonTable[y][x] = sonTable[y+1][x];
                sonTable[y+1][x] = 0;
                son.setZeroY(son.getZeroY() + 1);//son.zeroY++;
                son.setPath(son.getPath() + "D");//son.path = this.path + "D";
                break;
            }
            case LEFT:{
                if(x == 0) throw new IndexOutOfBoundsException();
                sonTable[y][x] = sonTable[y][x-1];
                sonTable[y][x-1] = 0;
                son.setZeroX(son.getZeroX() - 1);//son.zeroX--;
                son.setPath(son.getPath() + "L");//son.path = this.path + "L";
                break;
            }
            case RIGHT:{
                if(x == Const.COLS) throw new IndexOutOfBoundsException();
                sonTable[y][x] = sonTable[y][x+1];
                sonTable[y][x+1] = 0;
                son.setZeroX(son.getZeroX() + 1);//son.zeroX++;
                son.setPath(son.getPath() + "R");//son.path = this.path + "R";
                break;
            }
        }
        son.setDepth(son.getDepth() + 1);//son.depth = this.depth + 1;
        return son;
    }

    public LinkedList<Node> makeSons(){
        LinkedList<Node> l = new LinkedList<>();
        Node upSon, downSon, leftSon, rightSon;

        if(this.getLastMove() != Move.DOWN) {
            try {
                upSon = this.makeSon(Move.UP);
                l.add(upSon);
            }
            catch (IndexOutOfBoundsException e) {  }
        }

        if(this.getLastMove() != Move.UP) {
            try {
                downSon = this.makeSon(Move.DOWN);
                l.add(downSon);
            }
            catch (IndexOutOfBoundsException e) {  }
        }

        if(this.getLastMove() != Move.RIGHT) {
            try {
                leftSon = this.makeSon(Move.LEFT);
                l.add(leftSon);
            }
            catch (IndexOutOfBoundsException e) {  }
        }

        if(this.getLastMove() != Move.LEFT) {
            try {
                rightSon = this.makeSon(Move.RIGHT);
                l.add(rightSon);
            }
            catch (IndexOutOfBoundsException e) {  }
        }

        return l;
    }

    public LinkedList<Node> makeSonsRandom(){
        //same as the other one but the list is in a random order
        //just ensuring that the unbounded dfs doesn't get stuck in a corner
        LinkedList<Node> l = new LinkedList<>();
        Node upSon, downSon, leftSon, rightSon;

        try{
            upSon = this.makeSon(Move.UP);
            l.add(upSon);
        } catch(IndexOutOfBoundsException e) { }

        try{
            downSon = this.makeSon(Move.DOWN);
            int k = (int)(Math.random() * (l.size() + 1));
            l.add(k, downSon);
        } catch(IndexOutOfBoundsException e) { }

        try{
            leftSon = this.makeSon(Move.LEFT);
            int k = (int)(Math.random() * (l.size() + 1));
            l.add(k,leftSon);
        } catch(IndexOutOfBoundsException e) { }

        try{
            rightSon = this.makeSon(Move.RIGHT);
            int k = (int)(Math.random() * (l.size() + 1));
            l.add(k,rightSon);
        } catch(IndexOutOfBoundsException e) { }

        return l;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Node) {
            Node node = (Node) obj;
            int[][] table = node.getTable();

            for(int i=0; i<Const.ROWS; i++){
                for(int j=0; j<Const.COLS; j++){
                    if(this.table[i][j] != table[i][j]) return false;
                }
            }
            return true;
        }
        else return false;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for(int i=0; i<Const.ROWS; i++){
            for(int j=0; j<Const.COLS; j++){
                string.append(this.table[i][j])
                        .append(" ");
            }
            string.append("\n");
        }
        string.append("PATH: ")
                .append(this.getPath())
                .append("\n").append("DEPTH: ")
                .append(this.getDepth());

        return string.toString();
    }

    public String pathToString(Node root){ //assumes root is the actual root
        Node node = root.makeCopy();
        StringBuilder string = new StringBuilder(root.toString() + "\n---------\n");

        for(int i=0; i<path.length(); i++){
            switch(path.charAt(i)){
                case 'U':
                    node = node.makeSon(Move.UP);
                    break;
                case 'D':
                    node = node.makeSon(Move.DOWN);
                    break;
                case 'L':
                    node = node.makeSon(Move.LEFT);
                    break;
                case 'R':
                    node = node.makeSon(Move.RIGHT);
                    break;
            }
            string.append(node.toString()).
                    append("\n---------\n");
        }
        return string.toString();
    }

    public Move getLastMove(){
        if(path.length() == 0) return null;
        int last = path.length() - 1;
        char l = path.charAt(last);
        switch(l){
            case 'U': return Move.UP;
            case 'D': return Move.DOWN;
            case 'L': return Move.LEFT;
            case 'R': return Move.RIGHT;
        }
        return null;



    }


}
