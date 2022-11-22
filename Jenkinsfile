def pipeline_script

pipeline {
    agent any
	
	stages {

        stage ('Load Scripts'){
            steps{
                script{
                    pipeline_script= load "maven.groovy"
                   

                }
            }
        }
	
		
		
	  
		stage ('Slack Notification'){      
	slackSend channel: 'C04BXQLTZ2N', failOnError: true, message: 'testing', notifyCommitters: true, teamDomain: 'diplomadodevo-izc9001', tokenCredentialId: 'slack', username: 'U042FV39FMY'
		}

    }
}
