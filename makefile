compile:
	javac -cp .:./jar/* src/spms/*.java src/application/*.java src/user/*.java
run:
	cd src;java -cp .:../jar/* spms.WelcomePage
clean:
	rm src/spms/*.class src/user/*.class src/application/*.class
