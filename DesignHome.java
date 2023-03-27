import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;

public class DesignHome extends JFrame{
    private JPanel backgroundPanel;
    private JPanel topPanel;
    private JButton lineBtn;
    private JButton arcBtn;
    private JButton roomBtn;
    private JButton labelBtn;
    private JButton colorBtn;
    private JButton undoBtn;
    private JPanel bottomPanel;
    private JButton backBtn;
    private JPanel drawPanel;

    private final int FRAME_WIDTH = 900;
    private final int FRAME_HEIGHT = 800;


    private ArrayList<Shape> shapes = new ArrayList<Shape>();
    private Point2D.Float drawStart = null;
    private Point2D.Float drawEnd = null;

    private int currentAction;

    public DesignHome(){
        setFrame();
        btnConfig();

    }//End constructor

    private void setFrame(){
        setTitle("Home and Room Designer");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setContentPane(backgroundPanel);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }//End setFrame

    private void btnConfig(){
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == backBtn){
                    dispose();
                    HomePage homePage = new HomePage();
                }
            }
        });//END backBtn ActionListener


        lineBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == lineBtn){
                    currentAction = 1;
                    System.out.println(currentAction);

                    addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            drawStart = new Point2D.Float(e.getX(), e.getY());
                        }

                        @Override
                        public void mouseReleased(MouseEvent e) {
                            drawEnd = new Point2D.Float(e.getX(), e.getY());
                            Line2D.Float line2D = new Line2D.Float(drawStart, drawEnd);
                            shapes.add(line2D);
                            repaint();
                        }

                    });
                }
            }
        });//End lineBtn ActionListener

        
    }//End btnConfig method

    @Override
    public void paint(Graphics g) {
        super.paintComponents(g);
        Graphics2D g2d = (Graphics2D) g;
        Iterator<Shape> iterator = shapes.iterator();
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);

        //If the current action is the Line Button (represented as 1)
        if (currentAction == 1) {
            while (iterator.hasNext()) {
                g2d.setStroke(new BasicStroke(2));
                g2d.draw(iterator.next());
            }
        }


    }//End paint method


}//End DesignHome class
