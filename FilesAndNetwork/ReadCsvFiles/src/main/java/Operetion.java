public class Operetion {
    String accountType, currency, date, reference, description, accountNumber;
    double expense, coming;

    public Operetion(String accountType, String accountNumber, String currency, String date, String reference, String description, double coming, double expense) {
        this.accountType = accountType;
        this.accountNumber = accountNumber;
        this.currency = currency;
        this.date = date;
        this.reference = reference;
        this.description = description;
        this.coming = coming;
        this.expense = expense;
    }
}
