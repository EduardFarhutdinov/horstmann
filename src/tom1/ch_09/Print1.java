package tom1.ch_09;

public class Print1
{
    public static void main(String[] args) {
try {
    foo();
    System.out.print("A ");
}catch (Exception e){
    System.out.print("B ");
}finally {
    System.out.print("C");
        }
    }

    private static void foo () throws Exception{
        try{
            System.out.print("A1");
throw new Exception();
        }catch (Exception e){
            System.out.print("B1");
        throw new Exception();
        }finally {

            System.out.print("C1");
        }
    }
}
