Feature: Process logs
	As a Security Analyst
	I want each line of the log to be verified against suspicious behaviour
	So that I know the IP's that represent a thread to my application

Scenario: Normal log line
	Given the following activities
		||
	When the activity "30.212.19.124,1420113600,SUCCESS,Thomas.Davenport" is processed
	Then the analyser will return null
		
Scenario: Failure for the first time
	Given the following activities
		||
	When the activity "30.212.19.125,1420113600,FAILURE,John.Doe" is processed
	Then the analyser will return null

Scenario: Fifth failure in less than 5 minutes
	Given the following activities
		|30.212.19.126,1420113600,FAILURE,So.So|
		|30.212.19.126,1420113630,FAILURE,So.So|
		|30.212.19.126,1420113660,FAILURE,So.So|
		|30.212.19.126,1420113690,FAILURE,So.So|
	When the activity "30.212.19.126,1420113720,FAILURE,So.So" is processed
	Then the analyser will return "30.212.19.126"

Scenario: Fifth failure after 5 minutes
	Given the following activities
		|30.212.19.127,1420113600,FAILURE,Michael.Jackson|
		|30.212.19.127,1420113630,FAILURE,Michael.Jackson|
		|30.212.19.127,1420113660,FAILURE,Michael.Jackson|
		|30.212.19.127,1420113720,FAILURE,Michael.Jackson|
	When the activity "30.212.19.127,1420113901,FAILURE,Michael.Jackson" is processed
	Then the analyser will return null
