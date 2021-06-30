def remote = [:]
remote.name = "localhost"
remote.host = "localhost"
remote.allowAnyHosts = true  

withCredentials([usernamePassword(credentialsId: 'sshDeploy', passwordVariable: 'password', usernameVariable: 'userName')]) {
	remote.user = userName
	remote.password = password
}

pipeline {
  agent any

  stages {
    stage('Build') {
      
      steps {
        sh 'mvn clean install'
	archiveArtifacts 'target/test-1.0-SNAPSHOT-jar-with-dependencies.jar'
      }
    }


    stage('Test') {
      post {
        always {
          emailext(subject: "Jenkins Build ${currentBuild.currentResult}: Job ${env.JOB_NAME}", body: "${currentBuild.currentResult}: Job ${env.JOB_NAME} build ${env.BUILD_NUMBER} \n More info at: ${env.BUILD_URL}", attachLog: true, to: 'sushan@moco.com.np', from: 'sushan@moco.com.np')
          junit 'testcase/target/surefire-reports/*xml'
        }

      }
      steps {
	sh 'echo "transfer jar file to deployment server"'
	sshCommand remote: remote, command: 'ls demo-mockup'
	sshPut remote: remote, from:'target/test-1.0-SNAPSHOT-jar-with-dependencies.jar', into: 'demo-mockup', override: true
	sh 'rm -rf testcase/target'
	sshCommand remote: remote, command: 'bash ./demo-mockup/start.sh'
	sh 'cd testcase'
	sh 'mvn test "-Dtest=Test.Runner"'
	sh 'cd ..'
        archiveArtifacts 'testcase/target/surefire-reports/*html'
	sshCommand remote: remote, command: 'bash ./demo-mockup/stop.sh'
      }
    }

    stage('Deploy') {
      steps {
	sshCommand remote: remote, command: 'bash ./demo-mockup/start.sh'
        input 'Finished using the mockup maven app? (Click "Proceed" to continue)'
        sh 'echo Thank You'
	sshCommand remote: remote, command: 'bash ./demo-mockup/stop.sh'
      }
    }

  }
  options {
    skipStagesAfterUnstable()
  }
}
