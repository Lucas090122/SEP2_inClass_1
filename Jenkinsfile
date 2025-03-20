pipeline {
    agent any
     environment {
            MAVEN_HOME = "C:\\program files\\maven"
            PATH = "${env.PATH};${env.MAVEN_HOME}\\bin"
            // Define Docker Hub credentials ID
            DOCKERHUB_CREDENTIALS_ID = 'Docker_Hub'
            // Define Docker Hub repository name
            DOCKERHUB_REPO = 'atz7689/sep2_inclass_1'
            // Define Docker image tag
            DOCKER_IMAGE_TAG = 'latest_v2'
        }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Lucas090122/SEP2_inClass_1.git'
            }
        }
        stage('Build') {
            steps {
                bat 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }
        stage('Code Coverage') {
            steps {
                bat 'mvn jacoco:report'
            }
        }
        stage('Publish Test Results') {
            steps {
                junit '**/target/surefire-reports/*.xml'
            }
        }
        stage('Publish Coverage Report') {
            steps {
                jacoco()
            }
        }

         stage('Build Docker Image') {
                    steps {
                        // Build Docker image
                        script {
                            bat "docker build -t ${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG} ."
                        }
                    }
                }
                stage('Push Docker Image to Docker Hub') {
                    steps {
                        script {
                            withCredentials([usernamePassword(credentialsId: 'Docker_Hub', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                                bat "echo %DOCKER_PASS% | docker login -u %DOCKER_USER% --password-stdin"
                                bat "docker push ${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}"
                            }
                        }
                    }
                }
    }
}
