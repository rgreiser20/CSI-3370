import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

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
    //saving and loading functionality
    private JButton saveBtn;
    private JButton loadBtn;
    JFileChooser fileChooser = new JFileChooser();

    private final int FRAME_WIDTH = 1000;
    private final int FRAME_HEIGHT = 800;



    private ArrayList<Shape> shapes = new ArrayList<Shape>(); //Hold all lines (shapes) that are drawn
    private ArrayList<Color> colors = new ArrayList<Color>(); //Holds the colors of the shape
    private Point2D.Float drawStart = null; //The starting point of the drawn shape
    private Point2D.Float drawEnd = null; //The end point of the drawn shape
    private int currentAction; //Variable that represents which button is pressed/active at the moment
    private Color color;
    private static BufferedImage image; // the image we are drawing, for saving and loading
    private static Graphics2D g2d;
    private ImagePanel imagePanel; // references our class for facilitating loading an image


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
                dispose();
                HomePage homePage = new HomePage();
            }
        });//END backBtn ActionListener

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

        colorBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentAction = 5;
                JColorChooser colorChooser = new JColorChooser();
                color = JColorChooser.showDialog(null, "Color Picker", Color.BLACK);

            }
        });//End colorBtn ActionListener

        undoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentAction = 6;
                if(shapes.isEmpty() && colors.isEmpty()){
                    return;
                } else{
                    shapes.remove(shapes.size() - 1);
                    colors.remove(colors.size() - 1);
                    repaint();
                }
            }
        });//End undoBtn ActionListener

        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                System.out.println("save button pushed");
                int userSelection = fileChooser.showSaveDialog(new JFrame());

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();
                    String filePath = fileToSave.getAbsolutePath();

                    try {
                        ImageIO.write(image, "png", new File(filePath));
                        JOptionPane.showMessageDialog(null, "Image Save Successful");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error saving image: " + ex.getMessage());
                    }
                }
            }
        });//END saveBtn ActionListener

        loadBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int userSelection = fileChooser.showOpenDialog(new JFrame());
                imagePanel = new ImagePanel();

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToLoad = fileChooser.getSelectedFile();
                    try {
                        image = ImageIO.read(fileToLoad);
                        imagePanel.setImage(image);
                        drawPanel.add(imagePanel);
                        JOptionPane.showMessageDialog(null, "Image Load Successful");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error loading image: " + ex.getMessage());
                    }
                }
            }
        });//END loadBtn Listener


    }//End btnConfig method

    @Override
    public void paint(Graphics g) {
        super.paintComponents(g);

        // this image will be drawn on in order to be saved as output
        if(image == null) {
            // image to draw null ==> we create
            image = (BufferedImage) createImage(drawPanel.getWidth(), drawPanel.getHeight());
            g2d = (Graphics2D) image.getGraphics();
            // enable antialiasing
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            // clear draw area
            clear();
        }

        // this object is what the user sees in the drawn panel
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        displayShapes(g2);

    }//End paint method


    private void displayShapes(Graphics2D g2){
        for(int i=0; i<shapes.size(); i++){
            //draw on image to save
            g2d.setStroke(new BasicStroke(2));
            g2d.setColor(colors.get(i));
            g2d.draw(shapes.get(i));
            //draw on what is displayed to user
            g2.setStroke(new BasicStroke(2));
            g2.setColor(colors.get(i));
            g2.draw(shapes.get(i));
        }
    }//End drawShapes method

    public void clear() {
        g2d.setPaint(Color.white);
        // draw white on entire draw panel to clear
        g2d.fillRect(drawPanel.getX(), drawPanel.getY(), drawPanel.getWidth(), drawPanel.getHeight());
        g2d.setPaint(Color.black);
        repaint();
    }






}//End DesignHome class
