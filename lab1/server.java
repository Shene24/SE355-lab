 import java.io.*;
import java.net.*;
import java.util.Scanner;
 public class server {

    public static void main(String[] args) {
        final PrintWriter dataout;
        final BufferedReader dInput;
        final Scanner scan = new Scanner(System.in);
        try {
            ServerSocket ss = new ServerSocket(2022);
            Socket so = ss.accept();
             dataout = new PrintWriter(so.getOutputStream());
             dInput = new BufferedReader( new InputStreamReader(so.getInputStream()));
            //System.out.println(dInput.readUTF());
            
            //dataout.writeUTF("SE355");
            Thread sender = new Thread(new Runnable() {
                String msg;
                @Override
                public void run(){
                    while(true){
                      //  System.out.println("connected");
                        msg = scan.nextLine();
                        dataout.println(msg);
                        dataout.flush();
                    }
                }
            });
            sender.start();
            Thread receiver = new Thread(new Runnable() {
                String msg;
                @Override
                public void run(){
                    try {
                        msg =dInput.readLine();
                        while(msg!=null){
                            System.out.println("Client: "+ msg);
                            msg =dInput.readLine();
                        }
                        System.out.println("Client deconnect");
                        dataout.close();
                        so.close();
                        ss.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        // TODO: handle exception
                    }
                }
             });
             receiver.start();

            } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}
