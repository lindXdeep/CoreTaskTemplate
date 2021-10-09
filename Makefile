.DEFAULT_GOAL := build-execute

setup:
	mvn -N io.takari:maven:wrapper -Dmaven=3.6.3

lint-default:
	./mvnw checkstyle:checkstyle

lint-google:
	./mvnw checkstyle:check -Dcheckstyle.config.location=./checkstyle/google_checks.xml

lint-spring:
	./mvnw checkstyle:check -Dcheckstyle.config.location=./checkstyle/spring-checkstyle.xml

format-spring:
	./mvnw spring-javaformat:apply

clean:
	./mvnw clean

compile:
	./mvnw compiler:compile

build:
	./mvnw package

rebuild: clean build

run:
	./mvnw spring-boot:run

open-chrome:
	google-chrome --incognito --new-window http://localhost:8080

open-firefox:
	firefox --incognito --new-window http://localhost:8080

open: open-chrome

lint: lint-default lint-google lint-spring

run_browse:
	./open-browser.sh & make run

build-execute: lint rebuild run_browse

code:
	code-oss $@
