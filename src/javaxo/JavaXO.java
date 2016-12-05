/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaxo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zGuindouOS
 */
public class JavaXO {

    /**
     * @param args the command line arguments
     */
    private static boolean CltName;
    private static ArrayList<Socket> GamersSocket;

    private static void PrintIn(Socket s, String place) {
        PrintWriter out = null;
        try {
            for (Socket ss : GamersSocket) {
                if (ss != s) {
                    out = new PrintWriter(ss.getOutputStream());
                    //out.flush();
                    out.println(place);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(JavaXO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.flush();
        }
    }

    public JavaXO() {
        CltName = false;
        GamersSocket = new ArrayList<Socket>();
    }

    public static void main(String[] args) throws IOException {
        // TODO code application logic here

        ServerSocket Ssock = new ServerSocket(8005);

        new JavaXO();

        try {

            while (true) {
                Socket gamerSock = Ssock.accept();
                GamersSocket.add(gamerSock);

                try {
                    Thread newGgamer = new Thread(new Runnable() {
                        public void run() {
                            try {
                                BufferedReader br = new BufferedReader(new InputStreamReader(gamerSock.getInputStream()));
                                PrintWriter out = new PrintWriter(gamerSock.getOutputStream());

                                if (JavaXO.CltName == false) {
                                    out.println("X");
                                    out.flush();
                                    JavaXO.CltName = true;
                                } else {
                                    out.println("O");
                                    out.flush();
                                    JavaXO.CltName = false;
                                }
                                while (true) {

                                    switch (br.readLine()) {
                                        case "00":
                                            System.out.println("hello");
                                            PrintIn(gamerSock, "00");
                                            break;
                                        case "01":
                                            PrintIn(gamerSock, "01");
                                            break;
                                        case "02":

                                            PrintIn(gamerSock, "02");
                                            break;
                                        case "10":
                                            PrintIn(gamerSock, "10");
                                            break;
                                        case "11":
                                            PrintIn(gamerSock, "11");
                                            break;
                                        case "12":
                                            PrintIn(gamerSock, "12");
                                            break;
                                        case "20":
                                            PrintIn(gamerSock, "20");
                                            break;
                                        case "21":
                                            PrintIn(gamerSock, "21");
                                            break;
                                        case "22":
                                            PrintIn(gamerSock, "22");
                                            break;
                                        case "winner"  :
                                            PrintIn(gamerSock, "winner");
                                    }
                                }
                            } catch (IOException ex) {
                                Logger.getLogger(JavaXO.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });
                    newGgamer.start();
                } finally {
                    //gamerSock.close();
                }
            }

        } finally {
            //Ssock.close();
        }
    }
};
