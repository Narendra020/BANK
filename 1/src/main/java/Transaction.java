public class Transaction {
    private String transactionDate;
    private String description;
    private double amount;

    // Constructor
    public Transaction(String transactionDate, String description, double amount) {
        this.transactionDate = transactionDate;
        this.description = description;
        this.amount = amount;
    }

    // Getters and setters (if needed)
    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
