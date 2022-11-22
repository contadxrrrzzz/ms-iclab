import groovy.json.JsonOutput	
def pipeline_script

//Se define el color de SLACK
def COLOR_MAP = [
	'SUCCESS' : 'good',
	'FAILURE' : 'danger'
]

//Se obtienen los datos del user
def getBuildUser(){
return currentBuild.rawBuild.getCause(Cause.UserIdCause).getUserId()
}

pipeline {
    agent any
	
	//Se declara en Enviroment el usuario
	environment{
	BUILD_USER = ''
	}
	
	stages {

        stage ('Load Scripts'){
            steps{
                script{
                    pipeline_script= load "maven.groovy"
                   

                }
            }
        }
	
		
}
	post {
		always {
			script {
			BUILD_USER=getBuildUser()
			
			}
			slackSend channel: 'C04BXQLTZ2N',
			color: COLOR_MAP[currentBuild.currentResult],
			teamDomain: 'diplomadodevo-izc9001',
			tokenCredentialId: 'slack',
			username: 'U042FV39FMY',
				message: "*${currentBuild.currentResult}:* Job ${env.JOB_NAME} build ${env.BUILD_NUMBER} por ${BUILD_USER}"
		
		
			}
	
		}
}
