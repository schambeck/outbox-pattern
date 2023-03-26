package com.schambeck.design.outbox.outbox;

import com.schambeck.design.outbox.outbox.usecase.PublishPendingOrders;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static java.util.concurrent.TimeUnit.SECONDS;

@Component
@RequiredArgsConstructor
public class OutboxTask {
	private final PublishPendingOrders publishPendingOrders;
	@Scheduled(fixedRate = 5, timeUnit = SECONDS)
	public void publishPendingMessages() {
		publishPendingOrders.execute();
	}
}