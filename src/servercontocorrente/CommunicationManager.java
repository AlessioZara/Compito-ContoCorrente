
package servercontocorrente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alessio
 */
public class CommunicationManager implements Runnable{
    private final ContoCorrente cc;
    private Socket s;
    private InputStream in ;
    private OutputStream out;
    private PrintWriter scrittura;
    private BufferedReader lettura;
    boolean x = true;
    //stream    
    
    public CommunicationManager(Socket s, ContoCorrente cc) {
        this.s = s;
        this.cc = cc;
        try {
            in = s.getInputStream();
            out = s.getOutputStream();
            scrittura = new PrintWriter(new OutputStreamWriter(s.getOutputStream()), true);
            lettura = new BufferedReader(new InputStreamReader(s.getInputStream()));
        } catch (IOException ex) {
            Logger.getLogger(CommunicationManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Override
    public void run() {
        try{
        //gestione dei messaggio ricevuti dal client e le funzioni del contoCorrente
        
        
        while(x){
            String funzione = lettura.readLine();
            int scelta = Integer.parseInt(funzione);   
            switch(scelta){
                case 1:
                    funzione = lettura.readLine();
                    float applicazione = Float.parseFloat(funzione); 
                    
                    if(cc.versa(applicazione))
                        scrittura.println("Operazione eseguita");
                    else
                        scrittura.println("Operazione non eseguita");
                   System.out.println(cc.getSaldo());
                   break;
                 case 2:
                    funzione = lettura.readLine();
                    float y = Float.parseFloat(funzione);  
                    if(cc.preleva(y))
                        scrittura.println("Operazione eseguita");
                    else
                        scrittura.println("Operazione non eseguita");
                    System.out.println(cc.getSaldo());
                   break;  
                 case 3:
                     String conto = ""+cc.getSaldo();
                                            System.out.println(conto);
                     scrittura.println(conto);
                     break;
                 default:
                     scrittura.println("Errore!");
                     break;
            }
            if(lettura.readLine().equalsIgnoreCase("Sì"))
                x = false;  
                
            }
            
        } catch(IOException ex) {
            Logger.getLogger(CommunicationManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    //metodo nuovo
    public boolean var(){
        return x;
    }
    
}
