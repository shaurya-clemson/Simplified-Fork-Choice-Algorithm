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
