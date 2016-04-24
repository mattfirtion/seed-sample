/**
 * Pipeline generation for the Seed plug demo.
 *
 * The Seed plug-in will give the following parameters to this scripts, available directly as variables:
 *
 * - raw parameters (seed generator input + scm branch)
 *   - PROJECT - raw project name, like nemerosa/seed in GitHub
 *   - PROJECT_CLASS
 *   - PROJECT_SCM_TYPE
 *   - PROJECT_SCM_URL
 *   - BRANCH - basic branch name in the SCM, like branches/xxx in SVN
 *
 * - computed parameters:
 *   - SEED_PROJECT: project normalised name
 *   - SEED_BRANCH: branch normalised name
 *
 * The jobs are generated directly at the level of the branch seed job, so no folder needs to be created for the
 * branch itself.
 */

freeStyleJob("${SEED_PROJECT}-${SEED_BRANCH}-build") {
  logRotator(-1, 40)
  jdk 'JDK7'
  parameters {
    stringParam('COMMIT', 'HEAD', 'Commit to build')
  }

  scm {
    git {
      remote {
        url "https://www.github.com/mattfirtion/seed-sample.git"
        branch "origin/${BRANCH}"
      }
      wipeOutWorkspace()
      localBranch "${BRANCH}"
    }
  }

  steps {
    gradle {
      tasks('clean build')
      makeExecutable(true)
    }
  }

  publishers {
    archiveJunit("**/build/test-results/*.xml")
  }
}
