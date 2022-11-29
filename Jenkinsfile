import groovy.json.JsonOutput	
def pipeline_script
def stg = ""

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
	
		stage('Version') { 
            steps {
                script{
                    stg = "Version"
                }
                echo "${stg}"
                aumentarVersion()
            }
            
        }

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
				slackSend channel: 'C04BXQLTZ2N',
					teamDomain: 'diplomadodevo-izc9001', 
					tokenCredentialId: 'slack', 
					username: 'U042FV39FMY',
					color: COLOR_MAP [currentBuild.currentResult],
					message: "*${currentBuild.currentResult}:* JOB[${env.JOB_NAME}] Ejecución exitosa en stage."
			}

			failure {
				slackSend channel: 'C04BXQLTZ2N',
				teamDomain: 'diplomadodevo-izc9001', 
				tokenCredentialId: 'slack', 
				username: 'U042FV39FMY',
				color: COLOR_MAP [currentBuild.currentResult],	
					message: "*${currentBuild.currentResult}:* JOB[${env.JOB_NAME}] Ejecución fallida en stage."
				error "Ejecución fallida en stage"
			}
		
		
		
		
	}
		
}


def custom_msg()
{
    def AUTHOR = obtenerAutor()
    def JOB_NAME = env.JOB_NAME
    def BUILD_ID= env.BUILD_ID
    def version = extraeTag()
    def MSG= "[GRUPO-4 - ${AUTHOR}] [BRANCH: ${JOB_NAME}] [VERSION: ${version}]"
    return MSG
}

def extraeTag()
{   
    sh "git pull"
    sh "ls ${env.WORKSPACE}/.git/refs/tags/ > /var/jenkins_home/trabajo/tag.txt"
    def tag = sh(script: "cat /var/jenkins_home/trabajo/tag.txt", returnStdout: true).toString().trim()
    largo = tag.length()
    def resultado = tag.substring(largo-5, largo)
    return resultado
}
def tagAntiguo()
{   
    sh "git pull"
    sh "ls ${env.WORKSPACE}/.git/refs/tags/ > /var/jenkins_home/trabajo/tag.txt"
    def tag = sh(script: "cat /var/jenkins_home/trabajo/tag.txt", returnStdout: true).toString().trim()
    largo = tag.length()
    def resultado = tag.substring(largo-11, largo-6)
    return resultado
}
def obtenerAutor()
{   
    sh "git pull"
    def autor = sh(script: "git log -p -1 | grep Author", returnStdout: true).toString().trim()
    echo "${autor}"
    largo = autor.length()
    def resultado = autor.substring(8, largo)
    return resultado
}

def aumentarVersion()
{
    def tg = extraeTag()
    def branch = env.BRANCH_NAME
    def vActual = tagAntiguo()
    vActual = "${vActual}"
    def vNuevo = "${tg}"
    sh "/var/jenkins_home/trabajo/cambioTag.sh ${vActual} ${vNuevo} ${env.WORKSPACE}"
    script{
        if("${branch}" == 'develop'){
            echo "Entro a if develop"
        } else if("${branch}" == 'main'){
            echo "Entro a if main."
        } else if("${branch}" == 'feature*' || "${branch}" == 'release*' ){
            echo "Entro a if."
        }
    }
    return vNuevo
}



