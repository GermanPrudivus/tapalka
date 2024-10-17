package features.case_spining.pages;

import common.interfaces.Page;
import components.Text;
import constants.*;
import features.case_spining.components.FinalAnimation;
import features.case_spining.components.Roullete;
import features.case_spining.components.VerticalLine;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class CaseSpinningPage implements Page {
    final Theme theme = new Theme();

    private JFrame window;

    public CaseSpinningPage(JFrame window) {
        this.window = window;
    }

    @Override
    public void initializeContent() {
        JPanel BackColor = new JPanel();
        BackColor.setBackground(theme.getBackgroundColor());
        BackColor.setOpaque(true);
        BackColor.setLayout(new javax.swing.BoxLayout(BackColor, javax.swing.BoxLayout.Y_AXIS));
        
    
        HeadQuote(BackColor);

        JLayeredPane background = new JLayeredPane();
        background.setOpaque(false);
        background.setBackground(theme.getBlue());
        
        

        //Lighting lighting = new Lighting();
        //lighting.setBounds((size.getWidth()/2) - 375, size.getHeight() - 170, 750, 340);
        //background.add(lighting, Integer.valueOf(0));*/
    

        JPanel verticalLine = new VerticalLine();
        int linex = (window.getWidth() - 2) / 2;
        int lineHeight = (window.getHeight() / 3);
        verticalLine.setBounds(linex, 150, 4, lineHeight);
        background.add(verticalLine, Integer.valueOf(1));
       
        FinalAnimation anim = new FinalAnimation();
        int animWidth = 700;
        int animHeight = 500;
        int animX = (window.getWidth() - animWidth) / 2 ;  
        int animY = (window.getHeight() - animHeight) / 2 - 100;  

        anim.setBounds(animX, animY, animWidth, animHeight);  
        anim.setVisible(false);
    
    
        Roullete roullete = new Roullete(anim);
        //JPanel card = roullete.getMainPanel();
        int cardWidth = 300;
        int cardHeight = 200;
        int cardX = (window.getWidth() - cardWidth) / 2; // Center horizontally
        int cardY = (window.getHeight() / 2) - cardHeight-30; // Position beneath vertical line
        roullete.setBounds(0, cardY, window.getWidth(), cardHeight);
 
        roullete.setBackground(Color.blue);
        roullete.setOpaque(false);

        background.add(roullete, Integer.valueOf(0));
        background.add(anim, Integer.valueOf(2));
        
    

        background.setBounds(0, 0, window.getWidth(), window.getHeight());

        BackColor.add(background);
        window.add(BackColor);
    }

    @Override
    public void resizeContent(int width, int height) {
        
    }

    public void HeadQuote(JPanel back) {
        JPanel HeadText = new JPanel();
        HeadText.setOpaque(false);
        HeadText.setBackground(theme.getGreen());
        HeadText.setMaximumSize(new Dimension(window.getWidth(), 100));
        HeadText.setBorder(new EmptyBorder(30,0,30,0));
        
        HeadText.add(new Text("Tapalka", "Montserrat-SemiBold.ttf", 36f));
        HeadText.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        back.add(HeadText);
    }   
}