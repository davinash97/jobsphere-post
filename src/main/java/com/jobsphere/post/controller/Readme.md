## Controller
This directory holds up the endpoints for post handling

### Available endpoints
- [CommonPostController](CommonPostController.java):
  - `/post`: 
    - GET: For retrieval of all posts. (Array of Posts)
- [PostController](PostController.java):
  - `/{profile_id}/post/{post_id}`: For specific posts created by the profile.
    - GET: Retrieve the specific Post.
    - PUT: Update the specific Post. 
    - DELETE: Delete the Specific Post.
- [ProfilePostController](ProfilePostController.java): 
  - `/{profile_id}/post`: For specific posts created by the profile.
    - POST Create New Post for Profile.
    - GET: Retrieve All Posts from Profile.

#### Note
`profile_id` = The Unique ID of Profile ID.  
`post_id` = The Unique ID of Post ID.