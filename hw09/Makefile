#JUNIT_JAR = /usr/share/java/junit4.jar
JUNIT_JAR := $(shell find ~/ic312/junit/ -type f -name junit*)
HAMCREST := $(shell find ~/ic312/junit/ -type f -name hamcrest*)

compile:
	javac -Xlint:all -cp .:$(JUNIT_JAR):$(HAMCREST) *.java

test: compile
	java -ea -cp .:$(JUNIT_JAR):$(HAMCREST) org.junit.runner.JUnitCore GraphTest

%.pdf: %.dot
	neato $< -Tpdf >$@

clean:
	rm -f *.class $(patsubst %.dot,%.pdf,$(wildcard *.dot))

.PHONY: compile test clean
