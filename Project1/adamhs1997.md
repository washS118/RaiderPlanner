The first issue I chose to work on was https://github.com/gzdwsu/RaiderPlanner/issues/1. This
issue was fairly simple to resolve, and all the problem code was located in
src/edu/wright/cs/raiderplanner/controller/MenuController.java. No new code had to be written,
only existing code had to be rearranged to fit in an existing conditional block. All changes
were made in the `openProfile()` method. The issue was simple: If a user didn't choose a file
to open, certain blocks of the method shouldn't have executed. While the original author of the
method took care of some of this, some of the code that should have been in the `if` block 
was not. I simply moved the code into this block.

Work on this issue was done on the profile-error-adam branch. Three commits were made:
*7190de1 Load file iff is not null
*bc879f8 Change planner file only if chosen file is not null
*f1e72ea Prevent reloading main menu if null file chosen
I believe each of these commits were descriptive and appropriate for each code change. (In all
honesty, this probably could have been one commit, but for the purposes of the project it 
seemed best to break up.) As the issue was resolved, the commits were useful to the project.
See pull request at https://github.com/washS118/RaiderPlanner/pull/1.

---

The next issue I worked on was https://github.com/gzdwsu/RaiderPlanner/issues/2. This
issue, being a feature addition, was more complex than the bug above. I worked on this with
L. Gauldin, but kept my commits on a separate feature branch (ServerDev-adam) than his. In 
short, the our goal to resolve this issue was to add a simple chat client/server into the
RaiderPlanner application to make the chat feature stub somewhat functional. To start,
L. Gauldin added a stub server application in src/edu/wright/cs/raiderserver/ServerMain.java.
I populated this with functional chat server code. Client code was added to 
src/edu/wright/cs/raiderplanner/controller/ChatController.java.

I made a total of 15 commits to work on this issue. These are too many to list here, but can
be seen in the pull request at https://github.com/washS118/RaiderPlanner/pull/2. Note that
this pull request was to merge my feature development branch (ServerDev-adam) back into the
main feature branch (ServerDev; see branching.md for more on our model). This is not the
complete chat client/server code, and is not all that was eventually merged into master.
I feel that each of these commits was descriptive and appropriate for each code addition.
As we were able to implement a functional chat client in RaiderPlanner by building from
these commits, I feel that this was a positive change for the project.
