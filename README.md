# Test task for EPOL_SOFT

I hope you'll enjoy my solution and I won't make any inconvenience by building a microservice architecture and using the Gateway API enterprise pattern.

### Let's start...

## Check services remotely:
  * An angular client: https://epol-client.xfarm.xyz/
  
  * SOAP service: https://epol-soap.xfarm.xyz/ws/attributes.wsdl
  
  * Gateway API service: https://epol-gatewayapi.xfarm.xyz/attributes

## Expected Result:

1) **SQL scripts**(_In order to simplify the project, I used Spring script initializer instead of Liquibase framework for example_): 
        https://github.com/VladislavBedritsky/epolsoft_test/tree/master/soap-service/src/main/resources

2) **JAXB classes and xsd scheme**:
        https://github.com/VladislavBedritsky/epolsoft_test/tree/master/common-libs/src/main/resources/xsd       
               
3) **SOAP service**: 
        https://github.com/VladislavBedritsky/epolsoft_test/tree/master/soap-service 
        
4) **An angular client**:
        https://github.com/VladislavBedritsky/epolsoft_test/tree/master/angular-client
        
5) **Gateway API service**(_Implementation of the GATEWAY API pattern. In our case, it acts as a consumer of the SOAP service and as a data producer by REST protocol to clients. My decision is based on easy project extensibility and for team development also if necessary._):
        https://github.com/VladislavBedritsky/epolsoft_test/tree/master/gateway-api
        
## Steps to run services locally:
    
1) _Please, make sure that active profile switched to_ **dev**:
    https://github.com/VladislavBedritsky/epolsoft_test/blob/master/gateway-api/src/main/resources/application.yml
2) `git clone git@github.com:VladislavBedritsky/epolsoft_test.git`
3) Project build: 
    `mvn clean install`
4) Gateway Api start:
    * `cd gateway-api`
    * `mvn spring-boot:run`
    * http://localhost:8088/ws/attributes.wsdl
5) SOAP service start:
    * `cd soap-service`
    * `mvn spring-boot:run`
    * http://localhost:8077/attributes
6) An angular client start:
    * `cd angular-client`
    * `npm install`
    * `ng serve`     
    * http://localhost:4200
    
 ### OR you can type 1 command to run all services locally:
 * `docker-compose up -d`       
 
 ### Thanks for your time! Have a nice day)
         
