import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class Main {
    public static AtomicLong length3 = new AtomicLong(0);
    public static AtomicLong length4 = new AtomicLong(0);
    public static AtomicLong length5 = new AtomicLong(0);

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }

        new Thread(() -> {
            for(int i = 0; i < texts.length; i++){
                if (texts[i].length() == 3){
                    if (texts[i].charAt(0) == texts[i].charAt(2)){
                        length3.getAndIncrement();
                    }
                } else if (texts[i].length() == 4){
                    if (texts[i].charAt(0) == texts[i].charAt(3) && texts[i].charAt(1) != texts[i].charAt(2)){
                        length4.getAndIncrement();
                    }
                } else if(texts[i].length() == 5){
                    if (texts[i].charAt(0) == texts[i].charAt(4) && texts[i].charAt(1) != texts[i].charAt(3)){
                        length5.getAndIncrement();
                    }
                }
            }
        }).start();

        new Thread(() -> {
            for(int i = 0; i < texts.length; i++){
                if (texts[i].length() == 3){
                    if (texts[i].equals("aaa") || texts[i].equals("bbb") || texts[i].equals("ccc")){
                        length3.getAndIncrement();
                    }
                } else if (texts[i].length() == 4){
                    if (texts[i].equals("aaaa") || texts[i].equals("bbbb") || texts[i].equals("cccc")){
                        length4.getAndIncrement();
                    }
                } else if(texts[i].length() == 5){
                    if (texts[i].equals("aaaaa") || texts[i].equals("bbbbb") || texts[i].equals("ccccc")){
                        length5.getAndIncrement();
                    }
                }
            }
        }).start();

        new Thread(() -> {
            for(int i = 0; i < texts.length; i++){
                if (texts[i].length() == 3){
                    if (texts[i].charAt(0) <= texts[i].charAt(1) && texts[i].charAt(1) <= texts[i].charAt(2)){
                        length3.getAndIncrement();
                    }
                } else if (texts[i].length() == 4){
                    if (texts[i].charAt(0) <= texts[i].charAt(1) && texts[i].charAt(1) <= texts[i].charAt(2) &&
                            texts[i].charAt(2) <= texts[i].charAt(3)){
                        length4.getAndIncrement();
                    }
                } else if(texts[i].length() == 5){
                    if (texts[i].charAt(0) <= texts[i].charAt(1) && texts[i].charAt(1) <= texts[i].charAt(2) &&
                            texts[i].charAt(2) <= texts[i].charAt(3) && texts[i].charAt(3) <= texts[i].charAt(4)){
                        length5.getAndIncrement();
                    }
                }
            }
        }).start();





        Thread.sleep(300);
        System.out.println("Красивых слов с длиной 3: " + length3 + " шт");
        System.out.println("Красивых слов с длиной 4: " + length4 + " шт");
        System.out.println("Красивых слов с длиной 5: " + length5 + " шт");

    }


    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }
}
