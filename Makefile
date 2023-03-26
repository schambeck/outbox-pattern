BASE_URL = http://localhost:8080
CREATE_ENDPOINT = ${BASE_URL}/orders
ORDER_ID = 5017dc5b-bd54-45f8-9afa-588b875f50e6
CLOSE_ENDPOINT = ${BASE_URL}/orders/${ORDER_ID}/close

# Maven

dist:
	./mvnw clean package

run:
	java -jar target/outbox-pattern-1.0.0.jar

# Docker

compose-up:
	docker-compose up -d
compose-down:
	docker-compose down

# Endpoints

order-create:
	http POST ${CREATE_ENDPOINT} \
		clientId=796f5390-6a32-4f3f-a4f9-219cea1d5336 \
		issuedDate=2023-02-03 \
		totalCost:=2000 \
		items[0][productId]=7fba7340-d24f-4548-a327-add2cd2ad4a9 \
		items[0][quantity]:=2 \
		items[0][price]=3

order-close:
	http POST ${CLOSE_ENDPOINT}
