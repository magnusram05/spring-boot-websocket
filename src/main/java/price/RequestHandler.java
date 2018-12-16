package price;

import java.util.Map;

public interface RequestHandler<I, O> {
    O handleRequest(I r);
    Map<String, O> getCache();
}
