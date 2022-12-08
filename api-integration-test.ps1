$page = 'http://localhost:8080'

"Add user with id=1:"
curl --header 'Content-Type: application/json' `
     --request POST `
     --data '{"""id""":1,"""firstName""":"""John""","""lastName""":"""Do""","""phoneNumber""":"""123456789""","""email""":"""johndo@foo.com"""}' `
     $page'/api/person/new'

"`n"
"Add user with id=2:"
curl --header 'Content-Type: application/json' `
     --request POST `
     --data '{"""id""":2,"""firstName""":"""Janne""","""lastName""":"""Do""","""phoneNumber""":"""123456789""","""email""":"""jannedo@foo.com"""}' `
     "$page/api/person/new"

"`n"
"Fetch all users:"
curl --header 'Content-Type: application/json' `
     --request GET "$page/api/persons"
