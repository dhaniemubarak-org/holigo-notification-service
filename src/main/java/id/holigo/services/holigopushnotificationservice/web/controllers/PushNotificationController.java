package id.holigo.services.holigopushnotificationservice.web.controllers;


import com.google.firebase.messaging.FirebaseMessagingException;
import id.holigo.services.holigopushnotificationservice.services.PushNotificationService;
import id.holigo.services.holigopushnotificationservice.web.model.PushNotifcationPagedList;
import id.holigo.services.holigopushnotificationservice.web.model.PushNotificationCategoryEnum;
import id.holigo.services.holigopushnotificationservice.web.model.PushNotificationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/pushNotifications")
public class PushNotificationController {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 25;

    private final PushNotificationService pushNotificationService;


    @GetMapping(produces = "application/json")
    public ResponseEntity<PushNotifcationPagedList> ListPushNotification(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                                                         @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                                         @RequestParam(value = "category",required = false) PushNotificationCategoryEnum category,
                                                                         @RequestHeader(value = "user-id") Long userId
                                                                         ){

        if (pageNumber == null || pageNumber < 0){
            pageNumber = DEFAULT_PAGE_NUMBER;
        }

        if (pageSize == null || pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        PushNotifcationPagedList pushNotifcationPagedList = pushNotificationService.listPushNotification(userId,category, PageRequest.of(pageNumber, pageSize));

        return new ResponseEntity<>(pushNotifcationPagedList, HttpStatus.OK);
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<PushNotificationDto> PostPushNotification(@RequestBody PushNotificationDto pushNotificationDto) throws FirebaseMessagingException {
        pushNotificationService.postPushNotification(pushNotificationDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PushNotificationDto> readPushNotification(@PathVariable UUID id){
        pushNotificationService.putPushNotification(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
