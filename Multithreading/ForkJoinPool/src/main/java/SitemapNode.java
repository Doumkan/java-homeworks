import java.util.concurrent.CopyOnWriteArrayList;

public class SitemapNode {
    private volatile SitemapNode parent;
    private volatile int depth;
    private String link;
    private volatile CopyOnWriteArrayList<SitemapNode> children;

    public SitemapNode(String link) {
        depth = 0;
        this.link = link;
        parent = null;
        children = new CopyOnWriteArrayList<>();
    }

    private int calculateDepth() {
        int result = 0;
        if (parent == null) {
            return result;
        }
        result = 1 + parent.calculateDepth();
        return result;
    }

    public synchronized void addChild(SitemapNode element) {
        SitemapNode root = getRootElement();
        if (!root.contains(element.getLink())) {
            element.setParent(this);
            children.add(element);
        }
    }

    private boolean contains(String url) {
        if (this.link.equals(url)) {
            return true;
        }
        for (SitemapNode child : children) {
            if (child.contains(url))
                return true;
        }

        return false;
    }

    public String getLink() {
        return link;
    }

    private void setParent(SitemapNode sitemapNode) {
        synchronized (this) {
            this.parent = sitemapNode;
            this.depth = calculateDepth();
        }
    }

    public SitemapNode getRootElement() {
        return parent == null ? this : parent.getRootElement();
    }

    public CopyOnWriteArrayList<SitemapNode> getChildren() {
        return children;
    }
}
