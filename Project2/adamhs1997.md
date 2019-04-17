For this project, we continued work on https://github.com/gzdwsu/RaiderPlanner/issues/2. 
We essentially continued work on the issue to improve the existing chat system. I worked 
on this with issue again with L. Gauldin. While the goal of our first project submission
was to add a simple chat client and server, the goal this time around was to add the 
ability to have multiple clients connect to the server. Now, multiple users can chat on
the same chat server instance, allowing for a more group-oriented chat experience. I created
a base for the multiple-thread, multi-client server in the existing ServerMain.java (in
/src/edu/wright/cs/raiderserver). Lucas split the threading code out into separate 
ServerHandler and Client files. From there, we went back and forth on different 
implementations that did not have concurrency errors. Lucas was the one to find the final
implementation that allowed for multiple clients with no threading-related issues.

I made a total of 6 commits to work on this issue. These include:
*48baaae Start framework for multiple clients
*6391248 Fix IO for one client
*1cbc840 Read input from multiple clients
*fcf21b5 Let server send msg to all clients
*a2a714a Add back in separate read/write threads
*ca591a7 Fix message passing between clients
The details of these commits can be seen in the pull request at 
https://github.com/gzdwsu/RaiderPlanner/pull/40. Note that this pull request would merge 
the feature development branch back into the project master branch, rather than pulling the 
code all the way into the gzdwsu repository. This is not the complete chat client/server 
code, and more functionality will be added in the third project submission. I feel that 
each of these commits was descriptive and appropriate for each code addition. As we were 
able to implement a functional chat client with multiple clients stemming from these 
commits, I believe these were positive changes to RaiderPlanner.
