package hexlet.code;

public class Main {
    public static void main(String[] args) {
        String docWithSigns = "I can't shoot straight unless I've had a !?pint,.".replaceAll("[!?.,]", "");
        System.out.println(docWithSigns);
    }
}
