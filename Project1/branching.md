1. https://www.creativebloq.com/web-design/choose-right-git-branching-strategy-121518344 by "Creative Bloq Staff"
2. https://docs.microsoft.com/en-us/azure/devops/repos/git/git-branching-guidance?view=azure-devops by "Multiple Contributors"
3. https://git-scm.com/book/en/v2/Git-Branching-Branching-Workflows by "git-scm.com"

Links 1 and 2 discuss using what is called "GitHub Flow", a basic branching model where each feature is developed on its own 
branch directly from master. Link 3 is similar, but rather than creating new branches from master, you would maintain separate
master and develop branches. Master only would contain "release" code, while develop would contain stable code that has not
yet been pushed into production. In either model, the actual development is done on separate feature branches, so there is always
at least one branch with a stable, working program.

The model we used was similar to the one described in articles 1 and 2, where each feature was branched directly from master.
The only difference in our model was that we each made personal branches from the feature branch, so each person's individual 
work on the feature did not create conflicts. When one person was done with a part of the feature, his changes would be merged
to the main feature branch. When the main feature was complete, this would be merged into master.

This branching strategy was simple to use and easy to manage. However, it may not necessarily be best for larger projects since, 
in theory, any completed feature would be pushed directly into production by committing it to master. In a larger project, it
would probably be best to implement the strategy described in Link 3. This would allow us to have all stable code on develop, 
while giving more control over what goes into production by pushing batches of features into master.

