public class Megatron2 {
    public static void greet(String botName) {
        System.out.println("Hello! I'm " + botName + ".");
        System.out.println("What can I do for you?");
    }

    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
    public static void main(String[] args) {
        String botName = "Megatron 2";
        greet(botName);
        bye();
    }
}
