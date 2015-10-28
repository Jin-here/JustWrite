/**
 * Created by Administrator on 2015/8/21.
 */
public class HelloWorld {
    public String sayHello(){
        return "Hello Maven";
    }

    public static void main(String[] args){
        System.out.println(new HelloWorld().sayHello());
    }
}
