package price;

public class Price {

    private String symbol;
    private String bid;
    private String ask;
    private String quoteType;
    private String tenor = "S";
    private String mdRequestID;

    public Price() {
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getAsk() {
        return ask;
    }

    public void setAsk(String ask) {
        this.ask = ask;
    }

    public String getQuoteType() {
        return quoteType;
    }

    public void setQuoteType(String quoteType) {
        this.quoteType = quoteType;
    }

    public String getMdRequestID() {
        return symbol+"S";
    }

    public void setMdRequestID(String mdRequestID) {
        this.mdRequestID = mdRequestID;
    }

    public String getTenor() {
        return tenor;
    }

    public void setTenor(String tenor) {
        this.tenor = tenor;
    }
}
