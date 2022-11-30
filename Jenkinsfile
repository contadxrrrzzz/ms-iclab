def pipeline_script
def stg = ""



pipeline {
	
    agent any
	
	
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
		 stage('uploadNexus') { 
            steps {
                
                script{
                    def tag = extraeTag()
                    stg = "uploadNexus"

                    echo 'Uploading Nexus'
                  
	             nexusPublisher nexusInstanceId: 'nsx01', nexusRepositoryId: 'lab4-e4-local', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: '', filePath: "/var/jenkins_home/workspace/lab4-e4-local_master/build/DevOpsUsach2020-${tag}.jar"]], mavenCoordinate: [artifactId: 'DevOpsUsach2020', groupId: 'com.devopsusach2020', packaging: 'jar', version: "${tag}"]]]
                    
                
                }
                echo "${stg}"
            }
            
        }
        stage ('Testing Artifact'){
            steps
                {
                    script{
                        stg = "Testing Artifact"
                    }
                    echo 'Testing Artifact'
                    sh 'curl -X GET -u admin:admin http://nexus:8081/repository/lab4-e4-local/com.devopsusachs2020.DevOpsUsach2020.0.0.1.jar -O'
                    echo "${stg}"
                }
                
        }
           
    
	
		
}
	post {
		
		success {
				slackSend channel: 'C04BXQLTZ2N',
					teamDomain: 'diplomadodevo-izc9001', 
					tokenCredentialId: 'slack', 
					username: 'U042FV39FMY',
					message: "${custom_msg()} [STAGE: ${stg}][RESULTADO: EXITO]"
			}

			failure {
				slackSend channel: 'C04BXQLTZ2N',
				teamDomain: 'diplomadodevo-izc9001', 
				tokenCredentialId: 'slack', 
				username: 'U042FV39FMY',
				message: "${custom_msg()} [STAGE: ${stg}][RESULTADO: ERROR]"
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
    sh "ls ${env.WORKSPACE}/.git/refs/tags/ > ${env.WORKSPACE}/trabajo/tag.txt"
    def tag = sh(script: "cat ${env.WORKSPACE}/trabajo/tag.txt", returnStdout: true).toString().trim()
	echo tag
    largo = tag.length()
    def resultado = tag.substring(largo-5, largo)
    return resultado
}
def tagAntiguo()
{   
    def resultado
    sh "git pull"
    sh "ls ${env.WORKSPACE}/.git/refs/tags/ > ${env.WORKSPACE}/trabajo/tag.txt"
    def tag = sh(script: "cat ${env.WORKSPACE}/trabajo/tag.txt", returnStdout: true).toString().trim()
    largo = tag.length()
	echo tag
    script{
        if(largo >= 6){
            resultado = tag.substring(largo-11, largo-6)
        }
        if(largo == 5){
            resultado = tag.substring(largo-5, largo)
        }

    }
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
    sh "${env.WORKSPACE}/trabajo/cambioTag.sh ${vActual} ${vNuevo} ${env.WORKSPACE}"

    return vNuevo
}


