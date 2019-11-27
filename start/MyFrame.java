package start;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MyFrame extends JFrame {

    ArrayList<String> text;
    Font font = new Font("Arial", Font.PLAIN,16);

    int textLines = 100;

    MyFrame(){
        super("Tworzenie plików i folderów");
        setSize(800,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    MyFrame(ArrayList<String> s){
        this();
        text = new ArrayList<>();
        text.add("Raport z działania:");
        font = font.deriveFont(Font.PLAIN);
        text.addAll(s);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setFont(font);
        text.forEach(x ->{
            g.drawString(x,50, textLines);
            textLines +=25;
        });
    }
}
