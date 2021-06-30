pipeline {
  agent any

  def remote = [:]
  remote.name = "localhost"
  remote.host = "localhost"
  remote.allowAnyHosts = true  

  stages {
    withCredentials([usernamePassword(credentialsId: 'sshDeploy', passwordVariable: 'password', usernameVariable: 'userName')]) {
        remote.user = userName
        remote.password = password
    }
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
	sshScript remote: remote, script: './demo-mockup/start.sh'
	sh 'mvn test "-Dtestcase/test=Test.Runner"'
        archiveArtifacts 'testcase/target/surefire-reports/*html'
      }
    }

    stage('Deploy') {
      steps {
        sh '''
                ssh -t deploy@localhost
                ls
                cd demo-mockup
                ./start.sh
           '''
        input 'Finished using the mockup maven app? (Click "Proceed" to continue)'
        sh '''
                ssh -t deploy@localhost
                ls
                cd demo-mockup
                ./stop.sh
           '''
        sh 'echo Thank You'
      }
    }

  }
  options {
    skipStagesAfterUnstable()
  }
}
