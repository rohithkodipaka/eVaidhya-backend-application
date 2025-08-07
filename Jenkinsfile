pipeline {
  agent {
    docker {
      image 'rohithk29/jenkins-agent:latest'
      args '--user root -v /var/run/docker.sock:/var/run/docker.sock'
    }
  }

  environment {
    APP_DIR = "."  // Your app is at the root of the repo
    IMAGE_NAME = "rohithkodipaka/evaidhya-backend"
    DOCKER_IMAGE = "${IMAGE_NAME}:${BUILD_NUMBER}"
    GIT_REPO_NAME = "eVaidhya-backend-application"
    GIT_USER_NAME = "rohithkodipaka"
  }

  stages {
    stage('Checkout') {
      steps {
        git branch: 'main', url: "https://github.com/${GIT_USER_NAME}/${GIT_REPO_NAME}.git"
      }
    }

    stage('Build JAR') {
      steps {
        dir("${APP_DIR}") {
          sh 'mvn clean package -DskipTests'
        }
      }
    }

    stage('Build and Push Docker Image') {
      environment {
        REGISTRY_CREDENTIALS = credentials('Dockerhub') // set this in Jenkins
      }
      steps {
        dir("${APP_DIR}") {
          script {
            sh "docker build -t ${DOCKER_IMAGE} ."
            def dockerImage = docker.image("${DOCKER_IMAGE}")
            docker.withRegistry('https://index.docker.io/v1/', 'Dockerhub') {
              dockerImage.push()
            }
          }
        }
      }
    }
  }

  post {
    success {
      echo "✅ Deployment successful!"
    }
    failure {
      echo "❌ Pipeline failed. Check logs above."
    }
  }
}
