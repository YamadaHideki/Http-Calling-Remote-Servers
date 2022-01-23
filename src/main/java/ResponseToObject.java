import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

public class ResponseToObject {

    public <T> List<T> JsonToObjectsList(String json, Class<T> jsonToObjectClass) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        JavaType type = mapper.getTypeFactory()
                .constructCollectionType(List.class, jsonToObjectClass);
        return mapper.readValue(json, type);
    }

}
