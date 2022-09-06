package id.holigo.services.holigopushnotificationservice.services;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import id.holigo.services.holigopushnotificationservice.domain.PushNotification;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class FirebaseMessagingService {
    private final FirebaseMessaging firebaseMessaging;

    public FirebaseMessagingService(FirebaseMessaging firebaseMessaging) {
        this.firebaseMessaging = firebaseMessaging;
    }

    public void sendNotificationByTopic(PushNotification pushNotification) throws FirebaseMessagingException {
        Notification notification = Notification
                .builder()
                .setTitle(pushNotification.getTitle())
                .setBody(pushNotification.getDescription())
                .setImage(pushNotification.getImageUrl())
                .build();


        Message message = Message
                .builder()
                .setTopic(pushNotification.getTopic())
                .setNotification(notification)
//                .putAllData(new HashMap<>().put("",""))
                .build();
        firebaseMessaging.send(message);
    }

    public void sendNotificationByToken(PushNotification pushNotification, String token) throws FirebaseMessagingException {
        Notification notification = Notification
                .builder()
                .setTitle(pushNotification.getTitle())
                .setBody(pushNotification.getDescription())
                .setImage(pushNotification.getImageUrl())
                .build();


        Message message = Message
                .builder()
                .setToken(token)
                .setNotification(notification)
//                .putAllData(new HashMap<>().put("",""))
                .build();
        firebaseMessaging.send(message);

    }
}
