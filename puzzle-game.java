package puzzle-game;
import java.util.*;
/**
 *
 * @author Talha Celik
 * @school Necmettin Erbakan University
 * @number 15010011051
 *
 */
public class puzzle-game {
    //----------- Global Variables -----------//

    public static ArrayList<Integer> numbers = new ArrayList<>();
    public static Scanner in = new Scanner(System.in);
    public static int move_count = 0;

    //----------- Global Variables -----------//  
    public static void main() {
        System.out.println("Kayan Puzzle Oyunu");
        try {
            create_number();
            print_number();
            game();
        } catch (Exception e) {
            System.err.println("Hata Cikti. Sebebi : "+e);
        }

    } // main END

    public static void create_number() {
        // Rastgele sayi uretmek icin kullanilan fonksiyon
        // unique sayiler uretir
        // sifir (0) bosluk yerine gecmektedir

        for (int i = 0; i < 16; i++) {
            numbers.add(new Integer(i));
        }
        Collections.shuffle(numbers);
    } // create_number() END

    public static void print_number() {
        // ArrayListi ekrana tablo halinde basar
        int count = 0;
        for (int i = 0; i < numbers.size(); i++) {
            if (count == 4) {
                System.out.print("\n");
                count = 0;
            }
            System.out.print(numbers.get(i) + "   ");
            count++;
        }
    } // print_numbers() END

    public static boolean controller() {
        // controller fonksiyonu true dondururse
        // tablo tamamlanmistir ve oyun bitmistir
        boolean status = true;
        for (int i = 0; i < numbers.size(); i++) {
            for (int j = i + 1; j < numbers.size(); j++) {
                if (numbers.get(i) > numbers.get(j)) {
                    status = false;

                }
            }
        }
        return status;
    } // controller() : boolean END

    public static void game() {

        if (controller()) {
            // controller fonksiyonu true döndürürse oyun bitmistir.
            // Oyuna bastan baslamak mainden oluyor
            // oyuna yeniden baslarsa move_count sifirlaniyor
            System.out.println("Tebrikler " + move_count + " Hamlede Oyunu Kazandiniz.");
            System.out.println("Tekrar Oynamak İstermisiniz.( E / H )");
            String answer = in.next();
            if (answer == "H" || answer == "h" || answer == "hayır") {
                return;
            } else {
                move_count = 0;
                main();
            }
        }

        System.out.print("\nLütfen hareket ettirmek istediğiniz rakamı seçiniz: ");

        int user_num = in.nextInt();
        int index = -1; // girilen sayinin tablodaki yerinin index numarasi
        String status = "blank"; // hareket belirteci olarak kullanildi

        if (user_num > 15) {
            System.out.println("Tabloda Girdiginiz Sayi Bulunmamakta");
            game();
        }

        for (int i = 0; i < numbers.size(); i++) {
            if (user_num == numbers.get(i)) {
                index = i;
            }
        }
        // index +1 --> saga gider
        // index -1 --> sola gider
        // index +4 --> asagi gider
        // index -4 --> yukari gider
        // index < 4 yukari cikamaz
        // index > 11 assagi inemez
        // index == 0 sola gidemez
        // index == 15 saga gidemez

        if (index != 0 && numbers.get(index - 1) == 0) {
            status = "sola git";
            move_count++;
        }
        if (index != 15 && numbers.get(index + 1) == 0) {
            status = "saga git";
            move_count++;
        }
        if (index < 11 && numbers.get(index + 4) == 0) {
            status = "asagi git";
            move_count++;
        }
        if (index > 3 && numbers.get(index - 4) == 0) {
            status = "yukari git";
            move_count++;
        }

        switch (status) {
            case "saga git":
                numbers.set(index + 1, numbers.get(index));
                numbers.set(index, 0);
                print_number();
                game();
                break;
            case "sola git":
                numbers.set(index - 1, numbers.get(index));
                numbers.set(index, 0);
                print_number();
                game();
                break;
            case "asagi git":
                numbers.set(index + 4, numbers.get(index));
                numbers.set(index, 0);
                print_number();
                game();
                break;
            case "yukari git":
                numbers.set(index - 4, numbers.get(index));
                numbers.set(index, 0);
                print_number();
                game();
                break;
            default:
                System.out.println(numbers.get(index) + " Hareket Edemez");
                game();
        }

    } // game() END
}
