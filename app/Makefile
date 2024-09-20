setup:
	@gradle wrapper --gradle-version 7.4

clean:
	@./gradlew clean

build:
	@./gradlew clean build

start:
	APP_ENV=development ./gradlew run

installDist:
	@./gradlew clean installDist

run-dist:
	@./build/install/app/bin/app

run:
	@./gradlew run

generate-migrations:
	@./gradlew generateMigrations

lint:
	@./gradlew checkstyleMain checkstyleTest

test:
	@./gradlew test

report:
	@./gradlew jacocoTestReport

check-updates:
	@./gradlew dependencyUpdates

.PHONY: build