import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Tree {
    private static final char TAB = '\t';
    private static final char NEW_LINE = '\n';

    String node;
    List<Tree> children;

    public Tree(String node, Tree... children) {
        this.node = node;
        this.children = Arrays.asList(children);
    }

    public Tree(String node) {
        this.node = node;
        children = Collections.emptyList();
    }

    public void visualize(String graphName) {
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(Path.of("tests/" + graphName + ".dot"))) {
            bufferedWriter.write(treeToDotRepresentation(this));
            Runtime.getRuntime().exec("dot tests/" + graphName + ".dot -Tpng -o " + "tests/" + graphName + ".png");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String treeToDotRepresentation(Tree tree) {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph {").append("\n");
        bfs(tree, sb);
        sb.append("}");
        return sb.toString();
    }

    private static void bfs(Tree tree, StringBuilder sb) {
        Deque<TreeWithID> deque = new ArrayDeque<>();
        int id = 0;
        deque.add(new TreeWithID(tree, id));

        while (!deque.isEmpty()) {
            TreeWithID currentTree = deque.pop();

            sb.append(TAB)
                    .append(currentTree.id)
                    .append(" [label=\"")
                    .append(currentTree.tree.node)
                    .append("\"]")
                    .append(NEW_LINE);

            if (!currentTree.tree.children.isEmpty()) {
                for (Tree child : currentTree.tree.children) {
                    id++;
                    sb.append(TAB).append(currentTree.id).append(" -> ").append(id).append(NEW_LINE);
                    deque.add(new TreeWithID(child, id));
                }
            }
        }
    }

    private static class TreeWithID {
        private Tree tree;
        private int id;

        public TreeWithID(Tree tree, int id) {
            this.tree = tree;
            this.id = id;
        }
    }

}
