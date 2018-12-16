package price;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Controller
public class PriceController {

    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    @Autowired
    private SimpMessagingTemplate template;

    public RequestHandler<Request, Price> priceRequestHandler;

    @MessageMapping("/priceRequest")
    public void greeting(Request message) throws Exception {
        System.out.println(message);
        Price price = priceRequestHandler.handleRequest(message);
        template.convertAndSend(PriceUtil.getTopic(price.getSymbol()), price);
    }

    @PostConstruct
    public void randomize(){
        executor.submit(()->{
            while (true){
                Thread.sleep(1000);
                Set<Map.Entry<String, Price>> cacheEntrySet = priceRequestHandler.getCache().entrySet();
                for(Map.Entry<String, Price> entry : cacheEntrySet) {
                    Price price = entry.getValue();
                    PriceUtil.updatePrice(price);
                    template.convertAndSend(PriceUtil.getTopic(entry.getKey()), price);
                }
            }
        });
    }

    @Autowired
    public void setPriceRequestHandler(RequestHandler<Request, Price> priceRequestHandler) {
        this.priceRequestHandler = priceRequestHandler;
    }
}
