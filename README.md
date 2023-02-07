# Spring for GraphQL Example application

This is a simple Spring for GraphQL example application. You can follow several development steps by looking into
the Git commit history.

## Run the app

You can run the application using Gradle or your favourite IDE:

```bash

./gradlew :bootRun

```

Or import the project into your IDE and run the main class `nh.example.graphqlblog.SpringGraphqlJavaMagazinApplication`

The server runs on `http://localhost:9800`

## Useful URLs:

- GraphiQL: http://localhost:9800/graphiql
- Login: http://localhost:9800/login
- Logout: - Login: http://localhost:9800/logout

## Example queries

You can run queries from GraphiQL:

**Read a single blog post**
```graphql
query {
  post(postId:1){
    id
    comments {
      id
      comment
    }
  }
}
```

**Read multiple blog posts**
```graphql
query {
    posts(sortBy:date, sortDirection: ASC, page: 1, pageSize:3){
        pageNo
        pageSize

        hasPrev
        hasNext

        posts {
            id
            title
            date
            summary(maxWords: 5)
        }
    }
}

```

**Add a new post**

Note that you have to login first (http://localhost:9800/login). You can login using the username `susi` with password `susi`.

```graphql
mutation {
    addPost(input:{
        title:"Hello Spring for GraphQL",
        body: "An introduction to GraphQL applications built with Spring for GraphQL"
    }) {
        ...on  AddPostError {
            fieldName errorMsg
        }

        ...on AddPostSuccess {
            newPost {
                id
                title
                body
            }
        }
    }
}
```

# Contact

If you have comments or question, feel free to [contact me](https://nilshartmann.net). I also provide [consulting and trainings around GraphQL](https://nilshartmann.net/graphql) development (and more).

