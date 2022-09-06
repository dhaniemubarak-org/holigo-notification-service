package id.holigo.services.holigopushnotificationservice.services;

import com.google.firebase.messaging.FirebaseMessagingException;
import id.holigo.services.holigopushnotificationservice.domain.PushNotification;
import id.holigo.services.holigopushnotificationservice.repositories.PushNotificationRepository;
import id.holigo.services.holigopushnotificationservice.web.mappers.PushNotificationMapper;
import id.holigo.services.holigopushnotificationservice.web.model.PushNotifcationPagedList;
import id.holigo.services.holigopushnotificationservice.web.model.PushNotificationCategoryEnum;
import id.holigo.services.holigopushnotificationservice.web.model.PushNotificationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PushNotificationServiceImpl implements PushNotificationService {

    private PushNotificationRepository pushNotificationRepository;

    private FirebaseMessagingService firebaseMessagingService;

    @Autowired
    public void setFirebaseMessagingService(FirebaseMessagingService firebaseMessagingService) {
        this.firebaseMessagingService = firebaseMessagingService;
    }

    @Autowired
    public void setPushNotificationRepository(PushNotificationRepository pushNotificationRepository) {
        this.pushNotificationRepository = pushNotificationRepository;
    }

    private PushNotificationMapper pushNotificationMapper;

    @Autowired
    public void setPushNotificationMapper(PushNotificationMapper pushNotificationMapper) {
        this.pushNotificationMapper = pushNotificationMapper;
    }


    @Override
    public PushNotifcationPagedList listPushNotification(Long userId, PushNotificationCategoryEnum category, PageRequest pageRequest) {

        PushNotifcationPagedList pushNotifcationPagedList;

        Page<PushNotification> pushNotificationPage;

        if (category != null) {
            pushNotificationPage = pushNotificationRepository.findAllByCategoryAndUserId(category, userId, pageRequest);
        } else {
            pushNotificationPage = pushNotificationRepository.findAllByUserId(userId, pageRequest);
        }


        pushNotifcationPagedList = new PushNotifcationPagedList(pushNotificationPage
                .getContent()
                .stream()
                .map(pushNotificationMapper::pushNotificationToPushNotificationDto)
                .collect(Collectors.toList()),
                PageRequest
                        .of(pushNotificationPage.getPageable().getPageNumber(), pushNotificationPage.getPageable().getPageSize())
                , pushNotificationPage.getTotalElements());

        return pushNotifcationPagedList;
    }

    @Override
    public PushNotificationDto postPushNotification(PushNotificationDto pushNotificationDto) {
        PushNotification pushNotification = pushNotificationRepository.save(pushNotificationMapper.pushNotificationDtoToPushNotification(pushNotificationDto));
        try {
            if (pushNotification.getId() != null) {
                // send to firebase
                if (pushNotification.getTopic() != null) {
                    firebaseMessagingService.sendNotificationByTopic(pushNotification);
                } else {
                    firebaseMessagingService.sendNotificationByToken(pushNotification, pushNotificationDto.getToken());
                }
                return pushNotificationMapper.pushNotificationToPushNotificationDto(pushNotification);
            }
        } catch (FirebaseMessagingException e) {
            log.error("Token not found...");
        }
        return pushNotificationMapper.pushNotificationToPushNotificationDto(pushNotification);
    }

    // Read Notification
    @Override
    public PushNotificationDto putPushNotification(UUID id) {
        PushNotification notificationRead = pushNotificationRepository.findById(id);

        notificationRead.setReadAt(Timestamp.valueOf(LocalDateTime.now()));

        return pushNotificationMapper.pushNotificationToPushNotificationDto(pushNotificationRepository.save(notificationRead));
    }


}
