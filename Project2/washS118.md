For this project, Adam h.s. and I worked on expanding the chatroom to support
multiple clients. During development we used a single branch because we knew
we would only work on the project at seperate times. We made commits when done
with our work for the day or when finishing an iteration of the feature.

Adam built most of the threading code and core logic of the feature while I
Seperated the server logic into multiple parts to assist with maintainability.
I also worked on fixing the multiple concurency bugs found.

Commits:
	491eee - Fixed Stuff - Here i made some quick changes to remove concurency bugs
	a2a714 - Remove Debug Statments - This was just cleanup
	e58155 - Finished Multiclient Communication - This is where I finished the feature apart from the bugs
	d1489f - Refactor Server - This is where I broke apart Adam's code into multiple files.
