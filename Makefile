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
	rm -R ${CATALINA_BASE}/webapps/myapp/*

compile:
	./mvnw compiler:compile

deploy:
	./mvnw package war:war

redeploy:
	rm -R ${CATALINA_BASE}/webapps/myapp/*
	./mvnw package war:war

build:
	./mvnw package war:exploded 

open-chrome:
	google-chrome --incognito --new-window http://localhost:8080

open-firefox:
	firefox --incognito --new-window http://localhost:8080

browse:
	browse http://localhost:8080

code:
	code-oss $@


lint: lint-default lint-google lint-spring

build-execute: lint format-spring build open-chrome

open: open-chrome