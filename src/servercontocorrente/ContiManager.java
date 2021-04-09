package servercontocorrente;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alessio
 */
public class ContiManager {
    private HashMap<String, ContoCorrente> lista;
    private String[] t;

    public ContiManager() {
        lista = new HashMap();
        t = new String[10];
        genera();
    }
    
    private void genera(){
          for(int i = 0; i < 10; i++){
              lista.put(""+i, new ContoCorrente(""+i));
              t[i] = ""+i;
          }
    }
    
    public String getListaConti(){
        String l="";
        for(String s : lista.keySet()){
            l = l +"::"+s;
        }
        return l;
    }
    
    public boolean addClient(Socket s, String nconto){
        lista.get(nconto).addClient(s);
        return true;
    }
     public void setLista(HashMap<String, ContoCorrente> lista){
        this.lista = lista;
    }
    
    public void Salvattaggio(){
        File file = new File("");
        File file1 = new File(file.getAbsoluteFile()+"\\ContiManager.txt");
        FileWriter file2;
        try {
            file2 = new FileWriter(file1.getAbsoluteFile());
            BufferedWriter wr = new BufferedWriter(file2);
            for(int i = 0; i< lista.size(); i++){
                wr.write(t[i]+":"+lista.get(i).getSaldo());
                wr.newLine();     
            }
            wr.flush();
            wr.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ContiManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ContiManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
