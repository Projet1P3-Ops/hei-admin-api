FROM public.ecr.aws/lambda/java:11
ARG version
ARG JAR_FILE=build/libs/hei-admin-api-0.7.0.jar
ENV LAMBDA_HANDLER school.hei.haapi.LambdaHandler
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar", "$LAMBDA_HANDLER"]
EXPOSE 8080