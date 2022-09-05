package id.holigo.services.holigopushnotificationservice.web.mappers;

import id.holigo.services.holigopushnotificationservice.domain.PushNotification;
import id.holigo.services.holigopushnotificationservice.web.model.PushNotificationDto;
import org.mapstruct.Mapper;
@Mapper
public interface PushNotificationMapper {
    PushNotificationDto pushNotificationToPushNotificationDto(PushNotification pushNotification);

    PushNotification pushNotificationDtoToPushnotification(PushNotificationDto pushNotificationDto);
}
