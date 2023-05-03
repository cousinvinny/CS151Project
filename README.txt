Project was compiled with java version 1.8

Make sure the JRE System Library has build path to to the "sqlite-jdbc-3.41.2.1.jar" 
driver included in the project folder if the project does not already have the 
build path to the driver already.

If you wish to test for initial login detection:
	1. open the database InitialData.db
	2. go to default_password table
	3. go to initial_login_flag column
	4. change the value from FALSE to TRUE