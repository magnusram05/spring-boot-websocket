package price;

import org.apache.commons.lang3.StringUtils;

import java.util.Random;

public class PriceUtil {

    private static Random random = new Random();

    static {
        random.doubles(1.2568, 1.2598);
    }

    public static String getTopic(String symbol){
        return StringUtils.prependIfMissing(symbol,"/topic/");
    }

    public static Price getPrice(String symbol){
        Price price = new Price();
        price.setQuoteType("TRADEABLE");
        updatePrice(price);
        price.setSymbol(symbol);
        return price;
    }

    public static void updatePrice(Price price){
        double ask = random.nextDouble()+1;
        double bid = ask - 0.0050;
        price.setAsk(String.format("%1$,.4f",ask));
        price.setBid(String.format("%1$,.4f",bid));
    }
}
