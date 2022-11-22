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
		stage ('Ejecuta All Stages"'){
            steps{
                script{
			println "Ejecuta All Stages"
			pipeline_script.llamarPipeline()
                   

                }
            }
        }
	        
	
	

    }
