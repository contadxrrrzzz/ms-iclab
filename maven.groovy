def llamarPipeline()
{

stage('Compile') {
      sh './mvnw clean compile -e'
     }
}
     stage('Test') {
      sh './mvnw clean test -e'
    }
    stage('Jar') {
      sh './mvnw clean package -e'
    }
  stage('Run') {
    sh 'nohup bash mvnw spring-boot:run &'
  sleep 20
   }
stage('TestApp') {

 sh "curl -X GET 'http://localhost:8081/rest/mscovid/test?msg=testing'"
}

  
   
  stage ('Sonarqube Analisis') {
    withSonarQubeEnv('Sonar'){
		sh 'mvn clean package sonar:sonar'
    }
  }
    stage('SonarQualityGate') {
    timeout(time: 1, unit: 'HOURS') {
                    waitForQualityGate abortPipeline: true
    }
    }
    
stage ('UploadNexus'){
	nexusPublisher nexusInstanceId: 'nsx01', nexusRepositoryId: 'lab4-m4', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: '', filePath: '/var/jenkins_home/workspace/lab4-m4_master/build/DevOpsUsach2020-0.0.1.jar']], mavenCoordinate: [artifactId: 'DevOpsUsach2020', groupId: 'com.devopsusach2020', packaging: 'jar', version: '0.0.1']]]
}
	




return this



