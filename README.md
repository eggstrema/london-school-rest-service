London School REST Service
==========================

This is an example application which provides a REST interface for a very simple social network.
It is used to demonstrate the influence of horizontal slicing (implemented in London School TDD) on coupling and cohesion.

This Java application is structured like those that we typically find in corporate environments.
It uses
* Java 17
* Javalin
* Mockito and
* AssertJ.

Alternative Universe
--------------------

There is hopefully gonna be a second repository demonstrating the
same application implemented in vertical slices and Chicago School TDD.

Requirements
------------
1. Login:
   1. Users need to authenticate before they can take any actions.
2. Manage Personal Data:
   1. Users can enter,
   2. update, and
   3. delete their own city and zip code.
3. Consensual Connections:
   1. Users can request a connection to another user.
   2. The other user can accept or refuse the connection.
4. List Connections:
   1. Users can see a list of their connections
   2. grouped by city
   3. sorted by zip code.

