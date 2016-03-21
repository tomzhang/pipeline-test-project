#!/usr/bin/groovy
node{
  checkout scm
  sh "git remote set-url origin git@github.com:fabric8io/pipeline-test-project.git"

  def pipeline = load 'release.groovy'

  stage 'Updating dependencies'
  def prId = pipeline.updateDependencies('http://central.maven.org/maven2/')

  stage 'Stage'
  def stagedProject = pipeline.stage()

  stage 'Deploy'
  pipeline.deploy(stagedProject)

  stage 'Approve'
  pipeline.approveRelease(stagedProject)

  stage 'Promote'
  pipeline.release(stagedProject)
  pipeline.mergePullRequest(prId)
}
