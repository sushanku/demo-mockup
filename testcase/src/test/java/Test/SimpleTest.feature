Feature: Demo test

Background:  

   Given url 'http://localhost:4567' 
   
  
   Scenario: Send Get request by invalid(Numeric) as User Name
   Given path '/role/12345'
   When method Get
   Then match response == '"It is a number"'
   And match header Content-Type contains 'text/plain'
   
   
   Scenario: Send Get request by valid(Manish Sir) as User Name
   Given path '/role/Manish Sir'
   When method Get
   Then match response == '"hello Manish Sir"'
   And match header Content-Type contains 'text/plain'
   
   Scenario: Send Get request by valid(Parbati) as User Name
   Given path '/role/Parbati ji'
   When method Get
   Then match response == '"hello Parbati ji"'
   And match header Content-Type contains 'text/plain'
   
   Scenario: Send Get request by valid(Sanjeev) as User Name
   Given path '/role/Sanjeev'
   When method Get
   Then match response == '"hello Sanjeev"'
   And match header Content-Type contains 'text/plain'
      
   
   Scenario: Send Get request by valid(Sushan) as User Name
   Given path '/role/Sushan Dai'
   When method Get
   Then match response == '"hello Sushan Dai"'
   And match header Content-Type contains 'text/plain'
   
