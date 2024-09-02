## Service
This directory holds up the services handing with the database and error handling for Posts.


### Available Methods
- [CommonPostService](CommonPostService.java) 
  - For Retrieval of all posts.
    - Read: returns Array of Posts.
- [PostService](PostService.java)
  - For Specific Posts created by the Profile.
    - Read: return Array of Post(s) from Profile ID.
    - Update: return updated Post from Profile ID.
    - Delete: return Boolean value after deletion from the Profile ID.
- [ProfilePostService](ProfilePostService.java)
  - Create: Create a post by Profile ID.
  - Read: Retrieve all Posts by Profile ID.

`profile_id` = The Unique ID used for Profile ID.  
`post_id` = The Unique ID used for Post ID.