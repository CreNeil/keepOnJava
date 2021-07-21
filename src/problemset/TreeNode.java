import java.util.List;

public class TreeNode {
    private Integer id;
    private String name;
    private Integer pid;
    private List<TreeNode> children;

    public TreeNode(Integer id, String name, Integer pid) {
        this.id = id;
        this.name = name;
        this.pid = pid;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }


}
