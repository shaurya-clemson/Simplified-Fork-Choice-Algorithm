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
        
        // Initialize validators
        List<Validator> validators = new ArrayList<>();
        int totalDeposit = 0;
        for (int i = 0; i < NUM_VALIDATORS; i++) {
            validators.add(new Validator(i, DEPOSITS[i]));
            totalDeposit += DEPOSITS[i];
        }

        // Building a binary tree of depth 10
        Node checkpointTree = buildTree(10);

        // Simulating the voting process and tracking validators who finalized each checkpoint
        Map<Integer, Set<Integer>> finalizingValidatorsByCheckpoint = new HashMap<>();
        Node current_node = checkpointTree;
        List<Integer> blockchain = new ArrayList<>();

        for (int i = 0; i < 10; i++) { // Simulating 10 rounds
            Map.Entry<Node, Set<Integer>> supermajorityLink = superMajorityLink(validators, current_node, totalDeposit);
            if (supermajorityLink != null) {
                current_node = supermajorityLink.getKey();
                blockchain.add(current_node.number);
                finalizingValidatorsByCheckpoint.put(current_node.number, supermajorityLink.getValue());
            } else {
                break;
            }
        }

        // Priting the completed blockchain
        System.out.println("Congratulations! Your blockchain is complete.\nBlockchain: " + blockchain);

        System.out.println("\nHere are all the validators that validated each checkpoint:");

        for (Map.Entry<Integer, Set<Integer>> entry : finalizingValidatorsByCheckpoint.entrySet()) {
            System.out.println("Checkpoint: " + entry.getKey() + ", Validators: " + entry.getValue());
        }

    }
}
