def call(Map params){
    sh """
        ls
        git config --global user.name "${params.githubUserName}"
        git config --global user.email "${params.githubEmail}"
        git add Manifest_Files/deployment.yaml
        git commit -m "Updated Deployment Manifest with ${params.imageTag} version"
    """
    withCredentials([usernamePassword(credentialsId: "${params.githubCredID}", passwordVariable: 'pass', usernameVariable: 'user')]){
        sh "git push https://$user:$pass@${params.githubURL} main"
    }
}