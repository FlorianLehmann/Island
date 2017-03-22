package fr.unice.polytech.si3.qgl.iaac.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import fr.unice.polytech.si3.qgl.iaac.compass.EnumOrientation;
import fr.unice.polytech.si3.qgl.iaac.contracts.Contract;

import java.io.IOException;
import java.util.List;

/**
 * Created by florian on 22/03/2017.
 */
public class ReadJSON2 {

    /**
     * attributes
     */
    private String json;
    private ObjectMapper mapper;
    private Answer answer;

    /**
     * default constructor
     */
    public ReadJSON2() {
        mapper = new ObjectMapper();
    }

    /**
     *
     * @param json
     * @return
     * @throws IOException
     */
    public Answer read(String json) throws IOException {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
        answer = mapper.readValue(json, Answer.class);
        return answer;
    }

    /**
     *
     * @return the answer
     */
    public Answer getAnswer() {
        return answer;
    }
}
