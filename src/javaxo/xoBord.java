/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaxo;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.UIManager;

/**
 *
 * @author zGuindouOS
 */
public class xoBord extends javax.swing.JFrame {

    /**
     * Creates new form xoBord
     */
    private Socket sock;
    private InputStream is;
    private InputStreamReader ipsr;
    private BufferedReader br;
    private OutputStream os;
    private PrintWriter pw;
    private String playerSymbol;
    
    private static Boolean [][] xoGame = new Boolean[3][3];
    
    private boolean GameIsOuver() {
        boolean Ouver = false;
        //if (xoGame[0][0] && xoGame[0][1] && xoGame[0][2]) {
        if((xo00.getText().equals("X") && xo01.getText().equals("X") && xo02.getText().equals("X"))
                || (xo00.getText().equals("O") && xo01.getText().equals("O") && xo02.getText().equals("O"))) {
            //Ouver = true;
            xo00.setBackground(Color.red);
            xo01.setBackground(Color.red);
            xo02.setBackground(Color.red);
            return true;
        }
        //else if (xoGame[1][0] && xoGame[1][1] && xoGame[1][2]) {
        if((xo10.getText().equals("X") && xo11.getText().equals("X") && xo12.getText().equals("X"))
                || (xo10.getText().equals("O") && xo11.getText().equals("O") && xo12.getText().equals("O"))) {
            //Ouver = true;
            xo10.setBackground(Color.red);
            xo11.setBackground(Color.red);
            xo12.setBackground(Color.red);
            return true;
        }
        //else if (xoGame[2][0] && xoGame[2][1] && xoGame[2][2]) {
        if((xo20.getText().equals("X") && xo21.getText().equals("X") && xo22.getText().equals("X"))
                || (xo20.getText().equals("O") && xo21.getText().equals("O") && xo22.getText().equals("O"))) {
            //Ouver = true;
            xo20.setBackground(Color.red);
            xo21.setBackground(Color.red);
            xo22.setBackground(Color.red);
            return true;
        }
        //else if (xoGame[0][0] && xoGame[1][0] && xoGame[2][0]) {
        if((xo00.getText().equals("X") && xo10.getText().equals("X") && xo20.getText().equals("X"))
                || (xo00.getText().equals("O") && xo10.getText().equals("O") && xo20.getText().equals("O"))) {
            Ouver = true;
            xo00.setBackground(Color.red);
            xo10.setBackground(Color.red);
            xo20.setBackground(Color.red);
        }
        //else if (xoGame[0][1] && xoGame[1][1] && xoGame[2][1]) {
        if((xo01.getText().equals("X") && xo11.getText().equals("X") && xo21.getText().equals("X"))
                || (xo01.getText().equals("O") && xo11.getText().equals("O") && xo21.getText().equals("O"))) {
            //Ouver = true;
            xo01.setBackground(Color.red);
            xo11.setBackground(Color.red);
            xo21.setBackground(Color.red);
            return true;
        }
        //else if (xoGame[0][2] && xoGame[1][2] && xoGame[2][2]) {
        if((xo02.getText().equals("X") && xo12.getText().equals("X") && xo22.getText().equals("X"))
                || (xo02.getText().equals("O") && xo12.getText().equals("O") && xo22.getText().equals("O"))) {
            //Ouver = true;
            xo02.setBackground(Color.red);
            xo12.setBackground(Color.red);
            xo22.setBackground(Color.red);
            return true;
        }
        //else if (xoGame[0][0] && xoGame[1][1] && xoGame[2][2]) {
        if((xo00.getText().equals("X") && xo11.getText().equals("X") && xo22.getText().equals("X"))
                || (xo00.getText().equals("O") && xo11.getText().equals("O") && xo22.getText().equals("O"))) {
            //Ouver = true;
            xo00.setBackground(Color.red);
            xo11.setBackground(Color.red);
            xo22.setBackground(Color.red);
            return true;
        }
        //else if (xoGame[0][2] && xoGame[1][1] && xoGame[2][0]) {
        if((xo02.getText().equals("X") && xo11.getText().equals("X") && xo20.getText().equals("X"))
                || (xo02.getText().equals("O") && xo11.getText().equals("O") && xo20.getText().equals("O"))) {
            //Ouver = true;
            xo02.setBackground(Color.red);
            xo11.setBackground(Color.red);
            xo20.setBackground(Color.red);
            return true;
        }
        return Ouver;
    }
    
    private static void BlockGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(xoGame[i][j] == false) xoGame[i][j] = true;
            }
        }
    }

    private String getServerMsg() {
        String msg = null;
        try {
            msg = br.readLine();
        } catch (IOException ex) {
            Logger.getLogger(xoBord.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return msg;
        }
    }

    private void gameSymbol(JLabel label) {
        if (playerSymbol.equals("X")) {
            label.setText("X");
        } else {
            label.setText("O");
        }
    }

    private void ServerPrinting() {
        String str = null;
        switch(playerSymbol) {
            case "X" : str = "O"; break;
            case "O" : str = "X"; break;
        }
        //System.out.println(str);
        try {
            switch(this.br.readLine()) { 
                case "00" : xo00.setBackground(Color.blue); xo00.setText(str); xoGame[0][0] = true; this.enable(); Historique.setText("Gamer "+str+" 00\n"); Historique.repaint(); break;
                case "01" : xo01.setBackground(Color.blue); xo01.setText(str); xoGame[0][1] = true; this.enable(); Historique.setText("Gamer "+str+" 01\n"); Historique.repaint(); break;
                case "02" : xo02.setBackground(Color.blue); xo02.setText(str); xoGame[0][2] = true; this.enable(); Historique.setText("Gamer "+str+" 02\n"); Historique.repaint(); break;
                case "10" : xo10.setBackground(Color.blue); xo10.setText(str); xoGame[1][0] = true; this.enable(); Historique.setText("Gamer "+str+" 10\n"); Historique.repaint(); break;
                case "11" : xo11.setBackground(Color.blue); xo11.setText(str); xoGame[1][1] = true; this.enable(); Historique.setText("Gamer "+str+" 11\n"); Historique.repaint(); break;
                case "12" : xo12.setBackground(Color.blue); xo12.setText(str); xoGame[1][2] = true; this.enable(); Historique.setText("Gamer "+str+" 12\n"); Historique.repaint(); break;
                case "20" : xo20.setBackground(Color.blue); xo20.setText(str); xoGame[2][0] = true; this.enable(); Historique.setText("Gamer "+str+" 20\n"); Historique.repaint(); break;
                case "21" : xo21.setBackground(Color.blue); xo21.setText(str); xoGame[2][1] = true; this.enable(); Historique.setText("Gamer "+str+" 21\n"); Historique.repaint(); break;
                case "22" : xo22.setBackground(Color.blue); xo22.setText(str); xoGame[2][2] = true; this.enable(); Historique.setText("Gamer "+str+" 22\n"); Historique.repaint(); break;
                case "winner" :
                    BlockGame();
                    Historique.setText("You are the winner");
                    repaint();
                    this.enable();
                    break;
            }
        } catch (IOException ex) {
            Logger.getLogger(xoBord.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public xoBord() throws IOException {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        sock = new Socket("localhost", 8005);
        is = sock.getInputStream();
        ipsr = new InputStreamReader(is);
        br = new BufferedReader(ipsr);
        os = sock.getOutputStream();
        pw = new PrintWriter(os, true);
        this.playerSymbol = getServerMsg();
        player.setText(playerSymbol + " Gamer");
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                xoGame[i][j] = false;
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        xo00 = new javax.swing.JLabel();
        xo01 = new javax.swing.JLabel();
        xo02 = new javax.swing.JLabel();
        xo12 = new javax.swing.JLabel();
        xo11 = new javax.swing.JLabel();
        xo10 = new javax.swing.JLabel();
        xo20 = new javax.swing.JLabel();
        xo21 = new javax.swing.JLabel();
        xo22 = new javax.swing.JLabel();
        Historique = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        player = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        xo00.setBackground(new java.awt.Color(255, 255, 255));
        xo00.setFont(new java.awt.Font("Comic Sans MS", 1, 36)); // NOI18N
        xo00.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        xo00.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        xo00.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                xo00MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                xo00MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                xo00MouseExited(evt);
            }
        });

        xo01.setFont(new java.awt.Font("Comic Sans MS", 1, 36)); // NOI18N
        xo01.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        xo01.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        xo01.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                xo01MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                xo01MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                xo01MouseExited(evt);
            }
        });

        xo02.setFont(new java.awt.Font("Comic Sans MS", 1, 36)); // NOI18N
        xo02.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        xo02.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        xo02.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                xo02MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                xo02MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                xo02MouseExited(evt);
            }
        });

        xo12.setFont(new java.awt.Font("Comic Sans MS", 1, 36)); // NOI18N
        xo12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        xo12.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        xo12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                xo12MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                xo12MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                xo12MouseExited(evt);
            }
        });
        xo12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                xo12KeyPressed(evt);
            }
        });

        xo11.setFont(new java.awt.Font("Comic Sans MS", 1, 36)); // NOI18N
        xo11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        xo11.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        xo11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                xo11MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                xo11MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                xo11MouseExited(evt);
            }
        });

        xo10.setFont(new java.awt.Font("Comic Sans MS", 1, 36)); // NOI18N
        xo10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        xo10.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        xo10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                xo10MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                xo10MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                xo10MouseExited(evt);
            }
        });

        xo20.setFont(new java.awt.Font("Comic Sans MS", 1, 36)); // NOI18N
        xo20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        xo20.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        xo20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                xo20MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                xo20MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                xo20MouseExited(evt);
            }
        });

        xo21.setFont(new java.awt.Font("Comic Sans MS", 1, 36)); // NOI18N
        xo21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        xo21.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        xo21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                xo21MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                xo21MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                xo21MouseExited(evt);
            }
        });

        xo22.setFont(new java.awt.Font("Comic Sans MS", 1, 36)); // NOI18N
        xo22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        xo22.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        xo22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                xo22MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                xo22MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                xo22MouseExited(evt);
            }
        });

        Historique.setBackground(new java.awt.Color(255, 255, 255));
        Historique.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        Historique.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Historique.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaxo/Tic-Tac-Toe-Game-grey.png"))); // NOI18N
        jLabel1.setText("XOGame");

        player.setBackground(new java.awt.Color(255, 255, 255));
        player.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        player.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        player.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(player, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(player, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Historique, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(xo00, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(xo01, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(xo02, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(xo20, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(xo21, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(xo22, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(xo10, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(xo11, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(xo12, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(xo01, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(xo00, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(xo02, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(xo11, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(xo12, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(xo21, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(xo22, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(xo10, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(xo20, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(13, 13, 13)
                .addComponent(Historique, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void xo00MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xo00MouseClicked
        // TODO add your handling code here:
        if (!xoGame[0][0]) {
            gameSymbol(xo00);
            xoGame[0][0] = true;
            pw.println("00");
            this.disable();
        }
    }//GEN-LAST:event_xo00MouseClicked

    private void xo01MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xo01MouseClicked
        // TODO add your handling code here:
        if (!xoGame[0][1]) {
            gameSymbol(xo01);
            xoGame[0][1] = true;
            pw.println("01");
            this.disable();
        }
    }//GEN-LAST:event_xo01MouseClicked

    private void xo02MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xo02MouseClicked
        // TODO add your handling code here:
        if (!xoGame[0][2]) {
            gameSymbol(xo02);
            xoGame[0][2] = true;
            pw.println("02");
            this.disable();
        }
    }//GEN-LAST:event_xo02MouseClicked

    private void xo12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xo12MouseClicked
        // TODO add your handling code here:
        if (!xoGame[1][2]) {
            gameSymbol(xo12);
            xoGame[1][2] = true;
            pw.println("12");
            this.disable();
        }
    }//GEN-LAST:event_xo12MouseClicked

    private void xo12KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_xo12KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_xo12KeyPressed

    private void xo11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xo11MouseClicked
        // TODO add your handling code here:
        if (!xoGame[1][1]) {
            gameSymbol(xo11);
            xoGame[1][1] = true;
            pw.println("11");
            this.disable();
        }
    }//GEN-LAST:event_xo11MouseClicked

    private void xo10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xo10MouseClicked
        // TODO add your handling code here:
        if (!xoGame[1][0]) {
            gameSymbol(xo10);
            xoGame[1][0] = true;
            pw.println("10");
            this.disable();
        }
    }//GEN-LAST:event_xo10MouseClicked

    private void xo20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xo20MouseClicked
        // TODO add your handling code here:
        if (!xoGame[2][0]) {
            gameSymbol(xo20);
            xoGame[2][0] = true;
            pw.println("20");
            this.disable();
        }
    }//GEN-LAST:event_xo20MouseClicked

    private void xo21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xo21MouseClicked
        // TODO add your handling code here:
        if (!xoGame[2][1]) {
            gameSymbol(xo21);
            xoGame[2][1] = true;
            pw.println("21");
            this.disable();
        }
    }//GEN-LAST:event_xo21MouseClicked

    private void xo22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xo22MouseClicked
        // TODO add your handling code here:
        if (!xoGame[2][2]) {
            gameSymbol(xo22);
            xoGame[2][2] = true;
            pw.println("22");
            this.disable();
        }
    }//GEN-LAST:event_xo22MouseClicked

    private void xo00MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xo00MouseEntered
        // TODO add your handling code here:
        if (!xoGame[0][0]) {
            xo00.setBackground(Color.blue);
            gameSymbol(xo00);
        }
    }//GEN-LAST:event_xo00MouseEntered

    private void xo00MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xo00MouseExited
        // TODO add your handling code here:
        if (!xoGame[0][0]) {
            xo00.setBackground(Color.white);
            xo00.setText("");
        }
    }//GEN-LAST:event_xo00MouseExited

    private void xo01MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xo01MouseEntered
        // TODO add your handling code here:
        if (!xoGame[0][1]) {
            xo01.setBackground(Color.blue);
            gameSymbol(xo01);
        }
    }//GEN-LAST:event_xo01MouseEntered

    private void xo01MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xo01MouseExited
        // TODO add your handling code here:
        if (!xoGame[0][1]) {
            xo01.setBackground(Color.white);
            xo01.setText("");
        }

    }//GEN-LAST:event_xo01MouseExited

    private void xo02MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xo02MouseEntered
        // TODO add your handling code here:
        if (!xoGame[0][2]) {
            xo02.setBackground(Color.blue);
            gameSymbol(xo02);
        }

    }//GEN-LAST:event_xo02MouseEntered

    private void xo02MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xo02MouseExited
        // TODO add your handling code here:
        if (!xoGame[0][2]) {
            xo02.setBackground(Color.white);
            xo02.setText("");
        }

    }//GEN-LAST:event_xo02MouseExited

    private void xo10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xo10MouseEntered
        // TODO add your handling code here:
        if (!xoGame[1][0]) {
            xo10.setBackground(Color.blue);
            gameSymbol(xo10);
        }
    }//GEN-LAST:event_xo10MouseEntered

    private void xo10MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xo10MouseExited
        // TODO add your handling code here:
        if (!xoGame[1][0]) {
            xo10.setBackground(Color.white);
            xo10.setText("");
        }
    }//GEN-LAST:event_xo10MouseExited

    private void xo11MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xo11MouseEntered
        // TODO add your handling code here:
        if (!xoGame[1][1]) {
            xo11.setBackground(Color.blue);
            gameSymbol(xo11);
        }
    }//GEN-LAST:event_xo11MouseEntered

    private void xo11MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xo11MouseExited
        // TODO add your handling code here:
        if (!xoGame[1][1]) {
            xo11.setBackground(Color.white);
            xo11.setText("");
        }
    }//GEN-LAST:event_xo11MouseExited

    private void xo12MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xo12MouseEntered
        // TODO add your handling code here:
        if (!xoGame[1][2]) {
            xo12.setBackground(Color.blue);
            gameSymbol(xo12);
        }
    }//GEN-LAST:event_xo12MouseEntered

    private void xo12MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xo12MouseExited
        // TODO add your handling code here:
        if (!xoGame[1][2]) {
            xo12.setBackground(Color.white);
            xo12.setText("");
        }
    }//GEN-LAST:event_xo12MouseExited

    private void xo20MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xo20MouseEntered
        // TODO add your handling code here:

        if (!xoGame[2][0]) {
            xo20.setBackground(Color.blue);
            gameSymbol(xo20);
        }
    }//GEN-LAST:event_xo20MouseEntered

    private void xo20MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xo20MouseExited
        // TODO add your handling code here:
        if (!xoGame[2][0]) {
            xo20.setBackground(Color.white);
            xo20.setText("");
        }

    }//GEN-LAST:event_xo20MouseExited

    private void xo21MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xo21MouseEntered
        // TODO add your handling code here:
        if (!xoGame[2][1]) {
            xo21.setBackground(Color.blue);
            gameSymbol(xo21);
        }

    }//GEN-LAST:event_xo21MouseEntered

    private void xo21MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xo21MouseExited
        // TODO add your handling code here:
        if (!xoGame[2][1]) {
            xo21.setBackground(Color.white);
            xo21.setText("");
        }

    }//GEN-LAST:event_xo21MouseExited

    private void xo22MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xo22MouseEntered
        // TODO add your handling code here:
        if (!xoGame[2][2]) {
            xo22.setBackground(Color.blue);
            gameSymbol(xo22);
        }

    }//GEN-LAST:event_xo22MouseEntered

    private void xo22MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xo22MouseExited
        // TODO add your handling code here:
        if (!xoGame[2][2]) {
            xo22.setBackground(Color.white);
            xo22.setText("");
        }

    }//GEN-LAST:event_xo22MouseExited

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws IOException {
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(xoBord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(xoBord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(xoBord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(xoBord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        xoBord xo = new xoBord();
       /* Thread th1 = new Thread(new Runnable() {
            public void run() {
               xo.setVisible(true);
            }
        });
        th1.start();*/
        xo.setVisible(true);
        
        Thread th = new Thread(new Runnable() {
            public void run() {

                while(true && !xo.GameIsOuver()) {
                    xo.ServerPrinting();
                    if (xo.GameIsOuver()) {
                        BlockGame();
                        xo.pw.println("winner");
                        xo.enable();
                    }
                }                   
            }
        });
        th.start();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Historique;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel player;
    private javax.swing.JLabel xo00;
    private javax.swing.JLabel xo01;
    private javax.swing.JLabel xo02;
    private javax.swing.JLabel xo10;
    private javax.swing.JLabel xo11;
    private javax.swing.JLabel xo12;
    private javax.swing.JLabel xo20;
    private javax.swing.JLabel xo21;
    private javax.swing.JLabel xo22;
    // End of variables declaration//GEN-END:variables
}
