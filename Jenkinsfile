#!/usr/bin/groovy
node{
  checkout scm
  sh "git remote set-url origin git@github.com:fabric8io/pipeline-test-downstream.git"

  def pipeline = load 'release.groovy'

  pipeline.updateDependencies('http://central.maven.org/maven2/')

  def stagedProject = pipeline.stage()

  pipeline.deploy(stagedProject)

  pipeline.approveRelease(stagedProject)

  pipeline.release(stagedProject)
}
