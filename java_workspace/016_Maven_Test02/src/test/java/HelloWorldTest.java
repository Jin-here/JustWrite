import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by Administrator on 2015/8/21.
 */
public class HelloWorldTest {
    @Test
    public void testSayHello(){
        HelloWorld helloWorld = new HelloWorld();
        String result = helloWorld.sayHello();
        assertEquals("Hello Maven", result);
    }
}
