package cloud.model;

import cn.hutool.core.collection.ListUtil;
import lombok.*;

import javax.xml.soap.Node;
import java.util.Iterator;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NodeModel {

    private List<NodeModel> children;
    private String value;
    private String id;
    private String parentId;
    private String name;
    private String type;
    private String containerName;
    private String containerType;

    public void appendChild(NodeModel n) {
        if (this.children == null) {
            this.children = ListUtil.list(false);
        }
        this.children.add(n);
    }

    public void appendChildren(List<NodeModel> ns) {
        if (this.children == null) {
            this.children = ListUtil.list(false);
        }
        if (ns != null) {
            this.children.addAll(ns);
        }
    }

    public void removeChild(NodeModel nm) {
        this.children.remove(nm);
    }

    /**
     * @param tags      重要设备
     * @param saveTypes 保存的设备类型
     */
    public void removeInvalidChildren(List<String> tags, List<String> saveTypes) {
        Iterator<NodeModel> it = children.iterator();
        while (it.hasNext()) {
            NodeModel child = it.next();
            if (!saveTypes.contains(child.getType())
                    && (child.getChildren() != null && child.getChildren().size() < 2)
                    && !tags.contains(child.getValue())
                    && (child.getChildren().stream().noneMatch(u -> tags.contains(u.getValue())))) {
                it.remove();
            } else {
                child.removeInvalidChildren(tags, saveTypes); // 递归删除子节点中不符合条件的节点
                if (child.children.isEmpty()) {
                    it.remove(); // 如果子节点为空，则删除这个节点
                }
            }
        }
    }

}
