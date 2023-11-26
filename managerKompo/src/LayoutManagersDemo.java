import javax.swing.*;
import java.awt.*;
public class LayoutManagersDemo extends JFrame {
    public LayoutManagersDemo() {
        setTitle("Layout Managers Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        // Используем BorderLayout для управления компоновкой на уровне JFrame
        setLayout(new BorderLayout());
        // Создаем JPanel с FlowLayout
        JPanel flowLayoutPanel = new JPanel(new FlowLayout());
        flowLayoutPanel.add(new JButton("Button 1"));
        flowLayoutPanel.add(new JButton("Button 2"));
        flowLayoutPanel.add(new JButton("Button 3"));
        // Создаем JPanel с BorderLayout
        JPanel borderLayoutPanel = new JPanel(new BorderLayout());
        borderLayoutPanel.add(new JButton("North"), BorderLayout.NORTH);
        borderLayoutPanel.add(new JButton("West"), BorderLayout.WEST);
        borderLayoutPanel.add(new JButton("Center"), BorderLayout.CENTER);
        borderLayoutPanel.add(new JButton("East"), BorderLayout.EAST);
        borderLayoutPanel.add(new JButton("South"), BorderLayout.SOUTH);
        // Добавляем JPanel с FlowLayout в верхнюю часть JFrame
        add(flowLayoutPanel, BorderLayout.NORTH);
        // Добавляем JPanel с BorderLayout в центр JFrame
        add(borderLayoutPanel, BorderLayout.CENTER);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LayoutManagersDemo().setVisible(true));
    }
}
