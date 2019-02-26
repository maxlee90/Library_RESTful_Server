package library.service;

import java.util.Collection;
import java.util.EventObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

public abstract class BaseService {
  @Autowired private ApplicationEventPublisher eventPublisher;

  protected void publishEvent(EventObject event) {
    eventPublisher.publishEvent(event);
  }

  protected void publishEvents(Collection<? extends EventObject> events) {
    events.forEach(eventPublisher::publishEvent);
  }
}
