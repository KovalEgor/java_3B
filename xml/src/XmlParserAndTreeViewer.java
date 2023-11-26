import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.io.File;
public class XmlParserAndTreeViewer extends JFrame {
    public XmlParserAndTreeViewer() {
        setTitle("XML Parser and Tree Viewer");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTree xmlTree = parseXmlToTree("example.xml");
        JScrollPane scrollPane = new JScrollPane(xmlTree);
        add(scrollPane, BorderLayout.CENTER);
    }
    private JTree parseXmlToTree(String filePath) {
        try {
            File xmlFile = new File(filePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            Element rootElement = document.getDocumentElement();
            DefaultMutableTreeNode rootNode = createTreeNode(rootElement);
            JTree xmlTree = new JTree(rootNode);
            xmlTree.setRootVisible(true);
            return xmlTree;
        } catch (Exception e) {
            e.printStackTrace();
            showError("Error parsing XML file.");
            return null;
        }
    }
    private DefaultMutableTreeNode createTreeNode(Element element) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(element.getNodeName());
        NodeList children = element.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child instanceof Element) {
                DefaultMutableTreeNode childNode = createTreeNode((Element) child);
                node.add(childNode);
            } else if (child instanceof Text) {
                String text = ((Text) child).getData().trim();
                if (!text.isEmpty()) {
                    node.add(new DefaultMutableTreeNode(text));
                }
            }
        }
        return node;
    }
    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new XmlParserAndTreeViewer().setVisible(true));
    }
}
