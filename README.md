# WeatherForecastApp

# Requirements
Write an API whereInput:
ZipcodeOutput:
Displays weather forecast for the given zip code

# Solution Steps
Input: zipcode

# Implementation class code traversing
WeatherController --> WeatherService --> WeatherApiDao --> DTOs
WeatherService --> WeatherServiceImpl
WeatherApiDao --> WeatherDaoImpl

# Example URL
Entry Point WeatherAppApplication class to run application then call below API
1. http://localhost:8080/api/weather/v1/forecast?zipcode=500081
2. http://localhost:8080/api/weather/v1/forecast?zipcode=560076
3. http://localhost:8080/api/weather/v1/forecast?zipcode=110053

or run WeatherControllerTest to test mocked API endpoint.

# Response
1. Success : http://localhost:8080/api/weather/v1/forecast?zipcode=500081
   ```{
   "currentTemperature": 27,
   "highTemperature": 34.5,
   "lowTemperature": 22.8,
   "temperatureUnit": "Degree Celsius",
   "indicator": "Cached Temperature",
   "timestamp": "2024-03-03T22:58:40.124609"
   }
2. Failure: http://localhost:8080/api/weather/v1/forecast?zipcode=
   ```{
   "timestamp": "2024-03-03T17:27:09.200+00:00",
   "message": "failure: Invalid zipcode.",
   "details": "uri=/api/weather/v1/forecast"
   }

# Future Implementation
1. Add Swagger documentation
2. Add Security to access API through accessToken : can be uniquely generated encoded token or jwt token
3. I have added one API test due to constraint of time. We can add api unit test and junit test case for each functionality.
3. More business rules, for example: zipcode validator
4. call application.properties from cloudconfig
5. We can enhance with SpringCloud for distributed architecture
6. Deployment required files : Docker, Kubernetes







