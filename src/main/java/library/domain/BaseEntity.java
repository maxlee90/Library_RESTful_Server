package library.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import library.domain.event.BaseEvent;

public abstract class BaseEntity {
  private Collection<BaseEvent> events = new ArrayList<>();

  public void addEvent(BaseEvent event) {
    events.add(event);
  }

  public Collection<BaseEvent> getEvents() {
    return Collections.unmodifiableCollection(events);
  }
}
