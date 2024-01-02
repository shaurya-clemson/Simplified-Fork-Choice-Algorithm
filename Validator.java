import java.util.Random;

public class Validator {
    int id;
    int deposit;
    private static final Random random = new Random();

    public Validator(int validatorId, int deposit) {
        this.id = validatorId;
        this.deposit = deposit;
    }

    public Node vote(Node current_node) {
        if (current_node.left != null && current_node.right != null) {
            return random.nextBoolean() ? current_node.left : current_node.right;
        }
        return current_node.left != null ? current_node.left : current_node.right;
    }
}
