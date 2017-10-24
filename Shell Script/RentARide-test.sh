#!/bin/sh

# IMPORTANT:  this test is not repeatable as not all of the data is deleted

# run the write test
#
echo "==========================================================================="
echo "== Writing sample data"
echo
java -cp classes:/opt/classes/mysql-connector-java-5.1.37-bin.jar:/opt/classes/freemarker.jar:/opt/classes/servlet-api.jar edu.uga.cs.rentaride.test.object.WriteTest

# run the read test
#
echo "==========================================================================="
echo "== Reading the initial sample data"
echo
java -cp classes:/opt/classes/mysql-connector-java-5.1.37-bin.jar:/opt/classes/freemarker.jar:/opt/classes/servlet-api.jar edu.uga.cs.rentaride.test.object.ReadTest

# run the update test
#
echo "==========================================================================="
echo "== Updating the initial sample data"
echo
java -cp classes:/opt/classes/mysql-connector-java-5.1.37-bin.jar:/opt/classes/freemarker.jar:/opt/classes/servlet-api.jar edu.uga.cs.rentaride.test.object.UpdateTest

# run the read test
#
echo "==========================================================================="
echo "== Reading the updated sample data"
echo
java -cp classes:/opt/classes/mysql-connector-java-5.1.37-bin.jar:/opt/classes/freemarker.jar:/opt/classes/servlet-api.jar edu.uga.cs.rentaride.test.object.ReadTest

# run the delete test
#
echo "==========================================================================="
echo "== Deleting some of the sample data"
echo
java -cp classes:/opt/classes/mysql-connector-java-5.1.37-bin.jar:/opt/classes/freemarker.jar:/opt/classes/servlet-api.jar edu.uga.cs.rentaride.test.object.DeleteTest

# run the read test
#
echo "==========================================================================="
echo "== Reading the sample data after delete"
echo
java -cp classes:/opt/classes/mysql-connector-java-5.1.37-bin.jar:/opt/classes/freemarker.jar:/opt/classes/servlet-api.jar edu.uga.cs.rentaride.test.object.ReadTest