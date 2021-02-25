.DEFAULT_GOAL := compile-deploy

webapps=${CATALINA_BASE}/webapps/myapp

reformat:
	mvn spring-javaformat:apply

lint:
	mvn checkstyle:check

compile:
	mvn clean compile

deploy:
	rm -r $(webapps)/* & mvn war:war

build:
	mvn clean package

run:
	mvn validate

compile-deploy: compile deploy