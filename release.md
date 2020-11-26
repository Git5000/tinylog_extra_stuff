Releasing a New Version
=======================

Preconditions
-------------

 * Repository cloned and actual
 * JDK 8 and JDK 9 installed
 * Maven 3 installed
 
Steps for Each Release
----------------------

 1. Set new version: mvn versions:set -DnewVersion=VERSION -DgenerateBackupPoms=false
 2. Create a tag with new version number as name
 3. Deploy with JDK 9: mvn clean deploy -P release
 4. Upload P2 repository: mvn p2:site wagon:upload -P release --non-recursive
 5. Upload ZIP archives from target to website
 6. Generate Javadoc for tinylog API with JDK 8: mvn javadoc:javadoc
 7. Upload generated Javadoc for tinylog API to website
 8. Restore snapshot version: mvn versions:set -DnewVersion=2.MINOR-SNAPSHOT -DgenerateBackupPoms=false
 9. Push all commits and tags
10. Create release on GitHub with ZIP archives from target
11. Update tinylog version on website
12. Release a new post on website
