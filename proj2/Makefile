CMD := $(firstword $(MAKECMDGOALS))
JUNIT := $(shell find ~/ic312/junit/ -type f -name junit*)
JUNITCORE := org.junit.runner.JUnitCore
HAMCREST := $(shell find ~/ic312/junit/ -type f -name hamcrest*)

compile:
	javac *.java

test1: 
	javac -cp .:$(JUNIT):$(HAMCREST) TreeMapSanityTest.java
	java -cp	.:$(JUNIT):$(HAMCREST) $(JUNITCORE) TreeMapSanityTest

test2: 
	 javac Pharmacies.java
	 time java Pharmacies arcos-annapolis.tsv | diff - pharmacies-annapolis.txt
	 time java Pharmacies arcos-dmv.tsv | head | diff - pharmacies-dmv.txt

test3: 
	javac -cp .:$(JUNIT):$(HAMCREST) TopKSanityTest.java
	java -cp	.:$(JUNIT):$(HAMCREST) $(JUNITCORE) TopKSanityTest

test4:
	javac Zips.java
	time java Zips arcos-annapolis.tsv mdzips.tsv 10 | diff - test4.txt
	time java Zips arcos-dmv.tsv uszips.tsv 50 | diff - zips-dmv-50.txt
#$(CMD):
	#javac -cp .:$(JUNIT):$(HAMCREST) $@.java
	#java -cp	.:$(JUNIT):$(HAMCREST) $(JUNITCORE) $(CMD) 
