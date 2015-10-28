/**
 * Created by Administrator on 2015/8/12.
 */
public class TimeServer {
    public static void main(String[] args){
        MultiplexerTimeServer timerServer = new MultiplexerTimeServer(7777);
        new Thread(timerServer, "NIO-MultiplexerTimeServer-001").start();
    }
}
