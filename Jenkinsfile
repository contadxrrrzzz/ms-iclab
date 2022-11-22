import helpers.*
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
			steps{

			
	post {
			success {
				slackSend channel: 'C04BXQLTZ2N',  notifyCommitters: true, teamDomain: 'diplomadodevo-izc9001', tokenCredentialId: 'slack', username: 'U042FV39FMY', message: "[${env.USER}][${env.JOB_NAME}] Ejecución exitosa."
			}

			failure {
				slackSend channel: 'C04BXQLTZ2N', notifyCommitters: true, teamDomain: 'diplomadodevo-izc9001', tokenCredentialId: 'slack', username: 'U042FV39FMY', message: "[${env.USER}][${env.JOB_NAME}] Ejecución fallida en stage ${STAGE}."
				error "Ejecución fallida en stage ${STAGE}"
			}
		}
				}
			
			
			
		}

    }
}
