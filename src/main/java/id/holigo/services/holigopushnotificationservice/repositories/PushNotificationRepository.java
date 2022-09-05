package id.holigo.services.holigopushnotificationservice.repositories;

import id.holigo.services.holigopushnotificationservice.domain.PushNotification;
import id.holigo.services.holigopushnotificationservice.web.model.PushNotificationCategoryEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface PushNotificationRepository extends JpaRepository<PushNotification, Long> {

    Page<PushNotification> findAllByCategoryAndUserId(PushNotificationCategoryEnum pushNotificationCategoryEnum,Long userId,Pageable pageable );

    Page<PushNotification> findAllByUserId(Long userId, Pageable pageable);

    PushNotification findById(UUID id);

//    List<PushNotification> findAll();

}
