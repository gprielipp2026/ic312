CMD := $(firstword $(MAKECMDGOALS))
JUNIT := $(shell find ~/ic312/junit/ -type f -name junit*)
JUNITCORE := org.junit.runner.JUnitCore
HAMCREST := $(shell find ~/ic312/junit/ -type f -name hamcrest*)

$(CMD):
	@javac -cp .:$(JUNIT):$(HAMCREST) $@.java
	@java -cp	.:$(JUNIT):$(HAMCREST) $(JUNITCORE) $(CMD) 
