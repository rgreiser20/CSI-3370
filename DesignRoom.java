import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class DesignRoom extends JFrame{
    private JPanel backgroundPanel;
    private JButton lineBtn;
    private JButton ellipseBtn;
    private JButton rectangleBtn;
    private JButton colorBtn;
    private JButton undoBtn;
    private JButton backBtn;

    private JPanel topPanel;

    private JPanel bottomPanel;

    private JPanel drawPanel;
    private final int FRAME_WIDTH = 1000;
    private final int FRAME_HEIGHT = 800;


    private ArrayList<Shape> shapes = new ArrayList<Shape>(); //Hold all lines (shapes) that are drawn
    private ArrayList<Color> colors = new ArrayList<Color>(); //Holds the colors of the shape
    private Point2D.Float drawStart = null; //The starting point of the drawn shape
    private Point2D.Float drawEnd = null; //The end point of the drawn shape
    private int currentAction; //Variable that represents which button is pressed/active at the moment

    private Color color = null;

    public DesignRoom(){
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
                    addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            if(currentAction == 1) {
                                drawStart = new Point2D.Float(e.getX(), e.getY());
                            } else if (currentAction == 2) {
                                drawStart = new Point2D.Float(e.getX(), e.getY());
                            } else if (currentAction == 3) {
                                drawStart = new Point2D.Float(e.getX(), e.getY());
                            }
                        }

                        @Override
                        public void mouseReleased(MouseEvent e) {
                            drawEnd = new Point2D.Float(e.getX(), e.getY());

                            if(currentAction == 1) {
                                Line2D.Float line2D = new Line2D.Float(drawStart, drawEnd);
                                shapes.add(line2D);
                                colors.add(color);
                            } else if (currentAction == 2) {
                                double x1 = drawStart.getX();
                                double y1 = drawStart.getY();
                                double x2 = drawEnd.getX();
                                double y2 = drawEnd.getY();
//                                System.out.println("x1:" + x1 + ", y1:" + y1 + "\nx2:" + x2 + ", y2" + y2);
                                double x = 0;
                                double y = 0;
                                double w = 0;
                                double h = 0;
                                if(x1<x2){
                                    if(y1<y2){
                                        x = x1;
                                        y = y1;
                                        w = x2-x1;
                                        h = y2-y1;
                                    } else {
                                        x = x1;
                                        y = y2;
                                        w = x2-x1;
                                        h = y1-y2;
                                    }
                                } else {
                                    if(y1<y2){
                                        x = x2;
                                        y = y1;
                                        w = x1-x2;
                                        h = y2-y1;
                                    } else {
                                        x = x2;
                                        y = y2;
                                        w = x1-x2;
                                        h = y1-y2;
                                    }
                                }

                                Rectangle2D.Double rectangle2D = new Rectangle2D.Double(x, y, w, h);
                                shapes.add(rectangle2D);
                                colors.add(color);
                            } else if (currentAction == 3) {
                                double x1 = drawStart.getX();
                                double y1 = drawStart.getY();
                                double x2 = drawEnd.getX();
                                double y2 = drawEnd.getY();
//                                System.out.println("x1:" + x1 + ", y1:" + y1 + "\nx2:" + x2 + ", y2" + y2);
                                double x = 0;
                                double y = 0;
                                double w = 0;
                                double h = 0;
                                if(x1<x2){
                                    if(y1<y2){
                                        x = x1;
                                        y = y1;
                                        w = x2-x1;
                                        h = y2-y1;
                                    } else {
                                        x = x1;
                                        y = y2;
                                        w = x2-x1;
                                        h = y1-y2;
                                    }
                                } else {
                                    if(y1<y2){
                                        x = x2;
                                        y = y1;
                                        w = x1-x2;
                                        h = y2-y1;
                                    } else {
                                        x = x2;
                                        y = y2;
                                        w = x1-x2;
                                        h = y1-y2;
                                    }
                                }

                                Ellipse2D.Double ellipse2D = new Ellipse2D.Double(x, y, w, h);
                                shapes.add(ellipse2D);
                                colors.add(color);
                            }
                            repaint();
                        }
                    });
                }
            }
        });//End lineBtn ActionListener

        rectangleBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == rectangleBtn){
                    currentAction = 2;
                }
            }
        });//End rectangleBtn ActionListener

        ellipseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == ellipseBtn){
                    currentAction = 3;
                }
            }
        });//End ellipseBtn ActionListener

        colorBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == colorBtn){
                    currentAction = 5;
                    JColorChooser colorChooser = new JColorChooser();
                    color = JColorChooser.showDialog(null, "Color Picker", Color.BLACK);
                }
            }
        });//End colorBtn ActionListener

        undoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == undoBtn){
                    if(shapes.isEmpty() && colors.isEmpty()){
                        return;
                    } else{
                        currentAction = 6;
                        shapes.remove(shapes.size() - 1);
                        colors.remove(colors.size() - 1);
                        repaint();
                    }
                }
            }
        });//End undoBtn ActionListener

        
    }//End btnConfig method

    @Override
    public void paint(Graphics g) {
        super.paintComponents(g);
        Graphics2D g2d = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);
        displayShapes(g2d);
    }//End paint method


    private void displayShapes(Graphics2D g2d){
        for(int i=0; i<shapes.size(); i++){
            g2d.setStroke(new BasicStroke(2));
            g2d.setColor(colors.get(i));
            g2d.draw(shapes.get(i));
        }
    }//End drawShapes method

}
