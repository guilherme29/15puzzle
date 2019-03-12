import constants.Const;
import structures.Node;

import java.util.Scanner;
import static algorithm.Algorithm.*;

public class Puzzle {
    public static int[][] scanTable(Scanner stdin){
        int[][] table = new int[Const.ROWS][Const.COLS];
        for(int i=0; i<Const.ROWS; i++){
            for(int j=0; j<Const.COLS; j++){
                table[i][j] = stdin.nextInt();
            }
        }
        return table;
    }

    public static void main(String[] args){
        Scanner stdin = new Scanner(System.in);
        String choice = args[0];
        System.out.println();

        int[][] table = scanTable(stdin);
        Node root = new Node(table);

        System.out.println();
        int[][] table2 = scanTable(stdin);
        Node objective = new Node(table2);

        //System.out.println("=========");
        Node end;
        switch(choice){
            case "bfs":{
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
                try {
                    int maxDepth = Integer.parseInt(args[1]); //can throw exception if user does not give the depth
                    end = limitedIdfs(root, objective, maxDepth);

                }
                catch (ArrayIndexOutOfBoundsException e){
                    end = idfs(root, objective);
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
