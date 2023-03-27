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



    private ArrayList<Shape> shapes = new ArrayList<Shape>(); //Hold all lines (shapes) that are drawn
    private Point2D.Float drawStart = null;
    private Point2D.Float drawEnd = null;

    private int currentAction; //Variable that represents which button is pressed/active at the moment

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


        undoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == undoBtn){
                    currentAction = 6;
                    int lastShape = shapes.size() - 1;
                    shapes.remove(lastShape);
                    repaint();
                }
            }
        });//End undoBtn ActionListener

        
    }//End btnConfig method

    @Override
    public void paint(Graphics g) {
        super.paintComponents(g);
        Graphics2D g2d = (Graphics2D) g;

        //Iterates through the shapes arraylist
        Iterator<Shape> iterator = shapes.iterator();

        //Makes drawn Lines smoother
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);

        //If the current action is the Line Button (represented as 1 for the first button of JPanel)
        if (currentAction == 1) {
            while (iterator.hasNext()) {
                g2d.setStroke(new BasicStroke(2));
                g2d.draw(iterator.next());
            }
        }

        //If the current action is the Undo Button (represented as 6 for the sixth button of JPanel)
        if(currentAction == 6){
            while (iterator.hasNext()) {
                g2d.setStroke(new BasicStroke(2));
                g2d.draw(iterator.next());
            }
        }


    }//End paint method


}//End DesignHome class
