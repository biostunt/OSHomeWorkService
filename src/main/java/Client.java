import org.tanukisoftware.wrapper.WrapperListener;
import org.tanukisoftware.wrapper.WrapperManager;
import java.net.Socket;
public class Client implements WrapperListener {
    public static void main(String[] args) {
        WrapperManager.start(new Client(),args);
    }
    public Integer start(String[] args){
        int n = 0;
        while(n++ < 60000 * 60){
            try{
                Socket socket = new Socket("localhost",1234);
                if(socket.isConnected()){
                    socket.close();
                    new Service().start();
                    n = 0;
                }
                Thread.sleep(1000);
            } catch (Exception e){
                System.out.println("Can't Connect To MasterServer. Trying again...");
            }
        }
        return null;
    }
    public int stop(int i){
        return 0;
    }
    public void controlEvent(int i){
        WrapperManager.stop(0);
    }
}
