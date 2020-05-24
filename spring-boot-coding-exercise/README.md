## The Exercises

Example curl api calls for these exercises are listed in the following gist https://gist.github.com/bartonhammond/0a19da4c24c0f644ae38

**1. Find the hottest repositories created in the last week**

Use the [GitHub API][1] to expose an endpoint in this microservice the will get a list of the
highest starred repositories in the last week.

The endpoint should accept a parameter that sets the number of repositories to return.

The following fields should be returned:

      html_url
      watchers_count
      language
      description
      name
 Serice url:
 
 GET: http://localhost:8080/microservices/repositories/2
 
 Sample response:
 
[
    {
        "language": "Assembly",
        "description": "Tetris that fits into the boot sector.",
        "name": "tetros",
        "html_url": "https://github.com/daniel-e/tetros",
        "watchers_count": 690
    },
    {
        "language": "Assembly",
        "description": "Computer implementation as described in \"The Elements of Computing Systems\"",
        "name": "Nand2Tetris",
        "html_url": "https://github.com/havivha/Nand2Tetris",
        "watchers_count": 208
    }
]

**2. Find the oldest user accounts with zero followers**

Use the [GitHub API][1] to expose an endpoint in this microservice that will find the oldest
accounts with zero followers.

The endpoint should accept a parameter that sets the number of accounts to return.

The following fields should be returned:

      id
      login
      html_url

 Service url:
 
 GET: http://localhost:8080/microservices/accounts/2
 
 Sample response:
 
 [
    {
        "id": 33093268,
        "login": null,
        "html_url": "https://github.com/seungjulee/nand2tetris"
    },
    {
        "id": 18544372,
        "login": null,
        "html_url": "https://github.com/pharazin/nand2tetris_projects"
    }
  ]
