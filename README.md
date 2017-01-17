
Alfresco Patch to External Invites
==========================

Described at issue [ALF-21833](https://issues.alfresco.com/jira/browse/ALF-21833)

**Compatibility**
The current version has been developed using Alfresco 5.1 and Alfresco SDK 2.2.0

***Original Alfresco resources have been overwritten***

Using the ready-to-deploy-plugin
--------------------------------------
The binary distribution is made of one amp file to be deployed in Alfresco as a repo module at **releases** tab.

You can install them by using standard [Alfresco deployment tools](http://docs.alfresco.com/community/tasks/dev-extensions-tutorials-simple-module-install-amp.html) in `alfresco.war`

Building the artifacts
----------------------
If you are new to Alfresco and the Alfresco Maven SDK, you should start by reading [Jeff Potts' tutorial on the subject](http://ecmarchitect.com/alfresco-developer-series-tutorials/maven-sdk/tutorial/tutorial.html).

You can build the artifacts from source code using maven
```$ mvn clean package```