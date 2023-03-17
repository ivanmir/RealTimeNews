# Getting Started

This application will issue events using the SseEmitter class available on Spring Boot.

The way it works is that it will start a subscription via a Web Browser.
When you open F12 on your browser and click on the Network tab, you will see an http request named "subscribe".

This request will leave a socket opened on the browser (for the maximum time SseEmitter allows it to) with the http type = eventsource.
If you click on the request, you will see a column named "EventStream" which will get all the server sent messages.

To send server messages you can issue a POST request to the following API end-point: "/dispatchEvent" where the body should contain the following:
* form-data:
	* where:
		* Key = 'freshNews' 
		* Value = 'Message'
		
One you post this request, you will see the message on the browser's developer screen under the "subscribe" request on the column named "EventStream".

To push it to Cloud Foundry, you have to logon to your account

```bash
### Make sure you update the route on the file manifest.yaml file to reflect the DC your CF instance is running.
> mvn clean install -DskipTests
> cf push
```

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.5.7/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.5.7/maven-plugin/reference/html/#build-image)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.6.1/reference/htmlsingle/#using-boot-devtools)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.6.1/reference/htmlsingle/#boot-features-developing-web-applications)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

