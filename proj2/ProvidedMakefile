JUNIT_JAR = /usr/share/java/junit4.jar

compile:
	javac -Xlint:all -cp .:$(JUNIT_JAR) *.java

test1: compile
	java -ea -cp .:$(JUNIT_JAR) org.junit.runner.JUnitCore TreeMapSanityTest

test3: compile
	java -ea -cp .:$(JUNIT_JAR) org.junit.runner.JUnitCore TopKSanityTest

clean:
	rm -f *.class

.PHONY: compile test clean
