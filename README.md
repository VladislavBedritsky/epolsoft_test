# Test task for EPOL_SOFT

I hope you'll enjoy   my solution and I won't make any inconvenience by building a microservice architecture and using the Gateway API enterprise pattern.

### Let's started...

## Check services remotely:
  * An angular client: https://epol-client.xfarm.xyz/
  
  * SOAP service: https://epol-soap.xfarm.xyz/ws/attributes.wsdl
  
  * Gateway API service: https://epol-gatewayapi.xfarm.xyz/attributes

## Expected Result:

1) **SQL scripts**(_In order to simplify the project, I used Spring script initializer instead of Liquibase framework for example_): 
        https://github.com/VladislavBedritsky/epolsoft_test/tree/master/soap-service/src/main/resources
               
2) **SOAP service**: 
        https://github.com/VladislavBedritsky/epolsoft_test/tree/master/soap-service 
        
3) **An angular client**:
        https://github.com/VladislavBedritsky/epolsoft_test/tree/master/angular-client
        
4) **Gateway API service**(_Implementation of the GATEWAY API pattern. In our case, it acts as a consumer of the SOAP service and as a data producer by REST protocol to clients. My decision is based on easy project extensibility and also team development if necessary._):
        https://github.com/VladislavBedritsky/epolsoft_test/tree/master/gateway-api
        
## Steps to run services locally:
    
1) Make sure that active profile switched to **DEV**:
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
         