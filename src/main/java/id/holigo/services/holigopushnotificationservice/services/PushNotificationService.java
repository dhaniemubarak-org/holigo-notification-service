package id.holigo.services.holigopushnotificationservice.services;

import com.google.firebase.messaging.FirebaseMessagingException;
import id.holigo.services.holigopushnotificationservice.domain.PushNotification;
import id.holigo.services.holigopushnotificationservice.web.model.PushNotifcationPagedList;
import id.holigo.services.holigopushnotificationservice.web.model.PushNotificationCategoryEnum;
import id.holigo.services.holigopushnotificationservice.web.model.PushNotificationDto;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.UUID;

public interface PushNotificationService {

    PushNotifcationPagedList listPushNotification(Long userId,PushNotificationCategoryEnum pushNotificationCategoryEnum, PageRequest pageRequest);

    PushNotificationDto postPushNotification(PushNotificationDto pushNotificationDto) throws FirebaseMessagingException;

    PushNotificationDto putPushNotification(UUID id);

}
