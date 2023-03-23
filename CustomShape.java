import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomShape extends JFrame{
    private JPanel backgroundPanel;
    private JButton moveButton;
    private JButton resizeButton;
    private JButton labelButton;
    private JButton colorPickerButton;
    private JButton deleteShapeButton;
    private JPanel shapePanel;
    private JButton rectBtn;
    private JButton circleBtn;
    private JButton ovalBtn;
    private JButton triangleBtn;
    private JButton lineBtn;
    private JLabel shapePanelLbl;
    private JButton backBtn;

    private final int FRAME_WIDTH = 800;
    private final int FRAME_HEIGHT = 800;

    public CustomShape(){
        setFrame();
        btnConfig();
    }

    private void setFrame(){
        setTitle("Home and Room Designer");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setContentPane(backgroundPanel);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

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
    }
}
