import constants.Const;
import structures.Node;
import java.util.Scanner;

import static algorithm.Algorithm.*;

public class Puzzle {
    private static int[][] scanTable(){
        Scanner stdin = new Scanner(System.in);
        int[][] table = new int[Const.ROWS][Const.COLS];
        for(int i=0; i<Const.ROWS; i++){
            for(int j=0; j<Const.COLS; j++){
                table[i][j] = stdin.nextInt();
            }
        }
        return table;
    }

    public static void main(String[] args){
        String choice = args[0];

        System.out.println();
        int[][] table = scanTable();
        Node root = new Node(table);

        System.out.println();
        int[][] table2 = scanTable();
        Node objective = new Node(table2);

        //System.out.println("=========");

        switch(choice){
            case "bfs":{
                Node end;
                try{
                    int maxDepth = Integer.parseInt(args[1]);
                    end = limitedBfs(root, objective, maxDepth);
                }
                catch (ArrayIndexOutOfBoundsException e){
                    end = bfs(root, objective);
                }

                if(end == null){
                    System.out.println("error - solution not found.");
                }
                else{ System.out.println(end.pathToString(root)); }

                break;
            }
            case "dfs":{
                Node end;
                try{
                    int maxDepth = Integer.parseInt(args[1]);
                    end = limitedDfs(root, objective, maxDepth);
                }
                catch (ArrayIndexOutOfBoundsException e){
                    end = dfs(root, objective);

                }

                if(end == null){
                    System.out.println("error - solution not found.");
                }
                else{ System.out.println(end.pathToString(root)); }

                break;
            }
            case "idfs": {
                Node end;
                try {
                    int maxDepth = Integer.parseInt(args[1]); //can throw exception if user does not give the depth
                    end = idfs(root, objective, maxDepth);

                }
                catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("error - no depth provided.");
                    return;
                }

                if(end == null){
                    System.out.println("error - solution not found.");
                }
                else{ System.out.println(end.pathToString(root)); }

                break;
            }
            default: {
                System.out.println("error - please provide acceptable input.");
            }
        }
    }
}
