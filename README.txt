Project was compiled with java version 1.8

Make sure the JRE System Library has build path to to the "sqlite-jdbc-3.41.2.1.jar" 
driver included in the project folder if the project does not already have the 
build path to the driver already.

If you wish to test starting at initial login detection:
	1. open the database InitialData.db
	2. go to default_password table
	3. go to initial_login_flag column
	4. change the value from FALSE to TRUE
	5. also delete any password column data in the current_password table

If you enter the wrong password, the console will print out the correct password
from the database, but this is for testing purposes. It is a huge security risk
in a final product.