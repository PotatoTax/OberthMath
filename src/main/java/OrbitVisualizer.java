import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class OrbitVisualizer extends JPanel {
    private ArrayList<Shape> shapes;

    private OrbitVisualizer() {
        shapes = new ArrayList<>();

        generateOrbit(10000, 100);

        setSize(1000, 1000);
        setPreferredSize(new Dimension(1000, 1000));
        setMinimumSize(new Dimension(1000, 1000));
        setVisible(true);
    }

    private void generateOrbit(float apogee, float perigee) {
        float semiMajor = (apogee + perigee) / 2 + 6378;
        float width = apogee + perigee + 12756;
        float eccentricity = (width / 2 - perigee - 6378) / (width / 2);
        float height = (float) (2 * semiMajor * Math.sqrt(1 - Math.pow(eccentricity, 2)));

        System.out.println("Width : " + width);
        System.out.println("Height : " + height);
        System.out.println("Eccentricity : " + eccentricity);

        float scale = width / 1000;
        System.out.println("Scale : " + scale);

        shapes.add(new Ellipse2D.Float(0, (1000 - (height / scale)) / 2, width / scale, height / scale));
        shapes.add(new Ellipse2D.Float(perigee / scale, (1000 - (12756 / scale)) / 2, 12756 / scale, 12756 / scale));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Orbit Visualizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(new OrbitVisualizer());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        for (Shape s : shapes) {
            g2d.draw(s);
        }
        g2d.dispose();
    }
}
