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

    private int getZeroX() {
        return zeroX;
    }

    private int getZeroY() {
        return zeroY;
    }

    public int getDepth() { return depth; }

    public String getPath() {
        return path;
    }

    private int[][] getTable(){ return table; }

    private Node makeCopy(){
        Node copy = new Node(this.getTable());
        copy.path = this.path;
        copy.depth = this.depth;
        return copy;
    }

    private Node makeSon(Move move){
        Node son = this.makeCopy();
        int[][] sonTable = son.getTable();
        int x = son.getZeroX();
        int y = son.getZeroY();

        switch(move){
            case UP:{
                if(y == 0) throw new IndexOutOfBoundsException();
                sonTable[y][x] = sonTable[y-1][x];
                sonTable[y-1][x] = 0;
                son.zeroY--;
                son.path = this.path + "U";
                break;
            }
            case DOWN:{
                if(y == Const.ROWS-1) throw new IndexOutOfBoundsException();
                sonTable[y][x] = sonTable[y+1][x];
                sonTable[y+1][x] = 0;
                son.zeroY++;
                son.path = this.path + "D";
                break;
            }
            case LEFT:{
                if(x == 0) throw new IndexOutOfBoundsException();
                sonTable[y][x] = sonTable[y][x-1];
                sonTable[y][x-1] = 0;
                son.zeroX--;
                son.path = this.path + "L";
                break;
            }
            case RIGHT:{
                if(x == Const.COLS) throw new IndexOutOfBoundsException();
                sonTable[y][x] = sonTable[y][x+1];
                sonTable[y][x+1] = 0;
                son.zeroX++;
                son.path = this.path + "R";
                break;
            }
        }
        son.depth = this.depth + 1;
        return son;
    }

    public LinkedList<Node> makeSons(){
        LinkedList<Node> l = new LinkedList<>();
        Node upSon, downSon, leftSon, rightSon;

        try{
            upSon = this.makeSon(Move.UP);
            l.add(upSon);
        } catch(IndexOutOfBoundsException e) { }

        try{
            downSon = this.makeSon(Move.DOWN);
            l.add(downSon);
        } catch(IndexOutOfBoundsException e) { }

        try{
            leftSon = this.makeSon(Move.LEFT);
            l.add(leftSon);
        } catch(IndexOutOfBoundsException e) { }

        try{
            rightSon = this.makeSon(Move.RIGHT);
            l.add(rightSon);
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
        String string = "";
        for(int i=0; i<Const.ROWS; i++){
            for(int j=0; j<Const.COLS; j++){
                string += this.table[i][j] + " ";
            }
            string = string + "\n";
        }
        string = string + "PATH: " + this.path + "\n";
        string = string + "DEPTH: " + this.depth;
        return string;
    }

    public String pathToString(Node root){ //assumes root is the actual root
        Node node = root.makeCopy();
        String string = root.toString() + "\n---------\n";

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
            string += node.toString() + "\n---------\n";
        }
        return string;
    }



}
