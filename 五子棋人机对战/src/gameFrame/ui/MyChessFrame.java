package gameFrame.ui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyChessFrame extends JFrame implements MouseListener {
    int x=0,y=0;//保存棋子坐标
    int [][] allChess=new int[19][19];//记录棋子
    boolean isBlack=true;//判断黑棋先下还是白棋,默认黑棋
    boolean gameOver=false;
    String Object="黑方";

    public MyChessFrame() { setTitle("五子棋单机游戏");
        setSize(600,700);
        addMouseListener(this);

        setLocation(600, 200);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置窗口默认关闭操作


    }



    public void paint(Graphics g){
        ImageIcon icon1=new ImageIcon("picture\\背景.jpg");//加载图片
        Image image1=icon1.getImage();
        g.drawImage(image1,0,0,this);

        ImageIcon icon=new ImageIcon("picture\\木色.jpg");//加载图片
        Image image=icon.getImage();
        g.drawImage(image,20,100,450,450,this);

        g.setFont(new Font("微软雅黑",Font.BOLD,20));
        g.drawString("现在的下棋方为："+Object,20,80);

        g.setFont(new Font("宋体",Font.BOLD,20));
        g.drawString("开始游戏",500,150);
        g.drawString("重新开始",500,250);
        g.drawString("悔棋",500,350);

        g.drawString("认输",500,450);

        g.setFont(new Font("黑体",0,25));
        g.drawString("黑方时间",20,650);
        g.setFont(new Font("黑体",0,25));
        g.drawString("白方时间",220,650);

        /***绘制棋盘格19*19,像素***/
        for(int i=0;i<19;i++){
            g.drawLine(20,100+25*i,470,100+25*i);
            g.drawLine(20+25*i,100,20+25*i,550);
        }
        for(int i=0;i<3;i++){
            g.fillOval(93,173+150*i,6,6);
            g.fillOval(93+150,173+150*i,6,6);
            g.fillOval(93+300,173+150*i,6,6);
        }


        for(int i=0;i<19;i++){
            for(int j=0;j<19;j++){
                if(allChess[i][j]==1){//黑子
                    int tempX=i*25+20;
                    int tempY=j*25+100;
                    g.fillOval(tempX-7,tempY-7,14,14);
                }
                if(allChess[i][j]==2){
                    int tempX=i*25+20;
                    int tempY=j*25+100;
                    g.setColor(Color.WHITE);
                    g.fillOval(tempX-7,tempY-7,14,14);
                    g.setColor(Color.BLACK);
                    g.drawOval(tempX-7,tempY-7,14,14);
                }

            }
        }




    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //JOptionPane.showMessageDialog(this,"鼠标点击");

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(gameOver==false){
            x=e.getX();
            y=e.getY();
            System.out.println(x);
            System.out.println(y);
            if(x>=20&&x<=470&&y>=100&&y<=550){//判断是否在棋盘格内
                //System.out.println(x);
                x=(x-20)/25;
                y=(y-100)/25;


                if(allChess[x][y]==0){
                    if(isBlack==true){
                        allChess[x][y]=1;
                        isBlack=false;
                        Object="白方";
                    }else{

                        allChess[x][y]=2;
                        isBlack=true;
                        Object="黑方";

                    }
                    boolean winFlag=this.checkWin();
                    if(winFlag==true){
                        JOptionPane.showMessageDialog(this,"游戏结束"+(allChess[x][y]==1?"黑方":"白方")+"获胜");
                        gameOver=true;
                    }
                }

                else{
                    JOptionPane.showMessageDialog(this,"此位置已有棋子，请重新选择");
                }

                this.repaint();
            }
        }
        if(e.getX()>=500&&e.getX()<=550&&e.getY()>=330&&e.getY()<=350){
            JOptionPane.showMessageDialog(this,"悔棋");


        }



    }
    public boolean checkWin(){
        boolean flag=false;
        //判断以该棋子为出发点的水平、竖直及两条分别为45度角和135度角的线四个方向，是否最后落子的一方构成了连续五个棋子。

        int color=allChess[x][y];//表示棋子颜色

        // ****判断横向是否五连，即allChess[x][y]中y相同
        int i=1;
        int count = 1;//相连棋子个数
        while(color==allChess[x+i][y]){
            count++;
            i++;
        }
        i=1;
        while(color==allChess[x-i][y]){
            count++;
            i++;
        }
        if(count>=5){
            flag=true;
        }

        // ****判断纵向是否五连，即allChess[x][y]中x相同
        int i2=1;
        int count2 = 1;//相连棋子个数
        while(color==allChess[x][y+i2]){
            count2++;
            i2++;
        }
        i2=1;
        while(color==allChess[x][y-i2]){
            count2++;
            i2++;
        }
        if(count2>=5){
            flag=true;
        }

        //****判断45度方向向是否五连
        int i3=1;
        int count3 = 1;//相连棋子个数
        while(color==allChess[x-i3][y+i3]){
            count3++;
            i3++;
        }
        i3=1;
        while(color==allChess[x+i3][y-i3]){
            count3++;
            i3++;
        }
        if(count3>=5){
            flag=true;
        }
        //****判断135度方向是否五连
        int i4=1;
        int count4 = 1;//相连棋子个数
        while(color==allChess[x+i4][y+i4]){
            count4++;
            i4++;
        }
        i4=1;
        while(color==allChess[x-i4][y-i4]){
            count4++;
            i4++;
        }
        if(count4>=5){
            flag=true;
        }

        return flag;
}
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
        //JOptionPane.showMessageDialog(this,"鼠标离开");

    }
}


