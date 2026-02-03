public class Ui {

    public static void line() {
        for (int i = 0; i < 60; i ++) {
            System.out.print("_");
        }
        System.out.println();
    }

    public static void show(String s) {
        line();
        System.out.println(s);
        line();
    }

    public static void show(Response r) {
        line();
        System.out.println(r.message());
        line();
    }
}