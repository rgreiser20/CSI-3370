
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;
import java.util.ArrayList;

public class DesignHome extends JFrame{
    private JPanel backgroundPanel;
    private JPanel topPanel;
    private JButton lineBtn;
    private JButton arcBtn;
    private JButton labelBtn;
    private JButton colorBtn;
    private JButton undoBtn;
    private JPanel bottomPanel;
    private JButton backBtn;
    private JPanel drawPanel;

    private final int FRAME_WIDTH = 1000;
    private final int FRAME_HEIGHT = 800;



    private ArrayList<Shape> shapes = new ArrayList<Shape>(); //Hold all shapes that are drawn
    private ArrayList<Color> colors = new ArrayList<Color>(); //Holds the colors of the shape
    private ArrayList<JButton> buttons = new ArrayList<JButton>(); //Holds the JButtons
    private Point2D.Float drawStart = null; //The starting point of the drawn shape
    private Point2D.Float drawEnd = null; //The end point of the drawn shape
    private int currentAction; //Variable that represents which button is pressed/active at the moment
    private Color color;




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
        drawPanel.setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }//End setFrame

    private void btnConfig(){
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                HomePage homePage = new HomePage();
            }
        });//End backBtn ActionListener

        lineBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentAction = 1;
                addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if(currentAction ==  1) {
                            drawStart = new Point2D.Float(e.getX(), e.getY());
                        }
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        if(currentAction == 1) {
                            drawEnd = new Point2D.Float(e.getX(), e.getY());
                            Line2D.Float line2D = new Line2D.Float(drawStart, drawEnd);
                            shapes.add(line2D);
                            colors.add(color);
                            repaint();
                        }
                    }
                });
            }
        });//End lineBtn ActionListener

        arcBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentAction = 2;
                addMouseListener(new MouseAdapter() {
                    Point2D.Float start = null;
                    Point2D.Float end = null;
                    Point2D.Float controlP = null;
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if(currentAction == 2){
                            if(start == null ){
                                start = new Point2D.Float(e.getX(), e.getY());
                            } else if (end == null) {
                                end = new Point2D.Float(e.getX(), e.getY());
                            } else if (controlP == null) {
                                controlP = new Point2D.Float(e.getX(), e.getY());
                                QuadCurve2D curve2D = new QuadCurve2D.Float();
                                curve2D.setCurve(start, controlP, end);
                                shapes.add(curve2D);
                                colors.add(color);
                                repaint();
                                start = null;
                                end = null;
                                controlP = null;
                            }
                        }
                    }
                });
            }
        });//End arcBtn ActionListener

        labelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentAction = 3;
                addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if(currentAction == 3) {
                            int xPos = e.getX() - 52;
                            int yPos = e.getY() - 100;

                            String roomName = JOptionPane.showInputDialog("Enter room name:");
                            JButton roomBtn = new JButton(roomName);
                            roomBtn.setFont(new Font("Arial", Font.BOLD, 10));
                            roomBtn.setLocation(xPos, yPos);
                            roomBtn.setSize(90,30);
                            drawPanel.add(roomBtn);
                            buttons.add(roomBtn);
                            repaint();
                        }
                    }
                });
            }
        });//End labelBtn ActionListener


        colorBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentAction = 4;
                JColorChooser colorChooser = new JColorChooser();
                color = JColorChooser.showDialog(null, "Color Picker", Color.BLACK);
            }
        });//End colorBtn ActionListener

        undoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentAction = 5;
                if(shapes.isEmpty() && colors.isEmpty()){
                    return;
                } else{
                    shapes.remove(shapes.size() - 1);
                    colors.remove(colors.size() - 1);
                    repaint();
                }
            }
        });//End undoBtn ActionListener

    }//End btnConfig method

    @Override
    public void paint(Graphics g) {
        super.paintComponents(g);
        Graphics2D g2d = (Graphics2D) g;

        //Makes drawn Lines smoother
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
    }//End displayShapes method






}//End DesignHome class
