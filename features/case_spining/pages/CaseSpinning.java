package features.case_spining.pages;

import components.Text;
import constants.*;
import features.case_spining.components.FinalAnimation;
import features.case_spining.components.Roullete;
import features.case_spining.components.VerticalLine;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class CaseSpinning {
    final Size size = new Size();
    final Theme theme = new Theme();
    

    public CaseSpinning(JFrame window) {
        window.setSize(size.getWidth(), size.getHeight());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

        startContent(window);
        window.setVisible(true);
        
    }

    public void startContent(JFrame window) {
        JPanel BackColor = new JPanel();
        BackColor.setBackground(theme.getBackgroundColor());
        BackColor.setOpaque(true);
        BackColor.setLayout(new javax.swing.BoxLayout(BackColor, javax.swing.BoxLayout.Y_AXIS));
        
    
        HeadQuote(BackColor);

        JLayeredPane background = new JLayeredPane();
        background.setOpaque(false);
        background.setBackground(theme.getBlue());
        

        JPanel verticalLine = new VerticalLine();
        int linex = (size.getWidth() - 2) / 2;
        int lineHeight = (size.getHeight() / 3);
        verticalLine.setBounds(linex, 150, 4, lineHeight);
        background.add(verticalLine, Integer.valueOf(1));
       
        FinalAnimation anim = new FinalAnimation();
        int animWidth = 700;
        int animHeight = 500;
        int animX = (size.getWidth() - animWidth) / 2 ;  
        int animY = (size.getHeight() - animHeight) / 2 - 100;  

        anim.setBounds(animX, animY, animWidth, animHeight);  
        anim.setVisible(false);
    
    
        Roullete roullete = new Roullete(anim);
        JPanel card = roullete.getMainPanel();
        int cardHeight = 200;
        int cardY = (size.getHeight() / 2) - cardHeight-30; // Position beneath vertical line
        card.setBounds(0, cardY, size.getWidth(), cardHeight);
 
        card.setBackground(Color.blue);
        card.setOpaque(false);

        background.add(card, Integer.valueOf(0));
        background.add(roullete.blurOverlay, Integer.valueOf(1));
        background.add(anim, Integer.valueOf(2));
        
        background.setBounds(0, 0, size.getWidth(), size.getHeight());

        BackColor.add(background);
        window.add(BackColor);

    }


    public void HeadQuote(JPanel back) {
        JPanel HeadText = new JPanel();
        HeadText.setOpaque(false);
        HeadText.setBackground(theme.getGreen());
        HeadText.setMaximumSize(new Dimension(size.getWidth(), 100));
        HeadText.setBorder(new EmptyBorder(30,0,30,0));
        
        HeadText.add(new Text("Tapalka", "Montserrat-SemiBold.ttf", 36f));
        HeadText.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        back.add(HeadText);
    }







   
}