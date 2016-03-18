#!/usr/bin/groovy
def updateDependencies(source){

  def properties = []
  properties << ['<pipeline.test.project.dependency.version>','io/fabric8/pipeline-test-project-dependency']
  properties << ['<docker.maven.plugin.version>','io/fabric8/docker-maven-plugin']

  return updatePropertyVersion{
    updates = properties
    repository = source
    project = 'fabric8io/pipeline-test-project'
  }
}

def stage(){
  return stageProject{
    project = 'fabric8io/pipeline-test-project'
    useGitTagForNextVersion = true
  }
}

def deploy(project){
  deployProject{
    stagedProject = project
    resourceLocation = 'target/classes/kubernetes.json'
    environment = 'fabric8'
  }
}

def approveRelease(project){
  def releaseVersion = project[1]
  approve{
    room = null
    version = releaseVersion
    console = null
    environment = 'fabric8'
  }
}

def release(project){
  releaseProject{
    stagedProject = project
    useGitTagForNextVersion = true
    helmPush = false
    groupId = 'io.fabric8'
    githubOrganisation = 'fabric8io'
    artifactIdToWatchInCentral = 'pipeline-test-project'
    artifactExtensionToWatchInCentral = 'jar'
    promoteToDockerRegistry = 'docker.io'
    dockerOrganisation = 'fabric8'
    imagesToPromoteToDockerHub = ['pipeline-test-project']
    extraImagesToTag = null
  }
}

def mergePullRequest(prId){
  mergeAndWaitForPullRequest{
    project = 'fabric8io/pipeline-test-project'
    pullRequestId = prId
  }
}
return this;
