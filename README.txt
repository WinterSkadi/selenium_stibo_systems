------------------------------------------
Exercise - selenium_stibo_systems-1.0
------------------------------------------

	Pre-requisites
	--------------
		1. System Windows 7 s.p.1 x64 (English).
		2. Screen Resolution 1920x1080.
		3. Installed Chrome 66.0.3359.170 x64.
		4. Installed Java JDK 9.0.4 x64 with Java JRE 9.0.4 x64.
		5. Installed Maven 3.3.9.
		6. Installed IntelliJ 2017.3.5 (Community Edition).
		7. Used Page Object Model.


		The versions of the software provided above are the ones that were used during test development. It is possible that tests will run on newer version of the software too.


	How to run
	----------
	    1. In "src\test\java\resources\config.properties" file:
    			- depending on your Internet bandwidth/PC configuration you can adjust:
    			    a) ExplicitWait timeouts setting the "explicitTimeoutInSeconds" property,
    			    b) FluentWait timeouts setting "fluentWaitPollingFrequencyInMiliseconds", "fluentWaitTimeoutInMiliseconds" properties,
    			    c) FluentWait timeout for PopUp Window setting "fluentWaitPopUpPollingFrequencyInMiliseconds", "fluentWaitPopUpTimeoutInMiliseconds" properties.

		2. The tests can be run either through internal IntelliJ JUnit Runner or via Maven.