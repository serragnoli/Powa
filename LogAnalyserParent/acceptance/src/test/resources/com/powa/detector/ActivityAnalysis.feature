Feature: Process logs
	As a Security Analyst
	I each line of the log to be verified against suspicious behaviour
	So that I know the IP's that represent a thread to my application

Scenario: Normal log line
	Given the following activities
		|30.212.19.124,1420113600,SUCCESS,Thomas.Davenport|
	When the line is processed
	Then the analyser will return ""
		
Scenario: Failure for the first time
	Given the following activities
		|30.212.19.125,1420113600,FAILURE,John.Doe|
	When the line is processed
	Then the analyser will return ""

Scenario: Fifth failure in less than 5 minutes
	Given the following activities
		|30.212.19.126,1420113600,FAILURE,So.So|
		|30.212.19.126,1420113630,FAILURE,So.So|
		|30.212.19.126,1420113660,FAILURE,So.So|
		|30.212.19.126,1420113690,FAILURE,So.So|
		|30.212.19.126,1420113720,FAILURE,So.So|
	When the line is processed
	Then the analyser will return "30.212.19.126"

Scenario: Fifth failure after 5 minutes
	Given the following activities
		|30.212.19.127,1420113600,FAILURE,Michael.Jackson|
		|30.212.19.127,1420113630,FAILURE,Michael.Jackson|
		|30.212.19.127,1420113660,FAILURE,Michael.Jackson|
		|30.212.19.127,1420113901,FAILURE,Michael.Jackson|
	When the line is processed
	Then the analyser will return ""