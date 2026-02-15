#!/bin/bash
set -e

echo "Building JAR..."

./gradlew clean build -x test

echo "Successfully built JAR..."
echo "Building image..."

docker build -t finance-tracker-backend .

echo "Restarting backend..."

docker-compose up -d --force-recreate backend

echo "Done! Successfully recreate backend"
echo "Validating..."
MAX_WAIT=60
ELAPSED=0
while [ $ELAPSED -lt $MAX_WAIT ]; do
	if docker-compose logs backend |grep "Started FinanceTrackerApplicationKt"; then
		echo "Backend started successfully!"
		echo "Testing API..."
		curl http://localhost:8080/v1/transactions
		exit 0
	fi
	echo " Still waiting... (${ELAPSED}s)"
	sleep 2
	ELAPSED=$((ELAPSED+2))
done

echo "Timeout while waiting for backend to start"
exit 1
