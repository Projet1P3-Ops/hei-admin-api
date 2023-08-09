package school.hei.haapi;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.springframework.stereotype.Component;

@Component
public class LambdaHandler implements RequestStreamHandler{
    private static SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;
        static {
            try {
                handler = SpringBootLambdaContainerHandler.getAwsProxyHandler(HaApiApplication.class);
            } catch (ContainerInitializationException e) {
                e.printStackTrace();
                throw new RuntimeException("Could not initialize Spring framework", e);
            }
        }
        @Override
        public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context)
                throws IOException {
            handler.proxyStream(inputStream, outputStream, context);
        }
}
