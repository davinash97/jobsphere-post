## JobSphere-Post-Service
This Repository holds post-service/controller.
Part of Microservice Architecture used for [Jobsphere](https://github.com/jobsphere).

### Endpoints

#### /post

|  Endpoint  |  Method  |                            Description                             |
|:----------:|:--------:|:------------------------------------------------------------------:|
|   /post    |   GET    | A **GET** request to this endpoint will return all Posts available |

#### {profile_id}/post : profile_id = A unique ID of profile

|     Endpoint      | Method | Description                                                                                 |
|:-----------------:|:------:|:--------------------------------------------------------------------------------------------|
| {profile_id}/post |  POST  | A **POST** request with Profile ID to this endpoint will create new Post.                   |
| {profile_id/post  |  GET   | A **GET** request with Profile ID to this endpoint will return all Post(s) from Profile ID. |


#### {profile_id}/post/{post_id} : profile_id = A unique ID of post, : post_id = A unique ID of post

|          Endpoint           | Method | Description                                                                                          |
|:---------------------------:|:------:|:-----------------------------------------------------------------------------------------------------|
| {profile_id}/post/{post_id} |  PUT   | A **PUT** request with Profile ID and Post ID will update the specific post from specific profile    |
| {profile_id}/post/{post_id} |  GET   | A **GET** request with Profile ID and Post ID will retrieve the specific post from specific profile  |
| {profile_id}/post/{post_id} | DELETE | A **DELETE** request with Profile ID and Post ID will delete the specific post from specific profile |
