pipeline {
  agent {
    docker {
      image 'rohithk29/jenkins-agent:latest'
      args '--user root -v /var/run/docker.sock:/var/run/docker.sock'
    }
  }

  environment {
    APP_DIR = "."  // Project root
    IMAGE_NAME = "rohithk29/evaidya"
    DOCKER_IMAGE = "${IMAGE_NAME}:latest"
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
        REGISTRY_CREDENTIALS = credentials('Dockerhub') // Set this ID in Jenkins Credentials
      }
      steps {
        dir("${APP_DIR}") {
          script {
            // Ensure Docker is working
            sh 'docker info'

            // Build the Docker image
            sh "docker build -t ${DOCKER_IMAGE} ."

            // Login using credentials and push manually (avoids Jenkins docker context issues)
            withCredentials([usernamePassword(credentialsId: 'Dockerhub', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
              sh 'echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin'
              sh "docker push ${DOCKER_IMAGE}"
              sh 'docker logout'
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
