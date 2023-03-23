import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JFrame{
    private JPanel mainPanel;
    private JButton makeShapeBtn;
    private JButton newHomeBtn;
    private JButton newRoomBtn;
    private JButton loadHomeBtn;
    private JButton loadRoomBtn;
    private JLabel title;
    private JLabel homeLbl;
    private JLabel roomLbl;

    private final int FRAME_WIDTH = 500;
    private final int FRAME_HEIGHT = 500;


    public HomePage(){
        setFrame();
        btnConfig();
    }//End constructor

    private void setFrame(){
        setTitle("Home and Room Designer");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setContentPane(mainPanel);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }//End setFrame

    private void btnConfig(){
        newHomeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == newHomeBtn){
                    dispose();
                    DesignHome designHome = new DesignHome();
                }
            }
        }); //End newHomeBtn ActionListener

        //TODO: loadHome Button

        //TODO: newRoom Button

        //TODO: loadRoom Button

        makeShapeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == makeShapeBtn){
                    dispose();
                    CustomShape customShape = new CustomShape();
                }
            }
        });//End newRoomBtn actionListener

    }//End btnConfig










}//End MainPage class
