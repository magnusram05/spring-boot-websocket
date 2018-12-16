package price;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PriceHandler implements RequestHandler<Request, Price> {
    private Map<String, Price> cache = new ConcurrentHashMap<>();

    public Price handleRequest(Request request){
        String symbol = request.getSymbol();
        Price price = cache.put(symbol,PriceUtil.getPrice(symbol));
        return price;
    }

    public Map<String, Price> getCache(){
        return Collections.unmodifiableMap(cache);
    }
}
