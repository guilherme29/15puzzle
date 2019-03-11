package algorithm;

import structures.Node;
import java.util.LinkedList;

public class Algorithm {

    public static Node bfs(Node root, Node objective){
        LinkedList<Node> queue = new LinkedList<>();

        queue.addLast(root);
        while(!queue.isEmpty()){
            Node head = queue.removeFirst();
            LinkedList<Node> sons = head.makeSons();
            for(Node son : sons){
                if(son.equals(objective)) return son;
                else queue.addLast(son);
            }
        }
        return null;
    }

    public static Node limitedBfs(Node root, Node objective, int maxDepth){
        LinkedList<Node> queue = new LinkedList<>();

        queue.addFirst(root);
        while(!queue.isEmpty()){
            Node head = queue.removeFirst();
            LinkedList<Node> sons = head.makeSons();
            for(Node son : sons){
                if(son.equals(objective)) return son;
                else if(son.getDepth() < maxDepth){
                    queue.addLast(son);
                }
            }
        }
        return null;
    }

    public static Node dfs(Node root, Node objective){
        LinkedList<Node> stack = new LinkedList<>();

        stack.addFirst(root);
        while(!stack.isEmpty()){
            Node head = stack.removeFirst();
            LinkedList<Node> sons = head.makeSonsRandom();
            //I'm gonna check the sons but then I will not add them to any stack
            //The reasoning for this is that the stack keeps on growing but in reality
            //I'm always just expanding the top item
            for(Node son : sons){
                if(son.equals(objective)) return son;
            }
            stack.addFirst(sons.removeFirst());
        }
        return null;
    }

    public static Node limitedDfs(Node root, Node objective, int maxDepth){
        LinkedList<Node> stack = new LinkedList<>();

        stack.addFirst(root);
        while(!stack.isEmpty()){
            Node head = stack.removeFirst();
            LinkedList<Node> sons = head.makeSons();
            for(Node son : sons){
                if(son.equals(objective)) return son;
                else if(son.getDepth() < maxDepth){
                    stack.addFirst(son);
                }
            }
        }
        return null;
    }

    public static Node idfs(Node root, Node objective){
        Node result = null;
        for(int i=1; result == null; i++){
            result = limitedDfs(root, objective, i);
        }
        return result;
    }

    public static Node limitedIdfs(Node root, Node objective, int maxDepth){
        Node result = null;
        for(int i=1; i <= maxDepth && result == null; i++){
            result = limitedDfs(root, objective, i);
        }
        return result;
    }


}
