package graphiccontrol;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GraphicControl extends JFrame {
    Animal canvas = new Animal();
    
    JButton up = new JButton("Up");
    JButton down = new JButton("Down");
    JButton right = new JButton("Right");
    JButton left = new JButton("Left");

    public static void main(String[] args) {
        GraphicControl frame = new GraphicControl();
        frame.setTitle("Graphic Control");
        frame.setSize(780, 480);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }

    public GraphicControl() {
        Control control = new Control();

        setLayout(new GridLayout(1, 2));
        add(canvas);
        add(control);
    }
    
    class Control extends JPanel{
        public Control(){
            JPanel control2 = new JPanel();

            control2.setLayout(new GridLayout(2, 1));
            JButton reset = new JButton("Reset");
            reset.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    canvas.reset();
                }
            });
            JButton play = new JButton("Move");
            play.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    JFrame playFrame = new JFrame();
                    MovableAnimal owl = new MovableAnimal();
                    playFrame.add(owl);
                    playFrame.setTitle("Move owl");
                    playFrame.setSize(1080, 720);
                    playFrame.setVisible(true);              
                } 
            });
            control2.add(reset);
            control2.add(play);
              
            up.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    canvas.up();
                }
            });
            down.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    canvas.down();
                }
            });
            right.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    canvas.right();
                }
            });
            left.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    canvas.left();
                }
            });
            
            up.setBackground(Color.yellow);
            down.setBackground(Color.green);
            right.setBackground(Color.red);
            left.setBackground(Color.blue);
            reset.setBackground(Color.gray);
            play.setBackground(Color.LIGHT_GRAY);
            
            setLayout(new BorderLayout());
            add(up, BorderLayout.NORTH);
            add(down, BorderLayout.SOUTH);
            add(right, BorderLayout.EAST);
            add(left, BorderLayout.WEST);
            add(control2, BorderLayout.CENTER);
        }
    }
    
    class Animal extends JPanel {
        int xShift = 0;
        int yShift = 0;
        //OWL
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            int x = getWidth() / 2 + xShift;
            int y = getHeight() / 2 + yShift;
            //ear
            int[] earX = {x - 90, x - 40, x - 90, x - 105};
            int[] earY = {y - 140, y - 60, y - 40, y - 80};
            g.setColor(Color.gray);
            g.fillPolygon(earX, earY, earX.length);
            int[] earX2 = {x + 90, x + 40, x + 90, x + 105};
            int[] earY2 = {y - 140, y - 60, y - 40, y - 80};
            g.setColor(Color.gray);
            g.fillPolygon(earX2, earY2, earX2.length);
            //face
            g.setColor(Color.lightGray);
            g.fillOval(x - 115, y - 90, 230, 180);
            //eye
            g.setColor(Color.black);
            g.fillOval(x, y - 40, 100, 100);
            g.fillOval(x - 100, y - 40, 100, 100);
            g.setColor(Color.white);
            g.fillOval(x + 5, y - 35, 90, 90);
            g.fillOval(x - 95, y - 35, 90, 90);
            g.setColor(Color.black);
            g.fillOval(x + 25, y - 15, 50, 50);
            g.fillOval(x - 75, y - 15, 50, 50);
            g.setColor(Color.white);
            g.fillOval(x + 35, y + 2, 10, 15);
            g.fillOval(x - 45, y + 2, 10, 15);
            //beak
            g.setColor(new Color(166, 85, 0));
            int[] pX = {x, x + 30, x, x - 30};
            int[] pY = {y + 10, y + 35, y + 80, y + 35};
            g.fillPolygon(pX, pY, pX.length);
        }
        
        public void up() {
            yShift -= 5;
            repaint();
        }

        public void down() {
            yShift += 5;
            repaint();
        }

        public void right() {
            xShift += 5;
            repaint();
        }

        public void left() {
            xShift -= 5;
            repaint();
        }

        public void reset() {
            xShift = 0;
            yShift = 0;
            repaint();
        }
    }
    
    class MovableAnimal extends Animal{  
        public MovableAnimal(){
            addMouseMotionListener(new MouseMotionListener() {
                public void mouseDragged(MouseEvent me) {
                }

                @Override
                public void mouseMoved(MouseEvent me) {
                    xShift = me.getX() - getWidth()/2;
                    yShift = me.getY() - getHeight()/2;
                    repaint();
                }
            });
        }
    }
}
