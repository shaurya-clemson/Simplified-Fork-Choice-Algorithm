import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

public class Blockchain {

    private static Node buildTree(int depth) {
        Node root = new Node(0);
        addChildren(root, 0, depth);
        return root;
    }
    
    private static void addChildren(Node node, int currentDepth, int maxDepth) {
        if (currentDepth < maxDepth) {
            node.left = new Node(node.number * 2 + 1);
            node.right = new Node(node.number * 2 + 2);
            addChildren(node.left, currentDepth + 1, maxDepth);
            addChildren(node.right, currentDepth + 1, maxDepth);
        }
    }

    private static Map.Entry<Node, Set<Integer>> superMajorityLink(List<Validator> validators, Node current_node, int totalDeposit) {
        Map<Node, Set<Integer>> validatorVotes = Voting(validators, current_node);
        int supermajorityThreshold = totalDeposit / 2;
        
        for (Map.Entry<Node, Set<Integer>> entry : validatorVotes.entrySet()) {
            int totalVotes = entry.getValue().stream().mapToInt(id -> validators.get(id).deposit).sum();
            if (totalVotes > supermajorityThreshold) {
                return entry;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        // Constants
        final int NUM_VALIDATORS = 10;
        final int[] DEPOSITS = {500, 100, 300, 250, 150, 500, 600, 350, 200, 150};
