#!/usr/bin/groovy
node{
  checkout scm

  def pipeline = load 'release.groovy'

  pipeline.updateDependencies('http://central.maven.org/maven2/')

  def stagedProject = pipeline.stage()

  pipeline.release(stagedProject)
}
