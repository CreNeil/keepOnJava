import java.util.*;

public class Algo {
    //扁平数据结构，转树
    public static void main(String[] args) {
        List<TreeNode> treeNodes = new ArrayList<>();
        TreeNode node1 = new TreeNode(2, "a", null);
        TreeNode node2 = new TreeNode(0, "b", 2);
        TreeNode node3 = new TreeNode(1, "c", 0);
        TreeNode node4 = new TreeNode(4, "d", 2);
        TreeNode node5 = new TreeNode(3, "e", 4);
        TreeNode node6 = new TreeNode(5, "f", 4);
        treeNodes.add(node1);
        treeNodes.add(node2);
        treeNodes.add(node3);
        treeNodes.add(node4);
        treeNodes.add(node5);
        treeNodes.add(node6);

        Map<Integer, List<TreeNode>> map = new HashMap<>();
        TreeNode root = null;
        for (TreeNode treeNode : treeNodes) {
            if (Objects.nonNull(treeNode.getPid())) {
                List<TreeNode> children = map.getOrDefault(treeNode.getPid(), new ArrayList<>());
                children.add(treeNode);
                map.put(treeNode.getPid(), children);
            } else {
                root = treeNode;
            }
        }
        for (TreeNode treeNode : treeNodes) {
            if (map.containsKey(treeNode.getId())) {
                treeNode.setChildren(map.get(treeNode.getId()));
            }
        }
        System.out.println(root);
    }
}
