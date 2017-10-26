javac -d classes -cp mysql-connector-java-5.1.33-bin.jar src/edu/uga/cs/rentaride/RARException.java src/edu/uga/cs/rentaride/entity/*.java src/edu/uga/cs/rentaride/entity/impl/*.java src/edu/uga/cs/rentaride/object/*.java src/edu/uga/cs/rentaride/object/impl/*.java src/edu/uga/cs/rentaride/persistence/*.java src/edu/uga/cs/rentaride/persistence/impl/*.java src/edu/uga/cs/rentaride/test/*.java


java -cp classes:mysql-connector-java-5.1.33-bin.jar edu.uga.cs.rentaride.test.RentARideTester