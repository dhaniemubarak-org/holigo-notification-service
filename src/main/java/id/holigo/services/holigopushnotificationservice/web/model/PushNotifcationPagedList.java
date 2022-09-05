package id.holigo.services.holigopushnotificationservice.web.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class PushNotifcationPagedList extends PageImpl<PushNotificationDto> implements Serializable {

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public PushNotifcationPagedList(@JsonProperty("data") List<PushNotificationDto> content,
                                    @JsonProperty("number") int number,
                                    @JsonProperty("size") int size,
                                    @JsonProperty("totalElements") Long totalElements,
                                    @JsonProperty("pageable") JsonNode pageable){
        super(content, PageRequest.of(number, size), totalElements);
    }

    public PushNotifcationPagedList(List<PushNotificationDto> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

}