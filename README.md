# Rent_A_Ride_Part6

CLASSESDIR=./lib
javac -d classes -cp ${CLASSESDIR}/mysql-connector-java-5.1.33-bin.jar:${CLASSESDIR}/freemarker.jar:${CLASSESDIR}/jboss-servlet-api_3.1_spec-1.0.0.Final.jar src/edu/uga/cs/rentaride/RARException.java src/edu/uga/cs/rentaride/entity/*.java src/edu/uga/cs/rentaride/entity/impl/*.java src/edu/uga/cs/rentaride/object/*.java src/edu/uga/cs/rentaride/object/impl/*.java src/edu/uga/cs/rentaride/persistence/*.java src/edu/uga/cs/rentaride/persistence/impl/*.java src/edu/uga/cs/rentaride/test/*.java

#java -cp classes:/opt/classes/mysql-connector-java-5.1.33.jar:/opt/classes/freemarker.jar:/opt/classes/servlet-api.jar edu.uga.cs.rentaride.test.RentARideTester

java -cp classes:${CLASSESDIR}/mysql-connector-java-5.1.33-bin.jar edu.uga.cs.rentaride.test.RentARideTester
