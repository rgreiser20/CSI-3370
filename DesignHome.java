import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;
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
    private JButton deleteBtn;
    private JPanel bottomPanel;
    private JButton backBtn;
    private JPanel drawPanel;

    private final int FRAME_WIDTH = 900;
    private final int FRAME_HEIGHT = 800;

    ArrayList<Line2D.Float> lines = new ArrayList<Line2D.Float>();
    Point2D.Float drawStart = null;
    Point2D.Float drawEnd = null;

    Point2D.Float controlPoint = null;

    int currentAction;

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
        });//End backBtn ActionListener


        lineBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == lineBtn){
                    currentAction = 1;
                    addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            drawStart = new Point2D.Float(e.getX(), e.getY());
                        }

                        @Override
                        public void mouseReleased(MouseEvent e){
                            drawEnd = new Point2D.Float(e.getX(), e.getY());
                            Line2D.Float line2D = new Line2D.Float(drawStart, drawEnd);
                            lines.add(line2D);
                            repaint();
                        }
                    });//End button mouseListener
//                    LineShape line = new LineShape();
                }
            }
        });//End lineBtn ActionListener



        
    }//End btnConfig method

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        if (currentAction == 1) {
            Iterator<Line2D.Float> iterator = lines.iterator();
            while (iterator.hasNext()) {
                g2d.setStroke(new BasicStroke(2));
                g2d.draw(iterator.next());
            }
        }


    }//End paint method


}//End DesignHome class
