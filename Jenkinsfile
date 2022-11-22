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
		
		success {
				slackSend color: 'good', message: "[${env.USER}][${env.JOB_NAME}] Ejecución exitosa."
			}

			failure {
				slackSend color: 'danger', message: "[${env.USER}][${env.JOB_NAME}] Ejecución fallida en stage ${STAGE}."
				error "Ejecución fallida en stage"
			}
		
		
		
		
	}
		
}
