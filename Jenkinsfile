#!/usr/bin/groovy
node{
  checkout scm
  sh "git remote set-url origin git@github.com:fabric8io/pipeline-test-downstream.git"

  def pipeline = load 'release.groovy'

  pipeline.updateDependencies('http://central.maven.org/maven2/')

  def stagedProject = pipeline.stage()
echo "1 ${stagedProject}"
  pipeline.deploy(stagedProject)
echo "2 ${stagedProject}"
  pipeline.approve(stagedProject)
echo "3 ${stagedProject}"
  pipeline.release(stagedProject)
echo "4 ${stagedProject}"
}
