public class Main {
    public static void main(String[] args) {
        Container container = new Container();
        container.addCount(5672);
        System.out.println(container.getCount());

        // TODO: ниже напишите код для выполнения задания:
        //  С помощью цикла и преобразования чисел в символы найдите все коды
        //  букв русского алфавита — заглавных и строчных, в том числе буквы Ё.

        char symbolCode;
        int defoultQantiti = 1040;

        for (symbolCode = 1040; symbolCode <= 1103; symbolCode++) {
            if (symbolCode != 1104) {
                System.out.println(defoultQantiti + " - " + symbolCode);
            }
            if (defoultQantiti == 1045) {
                System.out.println("1025 - Ё");
            }
            if (defoultQantiti == 1077) {
                System.out.println("1105 - ё");
            }
            defoultQantiti ++;
        }
    }
}
