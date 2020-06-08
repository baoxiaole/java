/**
 * bxl
 * 2019/10/3 21:23
 */
public class NoVisibility {

    private static boolean ready;
    private static int number;

    private static class ReaderTread extends Thread{
        public void run(){
            while(!ready)
                System.out.println("yield!!");
                Thread.yield();
            System.out.println(number);
        }
    }

    public static void main(String[] args){
        new ReaderTread().run();
        number = 42;
        ready = true;
    }

}
