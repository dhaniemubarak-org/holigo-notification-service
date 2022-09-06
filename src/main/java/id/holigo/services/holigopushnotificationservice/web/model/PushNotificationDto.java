package id.holigo.services.holigopushnotificationservice.web.model;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class PushNotificationDto {

    private UUID id;

    private String icon;

    @NotNull
    private Long userId;

    private PushNotificationCategoryEnum category;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private Timestamp readAt;

    private String title;

    private String description;
    
    private String data;

    private String token;

    private String topic;

    private String imageUrl;


}
