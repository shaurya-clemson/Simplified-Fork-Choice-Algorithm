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
