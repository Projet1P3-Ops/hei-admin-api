FROM public.ecr.aws/lambda/java:11
ARG version
ARG JAR_FILE=build/libs/hei-admin-api-0.7.0.jar
#ENV LAMBDA_HANDLER school.hei.haapi.LambdaHandler
#{"$LAMBDA_HANDLER"}
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080