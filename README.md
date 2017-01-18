
Alfresco Patch to External Invites
==========================

Described at issue [ALF-21833](https://issues.alfresco.com/jira/browse/ALF-21833)

**Compatibility**
The current version has been developed using Alfresco 5.1 and Alfresco SDK 2.2.0

***Original Alfresco resources have been overwritten***

This issue was introduced by commit [Merged HEAD-BUG-FIX (5.1/Cloud) to HEAD (5.1/Cloud)](https://github.com/Alfresco/community-edition/commit/3651c8526e3a8b154bee8fbac41c569efa46a174), which resolves incompletely [MNT-12597: Share: Accepting site invitation via email does not show on the 'Completed Task' task](https://issues.alfresco.com/jira/browse/MNT-12597) for Alfresco 4.2

This patch does not address this problem, so the task will not be shown in the invited user Completed Task list. Nevertheless, the invited user will be able to accept the invitation.

Using the ready-to-deploy-plugin
--------------------------------------
The binary distribution is made of one amp file to be deployed in Alfresco as a repo module at **releases** tab.

You can install them by using standard [Alfresco deployment tools](http://docs.alfresco.com/community/tasks/dev-extensions-tutorials-simple-module-install-amp.html) in `alfresco.war`

Building the artifacts
----------------------
If you are new to Alfresco and the Alfresco Maven SDK, you should start by reading [Jeff Potts' tutorial on the subject](http://ecmarchitect.com/alfresco-developer-series-tutorials/maven-sdk/tutorial/tutorial.html).

You can build the artifacts from source code using maven
```$ mvn clean package```
