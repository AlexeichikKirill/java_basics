public class Printer {
    String queue = "";
    int zero = 0;
    int pagesCount = 0;

    public void append(String text) { append(text,"Текст"); }

    public void append(String text, String name) { append(text, name, zero); }

    public void append(String text, String name, int count) {
        queue = queue + "\n" + text + " - " + name + " - " + count + " стр.";
        pagesCount = pagesCount + count;
    }

    public void clear() {
        queue = "";
    }

    public void print() {
        append("", "", zero);
        System.out.println(queue);
        clear();
    }

    public int getPendingPagesCount() {
        return pagesCount;
    }

    public int totalAmountForAllTime() {
        append("", "", zero);
        return pagesCount;
    }
}
