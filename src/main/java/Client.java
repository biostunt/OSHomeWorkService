import org.tanukisoftware.wrapper.WrapperListener;
import org.tanukisoftware.wrapper.WrapperManager;
import java.net.Socket;
public class Client implements WrapperListener {
    public static void main(String[] args) {
        WrapperManager.start(new Client(),args);
    }
    public Integer start(String[] args){
        new Service().start();
        return null;
    }
    public int stop(int i){
        return 0;
    }
    public void controlEvent(int i){
        WrapperManager.stop(0);
    }
}
