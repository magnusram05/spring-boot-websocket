package price;

public class Request {

    private String symbol;

    public Request() {
    }

    public Request(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
